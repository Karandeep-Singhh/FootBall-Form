package footballFunctionality.funtionality;

import com.google.gson.Gson;
//import footballFunctionality.FootBallDao;
//import footballFunctionality.Player;
//import footballFunctionality.Validation;
import footballFunctionality.dao.FootBallRegistrationDAO;
import footballFunctionality.model.PlayerInfo;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FootBallOperation {

    private static PrintWriter out = null;
    private static Gson gson = null;
    private static FootBallRegistrationDAO dbOperation = null;
    private static String json = null;
    private static String result = null;

    /**
     * Performs operation upon POST requests
     * @param inputStream : brings the inputstream data
     * @param resp : usual response object
     * @throws IOException
     */
    public static void onPost(ServletInputStream inputStream, HttpServletResponse resp) throws IOException
    {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(inputStream));

        //--PrintWriter Object--
        out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        json = "";

        if(br != null)
        {
            json = br.readLine();
        }
        else
        {

        }


        gson = new Gson();
        PlayerInfo player = gson.fromJson(json, PlayerInfo.class);

        dbOperation = new FootBallRegistrationDAO();
        result = "failed";

        // if the fields data passes validations
        if(Validation.isValid(player))
        {
            // Checks if user/player exists in database to ensure a new entry
            if (dbOperation.checkUserExists(player))
            {
                result = "userexists";
            }
            result = dbOperation.registerNewPlayer(player);
        }

        player.setStatus(result);
        out.print(new Gson().toJson(player));
        out.flush();

    }

    /**
     * Performs operation upon GET requests
     * @param req
     * @param resp
     * @throws IOException
     */
    public static void onGet(String req, HttpServletResponse resp) throws IOException
    {

        json = req;

        gson = new Gson();
        PlayerInfo player =new PlayerInfo();
        player.setUserName(json);

        dbOperation = new FootBallRegistrationDAO();

        //--PrintWriter Object--
        out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Checks if user/player exists in database to retreive data
        if(dbOperation.checkUserExists(player))
        {
            PlayerInfo registeredPlayer = dbOperation.retreivePlayer(player.getUserName());
            registeredPlayer.setUserCheck(true);

            result = new Gson().toJson(registeredPlayer);
            System.out.println("This registered Player "+result);
        }
        else
        {
            player.setUserCheck(false);
            result = new Gson().toJson(player);
            System.out.println("this retreive servlet :" + result);
        }

        out.print(result);
        out.flush();

    }

    /**
     * Performs operation upon PUT requests
     * @param inputStream
     * @param resp
     * @throws IOException
     */
    public static void onPut(ServletInputStream inputStream, HttpServletResponse resp) throws IOException
    {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(inputStream));

        //--PrintWriter Object--
        out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        json = "";

        if(br != null)
        {
            json = br.readLine();
        }
        else
        {
            out.print("failed");
            return;
        }

        gson = new Gson();
        PlayerInfo player = gson.fromJson(json, PlayerInfo.class);

        dbOperation = new FootBallRegistrationDAO();

        result = "failed";

        if(Validation.isValid(player))
        {
            if (dbOperation.checkUserExists(player))
            {
                result = dbOperation.updateData(player);
                System.out.println(result);
            }
            else
            {
                result = "userexists";
            }
        }

        player.setStatus(result);
        out.print(new Gson().toJson(player));
        out.flush();
    }
}
