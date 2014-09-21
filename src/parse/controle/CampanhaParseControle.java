package parse.controle;

import java.util.ArrayList;

import model.beans.Campaign;
import model.dao.CampanhaDAO;
import parse.ParseException;
import parse.indices.IndicesParse;

public class CampanhaParseControle extends ParseControle<Campaign> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Campaign
	 */

	// Constructors
	public CampanhaParseControle(IndicesParse<Campaign> indicesParse) {
		super(indicesParse, new CampanhaDAO());
	}

	/*
	 * This method instantiates an object of Class Campaign
	 * @return an instance of Class Campaign
	 */
	@Override
	public Campaign novaInstancia() {
		Campaign campaign = new Campaign();
		return campaign;
	}

	/*
	 * This method checks if two instances are equal Class Campaign
	 * @param two instances of Class Campaign
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Campaign objetoUm, Campaign objetoDois) {
		return objetoUm.equals(objetoDois);
	}
	
	/*
	 * This method performs the processing and registration of instances of Class Campaign
	 */
	@Override
	public void cadastrarInstancias() throws ParseException {
		ArrayList<Campaign> listaCampanhas = new ArrayList<>();
		
		boolean foiEleito;
		for(int i = 0; i < this.listaInstancias.size(); i++) {
			
			foiEleito = false;
			
			if(listaCampanhas.contains(this.listaInstancias.get(i))) {
				continue;
			}
			
			for(int j = i; j < this.listaInstancias.size(); j++) {
				if(this.listaInstancias.get(i).getCampaignNameOfUrn().equalsIgnoreCase(this.listaInstancias.get(j).getCampaignNameOfUrn())) {
					if(this.listaInstancias.get(j).getCampaignResult().getCodigo() == 1 ||
					   this.listaInstancias.get(j).getCampaignResult().getCodigo() == 5) {
						foiEleito = true;
						listaCampanhas.add(this.listaInstancias.get(j));
						break;
					}
				}
			}
			
			if(!foiEleito) {
				this.listaInstancias.get(i).getCampaignResult().setCodigo(4);
				listaCampanhas.add(this.listaInstancias.get(i));
			}

		}
		
		this.listaInstancias = new ArrayList<>(listaCampanhas);
		super.cadastrarInstancias();
	}

}