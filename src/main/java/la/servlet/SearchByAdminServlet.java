package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

@WebServlet("/SearchByAdminServlet")
public class SearchByAdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        try {
            // パラメータの解析
            String action = request.getParameter("action");
            if (action == null || action.length() == 0 || action.equals("top")) {
                // topまたはパラメータなしの場合はトップページを表示 
                gotoPage(request, response, "/searchByAdmin");
            } else if (action.equals("title")) {
                ItemDAO dao = new ItemDAO();
                List<ItemBean> list = dao.findByName(request.getParameter("title"));
               
                // Listをリクエストスコープに入れてJSPへフォーワードする
                request.setAttribute("items", list);
                gotoPage(request, response, "/searchByAdmin.jsp");
            } else if (action.equals("author")) {
                ItemDAO dao = new ItemDAO();
                List<ItemBean> list = dao.findByAuthor(request.getParameter("author"));
                // Listをリクエストスコープに入れてJSPへフォーワードする
                request.setAttribute("items", list);
                gotoPage(request, response, "/searchByAdmin.jsp");
            } else if (action.equals("publisher")) {
                ItemDAO dao = new ItemDAO();
                List<ItemBean> list = dao.findByPublisher(request.getParameter("publisher"));
                // Listをリクエストスコープに入れてJSPへフォーワードする
                request.setAttribute("items", list);
                gotoPage(request, response, "/searchByAdmin.jsp");
            } else {
                request.setAttribute("message", "正しく操作してください。");
                gotoPage(request, response, "/errInternal.jsp");
            }
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("message", "内部エラーが発生しました。");
            gotoPage(request, response, "/errInternal.jsp");
        }
    }

    private void gotoPage(HttpServletRequest request,
            HttpServletResponse response, String page) throws ServletException,
            IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

   
   

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}