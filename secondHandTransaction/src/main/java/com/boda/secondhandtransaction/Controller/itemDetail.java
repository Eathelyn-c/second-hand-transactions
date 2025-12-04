package com.boda.secondhandtransaction.Controller;

import com.boda.secondhandtransaction.POJO.Item;
import com.boda.secondhandtransaction.Services.ItemsService;
import com.boda.secondhandtransaction.Services.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.util.List;

@WebServlet("/itemDetail")
public class itemDetail extends HttpServlet {
    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException, jakarta.servlet.ServletException{
        Integer id=(Integer) request.getSession().getAttribute("id");
        if (id == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        ItemsService itemsService=new ItemsService();
        List<Item> searchItems = itemsService.queryItemBySellerId((Integer) request.getSession().getAttribute("id"));
        if(searchItems!=null){
            request.setAttribute("searchItems", searchItems);
        }
        request.getRequestDispatcher("itemDetail.jsp").forward(request, response);
    }
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException, jakarta.servlet.ServletException{
        doPost(request, response);
    }
}
