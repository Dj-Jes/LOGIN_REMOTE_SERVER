package JDBC_Tasks;

import com.sun.tools.javac.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Drop_Row
{
    private final String url = "jdbc:postgresql://localhost/Employees";
    private final String user = "postgres";
    private final String password = "volvo210";


  public Connection connect() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }

  public int deleteRow(int id, String database) {

    String SQL = "DELETE FROM " + database + " WHERE \"EmpID\" = ?";

    int affectedrows = 0;

    try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(SQL)) {

      pstmt.setInt(1, id);

      affectedrows = pstmt.executeUpdate();

    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return affectedrows;
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Drop_Row drop = new Drop_Row();
    drop.deleteRow(100002, "\"LoginInfo\"");
    drop.deleteRow(100002, "\"PersInfo\"");

  }
}