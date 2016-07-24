package com.Tester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {
public static void main(String[] srgs)
{
try {
	Class.forName("com.mysql.jdbc.Driver");
	try {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","password");
		Statement st = con.createStatement();
		String sql = "select * from mystudents";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next())
		{
			String id = rs.getString("id");
			String fname = rs.getString("fname");
			System.out.println(id + " "+ fname);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}
}
