package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import parse.ParseDAO;
import parse.ParseException;
import modelo.beans.Partido;

public class PartidoDAO implements ParseDAO<Partido>{
	
	public enum Comparacao implements Comparator<Partido> {
		NOME {
			@Override
			public int compare(Partido p1, Partido p2) {
				return p1.getSigla().compareToIgnoreCase(p2.getSigla());
			}
		};
	}
	
	private static final String SIGLA_PARTIDO = "sigla";
	private static final String NUMERO_PARTIDO = "numero";
	private static final String NOME = "nome";
	
	private Connection conexao;
	private PreparedStatement instrucaoSQL;
	
	public PartidoDAO() {
		
	}
	
	@Override
	public void cadastrarListaParse(ArrayList<Partido> lista) throws ParseException {
		try {
			cadastrarPartidos(lista);
		} catch(Exception e) {
			throw new ParseException(e.getMessage());
		}
	}
	
	@Override
	public ArrayList<Partido> getListaParse() throws ParseException {
		ArrayList<Partido> lista = new ArrayList<>();
		try {
			lista = new ArrayList<>(getListaPartidos());
		} catch(Exception e) {
			throw new ParseException(e.getMessage());
		}
		return lista;
	}
	
	public void cadastrarPartidos(ArrayList<Partido> listaPartidos) throws SQLException {
		try {
			ArrayList<Partido> listaPartidosNaoCadastrados = new ArrayList<>();
			ArrayList<Partido> listaPartidosAtualizaveis = new ArrayList<>();
			LinkedList<Partido> listaPartidosCadastrados = getListaPartidos("sigla", "%");
			Collections.sort(listaPartidosCadastrados, Comparacao.NOME);
			for(Partido partido : listaPartidos) {
				if(Collections.binarySearch(listaPartidosCadastrados, partido, Comparacao.NOME) < 0) {
					listaPartidosNaoCadastrados.add(partido);
				} else {
					listaPartidosAtualizaveis.add(partido);
				}
			}
			
			this.conexao = new ConexaoBancoDados().getConexao();
			
			String comandoSQL = "INSERT INTO t_partido (sigla, numero)"
			          + "VALUES(?,?)";
			
			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);			
			
			this.conexao.setAutoCommit(false);
			
			for(Partido partido : listaPartidosNaoCadastrados) {
				instrucaoSQL.setString(1, partido.getSigla());
				instrucaoSQL.setString(2, partido.getNumeroPartido());
				instrucaoSQL.addBatch();
			}
			
			instrucaoSQL.executeBatch();
			
			comandoSQL = "UPDATE t_partido SET numero=? WHERE sigla=?";
			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);
			for(Partido partido : listaPartidosAtualizaveis) {
				for(Partido partidoCadastrado : listaPartidosCadastrados) {
					if(partido.getSigla().equals(partidoCadastrado.getSigla())) {
						if(partidoCadastrado.getNumeroPartido().equals(Partido.STRING_VAZIO)) {
							instrucaoSQL.setString(1, partido.getNumeroPartido());
							instrucaoSQL.setString(2, partido.getSigla());
							instrucaoSQL.addBatch();
						}
					}
				}
			}
			
			instrucaoSQL.executeBatch();
			
			this.conexao.commit();
			
		} catch(Exception e) {
			throw new SQLException("Partido - " + e.getMessage());
		} finally {
			fecharConexao();
		}		
	}
	
	public LinkedList<Partido> getTodosPartidos() throws SQLException
	{
		return getListaPartidos("sigla","%");		
	}
	
		
	public LinkedList<Partido> getListaPartidos(String tipo, String valor) throws SQLException {
		LinkedList<Partido> listaPartidos = new LinkedList<>();
		try {
			this.conexao = new ConexaoBancoDados().getConexao();
			
			String comandoSQL = "SELECT * FROM t_partido WHERE "+tipo+" LIKE '%"+valor+"%'";
			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);
			
			ResultSet resultadoSQL = (ResultSet) this.instrucaoSQL.executeQuery();
			while(resultadoSQL.next()) {
				Partido partido = new Partido();
				partido.setSigla(resultadoSQL.getString(SIGLA_PARTIDO));
				partido.setNumeroPartido(resultadoSQL.getString(NUMERO_PARTIDO));
				partido.setNome(resultadoSQL.getString(NOME));				
				listaPartidos.add(partido);
			}
			
		} catch(Exception e) {
			throw new SQLException(e.getMessage());
		} finally {
			fecharConexao();
		}
		
		return listaPartidos;
	}
		
	private void fecharConexao() throws SQLException {
		if(this.instrucaoSQL != null) {
			this.instrucaoSQL.close();
		}
		if(this.conexao != null) {
			this.conexao.close();
		}
	}
	
}
