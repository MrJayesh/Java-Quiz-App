package com.group_j.quiz;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DataBase {
	Connection con = null;
	PreparedStatement ps = null;
	String[] fileName = { "quiz_mcq.sql", "quiz_record.sql" };

	
	
	public DataBase() { //Jayesh
		try {
			String usrDir = System.getProperty("user.dir");

			Connector connector = new Connector();
			for (String s : fileName) {

				con = connector.getDBConnection();
				
				BufferedReader in = new BufferedReader(new FileReader(usrDir + "/src/com/group_j/quiz/" + s));
				String str;
				StringBuffer sb = new StringBuffer();
				while ((str = in.readLine()) != null) {
					sb.append(str + "\n");
				}
				in.close();
				String[] queries = sb.toString().split(";");

				for (int i = 0; i < queries.length - 1; i++) {
					
					PreparedStatement statement = con.prepareStatement(queries[i]);
					statement.executeUpdate();
					
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
