import java.util.*;
import java.sql.*;

class AddDepartment{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CollegeApplication", "root", null);
            System.out.println("Enter College id:");
            int collegeId = sc.nextInt();
            sc.nextLine(); 
	    System.out.println("Enter department id:");
            int deptId = sc.nextInt();
            sc.nextLine(); 
            System.out.println("Enter department name:");
            String name = sc.nextLine();
            System.out.println("Enter Quota:");
            String quota = sc.nextLine();
            System.out.println("Enter no. of seats:");
            int seatCount = sc.nextInt();
            sc.nextLine();
            
            // Insert department
            String insertQuery = "INSERT INTO Department (college_id, dept_id, name, quota, seats_available) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(insertQuery)) {
                stmt.setInt(1, collegeId);
                stmt.setInt(2, deptId);
                stmt.setString(3, name);
                stmt.setString(4, quota);
                stmt.setInt(5, seatCount);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Department added successfully.");
                } else {
                    System.out.println("Failed to add department.");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } 
   }
}

