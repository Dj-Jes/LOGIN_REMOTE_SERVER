package JDBC_Tasks;

import java.sql.*;

public class AddToTableImpr
{
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
    public int addEmp(String database, String fornavn, String mellemnavn, String efternavn,
                      String dob, String email, int tlf, String adresse, Boolean leder) {
        String SQL = "insert into" +database+  " " +
                "fornavn,mellemnavn,efternavn,dob,email,tlf,adresse, leder "+ "(?,?,?,?,?,?,?,?)";
        int affectedrows = 0;

        try (Connection conn = connect())
        {
            try (PreparedStatement pstmt = conn.prepareStatement(SQL))
            {
                pstmt.setString (1, fornavn);
                pstmt.setString (2, mellemnavn);
                pstmt.setString (3, efternavn);
                pstmt.setString (4,dob);
                pstmt.setString (5, email);
                pstmt.setInt    (6,tlf);
                pstmt.setString (7,adresse);
                pstmt.setBoolean(8,leder);


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

        AddToTableImpr addToTableImpr =new AddToTableImpr();
        addToTableImpr.addEmp("medarbejdere","Jon",null,"Jonson","20-4-1999","jon@gmail.com",
                123456,"Sted, Postnr,By",true);

    }
}
