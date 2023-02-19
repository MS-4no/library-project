package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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


@WebServlet("/CustomerUpdateByAdminServlet")
public class CustomerUpdateByAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		
		request.setCharacterEncoding("UTF-8");
        // ログイン後はセッションが存在する
        HttpSession session = request.getSession(false);
        if (session == null) { // セッションオブジェクトなし（ログイン前）
            request.setAttribute("message",
                "まだログインしていません。ログインから操作してください。");
            gotoPage(request, response, "/errInternal.jsp");
            return;
        }
       

        try {
            // パラメータの解析
            String action = request.getParameter("action");
            if (action == null || action.length() == 0
                    || action.equals("input_customer")) { 
                // input_customerまたはパラメータなしの場合は顧客情報入力ページを表示 
                gotoPage(request, response, "/update.jsp");
                
            } else if (action.equals("update")) {
            	int code =Integer.parseInt(request.getParameter("code"));
            	String name =request.getParameter("name");
            	String address =request.getParameter("address");
            	String tel =request.getParameter("tel");
            	String email =request.getParameter("email");
            	CustomerBean bean = new CustomerBean();
                bean.setCode(code);
                bean.setName(name);
                bean.setAddress(address);
                bean.setTel(tel);
                bean.setEmail(email);
                request.setAttribute("customer", bean);
                gotoPage(request, response, "/updateByAdmin.jsp");
            	return;
            }else if(action.equals("update_input")) {
            	int code =Integer.parseInt(request.getParameter("code"));
            	String name =request.getParameter("name");
            	String address =request.getParameter("address");
            	String tel =request.getParameter("tel");
            	String email =request.getParameter("email");
            	CustomerBean bean = new CustomerBean();
                bean.setCode(code);
                bean.setName(name);
                bean.setAddress(address);
                bean.setTel(tel);
                bean.setEmail(email);
            	if(name == null || address == null ||tel == null ||email == null 
            			 || name.length() == 0 ||address.length() == 0 ||tel.length() == 0 ||
            			email.length() == 0 ) {
            		showNotEnterdError(out);
    				return;	
            	
            	}	
            	CustomerDAO dao = new CustomerDAO();
            	int id = dao.updateCustomerByAdmin(bean);
            	request.setAttribute("id", id);
                gotoPage(request, response, "/updateCustomerByAdmin.jsp");
            	return;
            	
            }else if(action.equals("delete")){
            	int customerCode = Integer.parseInt(request.getParameter("code"));
            	CustomerDAO dao = new CustomerDAO();
            	dao.deleteCustomerByAdmin(customerCode);
            	request.setAttribute("id", customerCode);
                gotoPage(request, response, "/deleteCustomerByAdmin.jsp");
            	return;
            

            } else { 
                // actionの値が不正
                request.setAttribute("message", "正しく操作してください。");
                gotoPage(request, response, "/errInternal.jsp");
            }
            
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("message", "内部エラーが発生しました。");
            gotoPage(request, response, "/errInternal.jsp");
        }
    }

	
	private void showNotEnterdError(PrintWriter out) {
		out.println("<html><head><title>Plus</title></head><body>");
		out.println("<h1>フォーマットを全て入力してください</h1>");
		out.println("</body></html>");
		
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
	IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
