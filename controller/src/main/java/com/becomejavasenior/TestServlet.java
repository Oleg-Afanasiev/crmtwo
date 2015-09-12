package com.becomejavasenior;

import com.becomejavasenior.impl.DealImpl;
import com.becomejavasenior.impl.jdbc.DaoManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmytro Tsapko on 9/11/2015.
 */
public class TestServlet extends PersistServlet {

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoManager dm = DaoManager.getInstance( );
        Deal d = dm.getDealDAO().getById(10);
        Deal newDeal = new DealImpl();
        System.out.println(d);
        resp.getWriter().print(d);
    }
}
