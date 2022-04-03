package JDBC_Tasks;

import java.sql.*;

public class Select_From
{
  private final String url = "jdbc:postgresql://localhost/Employees";
  private final String user = "postgres";
  private final String password = "volvo210";

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
   * Get all rows in the actor table
   */
  public void getEmp() {

    String SQL = "SELECT \"EmpID\",\"First_name\", "
        + "\"Last_name\", \"Email\" FROM \"PersInfo\"";

    try (Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL)) {
      // display actor information
      displayEmp(rs);
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }

  /*
   * Get actors count
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
   * Display actor
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
   * Find actor by his/her ID
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
    select.findEmpByID(100030);
    System.out.println("Find by id");

  }
}