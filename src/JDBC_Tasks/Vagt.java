package JDBC_Tasks;

import java.sql.*;
import java.time.LocalDate;

public class Vagt {
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

    public Date date = Date.valueOf("1999-12-26");
    public Time startTime = new Time(12,52,15);
    public Time slutTime = new Time(15,52,15);

    public Time getStartTime() {
        return startTime;
    }

    public Time getSlutTime() {
        return slutTime;
    }

    public Date getDate() {
        return date;
    }

    public int opretvagt(String database, int medarbejderid, Date dato,
                         Time startTime, Time slutTime) {
        String SQL = "insert into "+database+""  +
                "(dato, starttid, sluttid, medarbejderid) values  (?,?,?,?)";
        int affectedrows = 0;

        try (Connection conn = connect())
        {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL))
            {
                pstmt.setObject(1, dato);
                pstmt.setTime(2, startTime);
                pstmt.setTime(3, slutTime);
                pstmt.setInt(4,medarbejderid);


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

        Vagt vagt = new Vagt();

        vagt.opretvagt("vagt",100005,  vagt.getDate() ,vagt.startTime, vagt.getSlutTime() );

    }
}
