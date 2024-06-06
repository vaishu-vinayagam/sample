import java.util.*;
import java.sql.*;

class CollegeAdmission {
    public static void main(String[] args) {
        try {
            // Connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CollegeApplication", "root", null);
            // Create tables if they don't exist
            String createCollegeTableSQL = "CREATE TABLE IF NOT EXISTS College (id int primary key, name varchar(20));";
            String createDepartmentTableSQL = "CREATE TABLE IF NOT EXISTS Department (college_id int, dept_id int, name varchar(10), quota char(2), seats_available int, foreign key(college_id) references College(id));";
            String createStudentTableSQL = "CREATE TABLE IF NOT EXISTS Student (id int primary key, name varchar(20), gender char(1), cutoff float, quota char(2));";
            String createAdmissionTableSQL = "CREATE TABLE IF NOT EXISTS Admission (stud_id INT,cutoff float,quota char(2),college_id INT, dept_id INT, FOREIGN KEY (stud_id) REFERENCES Student(id), FOREIGN KEY (college_id) REFERENCES College(id));";
            Statement stmt = con.createStatement();
            stmt.execute(createCollegeTableSQL);
            stmt.execute(createDepartmentTableSQL);
            stmt.execute(createStudentTableSQL);
            stmt.execute(createAdmissionTableSQL);

            Scanner sc = new Scanner(System.in);
            System.out.println("College Admission System");
            while (true) {
                System.out.println("1.Add college\n2.Add department\n3.Add student\n4.Display College details\n5.Display Student details\n6.Admission process\n7.Top Colleges\n8.Exit\nEnter your choice:");

                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next();
                    continue; 
                }
                int n = sc.nextInt();
                sc.nextLine();
                switch (n) {
                    case 1:
                        AddCollege.main(new String[0]);
                        break;
                    case 2:
                        AddDepartment.main(new String[0]);
                        break;
                    case 3:
                        AddStudent.main(new String[0]);
                        break;
                    case 4:
                        CollegeDetails.main(new String[0]);
                        break;
                    case 5:
                        StudentDetails.main(new String[0]);
                        break;
                    case 6:
                        Admission.main(new String[0]);
                        break;
                    case 7:
                        TopColleges.main(new String[0]);
                        break;
                    case 8:
                        con.close(); 
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}  
