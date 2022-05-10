package JDBC_Tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTable
{
    ConnInfo connInfo =new ConnInfo();
    private final String url = connInfo.getUrl();
    private final String user = connInfo.getUser();
    private final String password = connInfo.getPassword();


    public Connection connect() throws SQLException {
    return DriverManager.getConnection(url, user, password);
       }


    public int updateAll (String database, String searchCriteria) {

    String SQL = "select * from " + database+ " order by " +searchCriteria;

    int affectedrows = 0;
        System.out.println(""+SQL);

    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(SQL)) {
         pstmt.executeUpdate();

         } catch (SQLException ex) {
           System.out.println(ex.getMessage());
         }
         return affectedrows;
        }

        /*
         * @param args the command line arguments
         */
        public static void main(String[] args) {
        UpdateTable updateTable = new UpdateTable();
        updateTable.updateAll("medarbejdere","medarbejderid");
       /*     System.out.println("Medarbejdere sorteret");
        updateTable.updateAll("vagter","dato");
        updateTable.updateAll("tjekind","dato");
        updateTable.updateAll("login","medarbejderid");

        */
    }
}
