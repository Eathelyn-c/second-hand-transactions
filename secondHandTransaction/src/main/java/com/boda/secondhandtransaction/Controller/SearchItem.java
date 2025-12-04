package com.boda.secondhandtransaction.Controller;

import com.boda.secondhandtransaction.POJO.Item;
import com.boda.secondhandtransaction.Services.ItemsService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.util.List;

@WebServlet("/searchItem")
public class SearchItem extends HttpServlet {
    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException, jakarta.servlet.ServletException{
        Integer id=(Integer) request.getSession().getAttribute("id");
        if(id==null){
            response.sendRedirect("login.jsp");
            return;
        }
        String keyWord = request.getParameter("keyWord");
        ItemsService itemsService = new ItemsService();
        List<Item> items = itemsService.queryItem(keyWord);
        if(items != null){
            request.setAttribute("items", items);
        }
        request.getRequestDispatcher("secondhand.jsp").forward(request, response);
    }
}
