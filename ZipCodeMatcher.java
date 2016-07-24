package com.Tester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* IN this Program we are going to achieve zipcode matching. That is we have
Database which has zipcode and regions. We have a set of zipcodes and we have to match them
with the zipcodes in master database and attach them to the new set.
*/
class ZipCodeConnector{
	HashMap<Integer,String> zipMap = new HashMap<>();
	public void run() 
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tester","root","password");
			Statement stmt = con.createStatement();
			String SQL = "select * from zipcode";
			ResultSet result =  stmt.executeQuery(SQL);
			while(result.next())
			{
				int zip = Integer.parseInt(result.getString("zipcode"));
				String region = result.getString("region");
				zipMap.put(zip, region);
				
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
public class ZipCodeMatcher {
public static void main(String[] args)
{
	ZipCodeConnector zp = new ZipCodeConnector();
	
	zp.run();
	
	HashMap<Integer,String> hm = zp.zipMap;
	
	List<Integer> list = new ArrayList<>();
	
	list.add(84403);
	list.add(35487);
	list.add(64068);
	
	for(int i: list)
	{
		System.out.println(i+ "-" +hm.get(i));
	}
	
}
}
