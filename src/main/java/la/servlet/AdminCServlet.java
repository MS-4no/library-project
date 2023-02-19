package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CustomerBean;
import la.dao.CustomerDAO;
import la.dao.DAOException;

@WebServlet("/AdminCServlet")
public class AdminCServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null) { // セッションオブジェクトなし
            request.setAttribute("message",
                "セッションが切れています。もう一度トップページより操作してください。");
            gotoPage(request, response, "/errInternal.jsp");
            return;
        }
        

        try {
            String action = request.getParameter("action");
            if (action == null) {
            	request.setAttribute("message", "正しく操作してください。");
                gotoPage(request, response, "/errInternal.jsp");
                return;
            } else if (action.equals("IDSearch")) {
            	CustomerDAO dao = new CustomerDAO();
            	String  strid = request.getParameter("id");
            	if(strid == null || strid.length() == 0 ) {
            		showNotEnterdError(out);
    				return;
            	}
            	int id = Integer.parseInt(strid);
                CustomerBean customer = dao.findByID(id);
                
    				
            	
                request.setAttribute("customers", customer);
                request.setAttribute("test", 1);
                gotoPage(request, response, "/userSearch.jsp");
            } else if (action.equals("nameSearch")) {
            	CustomerDAO dao = new CustomerDAO();
                List<CustomerBean> list = dao.findByName(request.getParameter("name"));
                request.setAttribute("customers", list);
                gotoPage(request, response, "/userSearch.jsp");
            } else if (action.equals("telSearch")) {
            	CustomerDAO dao = new CustomerDAO();
                List<CustomerBean> list = dao.findByTel(request.getParameter("tel"));
                request.setAttribute("customers", list);
                gotoPage(request, response, "/userSearch.jsp");
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

    private void showNotEnterdError(PrintWriter out) {
		out.println("<html><head><title>Plus</title></head><body>");
		out.println("<h1>IDを入力してください</h1>");
		out.println("</body></html>");
		
	}
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}