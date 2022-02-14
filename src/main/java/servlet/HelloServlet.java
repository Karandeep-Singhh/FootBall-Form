package servlet;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/hello"}
    )
public class HelloServlet extends HttpServlet {

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

        String result = register.insert(player);
        player.setStatus(result);
        System.out.println(result);
        String toBack = new Gson().toJson(player);


        out.print(toBack);
        out.flush();


    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.getWriter().println("here");
        System.out.println("hitt on get");
        doPost(req, resp);
    }
    
}
