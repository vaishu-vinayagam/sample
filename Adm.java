import java.util.*;
import java.sql.*;

class Admission {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>(); // List to hold students
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CollegeApplication", "root", null);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT A.stud_id, S.cutoff, S.name, S.gender, S.quota FROM Admission A JOIN Student S ON A.stud_id = S.id WHERE A.college_id IS NULL");
            while (rs.next()) {
                int studId = rs.getInt("stud_id");
                float cutoff = rs.getFloat("cutoff");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String quota = rs.getString("quota");
                students.add(new Student(studId, cutoff, name, gender, quota));
            }
            Collections.sort(students, new Comparator<Student>() {
            @Override
                public int compare(Student s1, Student s2) {
                    int result = Float.compare(s2.getCutoff(), s1.getCutoff());
                    if (result == 0) {
                        result = s1.getName().compareTo(s2.getName());
                        if (result == 0) {
                            result = s1.getGender().compareTo(s2.getGender());
                        }
                    }
                    return result;
                }
            });
            for (Student student : students) {
                while(true){
	   	    System.out.println("For Student " + student.getStudId());
                    System.out.println("Enter College id:");
                    int cid = sc.nextInt();
                    System.out.println("Enter department id:");
                    int did = sc.nextInt();
                    int studentId = student.getStudId();
                    String quota = student.getQuota();
                        // Check seat availability in the Department table
                    try (PreparedStatement departmentStmt = con.prepareStatement("SELECT seats_available FROM Department WHERE college_id = ? AND dept_id = ? AND quota = ?")) {
                        departmentStmt.setInt(1, cid);
                        departmentStmt.setInt(2, did);
                        departmentStmt.setString(3, quota);
                        ResultSet departmentResultSet = departmentStmt.executeQuery();
                        if (departmentResultSet.next()) {
                            int seatsAvailable = departmentResultSet.getInt("seats_available");
                            if (seatsAvailable > 0) {
                                // Assign student to the department
                                System.out.println("Student ID " + studentId + " assigned to College " + cid + ", Department " + did);
                                PreparedStatement pstmt = con.prepareStatement("UPDATE Admission SET college_id = ?, dept_id = ? WHERE stud_id = ?");
                                pstmt.setInt(1, cid);
                                pstmt.setInt(2, did);
                                pstmt.setInt(3, studentId);
                                pstmt.executeUpdate();
                                    // Update seat availability
                                pstmt = con.prepareStatement("UPDATE College SET seats_booked = seats_booked + 1, seats_available = seats_available - 1 WHERE id = ?");
                                pstmt.setInt(1, cid);
                                pstmt.executeUpdate();
                                pstmt = con.prepareStatement("UPDATE Department SET seats_available = seats_available - 1 WHERE college_id = ? AND dept_id = ? AND quota = ?");
                                pstmt.setInt(1, cid);
                                pstmt.setInt(2, did);
                                pstmt.setString(3, quota);
                                pstmt.executeUpdate();
                                break;
                            } 
                            else {
                                System.out.println("No seats available in the specified department.");
                            }
                        } else {
                            System.out.println("No such department found for the specified college and quota.");
                        }
                    }
         		}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Student {
    private int studId;
    private float cutoff;
    private String name;
    private String gender;
    private String quota;
    public Student(int studId, float cutoff, String name, String gender, String quota) {
        this.studId = studId;
        this.cutoff = cutoff;
        this.gender = gender;
        this.name = name;
        this.quota = quota;
    }

    public String getQuota() {
        return quota;
    }
    public int getStudId() {
        return studId;
    }

    public String getGender() {
        return gender;
    }
    public Float getCutoff() {
        return cutoff;
    }
    public String getName() {
        return name;
    }
}

