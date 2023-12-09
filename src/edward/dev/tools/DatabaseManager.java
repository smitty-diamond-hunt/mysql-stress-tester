package edward.dev.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

	private final String dbUrl;
	private final String username;
	private final String password;
	private final String database;

	public DatabaseManager() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		this.username = Env.getInstance().getValue("DB_USERNAME");
		this.password = Env.getInstance().getValue("DB_PASSWORD");
		this.database = Env.getInstance().getValue("DB_NAME");
		this.dbUrl = "jdbc:mysql://" + Env.getInstance().getValue("DB_URL") + "/" + this.database;
	}

	public void query() {

		try {
			Connection conn = DriverManager.getConnection(this.dbUrl, this.username, this.password);
			String query = Env.getInstance().getValue("SQL_STATEMENT");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
}
