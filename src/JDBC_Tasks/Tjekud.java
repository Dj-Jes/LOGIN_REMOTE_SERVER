package JDBC_Tasks;

import java.sql.*;
import java.time.LocalTime;

public class Tjekud {
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
    public LocalTime tjekud = LocalTime.now();


    public int tjekud( int id,String database, LocalTime changeTo ) {
        String SQL = "UPDATE " +database+  " SET tjekudtid = ? "
                + "WHERE medarbejderid_fk = ?";
        System.out.println(""+SQL);

        int affectedrows = 0;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setObject(1, changeTo);
            pstmt.setInt(2, id);

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
        Tjekud tjekud = new Tjekud();
        tjekud.tjekud( 100000,"tidsregistrering",tjekud.tjekud);

    }
}
