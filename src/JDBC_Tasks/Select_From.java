package JDBC_Tasks;

import java.sql.*;

public class Select_From
{
  ConnInfo connInfo =new ConnInfo();
  private final String url = connInfo.getUrl();
  private final String user = connInfo.getUser();
  private final String password = connInfo.getPassword();

  /*
   * Connect to the PostgreSQL database
   *
   * @return a Connection object
   * @throws java.sql.SQLException
   */
  public Connection connect() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }


  /*
   * Get all rows in table
   */
  public void getEmp(String database) {

    String SQL = "SELECT \"EmpID\",\"First_name\", "
        + "\"Last_name\", \"Email\" FROM "+database;

    try (Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL)) {
      // display emp information
      displayEmp(rs);
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }

  /*
   * Get emp count
   * @return
   */

  public int getEmpCount(String database) {
    String SQL = "SELECT count(*) FROM "+ database;
    int count = 0;

    try (Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL)) {
      rs.next();
      count = rs.getInt(1);
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }

    return count;
  }

  /*
   * Display emp
   *
   * @param rs
   * @throws SQLException
   */



  private void displayEmp(ResultSet rs) throws SQLException {
    while (rs.next()) {
      System.out.println
          (rs.getString("medarbejderID") + "\t"
          + rs.getString("fornavn")      + "\t"
          + rs.getString("mellemnavn")   + "\t"
          + rs.getString("efternavn")    + "\t"
          + rs.getString("dob")          + "\t"
          + rs.getString("email")        + "\t"
          + rs.getInt("tlf")             + "\t"
          + rs.getString("adresse")      + "\t"
          + rs.getBoolean("leder"));

    }
  }

  /*
   * Find by  ID
   *
   * @param EmpID
   */
  public void findEmpByID(int medarbejderID, String database) {
    String SQL = "select * from "+database+ "WHERE medarbejderID = ?;";

    try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(SQL)) {

      pstmt.setInt(1, medarbejderID);
      ResultSet rs = pstmt.executeQuery();
      displayEmp(rs);
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }

  /*
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Select_From select = new Select_From();
    select.getEmp("medarbejdere");
    System.out.println("getEMP don");
    select.getEmpCount("medarbejdere");
    System.out.println(select.getEmpCount("medarbejdere")+"  Count don");
    select.findEmpByID(123456,"medarbejdere");
    System.out.println("Find by id");

  }
}