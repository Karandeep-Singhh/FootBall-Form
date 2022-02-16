package footballFunctionality.Controller;

import footballFunctionality.funtionality.FootBallOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/goal"}
    )
public class FootBallServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        FootBallOperation.onPost(req.getInputStream(),resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        FootBallOperation.onGet(req.getParameter("userName"),resp);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        FootBallOperation.onPut(req.getInputStream(),resp);

    }

}
