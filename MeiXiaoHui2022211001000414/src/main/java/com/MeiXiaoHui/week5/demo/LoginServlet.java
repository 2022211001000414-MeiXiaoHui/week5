package com.MeiXiaoHui.week5.demo;

import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    Connection con=null;

    @Override
    public void init() {
        String driver = getServletContext().getInitParameter("driver");
        String url = getServletContext().getInitParameter("url");
        String username = getServletContext().getInitParameter("username");
        String password = getServletContext().getInitParameter("password");

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username1 = request.getParameter("username");
        String password1 = request.getParameter("password");
        String sql = "select * from usertable where username=? and password=?";
        try (PreparedStatement pstmt =con.prepareStatement(sql)){
            pstmt.setString(1,username1);
            pstmt.setString(2,password1);
            ResultSet rs=pstmt.executeQuery();
            PrintWriter writer = response.getWriter();
//            ResultSet rs = con.createStatement().executeQuery(sql);
            if(rs.next()) {
                String Username = rs.getString("username");
                String Password = rs.getString("password");
                if(username1.equals(Username) && password1.equals(Password)){
                    writer.println("Login Success!!!");
                    writer.println("Welcome,"+username1);
                }
            } else {
                writer.println("Username or Password Error!!!"); }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
