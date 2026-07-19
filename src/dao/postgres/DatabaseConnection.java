package dao.postgres;

import java.sql.*;


public class DatabaseConnection {

	private static Connection connection;
	private static final String URL  ="jdbc:postgresql://localhost:5432/unina_task_board";
	private static final String USER  ="postgres";
	private static final String PASSWORD  ="810EEd69";

	private DatabaseConnection() {}

	public static Connection getInstance() {

		try {

			if (connection == null || connection.isClosed()) {

				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connessione database andata a buon fine.");

			}
		}
		catch(SQLException e) {

			System.out.println("Connessione database fallita.");
			e.printStackTrace();
		}

		return connection;

	}
}

