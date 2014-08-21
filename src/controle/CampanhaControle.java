package controle;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.beans.Campanha;
import modelo.beans.Candidato;
import modelo.dao.CampanhaDAO;

public class CampanhaControle {
	
	/*
+	 * Control class political campaign, which mediates the application layer with the model
+	 */
+	
+	// Attributes
	private CampanhaDAO campanhaDAO;

	// Constructors	
	public CampanhaControle() {
		this.campanhaDAO = new CampanhaDAO();
	}
	
	// Other methods
+	/*
+	 * Method making the request of an applicant for voter registration 
	 * @param a candidate
+	 * @return an ArrayList of candidates for voter registration
+	 */
	public ArrayList<Campanha> getListaCampanhas(Candidato candidato) throws SQLException {
		return this.campanhaDAO.getCampanhasPeloTituloEleitoral(candidato);
	}
	
	/*
+	 * Method making the request of a candidate by the abbreviation of the party and year
+	 * @param the abbreviation of the party and year
+	 * @return an ArrayLista the political party with the abbreviation of the party and year informed
+	 */
	public ArrayList<Campanha> getListaCampanhasPorSiglaPartidoEAno(String sigla, String ano) throws SQLException{
		return this.campanhaDAO.getCampanhasPorSiglaEAno(sigla,ano);
	}
	
	/*
+	 * Method that the query requests a set of five candidate with highest value in the data reported
+	 * @param the political cargo and year
+	 * @return an ArrayLista with the highest values ​​for the position and informed year
+	 */
	public ArrayList<Campanha> topFivePorCargoEAno(String cargo, Integer ano) throws SQLException{
		return this.campanhaDAO.TopFive(cargo, ano);
	}

	/*
+	 * Method making the request of a candidate for the post code and UF informed
+	 * @param the campaign of the desired candidate
+	 * @return a campaign
+	 */
	public Campanha getPeloAnoNumeroCodCargoEUf(Campanha campanha) throws SQLException {
		return this.campanhaDAO.getPeloAnoNumeroCodCargoEUf(campanha);
	}
}
