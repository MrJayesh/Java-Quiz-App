package com.group_j.quiz;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connector { //Jyoti

	Connection connection = null;

	public Connection getDBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
