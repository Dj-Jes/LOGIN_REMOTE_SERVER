package JDBC_Tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddToLoginTableImpr {

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
    public int addLogintest( String kodeord, int medarbejderid) {
        String SQL = "INSERT INTO login (kodeord, medarbejderid_fk) values (?,?)";
        int affectedrows = 0;

        System.out.println(""+ SQL);

        try (Connection conn = connect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1, kodeord);
                pstmt.setInt(2,medarbejderid);



                affectedrows = pstmt.executeUpdate();

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return affectedrows;
    }

    public int updatePassword( int medarbejderID, String changeTo ) {
        String SQL = "UPDATE login SET kodeord = " +changeTo+ " WHERE medarbejderid_fk = "+ medarbejderID;
        int affectedrows = 0;

        try (Connection conn = connect())
        {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL))
            {
                pstmt.setString(1, changeTo);
                pstmt.setInt(2, medarbejderID);
                affectedrows = pstmt.executeUpdate();

            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return affectedrows;
    }

    public int updatetest( int id, String changeTo ) {
        String SQL = "UPDATE login SET kodeord = ?"
                + "WHERE medarbejderid_fk = ?";
        System.out.println(""+SQL);

        int affectedrows = 0;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, changeTo);
            pstmt.setInt(2, id);

            affectedrows = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return affectedrows;
    }

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        AddToLoginTableImpr addToLoginImpr = new AddToLoginTableImpr();
        //addToLoginImpr.updatePassword(100006, "4321");
        //addToLoginImpr.addLogin("login", 100000, "1234" );
        //addToLoginImpr.addLogintest("1234",100006);
        addToLoginImpr.updatetest(100006, "4321");

    }
}
