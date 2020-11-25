package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import models.Employment;
import models.PhoneNumber;

public class DBConnection {
	private Connection connection;
	private String user;
	private String password;

	public DBConnection(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	private Connection getConnection() throws ClassNotFoundException {

		String url = "jdbc:mysql://localhost:3306/enterprise";
		Properties info = new Properties();
		info.put("user", this.user);
		info.put("password", this.password);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, info);
			System.out.println("Successfull Connection");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	// Get all data from the table
	public ResultSet getEmploymentTable() throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		String query = "SELECT * FROM enterprise.employment;";
		Statement stmt;
		try {
			connection = getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}
	public ResultSet getById(int id) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		String query = "SELECT * FROM enterprise.employment WHERE id=?;";
		PreparedStatement stmt;
		
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet getDepartmentTable() throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		String query = "SELECT * FROM enterprise.department;";
		Statement stmt;
		try {
			connection = getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}

	public void addEmployment(Employment employer) throws SQLException, ClassNotFoundException {
		String query1 = "INSERT INTO `enterprise`.`employment` (`name`, `departmentId`, `gender`) VALUES (?,?,?);";
		PreparedStatement stmt1;
		int queryResult;
		Long lastId = null;


		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			stmt1 = connection.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
			stmt1.setString(1, employer.getName());
			stmt1.setInt(2, employer.getDepartmentId());
			stmt1.setString(3, employer.getGender().toString());
			queryResult = stmt1.executeUpdate();
			
			if (queryResult != 0){
				ResultSet generatedKey = stmt1.getGeneratedKeys();
				if (generatedKey.next())
					lastId = generatedKey.getLong(1);
				
			}
			
			for (PhoneNumber phoneNumber : employer.getPhoneNumber()) {
				String query2 = "INSERT INTO `enterprise`.`phoneNumbers` (`employerId`, `number`) VALUES (?,?);";
				PreparedStatement stmt2;
				stmt2 = connection.prepareStatement(query2);
				stmt2.setInt(1, lastId.intValue());
				stmt2.setString(2, phoneNumber.getNumber());
				stmt2.executeUpdate();
			}

			connection.commit();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			connection.setAutoCommit(true);
		}
	}

	public void editEmployment(Employment employer) throws SQLException, ClassNotFoundException {
		String query = "UPDATE `enterprise`.`employment` SET `name` = ?, `departmentId` = ?, `gender` = ? WHERE (`id` = ?);";
		PreparedStatement stmt;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setString(1, employer.getName());
			stmt.setInt(2, employer.getDepartmentId());
			stmt.setString(3, employer.getGender().toString());
			stmt.setInt(4, employer.getId());
			stmt.executeUpdate();
			connection.commit();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			connection.setAutoCommit(true);
		}
	}
	public void deleteEmployer(int idEmployer) throws SQLException, ClassNotFoundException {
		String query = "DELETE FROM `enterprise`.`employment` WHERE (`id` = ?);";
		PreparedStatement stmt;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, idEmployer);
			stmt.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
