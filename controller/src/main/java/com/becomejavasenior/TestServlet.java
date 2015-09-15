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
        User user = dm.getUserDAO().getById(2);
        writer.print("<html> <body>");

            writer.print(user + " <br /> ");
            writer.print(user.getRole() + " <br /> ");
            writer.print(user.getId() + " <br /> ");

        writer.print("</body> </html> ");
    }
}
