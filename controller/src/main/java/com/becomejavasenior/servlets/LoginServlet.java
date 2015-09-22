package com.becomejavasenior.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.becomejavasenior.DaoManager;
import com.becomejavasenior.User;
import com.becomejavasenior.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Authentication Servlet
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/logout"}, loadOnStartup = 0)
public class LoginServlet extends PersistServlet {
    protected static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("logout") != null) {
            logger.debug("logout action");
            HttpSession session = req.getSession(false);
            if(session != null) {
                session.invalidate();
            }
            req.setAttribute("errorMessage", "You have been logged out!");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        } else {
            logger.debug("login action");
            RequestDispatcher requestDispatcher = null;

            String login = req.getParameter("input-login");
            String password = req.getParameter("input-password");

            if (login == null || password == null) {
                req.setAttribute("errorMessage", "Empty login / password!");
                requestDispatcher = req.getRequestDispatcher("/jsp/login.jsp");
            } else if (!login.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._-]+\\.[a-z]{2,3}$") || !password.matches("[A-Za-z_0-9]{4,16}")) {
                req.setAttribute("errorMessage", "Wrong login / password!");
                requestDispatcher = req.getRequestDispatcher("/jsp/login.jsp");
            } else {
                HttpSession session = req.getSession();
                DaoManager daoManager = DaoManager.getInstance();
                UserDAO userDAO = daoManager.getUserDAO();
                List<User> users = (List<User>) userDAO.getRange(0, 100);
                for (User u : users) {
                    if (u.getEmail().equals(login) && u.getPassword().equals(password)) {
                        session.setAttribute("activeUser", u);
                        session.setMaxInactiveInterval(30*60);
                        req.setAttribute("loginMessage", u.getUserName() + " - you have been successfully logged in!");
                        requestDispatcher = req.getRequestDispatcher("/jsp/index.jsp");
                        break;
                    }
                }
                if (session.getAttribute("activeUser") == null) {
                    req.setAttribute("errorMessage", "Wrong login / password!");
                    requestDispatcher = req.getRequestDispatcher("/jsp/login.jsp");
                }
            }
            requestDispatcher.forward(req, resp);
        }
    }
}
