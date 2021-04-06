package br.udesc.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.udesc.modelo.Classificacoes;
import br.udesc.modelo.IModelo;
import br.udesc.modelo.ServicoUsado;

public class ClassificacoesDao extends BaseDao {

	public ClassificacoesDao() {
		super("classificacoes", new String[] { "cd_filme", "cd_usuario" });
	}

	@Override
	public void addWhere(PreparedStatement stmt, IModelo entity) throws SQLException {
		int qtdCampos = this.getCamposClasse().size();
		stmt.setInt(qtdCampos + 1, ((Classificacoes) entity).getCdFilme());
		stmt.setInt(qtdCampos + 2, ((Classificacoes) entity).getCdUsuario());
	}

	public Classificacoes insertClassificacao(Classificacoes classificacao) throws Exception {
		classificacao.setDtMomento(new Timestamp(System.currentTimeMillis()));
		try {
			PreparedStatement stmt = connection.prepareStatement(this.getQueryInsert());
			this.bindValue(stmt, classificacao, false);
			stmt.execute();

			ServicoUsadoDao bcDao = (ServicoUsadoDao) this.newDaoInstance("ServicoUsadoDao");

			ServicoUsado backlog = new ServicoUsado(classificacao.getCdUsuario(), 1,
					new Timestamp(System.currentTimeMillis()));

			bcDao.add(backlog);

			return classificacao;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao criar classificacao");
		}
	}

	public Classificacoes updateClassificacao(Classificacoes classificacao) throws Exception {
		classificacao.setDtMomento(new Timestamp(System.currentTimeMillis()));
		try {
			this.update(classificacao);

			ServicoUsadoDao bcDao = (ServicoUsadoDao) this.newDaoInstance("ServicoUsadoDao");

			ServicoUsado backlog = new ServicoUsado(classificacao.getCdUsuario(), 2,
					new Timestamp(System.currentTimeMillis()));

			bcDao.add(backlog);

			return classificacao;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao criar classificacao");
		}
	}

		public boolean remove(int idFilme, int idUsuario) throws Exception {
			boolean result = false;
			String query = "DELETE " + " FROM " + this.table + " WHERE " + this.getFiltro();
//			try {
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, idFilme);
				statement.setInt(2, idUsuario);
				int resultQuery = statement.executeUpdate();
				if (resultQuery > 0) {
					ServicoUsadoDao bcDao = (ServicoUsadoDao) this.newDaoInstance("ServicoUsadoDao");
	
					ServicoUsado backlog = new ServicoUsado(idUsuario, 3, new Timestamp(System.currentTimeMillis()));
	
					bcDao.add(backlog);
	
					result = true;
				} else {
					throw new Exception("Filme não encontrado");
				}
	
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new Exception("Filme não encontrado");
//			}
			return result;
		}

	public List<Classificacoes> getAllByFilmes(int filmeId) {
		List<Classificacoes> registros = new ArrayList<>();
		try {
			PreparedStatement statement = connection
					.prepareStatement("select * from " + this.table + " where cd_filme =?");
			statement.setInt(1, filmeId);
			ResultSet rs = statement.executeQuery();

			List<Field> camposRegistro = this.getCamposClasse();
			while (rs.next()) {
				Classificacoes registro = new Classificacoes();
				this.loadModel(rs, registro);
				registros.add(registro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return registros;
	}
}
