package footballFunctionality.dao;

import java.sql.*;
import footballFunctionality.model.PlayerInfo;

public class FootBallRegistrationDAO
{

    private Statement statement = null;
    private ResultSet resultSet = null;
    private String dburl = "jdbc:sqlserver://localhost:1433;databaseName=Football;trustServerCertificate=true;";
    private String dbusrname = "sa";
    private String dbpassword = "963369";
    private String dbdriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String findUserQuery = "SELECT username FROM FootballPlayers WHERE username = ";
    private String insertUserQuery = "INSERT INTO FootballPlayers values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private String retreiveUserQuery = "SELECT * FROM FootballPlayers WHERE username = ";
    private String updateUserQuery = "UPDATE FootballPlayers " +
            "SET username = ?,firstname=?,lastname=?,phonecode =?,phone=?,age=?,email=?,team=?,position=?,paddress=?,pin=?,country=?,states=?,city=?" +
            " WHERE username = ";


    //----Loads The SQLServer Driver----
    public void loadDriver(String dbDriver)
    {
        try
        {
            Class.forName(dbDriver);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    //------Creates a Connection with Database-----
    public Connection getConnection()
    {

        try
        {
            loadDriver(dbdriver);
            con = DriverManager.getConnection(dburl, dbusrname, dbpassword);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("error message is :- "+e.getMessage());

        }
        return con;
    }
    //Creating a global object for connection to database
    private Connection con = getConnection();

    /**
     * Returns 'true' if a registered player is found.
     * @param player
     * @return
     */
    public boolean checkUserExists(PlayerInfo player)
    {
        try
        {
            statement = con.createStatement();
            resultSet = statement.executeQuery(findUserQuery+"'"+player.getUserName()+"'");

            while(resultSet.next())
            {
                if(resultSet.getString("username").equals(player.getUserName()))
                {
                    return true;
                }
            }
            return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Registers a new entry in the Database
     * @param player
     * @return
     */
    public String registerNewPlayer(PlayerInfo player) {


        if(!checkUserExists(player)) {


            try
            {
                PreparedStatement ps = con.prepareStatement(insertUserQuery);
                ps.setString(1, player.getUserName());
                ps.setString(2, player.getFirstName());
                ps.setString(3, player.getLastName());
                ps.setString(4, player.getPhoneCode());
                ps.setString(5, player.getPhoneNumber());
                ps.setString(6, player.getAgeGroup());
                ps.setString(7, player.getEmail());
                ps.setInt(8, Integer.parseInt(player.getTeam()));
                ps.setInt(9, Integer.parseInt(player.getPosition()));
                ps.setString(10, player.getAddress());
                ps.setString(11, player.getPinCode());
                ps.setInt(12, Integer.parseInt(player.getCountry()));
                ps.setInt(13, Integer.parseInt(player.getState()));
                ps.setInt(14, Integer.parseInt(player.getCity()));

                ps.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                return "failed";
            }
        }
        else
        {
            return "userexists";
        }

        return "success";
    }

    /**
     * Retrieves a specific user from DataBase.
     * @param referedUser
     * @return
     */
    public PlayerInfo retreivePlayer(String referedUser){

        PlayerInfo player = new PlayerInfo();

        try
        {
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(retreiveUserQuery+"'"+referedUser+"'");

            while (resultSet.next())
            {
                player.setUserName(resultSet.getString("username"));
                player.setFirstName(resultSet.getString("firstname"));
                player.setLastName(resultSet.getString("lastname"));
                player.setPhoneCode(resultSet.getString("phonecode"));
                player.setPhoneNumber(resultSet.getString("phone"));
                player.setAgeGroup(resultSet.getString("age"));
                player.setEmail(resultSet.getString("email"));
                player.setTeam(String.valueOf(resultSet.getInt("team")));
                player.setPosition(String.valueOf(resultSet.getString("position")));
                player.setPinCode(resultSet.getString("pin"));
                player.setAddress(resultSet.getString("paddress"));
                player.setCountry(String.valueOf(resultSet.getString("country")));
                player.setState(String.valueOf(resultSet.getString("states")));
                player.setCity(String.valueOf(resultSet.getString("city")));
            }
            player.setStatus("success");
            return player;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            player.setStatus("failed");
            return player;
        }

    }

    /**
     * Updates data for a specific user
     * @param player
     * @return
     */
    public String updateData(PlayerInfo player)
    {

        try{

            PreparedStatement ps = con.prepareStatement(updateUserQuery+"'" + player.getUserName() + "'");
            ps.setString(1, player.getUserName());
            ps.setString(2, player.getFirstName());
            ps.setString(3, player.getLastName());
            ps.setString(4, player.getPhoneCode());
            ps.setString(5, player.getPhoneNumber());
            ps.setString(6, player.getAgeGroup());
            ps.setString(7, player.getEmail());
            ps.setInt(8, Integer.parseInt(player.getTeam()));
            ps.setInt(9, Integer.parseInt(player.getPosition()));
            ps.setString(10, player.getAddress());
            ps.setString(11, player.getPinCode());
            ps.setInt(12, Integer.parseInt(player.getCountry()));
            ps.setInt(13, Integer.parseInt(player.getState()));
            ps.setInt(14, Integer.parseInt(player.getCity()));

            ps.executeUpdate();

            return "updated";

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "failed";
        }
    }

}
