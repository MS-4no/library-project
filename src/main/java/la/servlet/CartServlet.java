package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CartBean;
import la.bean.CustomerBean;
import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, 
          HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	HttpSession session = request.getSession(false);
    	 CustomerBean c = (CustomerBean)session.getAttribute("customerInfo");
        if (c == null) { // セッションオブジェクトなし（ログイン前）
            request.setAttribute("message",
                "まだログインしていません。ログインから操作してください。");
            gotoPage(request, response, "/errInternal.jsp");
            return;
        }
    	
    	try {
            // パラメータの解析
            String action = request.getParameter("action");
            if (action == null || action.length() == 0 || action.equals("show")) {
            	 
                // showまたはパラメータなしの場合はカートページを表示
                gotoPage(request, response, "/cart.jsp");
            } else if (action.equals("add")) {
            	String status = request.getParameter("status");
            	if(!status.equals("予約可")) {
            		request.setAttribute("message",
                            "この商品は現在予約できません。");
                        gotoPage(request, response, "/errInternal.jsp");
                        return;
            	}
            	
                // addはカートに追加処理
                int code = Integer.parseInt(request.getParameter("item_code"));
                CartBean cart = (CartBean)session.getAttribute("cart");
                if (cart == null) { // 初めてのクライアントの場合はカートを作成する
                    cart = new CartBean();
                   
                    session.setAttribute("cart", cart);
                }
                // 商品コードの商品を取得する
                ItemDAO dao = new ItemDAO();
                ItemBean bean = dao.findByPrimaryKey(code);
                // カートに追加する
                cart.addCart(bean);
                
              gotoPage(request, response, "/cart.jsp");  //カートに追加後カートに戻る
            } else if (action.equals("delete")) {
                // deleteはカートから削除処理
                
                CartBean cart = (CartBean)session.getAttribute("cart");
                if (cart == null) { // カートがない
                    request.setAttribute("message", "正しく操作してください1。");
                    gotoPage(request, response, "/errInternal.jsp");
                    return;
                }
                int code = Integer.parseInt(request.getParameter("item_code"));
                cart.deleteCart(code);
                gotoPage(request, response, "/cart.jsp");
            } else if (action.equals("deleteAll")) {
                // deleteはカートから削除処理

                CartBean cart = (CartBean)session.getAttribute("cart");
                if (cart == null) { // カートがない
                    request.setAttribute("message", "正しく操作してください2。");
                    gotoPage(request, response, "/errInternal.jsp");
                    return;
                }
                cart.deleteCartAll();
                gotoPage(request, response, "/cart.jsp");
            } else { 
                //actionの値が不正
                request.setAttribute("message", "正しく操作してください3。");
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