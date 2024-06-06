import java.util.*;
import java.sql.*;
class AddStudent{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Student id:");
        int id=sc.nextInt();
        sc.nextLine();
	System.out.println("Enter name:");
        String name=sc.nextLine();
        System.out.println("Enter Quota:");
        String quota=sc.nextLine();
        System.out.println("Enter gender:");
        String gender=sc.nextLine();
	System.out.println("Enter cut off:");
        float cutoff=sc.nextFloat();
        sc.nextLine();
        try{  
   	   Class.forName("com.mysql.jdbc.Driver");  
   	   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CollegeApplication","root",null);  
	   PreparedStatement stmt=con.prepareStatement("insert into Student(id,name,quota,gender,cutoff) values (?,?,?,?,?)");  
    	   stmt.setInt(1,id);
           stmt.setString(2,name);
  	   stmt.setString(3,quota);
	   stmt.setString(4,gender);
     	   stmt.setFloat(5,cutoff);
 	   stmt.executeUpdate();   
           PreparedStatement st=con.prepareStatement("INSERT INTO ADMISSION(stud_id,cutoff,quota) VALUES (?,?,?)");
	   st.setInt(1,id);
	   st.setFloat(2,cutoff);
	   st.setString(3,quota);
	   st.executeUpdate();
	   System.out.println("Student added successfully");
	   con.close();  
        }catch(Exception e){ System.out.println(e);}  
    }   
}
