package JDBC_Tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddToTable
{
  private final String url = "jdbc:postgresql://localhost/Employees";
  private final String user = "postgres";
  private final String password = "volvo210";

  private static final String INSERT_USERS_SQL = "INSERT INTO \"PersInfo\"" +
      "  ( \"First_name\", \"Last_name\", \"Email\") VALUES " +
      " (?, ?, ?);";

  public void insertRecord() throws SQLException
  {
    System.out.println(INSERT_USERS_SQL);
    // Step 1: Establishing a Connection
    try (Connection connection = DriverManager.getConnection(url, user, password);

        // Step 2:Create a statement using connection object
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
      preparedStatement.setString(1, "Bob");
      preparedStatement.setString(2, "Tony");
      preparedStatement.setString(3, "Tony@gmail.com");

      System.out.println(preparedStatement);
      // Step 3: Execute the query or update query
      preparedStatement.executeUpdate();
    } catch (SQLException e) {

      // print SQL exception information
      printSQLException(e);
    }

    // Step 4: try-with-resource statement will auto close the connection.
  }

  public static void printSQLException(SQLException ex)
  {
    for (Throwable e : ex)
    {
      if (e instanceof SQLException)
      {
        e.printStackTrace(System.err);
        System.err.println("SQLState: " + ((SQLException) e).getSQLState());
        System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
        System.err.println("Message: " + e.getMessage());
        Throwable t = ex.getCause();
        while (t != null)
        {
          System.out.println("Cause: " + t);
          t = t.getCause();
        }
      }

    }
  }
  public static void main(String[] argv) throws SQLException {
    AddToTable AddRow = new AddToTable();
    AddRow.insertRecord();
  }
}
