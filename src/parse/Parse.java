package parse;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;

import parse.LeitorCSV.ExecutorLeitorCSVObservador;
import parse.register.CadastroParse;

public abstract class Parse implements ExecutorLeitorCSVObservador {

	
	private LeitorCSV leitorCSV;
	private ArrayList<CadastroParse<?>> listaCadastrosParse;
	
	public Parse(String tipoArquivo, String ano) throws ParseException {
		this.leitorCSV = new LeitorCSV();
		this.leitorCSV.setExecutorLeitorCSVObservador(this);

		this.listaCadastrosParse = new ArrayList<>();
		adicionarCadastrosParseNaLista(listaCadastrosParse, tipoArquivo, ano);
	}
	
	protected abstract void adicionarCadastrosParseNaLista(ArrayList<CadastroParse<?>> listaCadastrosParse,
			String tipoArquivo, String ano) throws ParseException;
	
	public void executarParse(FileItem arquivo, String divisao, int linhaInicial) throws IOException, ParseException {
		this.leitorCSV.executarMetodoPorLinhaLida(arquivo, divisao, linhaInicial);
		finalizarCadastros();
	}
	
	@Override
	public void executarMetodoPorLinhaDoArquivo(String[] campo) {
		try {
			for(CadastroParse<?> cadastroParse : this.listaCadastrosParse) {
				cadastroParse.executarLinhaDoArquivo(campo);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void finalizarCadastros() throws ParseException {
		for(CadastroParse<?> cadastroParse : this.listaCadastrosParse) {
			cadastroParse.cadastrarInstancias();
		}
	}

}





















