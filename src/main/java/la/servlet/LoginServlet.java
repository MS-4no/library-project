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


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		
		//ログイン処理
		if(action.equals("login")) { 
			try{
				int code = Integer.parseInt(request.getParameter("code"));
				String passWord = request.getParameter("pw");
						
			try {
			//*ユーザー情報をDBから取り出しCustomerBeanに入れる
			  CustomerDAO customer = new CustomerDAO();
			  CustomerBean bean = new CustomerBean();
			  bean = customer.findCustomer(code);
			
			  if(bean != null) {
					
			  
				  String CustomerPass = bean.getPass();
			 
			  
			 	if(passWord.equals(CustomerPass)) {
				//＊beanのパスワードと入力されたpwが一致したら) { //＊USER,PASS変更
					
				//ユーザーのsession領域取得
				HttpSession session = request.getSession(); 
				//trueを"isLogin"という名前で登録(ログインした目印→AddCart)
				session.setAttribute("isLogin", "true");
				//ユーザー情報を登録
				session.setAttribute("customerInfo", bean); //ユーザー情報＝「ustomerInfo」
				
				
				//表示
//				out.println("<html><head><title>ShowCart</title></head><body>");
//				out.println("<h1>ログイン成功！</h1>");
//				out.println("</body></html>");
				
				
				//管理者とユーザーで画面分岐
					if(code == 0) {
						gotoPage(request, response, "/admin.jsp");//管理者画面
					}else {
						gotoPage(request, response, "/top.jsp");//トップページ
				
					}
				
			 	}else {
			 		//表示
			 		out.println("<html><head><title>ShowCart</title></head><body>");
			 		out.println("<a href=\"/library/top.jsp\">TOP</a>");
			 		out.println("<h1>パスワードが違います</h1>");
			 		out.println("</body></html>");
				 }
			 	
			  }else {
				//表示
				out.println("<html><head><title>ShowCart</title></head><body>");
				out.println("<h1>ユーザー名が違います</h1>");
				out.println("</body></html>");
			  }
				
	  
			} catch (DAOException e) {
	            e.printStackTrace();
	            request.setAttribute("message", "内部エラーが発生しました。");
	            gotoPage(request, response, "/errInternal.jsp");
			}
			
		}catch(NumberFormatException e) {
				 e.printStackTrace();
		         request.setAttribute("message", "正しい値を入力してください");
		         gotoPage(request, response, "/errInternal.jsp");
		}
		
			
		//ログアウト処理
		}else if(action.equals("logout")){
			
			//ユーザーjackのsession領域からfalse呼び出し
			HttpSession session = request.getSession(false);
			
			if (session !=null) { 
				session.invalidate(); //セッション領域を無効化する
				out.println("<html><head><title>ShowCart</title></head><body>");
				out.println("<a href=\"/library/top.jsp\">TOP</a>");
				out.println("<h1>ログアウトしました</h1>");
				out.println("</body></html>");
			}
			
		}
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
