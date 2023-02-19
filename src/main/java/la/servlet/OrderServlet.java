package la.servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CartBean;
import la.bean.CustomerBean;
import la.dao.DAOException;
import la.dao.OrderDAO;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 注文処理の業務はすべてセッションとCartが存在することが前提
        HttpSession session = request.getSession(false);
        if (session == null) { // セッションオブジェクトなし（ログイン前）
            request.setAttribute("message",
                "まだログインしていません。ログインから操作してください。");
            gotoPage(request, response, "/errInternal.jsp");
            return;
        
        }
        CartBean cart = (CartBean)session.getAttribute("cart");
        if (cart == null) { // カートがない
            request.setAttribute("message", "正しく操作してください1。");
            gotoPage(request, response, "/errInternal.jsp");
            return;
        }

       
                CustomerBean customer = 
                         (CustomerBean)session.getAttribute("customerInfo");
                if (customer == null) { // 顧客情報がない
                    request.setAttribute("message", "ログインしてください");
                    gotoPage(request, response, "/errInternal.jsp");
                    return;
                }
                String strYear = request.getParameter("year");
    			String strMonth = request.getParameter("month");
    			String strDate = request.getParameter("day");
    			if(strYear == null || strYear.length() == 0 
    					|| strMonth == null || strMonth.length() == 0 
    					|| strDate == null || strDate.length() == 0) {
    				request.setAttribute("message", "貸出予定日を入力してください");
    				gotoPage(request, response, "/errInternal.jsp");
    				return;
    			}
    			int year,month,date;
    			try {
    				year = Integer.parseInt(strYear);
    				month = Integer.parseInt(strMonth);
    				date = Integer.parseInt(strDate);
    				
    				
    			}catch(NumberFormatException e) {
    				request.setAttribute("message", "予定日は数値を入力してください");
    				gotoPage(request, response, "/errInternal.jsp");
    				return;
    			}
    			
    			if(year < 1 || year > 3000 || month < 1 || month > 12 || date < 1 || date > 31 
    					|| (((month==4 ||month==6 ||month==9 ||month==11)&&date==31)) 
    					||(month==2 && date>29)){
    				request.setAttribute("message", "予定日は正しい数値を入力してください");
    				gotoPage(request, response, "/errInternal.jsp");
    				return;
    			}
                try {
                OrderDAO order = new OrderDAO();
                long a = order.calcDate(year, month, date);
                
                if( a > 7 || a < 0) {
                	request.setAttribute("message", "予定日は一週間以内の日にちを入力してください");
    				gotoPage(request, response, "/errInternal.jsp");
    				return;
                }
                
                LocalDate d = LocalDate.of(year, month, date);
                Date reserveDate = Date.valueOf(d);
                
                
                int orderNumber = order.saveOrder(customer, cart,reserveDate);
                // 注文後はセッション情報をクリアする
                session.removeAttribute("cart");
                session.removeAttribute("customer");
                // 注文番号をクライアントへ送る
                request.setAttribute("orderNumber", Integer.valueOf(orderNumber));
                gotoPage(request, response, "/order.jsp");
             
                // actionの値が不正
                
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