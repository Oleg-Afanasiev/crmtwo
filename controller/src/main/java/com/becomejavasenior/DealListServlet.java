package com.becomejavasenior;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * Created by oleg on 9/17/15.
 */
@WebServlet(name="DealListServlet", urlPatterns = "/deallist", loadOnStartup = 0)
public class DealListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("dealName", "newdeal1");
        } catch(DAOException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("dealList.jsp").forward(request, response);
    }
}
