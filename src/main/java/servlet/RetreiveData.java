package servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(
        name = "RetreiveUser",
        urlPatterns = {"/retreival"}
)
public class RetreiveData extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(req.getInputStream()));
//        System.out.println("hitt");
        String json = "";
        if(br != null){
            json = br.readLine();
            System.out.println("this retrieve json : "+json);
        } else{
            System.out.println("br is null");
        }
        Gson gson = new Gson();
        Player player = gson.fromJson(json, Player.class);
//        System.out.println(player.getEmail());

        FootBallDao retreive = new FootBallDao();

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if(UserAvailability.userChecker(player)){
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

}
