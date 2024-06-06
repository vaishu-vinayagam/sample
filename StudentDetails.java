import java.util.*;
import java.sql.*;  
class StudentDetails{  
    public static void main(String args[]){  
       try{  
   	 Class.forName("com.mysql.jdbc.Driver");  
   	 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CollegeApplication","root",null);  
         Statement stmt=con.createStatement();  
    	 ResultSet rs=stmt.executeQuery("select * from Student s left join Admission a on s.id=a.stud_id");  
    	 while(rs.next()){  
    	   System.out.println("Student id: "+rs.getInt(1)+"\nStudent Name: "+rs.getString(2)+"\nGender: "+rs.getString(3)+"\nQuota: "+rs.getString(5)+"\nCutoff: "+rs.getFloat(4)+"\nDepartment Id: "+rs.getInt(10)+"\nCollege Id: "+rs.getInt(9));  
	   System.out.println();
    	 }
         con.close();  
      }catch(Exception e){ System.out.println(e);}  
   }  
}

