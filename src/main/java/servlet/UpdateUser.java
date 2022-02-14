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
        name = "UpdateServlet",
        urlPatterns = {"/updation"}
)
public class UpdateUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
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
//        System.out.println(player.getEmail());
        FootBallDao update = new FootBallDao();

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");


        String result = update.updateData(player);
        player.setStatus(result);
        System.out.println(result);
        String toBack = new Gson().toJson(player);


        out.print(toBack);
        out.flush();
    }
}
