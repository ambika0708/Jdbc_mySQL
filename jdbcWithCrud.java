import java.sql.*;
import java.util.Scanner;

public class jdbcWithCrud {
    public static void main(String[] args) throws SQLException {
        updateData();
        //deleteData();
        //insertData();
        //fetchData();
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/film_Industry", "root", "@ambikasurya07");
            Statement st = con.createStatement();
            String sql = "select * from actors";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("name");
                int age = rs.getInt("Age");
                String gender = rs.getString("gender");
                long amount = rs.getLong("amount");
                System.out.println("id = " + id + " name = " + name + " age = " + age + " gender = " + gender + " amount = " + amount);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void fetchData() {
        Connection con = null;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name ");
        String name = in.nextLine();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/film_industry", "root", "@ambikasurya07");
            Statement st = con.createStatement();
            String sql = "select * from actors where name= '" + name + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String nameFound = rs.getString("name");
                System.out.println("Found");
            } else {
                System.out.println(name + " Not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //PREPARED STATEMENT
    public static void insertData() throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/film_Industry", "root", "@ambikasurya07");
            String sql = "insert into actors values(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.println("Enter Id: ");
                int id = in.nextInt();
                System.out.println("Enter Name: ");
                String name = in.next();
                System.out.println("Enter Age: ");
                int age = in.nextInt();
                System.out.println("Enter Gender: ");
                String gender = in.next();
                System.out.println("Enter amount: ");
                long amount = in.nextLong();
                pst.setInt(1, id);
                pst.setString(2, name);
                pst.setInt(3, age);
                pst.setString(4, gender);
                pst.setLong(5, amount);
                pst.executeUpdate();
                System.out.println("Added Successfully");
                System.out.println("Do you need to insert one more record {Yes / No}");
                String option = in.next();
                if (option.equalsIgnoreCase("No")) {
                    System.out.println("Thank You .." + '\n');
                    break;
                }
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static void deleteData() throws SQLException {
        Connection con = null;
        Scanner in = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/film_industry", "root", "@ambikasurya07");
            System.out.println("Enter the id you want to delete");
            int id = in.nextInt();
            String sql = "delete from actors where id ='" + id + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            int rows = pst.executeUpdate();
            System.out.println("Number of Rows affected " + rows);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateData() throws SQLException {
        Connection con = null;
        Scanner in = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/film_industry", "root", "@ambikasurya07");
            System.out.println("Enter the id you want to update");
            int id = in.nextInt();
            System.out.println("Enter the name you want to update");
            String name = in.next();
            String sql = "update actors set name = '" + name + "' where id = '" + id + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            int rows = pst.executeUpdate();
            System.out.println("Number of Rows affected " + rows);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
