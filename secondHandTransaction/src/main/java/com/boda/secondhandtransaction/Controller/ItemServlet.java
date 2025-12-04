package com.boda.secondhandtransaction.Controller;

import com.boda.secondhandtransaction.POJO.Item;
import com.boda.secondhandtransaction.Services.ItemsService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
//发布物品
@WebServlet("/item")
public class ItemServlet extends HttpServlet {
    @Override
    //发布物品
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException, jakarta.servlet.ServletException{
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        Integer sellerId = (Integer) request.getSession().getAttribute("id");
        if(sellerId==null){
            response.sendRedirect("login.jsp");
            return;
        }
        ItemsService itemsService = new ItemsService();
        int result = itemsService.insertItem(name, description,price,sellerId);
        if (result == 1) {
            request.setAttribute("success", "发布成功");
        } else {
            request.setAttribute("error", "发布失败");
        }
        request.getRequestDispatcher("secondhand.jsp").forward(request, response);
    }
}
