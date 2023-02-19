package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;


@WebServlet("/ItemInfoServlet")
public class ItemInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		  
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		        request.setCharacterEncoding("UTF-8");
		        // ログイン後はセッションが存在する

		        try {
		            // パラメータの解析
		            String action = request.getParameter("action");
		            if (action.equals("regist")) {
		            	 // confirmは確認処理を行う 
		            	String name =request.getParameter("name");
		            	String author =request.getParameter("author");
		            	String publisher =request.getParameter("publisher");
		            	String image =request.getParameter("image");
		            	
		            	//未入力チェック
		            	if(name == null || author == null ||publisher == null 
		            			||image == null || name.length() == 0 ||author.length() == 0  ||
		            			publisher.length() == 0 ||image.length() == 0 ) {
		            		showNotEnterdError(out);
		    				return;	
		    				
		            	}
		            	
		               ItemDAO dao = new ItemDAO();
		                int a = dao.registItem(name, author, publisher, image);
		                request.setAttribute("code", a);
		                request.setAttribute("author", author);
		                request.setAttribute("publisher", publisher);
		                request.setAttribute("name", name);
		                request.setAttribute("image", image);
		                request.setAttribute("message","登録");
		                gotoPage(request, response, "/registResult.jsp");
		            } else if (action.equals("register")) {
		            	 request.setAttribute("key",2);
			                gotoPage(request, response, "/addItem.jsp");
		                
		            } else if (action.equals("update")) {
		                 // orderは内容確定
		                int code = Integer.parseInt(request.getParameter("item_code"));
		                ItemDAO dao = new ItemDAO();
		                ItemBean bean = dao.findByPrimaryKey(code);
		                request.setAttribute("name", bean.getName());
		                request.setAttribute("author", bean.getAuthor());
		                request.setAttribute("publisher", bean.getPublisher());
		                request.setAttribute("image", bean.getImg());
		                request.setAttribute("code", code);
		                
		                request.setAttribute("key", 1);
		                gotoPage(request, response, "/addItem.jsp");
		            } else if(action.equals("decide")) {
		            	 // confirmは確認処理を行う 
		            	int code = Integer.parseInt(request.getParameter("code"));
		            	String name =request.getParameter("name");
		            	String author =request.getParameter("author");
		            	String publisher =request.getParameter("publisher");
		            	String image =request.getParameter("image");
		            	
		            	//未入力チェック
		            	if(name == null || author == null ||publisher == null 
		            			||image == null || name.length() == 0 ||author.length() == 0  ||
		            			publisher.length() == 0 ||image.length() == 0 ) {
		            		showNotEnterdError(out);
		    				return;	
		    				
		            	}
		            	
		               ItemDAO dao = new ItemDAO();
		               dao.update(code, name, author, publisher, image);
		               request.setAttribute("code", code);
		                request.setAttribute("author", author);
		                request.setAttribute("publisher", publisher);
		                request.setAttribute("name", name);
		                request.setAttribute("image", image);
		                request.setAttribute("message","更新");
		                gotoPage(request, response, "/registResult.jsp");
		            } else if(action.equals("delete")) {
		            	int code = Integer.parseInt(request.getParameter("item_code"));
		            	String name =request.getParameter("name");
		            	ItemDAO dao = new ItemDAO();
		            	dao.delete(code);
		            	request.setAttribute("name", name);
		            	request.setAttribute("code",code );
		                gotoPage(request, response, "/deleteItem.jsp");
		            	
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
