package servlet;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/goal"}
    )
public class FootBall extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(req.getInputStream()));
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
        Player player = gson.fromJson(json, Player.class);
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//
        String json = req.getParameter("userName");

        Gson gson = new Gson();
        Player player =new Player();
        player.setUserName(json);
        FootBallDao retreive = new FootBallDao();

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if(retreive.checkUser(player)){
            Player registeredPlayer = retreive.retreivePlayer(player.getUserName());
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

    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";

        if(br != null){
            json = br.readLine();
            System.out.println("update request : "+json);
        } else{
            System.out.println("br is null");
        }

        Gson gson = new Gson();
        Player player = gson.fromJson(json, Player.class);

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
