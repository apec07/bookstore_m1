package bookstore_m1;

import java.sql.*;

public class TestConnection {

	private static Connection con = null;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/db_morgan_bookstore?" + "autoReconnect=true&useSSL=false";
		String userName = "root";
		String password = "1234";

		con = DriverManager.getConnection(url, userName, password);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM prod_category");
		ResultSetMetaData rsmd = rs.getMetaData();

		int colNum = rsmd.getColumnCount();
		String[] strs = new String[colNum];
		for (int i = 1; i <= colNum; i++) {
			System.out.print(rsmd.getColumnName(i) + "\t");
		}
		System.out.println();
		while (rs.next()) {
			for (int i = 0; i < colNum; i++) {

				strs[i] = rs.getString(i + 1);
				System.out.print(strs[i] + "\t");
			}

			System.out.println();

		}

	}

}
