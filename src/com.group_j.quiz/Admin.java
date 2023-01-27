import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
	private String correctPassword = "1234";
	Connection con = null;
	PreparedStatement ps = null;
	Scanner sc = new Scanner(System.in);

	

	

	public void printAllData() { //Jayesh
		try {
			Connector connector = new Connector();
			con = connector.getDBConnection();
			String sql = "SELECT Id, usrName, Score, grade, remark FROM quiz.record";
			ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = ps.executeQuery();
		
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int numCols = rsmd.getColumnCount();

			int numRows = 0;
		    while (rs.next()) {
		        numRows++;
		    }
		    if(numRows>0) {
	
			int[] maxLengths = new int[numCols];

			
			for (int i = 1; i <= numCols; i++) {
				maxLengths[i - 1] = rsmd.getColumnName(i).length();
			}

			

			while (rs.next()) {
				for (int i = 1; i <= numCols; i++) {
					int length = rs.getString(i).length();
					if (length > maxLengths[i - 1]) {
						maxLengths[i - 1] = length;
					}
				}
			}

			String border = "+";
			for (int i = 0; i < numCols; i++) {
				border += String.format("%-" + (maxLengths[i] + 2) + "s+", "-").replace(" ", "-");
			}

			System.out.println(border);
			System.out.print("|");
			for (int i = 1; i <= numCols; i++) {
				System.out.printf(" %-" + maxLengths[i - 1] + "s |", rsmd.getColumnName(i));
			}
			System.out.println();
			System.out.println(border);
			rs.first();

			do {
				System.out.print("|");
				for (int i = 1; i <= numCols; i++) {
					System.out.printf(" %-" + maxLengths[i - 1] + "s |", rs.getString(i));
				}
				System.out.println();
			} while (rs.next());

			
			System.out.println(border);
		    }else {
		    	System.out.println("No Record Present in DataBase");
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
