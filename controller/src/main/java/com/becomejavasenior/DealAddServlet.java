package com.becomejavasenior;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by oleg on 9/17/15.
 */
public class DealAddServlet extends HttpServlet {
    private DaoManager daoManager;

    @Override
    public void init() throws ServletException {
        super.init();
        daoManager = DaoManager.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("users", getAllUsers());
        } catch(DAOException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("dealAdd.jsp").forward(request, response);
    }

    private List<UserDAO> getAllUsers() throws DAOException {
        UserDAO userDAO = daoManager.getUserDAO();
        return null;
    }
}
