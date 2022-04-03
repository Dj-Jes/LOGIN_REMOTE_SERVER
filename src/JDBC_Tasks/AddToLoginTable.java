package JDBC_Tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddToLoginTable
{
  private final String url = "jdbc:postgresql://localhost/Employees";
  private final String user = "postgres";
  private final String password = "volvo210";

  private static final String Insert_Login_SQL = "INSERT INTO \"LoginInfo\"" +
      "  ( \"Password\") VALUES " + " (?);";

  public void insertLogin() throws SQLException
  {
    System.out.println(Insert_Login_SQL);
    // Step 1: Establishing a Connection
    try (Connection connection = DriverManager.getConnection(url, user, password);

        // Step 2:Create a statement using connection object
        PreparedStatement preparedStatement = connection.prepareStatement(
            Insert_Login_SQL))
    {
      preparedStatement.setString(1, "1234");

      System.out.println(preparedStatement);
      // Step 3: Execute the query or update query
      preparedStatement.executeUpdate();
    }
    catch (SQLException e)
    {

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

  public static void main(String[] argv) throws SQLException
  {
    AddToLoginTable AddRow = new AddToLoginTable();
    AddRow.insertLogin();
  }
}
