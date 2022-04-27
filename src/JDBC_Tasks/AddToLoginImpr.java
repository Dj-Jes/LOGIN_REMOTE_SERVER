package JDBC_Tasks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddToLoginImpr {

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
    public int addLogin(String database, String password) {
        String SQL = "INSERT INTO "+ database + password +" VALUES  (?)";
        int affectedrows = 0;

        try (Connection conn = connect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1, password);


                affectedrows = pstmt.executeUpdate();

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return affectedrows;
    }

    public int updatePassword( int medarbejderID, String changeTo ) {
        String SQL = "UPDATE login SET" +changeTo+ "= ? "
                + "WHERE kodeord = ?";
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

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        AddToLoginImpr addToLoginImpr = new AddToLoginImpr();
        addToLoginImpr.addLogin("medarbejdere", "1234");

    }
}
