package com.becomejavasenior;

import com.becomejavasenior.impl.DealImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * Created by Dmytro Tsapko on 9/11/2015.
 */
public class TestServlet extends PersistServlet {

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoManager dm = DaoManager.getInstance( );
        PrintWriter writer = resp.getWriter();
        writer.println(dm.getCommentDAO().getById(1));
        writer.println(dm.getCompanyDAO().getById(1));
        writer.println(dm.getContactDAO().getById(1));
        writer.println(dm.getDealDAO().getById(1));
        writer.println(dm.getDealStatusDAO().getById(1));
        writer.println(dm.getFileDAO().getById(1));
        writer.println(dm.getPhoneDAO().getById(1));
        writer.println(dm.getRoleDAO().getById(1));
        writer.println(dm.getTagDAO().getById(1));
        writer.println(dm.getTaskDAO().getById(1));
        writer.println(dm.getTaskPeriodDAO().getById(1));
        writer.println(dm.getTaskTypeDAO().getById(1));
        writer.println(dm.getUserDAO().getById(1));
    }
}
