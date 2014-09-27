package parse;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.apache.commons.fileupload.FileItem;

public class CSVReader {
	
	public interface ExecutorLeitorCSVObservador {
		public void executarMetodoPorLinhaDoArquivo(String campo[]);
	}
	
	private ExecutorLeitorCSVObservador executorLeitorCSVObservador;
	
	public CSVReader() {
		this.executorLeitorCSVObservador = null;
	}
	
	public void runMethodForReadLine(FileItem arquivo, String divisao, int linhaInicial) throws IOException {
		String campo[];
		String linha;
		int totalLinhas = getNumeroLinhas(arquivo);
		System.out.println("lendo linha: Iniciou");
		BufferedReader leitorArquivo = new BufferedReader(new InputStreamReader(arquivo.getInputStream()));
		
		ignorarLinhas(leitorArquivo, linhaInicial);
		for(int i = linhaInicial; ((linha = leitorArquivo.readLine()) != null) ; i++ ) {
			if(i % 10000 == 0) {
				System.out.println("lendo linha: " + i + " / " + totalLinhas);
			}
			
			linha = transformarPontoVirgulasDoCampoEmVirgula(linha);
			campo = linha.split(divisao);

			removerAspas(campo);
			notificarObservador(campo);
		}
		System.out.println("lendo linha: Terminou");
		
		leitorArquivo.close();
	}
	
	public int getNumeroLinhas(FileItem arquivo) throws IOException {
		int numeroLinhas;
		
		long tamanhoArquivo = arquivo.getSize();
		InputStream inputStream = arquivo.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(inputStream);
		
		LineNumberReader lineRead = new LineNumberReader(new InputStreamReader(dataInputStream));
		lineRead.skip(tamanhoArquivo);
		
		numeroLinhas = lineRead.getLineNumber() + 1;
		
		return numeroLinhas;
	}
	
	private void ignorarLinhas(BufferedReader leitorArquivo, int numeroLinhas) throws IOException {
		for(int i = 1; (i < numeroLinhas) && (leitorArquivo.readLine() != null); i++);
	}
	
	private void removerAspas(String palavra[]) {
		for(int i = 0; i < palavra.length; i++) {
			if(palavra[i].length() > 0 && palavra[i].charAt(0) == '"') {
				palavra[i] = palavra[i].substring(1, palavra[i].length());
			}
			if(palavra[i].length() > 0 && palavra[i].charAt(palavra[i].length()-1) == '"') {
				palavra[i] = palavra[i].substring(0, palavra[i].length()-1);
			}
		}
	}
	
	private String transformarPontoVirgulasDoCampoEmVirgula(String palavra) {
		String novaPalavra;
		char caracteres[] = palavra.toCharArray();
		for(int i = 1; i < caracteres.length-1; i++) {
			if( caracteres[i] == ';' && (caracteres[i-1] != '"' || caracteres[i+1] != '"') ){
				caracteres[i] = ',';
			}
		}
		
		novaPalavra = String.copyValueOf(caracteres);
		return novaPalavra;
	}

	public void setExecutorLeitorCSVObservador(
			ExecutorLeitorCSVObservador executorLeitorCSVObservador) {
		this.executorLeitorCSVObservador = executorLeitorCSVObservador;
	}
	
	private void notificarObservador(String campo[]) {
		if(this.executorLeitorCSVObservador != null) {
			this.executorLeitorCSVObservador.executarMetodoPorLinhaDoArquivo(campo);
		}
	}
	
}
