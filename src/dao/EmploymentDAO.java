package dao;

import java.util.List;

import models.Employment;


public interface EmploymentDAO {
	
	public List<Employment> getEmployments();

	public void save(Employment student);

	public void update(Employment student);

	public Employment seachById(int id);

	public void delete(int id);



}
