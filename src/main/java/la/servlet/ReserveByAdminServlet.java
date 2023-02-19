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

import la.dao.DAOException;
import la.dao.OrderDAO;

@WebServlet("/ReserveByAdminServlet")
public class ReserveByAdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null) { // セッションオブジェクトなし
            request.setAttribute("message",
                "セッションが切れています。もう一度トップページより操作してください。");
            gotoPage(request, response, "/errInternal.jsp");
            return;
        }
             String action = request.getParameter("action");
             int itemCode = 0;
        	if(action.equals("reserve")) {
        		String status = request.getParameter("status");
            	if(!status.equals("予約可")) {
            		request.setAttribute("message",
                            "この商品は現在予約できません。");
                        gotoPage(request, response, "/errInternal.jsp");
                        return;
            	}
        		
        		itemCode = Integer.parseInt(request.getParameter("item_code"));
        		request.setAttribute("item_code", itemCode);
        		gotoPage(request, response, "/reserve.jsp");
        		return;
        	}else if(action.equals("decide")){
        		int customerCode = Integer.parseInt(request.getParameter("id"));
        		
        		try {
        			
        			String strYear = request.getParameter("year");
        			String strMonth = request.getParameter("month");
        			String strDate = request.getParameter("day");
        			itemCode = Integer.parseInt(request.getParameter("item_code"));
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
        			
        			OrderDAO order = new OrderDAO(); 
        			long a = order.calcDate(year, month, date);
                        
                    if( a > 7 || a < 0) {
                    	request.setAttribute("message", "予定日は一週間以内の日にちを入力してください");
        				gotoPage(request, response, "/errInternal.jsp");
                    }
                    
                    LocalDate d = LocalDate.of(year, month, date);
                    Date reserveDate = Date.valueOf(d);
                    
                    int orderNumber = order.reserveByAdmin(customerCode, itemCode,reserveDate);
                   
                    request.setAttribute("customerID", customerCode);
                    request.setAttribute("orderNumber", orderNumber);
                    gotoPage(request, response, "/orderByAdmin.jsp");
                 
                    
            } catch (DAOException e) {
                e.printStackTrace();
                request.setAttribute("message", "内部エラーが発生しました。");
                gotoPage(request, response, "/errInternal.jsp");
        		
            }
        	request.setAttribute("message", "正しく操作してください。");
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