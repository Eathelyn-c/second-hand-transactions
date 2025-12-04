package com.boda.secondhandtransaction.Controller;

import com.boda.secondhandtransaction.Services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        UserService userService = new UserService();
        int confirm = userService.confirmPassword(password, confirmPassword);
        if (confirm == 0) {
            request.setAttribute("error", "密码不一致");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        int result = userService.register(username, password);
        if (result == 1) {
            request.getSession().setAttribute("id", UserService.getUserId(username));
            request.getSession().setAttribute("username", username);
            request.setAttribute("success", "注册成功");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "注册失败");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
