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
import parse.ParseException;
import parse.ParseParty;
import parse.CSVReader.ExecutorReaderCSVObserver;

@SuppressWarnings("deprecation")
public class ReaderCSVTest {

	public static final String FILE_NAME = "/src/test/parse/csv_testes.csv";
	public static final int NUMBER_LINES_FILE = 8232;
	
	private FileItem fileItem;
	
	private CSVReader cSVReader;
	private ExecutorReaderCSVObserver executorReaderCSV;
	
	private ParseParty parseParty;
	
	@Before
	public void setUp() throws IOException, ParseException {
		
		initFileItem();
		
		this.executorReaderCSV = new ExecutorReaderCSVObserver() {
			@Override
			public void runMethodForEachRead(String[] campo) {
				
			}
		};
		this.cSVReader = new CSVReader();
		this.cSVReader.setExecutorLeitorCSVObservador(this.executorReaderCSV);
		
		this.parseParty = new ParseParty("expense", "2002");
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
	
	@Test
	public void runToReadNoLineMethodForNoExceptionThrowForParseParty() throws Exception {
		
		this.parseParty.runParse(this.fileItem, ";", 1);
	}
	
	@Test
	public void mustShouldRunLineMethodReadFromTheLine10000NoExceptionThrowForParseParty() throws Exception {
		
		this.parseParty.runParse(this.fileItem, ";", 10000);
	}
	
	@Test(expected=Exception.class)  
	public void throwsAnExceptionWhenPassingANull() throws Exception {
		
		this.parseParty.runMethodForEachRead(null);
	}
	
	
	private InputStream getNewInputStream() throws FileNotFoundException {
		
		String directory = new File("").getAbsolutePath();
		String archivePath = directory + FILE_NAME;
		return new FileInputStream(new File(archivePath));
	}
	
	private void initFileItem() {
		
		this.fileItem = new FileItem() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

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
	
	/* Auxiliary test for implementation of the abstract class FileItem */
	
	@Test
	public void auxiliaryTest1() throws Exception {
		this.fileItem.setHeaders(null);
	}
	
	@Test
	public void auxiliaryTest2() throws Exception {
		this.fileItem.getHeaders();
	}
	
	@Test
	public void auxiliaryTest3() throws Exception {
		this.fileItem.write(null);
	}
	
	@Test
	public void auxiliaryTest4() throws Exception {
		this.fileItem.setFormField(false);
	}
	
	@Test
	public void auxiliaryTest5() throws Exception {
		this.fileItem.setFieldName(null);
	}
	
	@Test
	public void auxiliaryTest6() throws Exception {
		this.fileItem.isInMemory();
	}
	
	@Test
	public void auxiliaryTest7() throws Exception {
		this.fileItem.isFormField();
	}
	
	@Test
	public void auxiliaryTest8() throws Exception {
		this.fileItem.getString(null);
	}
	
	@Test
	public void auxiliaryTest9() throws Exception {
		this.fileItem.getString();
	}
	
	@Test
	public void auxiliaryTest10() throws Exception {
		this.fileItem.getOutputStream();
	}
	
	@Test
	public void auxiliaryTest11() throws Exception {
		this.fileItem.getName();
	}
	
	@Test
	public void auxiliaryTest12() throws Exception {
		this.fileItem.getInputStream();
	}
	
	@Test
	public void auxiliaryTest13() throws Exception {
		this.fileItem.getFieldName();
	}
	
	@Test
	public void auxiliaryTest14() throws Exception {
		this.fileItem.getContentType();
	}
	
	@Test
	public void auxiliaryTest15() throws Exception {
		this.fileItem.get();
	}
	
	@Test
	public void auxiliaryTest16() throws Exception {
		this.fileItem.delete();
	}
}