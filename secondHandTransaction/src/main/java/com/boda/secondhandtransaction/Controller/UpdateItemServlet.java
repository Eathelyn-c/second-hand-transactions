package com.boda.secondhandtransaction.Controller;

import com.boda.secondhandtransaction.POJO.Item;
import com.boda.secondhandtransaction.Services.ItemsService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/updateItem")
public class UpdateItemServlet extends HttpServlet {
    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException, jakarta.servlet.ServletException{
        Integer id = (Integer)request.getSession().getAttribute("id");
        if(id==null){
            response.sendRedirect("login.jsp");
            return;
        }
        String itemId = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        if(itemId==null||itemId.equals("")||name==null||name.equals("")||description==null||description.equals("")||price==null||price.equals("")){
            request.setAttribute("error", "请填写完整");
            request.getRequestDispatcher("updateItem.jsp").forward(request, response);
            return;
        }
        try{
            int itemIdInt = Integer.parseInt(itemId);
            ItemsService itemsService = new ItemsService();
            int result = itemsService.updateItem(itemIdInt, name, description, price, id);
            if(result>0){
                request.setAttribute("success", "修改成功");
                request.getRequestDispatcher("itemDetail").forward(request, response);
            }else{
                request.setAttribute("error", "修改失败");
                request.getRequestDispatcher("itemDetail").forward(request, response);
            }
        }catch(NumberFormatException e){
            request.setAttribute("error", "修改失败,无效的物品id");
            request.getRequestDispatcher("itemDetail").forward(request, response);
        }
    }
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException, jakarta.servlet.ServletException{
        Integer id = (Integer)request.getSession().getAttribute("id");
        if(id==null){
            response.sendRedirect("login.jsp");
            return;
        }
        String itemId = request.getParameter("id");
        if(itemId==null||itemId.equals("")){
            response.sendRedirect("itemDetail");
            return;
        }
        try{
            int itemIdInt = Integer.parseInt(itemId);
            ItemsService itemsService = new ItemsService();
            Item item = itemsService.queryItemById(itemIdInt);
            if(item==null){
                response.sendRedirect("itemDetail?error=物品不存在");
                return;
            }
            request.setAttribute("item", item);
            request.getRequestDispatcher("updateItem.jsp").forward(request, response);
        }catch(NumberFormatException e){
            response.sendRedirect("itemDetail?error=无效的物品id");
        }
    }
}
