import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    // Class for connecting with database

    private int columns;
    private ResultSetMetaData rsmd;

    public Database() throws SQLException {
        //At start get number of columns

        ResultSet rs = getAllTeams();
        rsmd = rs.getMetaData();

        columns = rsmd.getColumnCount();
    }

    public Connection getConnection() throws SQLException { // Function that attempts connection to server
        Connection conn;
        conn = DriverManager.getConnection("jdbc:mysql://localhost/Teams", "root", "root");
        return conn;
    }

    public String[] getTeamsData() throws SQLException { // Function that returns all column names from database

        String[] teamData = new String[columns];

        for (int i = 1; i <= columns; i++) {
            teamData[i - 1] = rsmd.getColumnName(i);
        }
        return teamData;
    }

    public ResultSet getAllTeams() throws SQLException { // Function that returns entire table from database
        Connection conn = getConnection();
        PreparedStatement selectStatement = conn.prepareStatement("select * from teams");
        return selectStatement.executeQuery();
    }

    public List<Team> getTeamsTable(String search) throws SQLException { // Function that returns search result with keyword from database
        List<Team> teams = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement selectStatement = conn.prepareStatement("select * from teams where Name like ?");

        if (search.equals("empty"))
            selectStatement.setString(1, "%");
        else
            selectStatement.setString(1, "%"+search + "%");

        ResultSet rs = selectStatement.executeQuery();

        while (rs.next()) { // will traverse through all rows

            Team team = new Team(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(10),
                    rs.getString(11)
            );
            teams.add(team);
        }

        return teams;
    }

    public boolean login(String username, String password) throws SQLException { // Function that checks username and password with database

        ResultSet rs = findUsername(username); // 1.First find username

        if (rs.next()) {
            if (rs.getString(2).equals(username) && rs.getString(3).equals(password)) // 2.Then check if username and password are a match
                return true;
        }
        return false;
    }

    public boolean create(String username, String password) throws SQLException {// Function that creates username and password in database

        if (username.equals("?") || findUsername(username).next()|| password.equals("?")) //  ->
            // Check if username and password are valid, 1. if the username wasn't empty, 2. if that username is not in database and 3. if password wasn't empty
            return false;
        Connection conn = getConnection();

        PreparedStatement selectStatement = conn.prepareStatement("insert into passwords(userName,userPassword) values(?,?)");
        selectStatement.setString(1, username);
        selectStatement.setString(2, password);

        selectStatement.execute();
        if (selectStatement.getUpdateCount() > 0) // Check if created password was successful
            return true;
        else return false;

    }

    public ResultSet findUsername(String username) throws SQLException { // Function that returns ResultSet with a search in passwords with keyword username
        Connection conn = getConnection();
        PreparedStatement selectStatement = conn.prepareStatement("select * from passwords where userName=?");
        selectStatement.setString(1, username);

        return selectStatement.executeQuery();

    }


    public int getColumns() {
        return columns;
    }

}