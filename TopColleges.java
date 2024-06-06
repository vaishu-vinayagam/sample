import java.util.*;
import java.sql.*;  
class TopColleges{  
    public static void main(String args[]){  
    try{ 
	 Scanner sc=new Scanner(System.in);
         System.out.println("Enter n:");
 	 int n=sc.nextInt();
   	 Class.forName("com.mysql.jdbc.Driver");  
   	 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CollegeApplication","root",null);  
    	 Statement stmt=con.createStatement();  
    	 ResultSet rs=stmt.executeQuery("select name from College order by seats_available,seats_booked desc;");  
    	 while(rs.next()){  
    	   System.out.println(rs.getString(1));  
    	}
        con.close();  
    }catch(Exception e){ System.out.println(e);}  
  } 
}
