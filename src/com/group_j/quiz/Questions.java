package com.group_j.quiz;
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

	public void checkAnswers(String usrinput) {  //Jyoti
		try {
			Connector connector = new Connector();
			con = connector.getDBConnection();
			String sql = "SELECT correct_answer FROM quiz.mcq WHERE id=?";

			ps = con.prepareStatement(sql);
			ps.setInt(1, currentRow);
			ResultSet answers = ps.executeQuery();
			String correctAnswer = "";
			if (answers.next()) {
				correctAnswer = answers.getString("correct_answer");

			}

			if (usrinput.equals(correctAnswer)) {
				score = score + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void recordData(String name, int score, String grade, String remark) throws SQLException { //Jyoti

		Connector connector = new Connector();
		con = connector.getDBConnection();
		String sql = "INSERT INTO `quiz`.`record`(`usrName`,`Score`,`grade`,`remark`) VALUES(?,?,?,?);";
		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, score);
		ps.setString(3, grade);
		ps.setString(4, remark);
		ps.executeUpdate();

	}

	public ResultSet returnQuestions() throws SQLException { //Kajal

		Connector connector = new Connector();
		con = connector.getDBConnection();
		String sql = "SELECT questions, option1, option2, option3, option4 FROM quiz.mcq LIMIT 10";
		ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = ps.executeQuery();
		return rs;

	}
	
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
	public void getGrade(int score) {  //Nikhil
		// Class A- 8-10
		// Class B- 6-8
		// Class C- 5
		// Class D- <5 then its fail.
		if (score >= 8 && score <= 10) {
			grade = "A";
			remark = "Pass";
		} else if (score >= 6 && score < 8) {
			grade = "B";
			remark = "Pass";
		} else if (score == 5) {
			grade = "C";
			remark = "Pass";
		} else if (score < 5) {
			grade = "D";
			remark = "Fail";
		}

	}


}
