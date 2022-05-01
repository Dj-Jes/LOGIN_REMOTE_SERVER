package JDBC_Tasks;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class TjekInd {
    ConnInfo connInfo = new ConnInfo();
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
    public int tjekind (String database, int medarbejderid) {
        String SQL = "INSERT INTO "+ database + medarbejderid +"(dato, tjekud) VALUES  (?,?,?)";
        int affectedrows = 0;

        try (Connection conn = connect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setInt(1, medarbejderid);
                pstmt.setObject(2, LocalDate.now());
                pstmt.setObject(3, LocalTime.now());


                affectedrows = pstmt.executeUpdate();

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return affectedrows;
    }

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        TjekInd tjek = new TjekInd();

        tjek.tjekind("tjekind", 1234);
    }
}
