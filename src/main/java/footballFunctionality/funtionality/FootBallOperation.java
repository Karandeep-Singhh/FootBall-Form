package footballFunctionality.funtionality;

import com.google.gson.Gson;
//import footballFunctionality.FootBallDao;
//import footballFunctionality.Player;
//import footballFunctionality.Validation;
import footballFunctionality.dao.FootBallDao;
import footballFunctionality.model.PlayerInfo;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FootBallOperation {


    public static void onPost(ServletInputStream inputStream, HttpServletResponse resp) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(inputStream));
//        System.out.println("hitt");
        String json = "";
        if(br != null){
            json = br.readLine();
            System.out.println("helloServlet : "+json);
        } else{
            System.out.println("br is null");
        }
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        PlayerInfo player = gson.fromJson(json, PlayerInfo.class);
//        System.out.println(player.getEmail());
        FootBallDao register = new FootBallDao();
        String result = "failed";

        if(Validation.isValid(player)) {
            if (!register.checkUser(player)) {
                result = register.insert(player);
                System.out.println(result);
            } else {
                result = "User already exist";
            }
        }
        player.setStatus(result);
        String toBack = new Gson().toJson(player);
        out.print(toBack);
        out.flush();
    }
    public static void onGet(String req, HttpServletResponse resp) throws IOException {
        String json = req;

        Gson gson = new Gson();
        PlayerInfo player =new PlayerInfo();
        player.setUserName(json);
        FootBallDao retreive = new FootBallDao();

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if(retreive.checkUser(player)){
            PlayerInfo registeredPlayer = retreive.retreivePlayer(player.getUserName());
            registeredPlayer.setUserCheck(true);
            String responsePlayer = new Gson().toJson(registeredPlayer);

            System.out.println("This registered Player "+responsePlayer);

            out.print(responsePlayer);
            out.flush();
        } else{
            player.setUserCheck(false);
            String userResponse = new Gson().toJson(player);
            System.out.println("this retreive servlet :" + userResponse);
            out.print(userResponse);
            out.flush();
        }
    }
    public static void onPut(ServletInputStream inputStream, HttpServletResponse resp) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(inputStream));
        String json = "";

        if(br != null){
            json = br.readLine();
            System.out.println("update request : "+json);
        } else{
            System.out.println("br is null");
        }

        Gson gson = new Gson();
        PlayerInfo player = gson.fromJson(json, PlayerInfo.class);

        FootBallDao update = new FootBallDao();

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String result = "failed";

        if(Validation.isValid(player)) {
            if (update.checkUser(player)) {
                result = update.updateData(player);
                System.out.println(result);
            } else {
                result = "User do not exist";
            }
        }
        player.setStatus(result);
        String toBack = new Gson().toJson(player);
        out.print(toBack);
        out.flush();
    }
}
