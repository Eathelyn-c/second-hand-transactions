package com.boda.secondhandtransaction.Controller;
import com.boda.secondhandtransaction.POJO.User;

import com.boda.secondhandtransaction.Services.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        int result = userService.login(username, password);
        if (result == 1) {
            int userId = UserService.getUserId(username);
            request.getSession().setAttribute("id", userId);
            request.getSession().setAttribute("username", username);
            request.getRequestDispatcher("secondhand.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "用户名或密码错误");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
