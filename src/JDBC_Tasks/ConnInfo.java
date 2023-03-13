package JDBC_Tasks;

public class ConnInfo
{
//elephantSQL
  private final String url = "jdbc:postgresql://hattie.db.elephantsql.com:5432/ndjnwzho";
  private final String user = "YOUR_USERNAME";
  private final String password = "YOUR_PASSWORD";

  public String getUrl()
  {
    return url;
  }

  public String getUser()
  {
    return user;
  }

  public String getPassword()
  {
    return password;
  }

/*
  private final String url = "jdbc:postgresql://localhost:5432/testTest";
  private final String user = "postgres";
  private final String password = "LOCAL_POSTGRESQL_PASSWORD";

  public String getUrl()
  {
    return url;
  }

  public String getUser()
  {
    return user;
  }

  public String getPassword()
  {
    return password;
  }

   */
}
