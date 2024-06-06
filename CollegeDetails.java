import java.sql.*;  
class CollegeDetails{  
    public static void main(String args[]){  
      try{  
   	 Class.forName("com.mysql.jdbc.Driver");  
   	 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CollegeApplication","root",null);  
    	 Statement stmt=con.createStatement();  
    	 ResultSet rs=stmt.executeQuery("select * from College c left join Department d on c.id=d.college_id");  
    	 while(rs.next()){  
    	   System.out.println("College Id: "+ rs.getInt(1)+"\nCollege Name: "+rs.getString(2)+"\nDepartment Id: "+rs.getInt(4)+"\nDepartment Name: "+rs.getString(5)+"\nQuota: "+rs.getString(6)+"\nNo. of seats: "+rs.getInt(7));  
	   System.out.println("\n");
    	 }
         con.close();  
      }catch(Exception e){ System.out.println(e);}
   }   
}

