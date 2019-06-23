package lesson_2;

import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    public static void main(String[] args) {

        try {
            connect();


            stmt.executeUpdate("CREATE TABLE Students (" +
                    "StudID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name TEXT NOT NULL, GroupName TEXT NOT NULL, Score INTEGER NOT NULL)"
            );
            stmt.executeUpdate("INSERT INTO Students (Name, GroupName, Score) VALUES ('Bob', 'Tre', '80')");
            stmt.executeUpdate("INSERT INTO Students (Name, GroupName, Score) VALUES ('Bob1', 'Tre1', '70')");
            stmt.executeUpdate("INSERT INTO Students (Name, GroupName, Score) VALUES ('Bob3', 'Tre2', '60')");

            stmt.executeUpdate("DELETE FROM Students WHERE Name = 'Bob1'");

            ResultSet rs = stmt.executeQuery("SELECT * FROM Students");

            while (rs.next()){
                System.out.print(rs.getInt(1) + " " + rs.getString("name") + " "+ rs.getString("GroupName") + " " + rs.getString("Score") + "\n");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void connect() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:MainDB.db");
        stmt = connection.createStatement();

    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
