package parse;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.fileupload.FileItem;

import parse.CSVReader.ExecutorReaderCSVObserver;
import parse.register.RegisterParse;

public abstract class Parse implements ExecutorReaderCSVObserver {

	
	private CSVReader cSVReader;
	private ArrayList<RegisterParse<?>> listRegisterParse;
	
	public Parse(String fileType, String year) throws ParseException {
		this.cSVReader = new CSVReader();
		this.cSVReader.setExecutorLeitorCSVObservador(this);

		this.listRegisterParse = new ArrayList<>();
		addRegisterParseOnList(listRegisterParse, fileType, year);
	}
	
	protected abstract void addRegisterParseOnList(ArrayList<RegisterParse<?>> listRegisterParse,
			String fileType, String year) throws ParseException;
	
	public void runParse(FileItem file, String division, int initialLine) throws IOException, ParseException {
		this.cSVReader.runMethodForReadLine(file, division, initialLine);
		finalizeRegister();
	}
	
	@Override
	public void runMethodForEachRead(String[] field) {
		try {
			for(RegisterParse<?> registerParse : this.listRegisterParse) {
				registerParse.runFileLine(field);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void finalizeRegister() throws ParseException {
		for(RegisterParse<?> registerParse : this.listRegisterParse) {
			registerParse.registerInstances();
		}
	}
}