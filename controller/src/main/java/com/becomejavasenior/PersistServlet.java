package com.becomejavasenior;

import com.becomejavasenior.jdbc.DaoManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmytro Tsapko on 9/11/2015.
 */
public class PersistServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        DaoManager daoManager = DaoManager.getInstance();

        doGetInPersistentCtx(req, resp);

        daoManager.closeConnection();
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        DaoManager daoManager = DaoManager.getInstance();

        doHeadInPersistentCtx(req, resp);

        daoManager.closeConnection();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        DaoManager daoManager = DaoManager.getInstance();

        doPostInPersistentCtx(req, resp);

        daoManager.closeConnection();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        DaoManager daoManager = DaoManager.getInstance();

        doPutInPersistentCtx(req, resp);

        daoManager.closeConnection();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        DaoManager daoManager = DaoManager.getInstance();

        doDeleteInPersistentCtx(req, resp );

        daoManager.closeConnection();
    }

    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {}

    protected void doHeadInPersistentCtx(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {}

    protected void doPostInPersistentCtx(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {}

    protected void doPutInPersistentCtx(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {}

    protected void doDeleteInPersistentCtx(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {}
}
