import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Questions {

	Connection con = null;
	PreparedStatement ps = null;

	int score = 0;
	String grade;
	String remark;
	String usrName;
	int currentRow;
	int i = 1;

	
	public void takeTest() {   //Jayesh

		try {
			System.out.println("Enter Your Name");
			Scanner sc = new Scanner(System.in);
			usrName = sc.nextLine();
			System.out.println();

			ResultSet rs = returnQuestions();

			// Get the total number of rows in the ResultSet
			rs.last();
			int totalRows = rs.getRow();
			rs.beforeFirst();

			List<Integer> rows = IntStream.rangeClosed(1, totalRows).boxed().collect(Collectors.toList());

			Collections.shuffle(rows);

			for (int row : rows) {
				currentRow = row;
				rs.absolute(row);

				String question = rs.getString("questions");
				String option1 = rs.getString("option1");
				String option2 = rs.getString("option2");
				String option3 = rs.getString("option3");
				String option4 = rs.getString("option4");

				System.out.println("Question " + i + ": " + question);
				System.out.println("Option 1:   " + option1);
				System.out.println("Option 2:   " + option2);
				System.out.println("Option 3:   " + option3);
				System.out.println("Option 4:   " + option4);

				System.out.println("Enter Correct Option....");

				int usrInput = -1;
				int counter = 1;
				while (usrInput < 1 || usrInput > 4) {
					if (counter == 1) {
						usrInput = sc.nextInt();
						counter++;
					} else {
						System.out.println("Please Enter Correrct Option between 1 to 4. ");
						usrInput = sc.nextInt();

					}
				}

				System.out.println();
				rs.absolute(row);
				String usrAnswer = rs.getString(1 + usrInput);
				// System.out.println("YOU SELECTED: "+usrAnswer);
				checkAnswers(usrAnswer);
				i++;
				getGrade(score);

			}
			recordData(usrName, score, grade, remark);
			System.out.println("Thank You for Taking Test.....");
			System.out.println("******************************");
			System.out.println();
			System.out.println("Press 1 for Admin Panal Login otherwise Enter Anykey to Quite Program");
			String s=sc.next();
			if(s.equals("1")) {
				Admin admin = new Admin();
				admin.adminlogin();
			}else {
				System.exit(1);
			}
			
			
			sc.close();
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// finally block used to close resources

			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
