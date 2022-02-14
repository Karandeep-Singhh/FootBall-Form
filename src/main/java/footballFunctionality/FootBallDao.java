package footballFunctionality;

import java.sql.*;

public class FootBallDao {
    private Connection con = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String dburl = "jdbc:sqlserver://localhost:1433;databaseName=Football;trustServerCertificate=true;";
    private String dbusrname = "sa";
    private String dbpassword = "963369";
    private String dbdriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public void loadDriver(String dbDriver) {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public Connection getConnection() {

        try {
            loadDriver(dbdriver);
            con = DriverManager.getConnection(dburl, dbusrname, dbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error message is :- "+e.getMessage());

        }
        return con;
    }

    public boolean checkUser(Player player){
        con = getConnection();
        try {
            statement = con.createStatement();
            String sql = "SELECT username FROM FootballPlayers WHERE username = '"+player.getUserName()+"'";
            resultSet = statement.executeQuery(sql);
//            System.out.println("hitt here");
            while(resultSet.next()){
                if(resultSet.getString("username").equals(player.getUserName())){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String insert(Player player) {
//		loadDriver(dbdriver);
        con = getConnection();
        String result = "success";

        if(!checkUser(player)) {

            String sql = "INSERT INTO FootballPlayers values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try {
                PreparedStatement ps = con.prepareStatement(sql);
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
            } catch (SQLException e) {
                e.printStackTrace();
                result = "Operation Failed";
            }
        }
        else{
            result = "userexists";
        }

        return result;

    }

    public Player retreivePlayer(String referedUser){
        con = getConnection();
        Player player = new Player();
        String query = "SELECT * FROM FootballPlayers WHERE username = '"+referedUser+"'";

        try{
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
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

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("exception at retreival");
        }

        return player;
    }

    public String updateData(Player player){
        con = getConnection();
        String result = "failed";

        try{
            String sql = "UPDATE FootballPlayers " +
                    "SET username = ?,firstname=?,lastname=?,phonecode =?,phone=?,age=?,email=?,team=?,position=?,paddress=?,pin=?,country=?,states=?,city=?" +
                    " WHERE username = '" + player.getUserName() + "'";
            System.out.println(player.getPosition()+" before update");
            PreparedStatement ps = con.prepareStatement(sql);
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
            result = "updated";

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
