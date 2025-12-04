package com.boda.secondhandtransaction.Controller;

import com.boda.secondhandtransaction.Services.ItemsService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/deleteItem")
public class DeleteItemServlet extends HttpServlet {
    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws java.io.IOException, jakarta.servlet.ServletException{
        Integer id = (Integer)request.getSession().getAttribute("id");
        if(id==null){
            response.sendRedirect("secondhand.jsp");
            return;
        }
        //获取想要删除的商品id
        String itemId = request.getParameter("id");
        if(itemId==null || itemId.isEmpty()){
            response.sendRedirect("secondhand.jsp");
            return;
        }
        try{
            int itemIdInt = Integer.parseInt(itemId);
            ItemsService itemsService = new ItemsService();
            int result = itemsService.deleteItemById(itemIdInt);
            if(result>0) {
                request.setAttribute("success", "删除成功");
                request.getRequestDispatcher("itemDetail").forward(request, response);
            }else{
                request.setAttribute("error", "删除失败");
                request.getRequestDispatcher("itemDetail").forward(request, response);
            }
        }catch (NumberFormatException e){
            request.setAttribute("error", "无效的物品id");
            request.getRequestDispatcher("itemDetail").forward(request, response);
        }
    }
}
