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
        name = "UserChecker",
        urlPatterns = {"/userCheck"}
)
public class UserAvailability extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(req.getInputStream()));
//        System.out.println("hitt");
        String json = "";
        if(br != null){
            json = br.readLine();
            System.out.println("available : "+json);
        } else{
            System.out.println("br is null");
        }
        Gson gson = new Gson();
        Player player = gson.fromJson(json, Player.class);
//        System.out.println(player.getEmail());
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String userResponse = "none";

        if(userChecker(player)){
            player.setUserCheck(true);
            userResponse = new Gson().toJson(player);
            System.out.println("this availability :" + userResponse);
            out.print(userResponse);
            out.flush();
        } else {
            player.setUserCheck(false);
            userResponse = new Gson().toJson(player);
            System.out.println("this availability :" + userResponse);
            out.print(userResponse);
            out.flush();
        }

    }

    public static boolean userChecker(Player player) {
        FootBallDao register = new FootBallDao();

        if(register.checkUser(player)){
            return true;
        }
        return false;
    }
}
