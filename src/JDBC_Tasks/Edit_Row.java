package JDBC_Tasks;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Edit_Row
{
  ConnInfo connInfo =new ConnInfo();
  private final String url = connInfo.getUrl();
  private final String user = connInfo.getUser();
  private final String password = connInfo.getPassword();


    public Connection connect() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }

    /*
     * Update emp's last name based on actor's id
     *
     * @param id
     * @param lastName
     * @return the number of affected rows
     */
    public int updateInfo( int ID,String database, String changeParameter, String changeTo ) {
      String SQL = "UPDATE" +database+  "SET" +changeTo+ "= ? "
          + "WHERE" +changeParameter+ "= ?";
      int affectedrows = 0;

      try (Connection conn = connect())
      {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL))
        {
          pstmt.setString(1, changeTo);
          pstmt.setInt(2, ID);
          affectedrows = pstmt.executeUpdate();

        }
      }
      catch (SQLException ex)
      {
        System.out.println(ex.getMessage());
      }
      return affectedrows;
    }

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Edit_Row editrow = new Edit_Row();
      editrow.updateInfo(100045,"medarbejdere","\"Last_name\"","ko");

    }
}
