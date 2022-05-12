package JDBC_Tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Drop_Row
{
  ConnInfo connInfo =new ConnInfo();
  private final String url = connInfo.getUrl();
  private final String user = connInfo.getUser();
  private final String password = connInfo.getPassword();


  public Connection connect() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }

  public int deleteRow(int medarbejderid, String database) {

    String SQL = "DELETE FROM " + database + " WHERE medarbejderid = ?";

    int affectedrows = 0;

    try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(SQL)) {

      pstmt.setInt(1, medarbejderid);

      affectedrows = pstmt.executeUpdate();

    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
    return affectedrows;
  }

  /*
   * @param args the command line arguments
   */

  public int idToDelete = 100003;
  public static void main(String[] args) {

    Drop_Row drop = new Drop_Row();
    drop.deleteRow(drop.idToDelete, "login");
    drop.deleteRow(drop.idToDelete, "medarbejder");
    drop.deleteRow(drop.idToDelete, "tidsregistrering");
    drop.deleteRow(drop.idToDelete, "vagt");

  }
}