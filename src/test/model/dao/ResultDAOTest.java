package test.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Result;
import model.dao.ResultDAO;

import org.junit.Assert;
import org.junit.Test;

import test.TemplateTest;

public class ResultDAOTest extends TemplateTest {

	private ResultDAO resultDAO;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.resultDAO = new ResultDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void shouldRetrieveResultByCode() throws SQLException {
		
		ArrayList<Result> resultList = new ArrayList<>();
		
		Result r1 = new Result();
		r1.setResultType(1);
		r1.setResultDescription("Result 1");
		resultList.add(r1);
		
		Result r2 = new Result();
		r2.setResultType(2);
		r2.setResultDescription("Result 2");
		resultList.add(r2);
		
		this.resultDAO.registerUnregisteredObjectArrayListOnDatabase(resultList);
			
		Result r3 = new Result();
		r3 = this.resultDAO.getResultByCode(1);
		Assert.assertEquals(r1, r3);
	}
	
	@Test
	public void shouldNotFindResult() throws SQLException {
		
		ArrayList<Result> lista = new ArrayList<>();
		
		Result r1 = new Result();
		r1.setResultType(1);
		r1.setResultDescription("Result 1");
		lista.add(r1);
		
		Result r2 = new Result();
		r2.setResultType(2);
		r2.setResultDescription("Result 2");
		lista.add(r2);
		
		this.resultDAO.registerUnregisteredObjectArrayListOnDatabase(lista);
			
		Result r3 = new Result();
		r3 = this.resultDAO.getResultByCode(5);
		Assert.assertNotEquals(r1, r3);
	}
	
	@Test
	public void comparationValues() throws Exception {
		
		Result r1 = new Result();
		Result r2 = new Result();
		r1.setResultType(1);
		r2.setResultType(2);
		int resultado;

		resultado = ResultDAO.CompareTwoResultsType.RESULT_TYPE.compare(r1, r2);
		
		Assert.assertNotEquals(0,resultado);
	}

}
