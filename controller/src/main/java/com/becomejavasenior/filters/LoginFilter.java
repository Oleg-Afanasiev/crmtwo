package com.becomejavasenior.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.becomejavasenior.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Authentication Filter
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

@WebFilter(filterName = "LoginFilter",
        urlPatterns = {"/jsp/*", "/crm/*"},
        initParams = @WebInitParam(name = "loginActionURI", value = "/login"))
public class LoginFilter implements Filter {
    private String LOGIN_ACTION_URI;
    protected static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    public void init(FilterConfig fConfig) throws ServletException {
        LOGIN_ACTION_URI = fConfig.getInitParameter("loginActionURI");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");

        if (user == null && !LOGIN_ACTION_URI.equals(req.getRequestURI())){
            logger.debug("Unauthorized access request");
            RequestDispatcher rd = req.getRequestDispatcher("/jsp/login.jsp");
            rd.forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
