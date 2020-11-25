package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import models.Employment;

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

			stmt1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			stmt1.setString(1, employer.getName());
			stmt1.setInt(2, employer.getDepartmentId());
			stmt1.setString(3, employer.getGender().toString());
			queryResult = stmt1.executeUpdate();

			if (queryResult != 0) {
				ResultSet generatedKey = stmt1.getGeneratedKeys();
				if (generatedKey.next())
					lastId = generatedKey.getLong(1);

			}

			for (String phoneNumber : employer.getPhoneNumber()) {
				String query2 = "INSERT INTO `enterprise`.`phoneNumbers` (`employerId`, `number`) VALUES (?,?);";
				PreparedStatement stmt2;
				stmt2 = connection.prepareStatement(query2);
				stmt2.setInt(1, lastId.intValue());
				stmt2.setString(2, phoneNumber);
				stmt2.executeUpdate();
			}

			connection.commit();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	public void editEmployment(Employment employer) throws SQLException, ClassNotFoundException {
		String query1 = "UPDATE `enterprise`.`employment` SET `name` = ?, `departmentId` = ?, `gender` = ? WHERE (`id` = ?);";
		PreparedStatement stmt1;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			stmt1 = connection.prepareStatement(query1);
			stmt1.setString(1, employer.getName());
			stmt1.setInt(2, employer.getDepartmentId());
			stmt1.setString(3, employer.getGender().toString());
			stmt1.setInt(4, employer.getId());
			stmt1.executeUpdate();
			
			PreparedStatement stmt2;
			String query2 = "DELETE FROM `enterprise`.`phoneNumbers` WHERE (`employerId` = ?);";
			stmt2 = connection.prepareStatement(query2);
			stmt2.setInt(1, employer.getId());
			stmt2.executeUpdate();
			
			for (String phoneNumber : employer.getPhoneNumber()) {
				String query3 = "INSERT INTO `enterprise`.`phoneNumbers` (`employerId`, `number`) VALUES (?,?);";
				PreparedStatement stmt3;
				stmt3 = connection.prepareStatement(query3);
				stmt3.setInt(1, employer.getId());
				stmt3.setString(2, phoneNumber);
				stmt3.executeUpdate();
			}
			
			
			connection.commit();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.setAutoCommit(true);
		}
	}

	public void deleteEmployer(int idEmployer) throws SQLException, ClassNotFoundException {
		PreparedStatement stmt1;
		String query1 = "DELETE FROM `enterprise`.`phoneNumbers` WHERE (`employerId` = ?);";
		PreparedStatement stmt2;
		String query2 = "DELETE FROM `enterprise`.`employment` WHERE (`id` = ?);";

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			stmt1 = connection.prepareStatement(query1);
			stmt1.setInt(1, idEmployer);
			stmt1.executeUpdate();
			
			stmt2 = connection.prepareStatement(query2);
			stmt2.setInt(1, idEmployer);
			stmt2.executeUpdate();

			connection.commit();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.setAutoCommit(true);
		}
	}
	
	public ResultSet getPhoneNumbers(int idEmployer) throws SQLException, ClassNotFoundException {
		String query = "SELECT * FROM `enterprise`.`phoneNumbers` WHERE (`employerId` = ?);";
		

		PreparedStatement stmt;
		ResultSet rs = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, idEmployer);
			rs = stmt.executeQuery();

			connection.commit();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}


}
