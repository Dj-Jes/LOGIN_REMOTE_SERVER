package JDBC_Tasks;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

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
    public LocalDate date = LocalDate.now();

    public LocalTime tjekind = LocalTime.now();

    public LocalTime getTjekind() {
        return tjekind;
    }
    public LocalDate getDate() {

        return date;
    }

    public int tjekind (int medarbejderid, LocalDate dato, LocalTime tjekindtid, Time tjekudtid) {
        String SQL = "INSERT INTO tjekind"+" (medarbejderid, dato, tjekindtid ,tjekudtid) VALUES  (?,?,?,?)";
        int affectedrows = 0;
        System.out.println(""+ SQL);

        try (Connection conn = connect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setInt(1, medarbejderid);
                pstmt.setObject(2, dato);
                pstmt.setObject(3, tjekindtid);
                pstmt.setTime(4, tjekudtid);

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

        tjek.tjekind( 3, tjek.getDate(),tjek.tjekind, null);
    }

}
