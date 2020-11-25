package dao;

import java.util.List;

import models.Employment;


public interface EmploymentDAO {
	
	public List<Employment> getEmployments() throws Exception;

	public void save(Employment employer);

	public void update(Employment employer);

	public Employment seachById(int id) throws Exception;

	public void delete(int id);

	List<String> getPhoneNumbes(int idEmployer) throws Exception;


}
