package com.becomejavasenior.servlets;

import com.becomejavasenior.DaoManager;
import com.becomejavasenior.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Dmytro Tsapko on 9/11/2015.
 */
public class TestServlet extends PersistServlet {

    @Override
    protected void doGetInPersistentCtx(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoManager dm = DaoManager.getInstance( );
//        PrintWriter writer = resp.getWriter();
        File file = dm.getFileDAO().getById(4);
        java.io.File myFile = new java.io.File("C:\\Users\\user\\Downloads\\RTS_Sample_Dashboard.png");
        FileInputStream fis = new FileInputStream(myFile);
        byte[] temp = null;
        ArrayList<Byte> data = new ArrayList<>();
        int c;
        while((c = fis.read()) > -1){
            data.add((byte)c);
        }
        temp = new byte[data.size()];
        for (int i = 0; i < data.size(); i++) {
            Byte b = data.get(i);
            temp[i] = b;

        }
        file.setContent(temp);
        file.setMimeType("image/png");
        file.setName(null);
        dm.getFileDAO().insertOrUpdate(file);

        resp.setContentType(file.getMimeType());
            OutputStream os = resp.getOutputStream();
        os.write(file.getContent());

    }
}
