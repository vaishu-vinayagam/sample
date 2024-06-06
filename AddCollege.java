import java.sql.*;
import java.util.*;
class AddCollege{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
	System.out.println("Enter College id: ");
        int id= sc.nextInt();
        sc.nextLine();
	System.out.println("Enter college name:");
        String name = sc.nextLine();
   	try{
           Class.forName("com.mysql.jdbc.Driver");  
   	   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CollegeApplication","root",null);  
	   PreparedStatement pstmt=con.prepareStatement("INSERT INTO College VALUES (?,?)");  
            pstmt.setString(2, name);
            pstmt.setInt(1, id);
            int x=pstmt.executeUpdate();
	    System.out.println("College added successfully");
	    con.close();  
        }
	catch(Exception e){ 
	    System.out.println(e);
	}  
    }   
}
