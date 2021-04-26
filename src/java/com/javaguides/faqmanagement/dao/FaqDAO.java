package com.javaguides.faqmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaguides.faqmanagement.model.Faq;
public class FaqDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/FAQS?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	//  ?useSSL=false
	//  ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
	private String jdbcUsername = "root";
	private String jdbcPassword = "n0m3l0";

	private static final String INSERT_FAQS_SQL = "INSERT INTO faqs" + "  (pregunta, respuesta) VALUES "
			+ " (?, ?);";

	private static final String SELECT_FAQ_BY_ID = "select id,pregunta,respuesta from faqs where id =?";
	private static final String SELECT_ALL_FAQS = "select * from faqs";
	private static final String DELETE_FAQS_SQL = "delete from faqs where id = ?;";
	private static final String UPDATE_FAQS_SQL = "update faqs set pregunta = ?,respuesta = ? where id = ?;";

	public FaqDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {              
			Class.forName("com.mysql.jdbc.Driver");
		    // Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertFaq(Faq faq) throws SQLException {
		System.out.println(INSERT_FAQS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FAQS_SQL)) {
			preparedStatement.setString(1, faq.getPregunta());
			preparedStatement.setString(2, faq.getRespuesta());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Faq selectFaq(int id) {
		Faq faq = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FAQ_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String pregunta = rs.getString("pregunta");
				String respuesta = rs.getString("respuesta");
				faq = new Faq(id, pregunta, respuesta);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return faq;
	}

	public List<Faq> selectAllFaqs() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Faq> Faqs = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FAQS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String pregunta = rs.getString("pregunta");
				String respuesta = rs.getString("respuesta");
				Faqs.add(new Faq(id, pregunta, respuesta));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return Faqs;
	}

	public boolean deleteFaq(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_FAQS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateFaq(Faq faq) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_FAQS_SQL);) {
			statement.setString(1, faq.getPregunta());
			statement.setString(2, faq.getRespuesta());
			statement.setInt(3, faq.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
