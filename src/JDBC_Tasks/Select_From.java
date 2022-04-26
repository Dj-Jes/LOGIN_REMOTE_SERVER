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
  public void getEmp() {

    String SQL = "SELECT \"EmpID\",\"First_name\", "
        + "\"Last_name\", \"Email\" FROM \"PersInfo\"";

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
  public int getEmpCount() {
    String SQL = "SELECT count(*) FROM \"PersInfo\"";
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
      System.out.println(rs.getString("EmpID") + "\t"
          + rs.getString("First_name") + "\t"
          + rs.getString("Last_name") + "\t"
          + rs.getString("Email"));

    }
  }

  /*
   * Find by  ID
   *
   * @param EmpID
   */
  public void findEmpByID(int EmpID) {
    String SQL = "select * from \"PersInfo\""
        + "WHERE \"EmpID\" = ?;";

    try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(SQL)) {

      pstmt.setInt(1, EmpID);
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
    select.getEmp();
    System.out.println("getEMP don");
    select.getEmpCount();
    System.out.println(select.getEmpCount()+"  Count don");
    select.findEmpByID(100044);
    System.out.println("Find by id");

  }
}