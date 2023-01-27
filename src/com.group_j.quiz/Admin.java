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

	
	public boolean checkPass() { //Nikhil

		System.out.println("Enter Password");

		String input = sc.next();

		while (!input.equals(correctPassword)) {
			System.out.print("Enter password: ");
			input = sc.nextLine();
			if (!input.equals(correctPassword)) {
				System.out.println("Incorrect password. Please try again.");
			}
		}

		return true;

	}

	

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
	public void printData(ResultSet rs) { //Jyoti
		try {
			if (!rs.isBeforeFirst()) {
				System.out.println("The provided id is not present in the DataBase");
			} else {

				List<String> columns = new ArrayList<>();

				columns.add("ID");
				columns.add("User Name");
				columns.add("Marks");
				columns.add("Grade");
				columns.add("Remark");
				int[] columnWidths = new int[5];
				for (int i = 0; i < 5; i++) {
					columnWidths[i] = columns.get(i).length();
				}
				while (rs.next()) {
					for (int i = 1; i <= 5; i++) {
						int width = rs.getString(i).length();
						if (width > columnWidths[i - 1]) {
							columnWidths[i - 1] = width;
						}
					}
				}
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < columnWidths[i] + 2; j++) {
						sb.append("-");
					}
					sb.append("+");
				}
				System.out.println(sb);
				sb.setLength(0);
				for (int i = 0; i < 5; i++) {
					sb.append(String.format("%-" + (columnWidths[i] + 2) + "s|", columns.get(i)));
				}
				System.out.println(sb);
				sb.setLength(0);
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < columnWidths[i] + 2; j++) {
						sb.append("-");
					}
					sb.append("+");
				}
				System.out.println(sb);
				sb.setLength(0);
				rs.beforeFirst();
				while (rs.next()) {
					for (int i = 1; i <= 5; i++) {
						sb.append(String.format("%-" + (columnWidths[i - 1] + 2) + "s|", rs.getString(i)));
					}
					System.out.println(sb);
					sb.setLength(0);
				}
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < columnWidths[i] + 2; j++) {
						sb.append("-");
					}
					sb.append("+");
				}
				System.out.println(sb);
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}

		
	public void adminlogin() { //Jyoti
		boolean flag = checkPass();
		if (flag) {
			System.out.println("Login Successful....");
			System.out.println();
			System.out.println("1. Search Result by ID");
			System.out.println("2. Display Database");
			System.out.println("3. Clear All Records");
			int i = sc.nextInt();

			switch (i) {
			case 1:
				System.out.println("Enter Id to get Score");
				int id = sc.nextInt();

				ResultSet ds = getData(id);
				printData(ds);

				break;

			case 2:
				printAllData();
				break;
				
			case 3:
				clearRecords();
				break;

			default:
				System.out.println("Incorrect Choice");
				adminlogin();
			}

		}
		sc.close();
	}

}
