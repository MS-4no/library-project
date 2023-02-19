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


@WebServlet("/CustomerRegistServlet")
public class CustomerRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		  
		
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
		                gotoPage(request, response, "/regist.jsp");
		            } else if (action.equals("confirm")) {
		            	 // confirmは確認処理を行う 
		            	String name =request.getParameter("name");
		            	String tel =request.getParameter("tel");
		            	String address =request.getParameter("address");
		            	String email =request.getParameter("email");
		            	String pass =request.getParameter("pass");
		            	
		            	//未入力チェック
		            	if(name == null || address == null ||tel == null ||email == null 
		            			||pass == null || name.length() == 0 ||address.length() == 0 ||tel.length() == 0 ||
		            			email.length() == 0 ||pass.length() == 0 ) {
		            		showNotEnterdError(out);
		    				return;	
		    				
		            	}
		            	
		               
		                CustomerBean bean = new CustomerBean();
		                bean.setName(name);		                
		                bean.setTel(tel);
		                bean.setAddress(address);
		                bean.setEmail(email);
		                bean.setPass(pass);
		                
		                session.setAttribute("customer", bean);
		                
		                gotoPage(request, response, "/registConfirm.jsp");
		                
		            } else if (action.equals("order")) {
		                 // orderは内容確定
		                CustomerBean customer = 
		                         (CustomerBean)session.getAttribute("customer");
		                if (customer == null) { // 顧客情報がない
		                    request.setAttribute("message", "正しく操作してください。");
		                    gotoPage(request, response, "/errInternal.jsp");
		                    return;
		                }

		                CustomerDAO order = new CustomerDAO();
		                int customerNumber = order.registCustomer(customer);
		                // 確定後はセッション情報をクリアする
		                session.removeAttribute("customer");
		                // ユーザーIDを返す
		                request.setAttribute("customerNumber", Integer.valueOf(customerNumber));
		                gotoPage(request, response, "/registOrder.jsp");
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
