package com.zcc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * @Author zcc
 * @Date 2021/8/12 13:49
 * @Describe
 */
@WebServlet
public class SecKillServlet extends HttpServlet {
    public SecKillServlet() {
        super();
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String userId = new Random().nextInt(5000)+"";
        String prodid = req.getParameter("prodid");

        boolean isSuccess = SecKillRedis.doSecKill(userId,prodid);
        resp.getWriter().println(isSuccess);
    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doDelete(req, resp);
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPut(req, resp);
//    }
}
