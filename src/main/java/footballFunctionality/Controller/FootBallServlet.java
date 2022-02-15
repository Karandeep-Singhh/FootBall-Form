package footballFunctionality.Controller;

import com.google.gson.Gson;
import footballFunctionality.funtionality.FootBallOperation;

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
        name = "MyServlet", 
        urlPatterns = {"/goal"}
    )
public class FootBallServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        FootBallOperation.onPost(req.getInputStream(),resp);

    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        FootBallOperation.onGet(req.getParameter("userName"),resp);

    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        FootBallOperation.onPut(req.getInputStream(),resp);

    }

}