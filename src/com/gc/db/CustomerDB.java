package com.gc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gc.model.Customer;

public class CustomerDB {
	private Connection connection;

	public void connectToDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Murach", "root", "sesame");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException el) {
			// TODO Auto-generated catch block
			el.printStackTrace();
		}
	}

	public List<Customer> getCustomerList() {
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
			connectToDB();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from user");
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setFirstName(resultSet.getString(3));
				customer.setLastName(resultSet.getString(4));
				customer.setEmail(resultSet.getString(2));

				list.add(customer);
				System.out
						.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		CustomerDB cdb = new CustomerDB();
		cdb.getCustomerList();
	}

	public void addCustomer(Customer customer) throws SQLException {
		connectToDB();

		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO User (firstName, lastName, email) VALUES (?, ?, ?)");
		preparedStatement.setString(1, customer.getFirstName());
		preparedStatement.setString(2, customer.getLastName());
		preparedStatement.setString(3, customer.getEmail());
		preparedStatement.executeUpdate();
	}

	public void deleteCustomer(String emailId) throws SQLException {
		connectToDB();

		String preparedQuery = "DELETE FROM User WHERE Email = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery);
		preparedStatement.setString(1, emailId);
		preparedStatement.executeUpdate();

	}
}