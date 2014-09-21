package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.beans.Campaign;
import modelo.beans.Candidate;
import modelo.beans.Position;
import modelo.beans.Party;
import modelo.beans.Resultado;

public class CampanhaDAO extends BasicoDAO<Campaign> {
	
	/*
	 * Class for manipulating the data about election campaigns
	 */

	//Attributes
	private CandidatoDAO candidatoDAO;
	private PartidoDAO partidoDAO;
	private CargoDAO cargoDAO;
	private ResultadoDAO resultadoDAO;

	// Constants
	private static final String NOME_TABELA = "campanha";
	private final String ID = "id_campanha";
	private final String ANO = "ano";
	private final String NUM_CANDIDATO = "numero_candidato";
	private final String COD_RESULTADO = "resultado_cod_resultado";
	private final String COD_CARGO = "cargo_cod_cargo";
	private final String NUMERO_PARTIDO = "partido_numero";
	private final String TITULO_CANDIDATO = "candidato_titulo_eleitoral";
	private final String NOME_URNA = "nome_de_urna";
	private final String UF = "uf";
	private final String DESPESA_MAX_DECLARADA = "despesa_maxima_declarada";
	private final String DESPESA_MAX_CALCULADA = "despesa_maxima_calculada";
	private final String RECEITA_MAX_CALCULADA = "receita_maxima_calculada";

	private final String SQL_SELECT = "SELECT * FROM " + NOME_TABELA;
	private final String SQL_INSERT = "INSERT INTO " + NOME_TABELA + " (" + ID
			+ ", " + ANO + ", " + NUM_CANDIDATO + ", " + COD_RESULTADO + ", "
			+ COD_CARGO + ", " + NUMERO_PARTIDO + ", " + TITULO_CANDIDATO
			+ ", " + NOME_URNA + ", " + UF + ", " + DESPESA_MAX_DECLARADA
			+ ", " + DESPESA_MAX_CALCULADA + ", " + RECEITA_MAX_CALCULADA
			+ ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final String INDEX_CANDIDATO = "campanha_fk_3";
	private final String INDEX_PARTIDO = "campanha_fk_4";
	private final String INDEX_ANO = "campanha_sk_1";

	// Constructors
	public CampanhaDAO() {
		super(NOME_TABELA, null);
	}

	// Other methods
	/*
	 * This method retrieves the SQL command to insert data
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSqlInsert() {
		return SQL_INSERT;
	}

	/*
	 * This method retrieves the string that has the SQL command for selecting data in a database table
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSqlSelect() {
		return SQL_SELECT;
	}

	/*
	 * This method returns an SQL command for selecting a candidate by the name of urn
	 * @param a String with the name of urn
	 * @return a String with the SQL command
	 */
	public String getSqlSelectNomeUrna(String nome) {
		return "SELECT " + TITULO_CANDIDATO + " FROM " + NOME_TABELA
				+ " WHERE " + NOME_URNA + " LIKE '%" + nome + "%'";
	}

	/*
	 * This method prepares a list of campaigns to be registered
	 * @param an ArrayList<Campaign>
	 * @param a SQLinstruction
	 */
	@Override
	protected void adicionarListaNoBatch(ArrayList<Campaign> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Campaign campaign : lista) {
			instrucaoSQL.setInt(1, campaign.getId());
			instrucaoSQL.setInt(2, campaign.getAno());
			instrucaoSQL.setInt(3, campaign.getNumeroCandidato());
			instrucaoSQL.setInt(4, campaign.getResultado().getCodigo());
			instrucaoSQL.setInt(5, campaign.getCargo().getCodigo());
			instrucaoSQL.setInt(6, campaign.getPartido().getNumero());
			instrucaoSQL.setString(7, campaign.getCandidato()
					.getTituloEleitoral());
			instrucaoSQL.setString(8, campaign.getNomeDeUrna());
			instrucaoSQL.setString(9, campaign.getUf());
			instrucaoSQL.setFloat(10, campaign.getDespesaMaxDeclarada());
			instrucaoSQL.setFloat(11, campaign.getDespesaTotalCalculada());
			instrucaoSQL.setFloat(12, campaign.getReceitaTotalCalculada());
			instrucaoSQL.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Campaigns>
	 * @param an ArrayList<Campaign>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Campaign> lista, ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Position position = new Position();
			Resultado resultado = new Resultado();
			Party party = new Party();
			Candidate candidate = new Candidate();
			PreparaCamposCargoEResultado(position, resultado, resultadoSQL);
			PreparaCamposCandidatoEPartido(party, candidate, resultadoSQL);

			Campaign campaign = new Campaign();
			campaign.setId(resultadoSQL.getInt(ID));
			campaign.setResultado(resultado);
			campaign.setCargo(position);
			campaign.setPartido(party);
			campaign.setCandidato(candidate);
			campaign.setAno(resultadoSQL.getInt(ANO));
			campaign.setNumeroCandidato(resultadoSQL.getInt(NUM_CANDIDATO));
			campaign.setNomeDeUrna(resultadoSQL.getString(NOME_URNA));
			campaign.setUf(resultadoSQL.getString(UF));
			campaign.setDespesaMaxDeclarada(resultadoSQL.getFloat(DESPESA_MAX_DECLARADA));
			campaign.setDespesaTotalCalculada(resultadoSQL.getFloat(DESPESA_MAX_CALCULADA));
			campaign.setReceitaTotalCalculada(resultadoSQL.getFloat(RECEITA_MAX_CALCULADA));
			lista.add(campaign);
		}
	}

	/*
	 * This method prepares attributes Position and Results
	 * @param an instance of Class Position
	 * @param an instance of Class Result
	 * @param a SQLresult
	 */
	private void PreparaCamposCargoEResultado(Position position, Resultado resultado,
			ResultSet resultadoSQL) throws SQLException {
		position.setCodigo(resultadoSQL.getInt(COD_CARGO));
		resultado.setCodigo(resultadoSQL.getInt(COD_RESULTADO));
	}

	/*
	 * This method prepares attributes Candidate and Party
	 * @param an instance of Class Party
	 * @param an instance of Class Candidate
	 * @param a SQLresult
	 */
	private void PreparaCamposCandidatoEPartido(Party party,
			Candidate candidate, ResultSet resultadoSQL) throws SQLException {
		party.setNumero(resultadoSQL.getInt(NUMERO_PARTIDO));
		candidate.setTituloEleitoral(resultadoSQL.getString(TITULO_CANDIDATO));
	}

	/*
	 * This method retrieves a list of campaigns from a voter
	 * @param an instance of Class Candidate
	 * @return an ArrayList<Campaign>
	 */
	public ArrayList<Campaign> getCampanhasPeloTituloEleitoral(
			Candidate candidate) throws SQLException {
		ArrayList<Campaign> listaCampanha = new ArrayList<>();
		String comandoSQL = SQL_SELECT + " USE INDEX (" + INDEX_CANDIDATO + ")"
				+ " WHERE " + TITULO_CANDIDATO + " = '"
				+ candidate.getTituloEleitoral() + "' ";
		listaCampanha = buscaBD(comandoSQL);
		return listaCampanha;
	}

	/*
	 * This method retrieves a list of campaigns from an acronym and year
	 * @param a String acronym
	 * @param a String year
	 * @return an ArrayList<Campaign>
	 */
	public ArrayList<Campaign> getCampanhasPorSiglaEAno(String sigla, String ano)
			throws SQLException {
		this.partidoDAO = new PartidoDAO();

		ArrayList<Campaign> listaCampanha = new ArrayList<>();
		Party party = this.partidoDAO.getPelaSigla(sigla);
		String comandoSQL = SQL_SELECT + " USE INDEX (" + INDEX_PARTIDO + ", "
				+ INDEX_ANO + ")" + " WHERE " + NUMERO_PARTIDO + " = '"
				+ party.getNumero() + "' AND " + ANO + " = '" + ano + "' ";
		listaCampanha = buscaBD(comandoSQL);
		return listaCampanha;
	}

	/*
	 * This method retrieves information from an instance of Class Campaign
	 * @param an instance of Class Campaign
	 * @return an instance of Class Campaign
	 */
	public Campaign getPeloAnoNumeroCodCargoEUf(Campaign campaign)
			throws SQLException {
		ArrayList<Campaign> listaCampanha = new ArrayList<>();
		String comandoSQL = SQL_SELECT + " WHERE " + ANO + " = "
				+ campaign.getAno() + " AND " + UF + " = '" + campaign.getUf()
				+ "' AND " + NUM_CANDIDATO + " = '"
				+ campaign.getNumeroCandidato() + "' AND " + COD_CARGO + " = "
				+ campaign.getCargo().getCodigo();
		listaCampanha = buscaBD(comandoSQL);
		if(listaCampanha.isEmpty()) {
			return null;
		} else {
			return listaCampanha.get(0);
		}
	}

	/*
	 * This method retrieves a complete list of campaigns stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Campaign>
	 */
	public ArrayList<Campaign> buscaBD(String SQL) throws SQLException {
		ArrayList<Campaign> listaCampanha = new ArrayList<>();
		this.candidatoDAO = new CandidatoDAO();
		this.cargoDAO = new CargoDAO();
		this.partidoDAO = new PartidoDAO();
		this.resultadoDAO = new ResultadoDAO();

		try {
			this.conexao = new ConexaoBancoDados().getConexao();

			String comandoSQL = SQL;
			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);
			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();

			while(resultadoSQL.next()) {
				Campaign campaign = new Campaign();
				campaign.setAno(resultadoSQL.getInt(ANO));
				campaign.setCargo(cargoDAO.getPeloCod(resultadoSQL.getInt(COD_CARGO)));
				campaign.setDespesaMaxDeclarada(resultadoSQL.getFloat(DESPESA_MAX_DECLARADA));
				campaign.setDespesaTotalCalculada(resultadoSQL.getFloat(DESPESA_MAX_CALCULADA));
				campaign.setNomeDeUrna(resultadoSQL.getString(NOME_URNA));
				campaign.setNumeroCandidato(resultadoSQL.getInt(NUM_CANDIDATO));
				campaign.setPartido(partidoDAO.getPeloNumero(resultadoSQL.getString(NUMERO_PARTIDO)));
				campaign.setReceitaTotalCalculada(resultadoSQL.getFloat(RECEITA_MAX_CALCULADA));
				campaign.setResultado(resultadoDAO.getPeloCod(resultadoSQL.getInt(COD_RESULTADO)));
				campaign.setUf(resultadoSQL.getString(UF));
				campaign.setCandidato(candidatoDAO.getCandidatoPeloTitulo(resultadoSQL.getString(TITULO_CANDIDATO)));
				if(campaign != null) {
					listaCampanha.add(campaign);
				}
			}
		} catch(SQLException e) {
			throw new SQLException("CampanhaDAO - " + e.getMessage());
		} finally {
			fecharConexao();
		}
		return listaCampanha;
	}
	
	/*
	 * This method retrieves a list of campaigns for the functionality of the Top Five Graphics
	 * @param a String with the position
	 * @param an Integer with the year
	 * @return an ArrayList<Campaign>
	 */
	public ArrayList<Campaign> TopFive(String cargo, Integer ano) throws SQLException {
		int codigo = 0;
		switch(cargo.toLowerCase()) {
		
			case "presidente": 
				codigo = 1;
				break;
			case "governador": 
				codigo = 3;
				break;
			case "senador": 
				codigo = 5;
				break;
			default: 
				return null;
		}
		
		String comandoSQL = SQL_SELECT + " WHERE " + ANO + " = " + ano + " and " 
		                   +COD_CARGO + " = " + codigo + " ORDER BY " + DESPESA_MAX_DECLARADA
		                   +" DESC LIMIT 5";
		
		return buscaBD(comandoSQL);
	}
}