package test.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.junit.Before;
import org.junit.Test;

import parse.CSVReader;
import parse.CSVReader.ExecutorReaderCSVObserver;

public class ReaderCSVTest {

	public static final String FILE_NAME = "/src/test/parse/csv_testes.csv";
	public static final int NUMBER_LINES_FILE = 8232;
	
	private FileItem fileItem;
	
	private CSVReader cSVReader;
	private ExecutorReaderCSVObserver executorReaderCSV;
	
	@Before
	public void setUp() throws IOException {
		
		initFileItem();
		
		this.executorReaderCSV = new ExecutorReaderCSVObserver() {
			@Override
			public void runMethodForEachRead(String[] campo) {
				
			}
		};
		this.cSVReader = new CSVReader();
		this.cSVReader.setExecutorLeitorCSVObservador(this.executorReaderCSV);
	}
	
	@Test
	public void numberOfRowsMustEqualTheNumberOfLinesOfFile() throws Exception {
		
		int numberLines = this.cSVReader.getNumberOfLines(this.fileItem);
		Assert.assertEquals(NUMBER_LINES_FILE, numberLines);
	}

	@Test
	public void runToReadNoLineMethodForNoExceptionThrow() throws Exception {
		
		this.cSVReader.runMethodForReadLine(this.fileItem, ";", 1);
	}
	
	@Test
	public void mustShouldRunLineMethodReadFromTheLine10000NoExceptionThrow() throws Exception {
		
		this.cSVReader.runMethodForReadLine(this.fileItem, ";", 10000);
	}
	
	
	private InputStream getNewInputStream() throws FileNotFoundException {
		
		String directory = new File("").getAbsolutePath();
		String archivePath = directory + FILE_NAME;
		return new FileInputStream(new File(archivePath));
	}
	
	private void initFileItem() {
		
		this.fileItem = new FileItem() {
			
			@Override
			public void setHeaders(FileItemHeaders arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public FileItemHeaders getHeaders() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void write(File arg0) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setFormField(boolean arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setFieldName(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isInMemory() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isFormField() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String getString(String arg0) throws UnsupportedEncodingException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public long getSize() {
				try {
					return getNewInputStream().available();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return 0;
			}
			
			@Override
			public OutputStream getOutputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public InputStream getInputStream() throws IOException {
				return getNewInputStream();
			}
			
			@Override
			public String getFieldName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public byte[] get() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void delete() {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
}