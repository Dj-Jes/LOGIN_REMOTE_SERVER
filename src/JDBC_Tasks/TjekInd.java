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
    public LocalDate date = LocalDate.now();
    public Time time1 = new Time(12,52,15);
    public Time time2 = new Time(15,52,15);

    public Time getTime1() {
        return time1;
    }

    public Time getTime2() {
        return time2;
    }

    public LocalDate getDate() {

        return date;
    }

    public int tjekind (int medarbejderid, LocalDate dato, Time tjekindtid, Time tjekudtid) {
        String SQL = "INSERT INTO tjekind"+" (medarbejderid, dato, tjekindtid ,tjekudtid) VALUES  (?,?,?,?)";
        int affectedrows = 0;
        System.out.println(""+ SQL);

        try (Connection conn = connect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setInt(1, medarbejderid);
                pstmt.setObject(2, dato);
                pstmt.setTime(3, tjekindtid);
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

        tjek.tjekind( 123456, tjek.getDate(), tjek.getTime1(), tjek.getTime2());
    }

}
