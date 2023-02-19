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
import la.bean.HistoryBean;
import la.bean.ReviewBean;
import la.dao.DAOException;
import la.dao.HistoryDAO;
import la.dao.ReviewDAO2;

@WebServlet("/AdminRServlet")
public class AdminRServlet extends HttpServlet {

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
            // パラメータの解析
            String action = request.getParameter("action");
            if (action == null) {
            	request.setAttribute("message", "正しく操作してください。");
                gotoPage(request, response, "/errInternal.jsp");
                return;
            } else if (action.equals("reserved")) {
            	HistoryDAO dao = new HistoryDAO();
            	List<HistoryBean> list = dao.findOrderedByStatus("予約済");
                request.setAttribute("histories", list);
                gotoPage(request, response, "/reservationConfirm.jsp");
            } else if (action.equals("checkedout")) {
            	HistoryDAO dao = new HistoryDAO();
            	List<HistoryBean> list = dao.findOrderedByStatus("貸出中");
                request.setAttribute("histories", list);
                gotoPage(request, response, "/reservationConfirm.jsp");
//            } else if (action.equals("all")) {
//            	HistoryDAO dao = new HistoryDAO();
//            	List<HistoryBean> list = dao.findOrderedAll();
//                request.setAttribute("histories", list);
//                gotoPage(request, response, "/reservationConfirm.jsp");
            } else if (action.equals("titleSearch")) {
            	HistoryDAO dao = new HistoryDAO();
            	List<HistoryBean> list = dao.findOrderedByName(request.getParameter("title"));
                request.setAttribute("histories", list);
                gotoPage(request, response, "/reservationConfirm.jsp");
            } else if (action.equals("authorSearch")) {
            	HistoryDAO dao = new HistoryDAO();
            	List<HistoryBean> list = dao.findOrderedByAuthor(request.getParameter("author"));
                request.setAttribute("histories", list);
                gotoPage(request, response, "/reservationConfirm.jsp");
            } else if (action.equals("publisherSearch")) {
            	HistoryDAO dao = new HistoryDAO();
            	List<HistoryBean> list = dao.findOrderedByPublisher(request.getParameter("publisher"));
                request.setAttribute("histories", list);
                gotoPage(request, response, "/reservationConfirm.jsp");
            
            } else if (action.equals("checkout")) {
            	HistoryDAO dao = new HistoryDAO();
            	int i = Integer.parseInt(request.getParameter("item_code"));
            	int o = Integer.parseInt(request.getParameter("ordered_code"));
            	dao.checkout(i,o);
            	request.setAttribute("icode", i);
            	request.setAttribute("ocode", o);
            	request.setAttribute("name", request.getParameter("name"));
            	request.setAttribute("customer", request.getParameter("customer"));
            	request.setAttribute("message", "貸出処理");
            	gotoPage(request, response, "/statusUp.jsp");
            } else if (action.equals("returns")) {
            	HistoryDAO dao = new HistoryDAO();
            	int i = Integer.parseInt(request.getParameter("item_code"));
            	int o = Integer.parseInt(request.getParameter("ordered_code"));
            	request.setAttribute("icode", i);
            	request.setAttribute("ocode", o);
            	request.setAttribute("name", request.getParameter("name"));
            	request.setAttribute("customer", request.getParameter("customer"));
            	request.setAttribute("message", "返却処理");
            	dao.returns(i,o);
                gotoPage(request, response, "/statusUp.jsp");
            } else if (action.equals("codeSearch")) {
            	String  strid = request.getParameter("code");
            	if(strid == null || strid.length() == 0 ) {
            		showNotEnterdError(out);
    				return;
            	}
            	int id = Integer.parseInt(strid);
            	HistoryDAO dao = new HistoryDAO();
            	List<HistoryBean> list = dao.findOrderedByCode(id);
                request.setAttribute("histories", list);
                gotoPage(request, response, "/reservationConfirm.jsp");
            } else if (action.equals("customerIDSearch")) {
            	CustomerBean customer = (CustomerBean)session.getAttribute("customerInfo");
            	if(customer == null) {
            		request.setAttribute("message", "ログインしてください。");
                    gotoPage(request, response, "/errInternal.jsp");
                    return;
            	}
            	HistoryDAO dao = new HistoryDAO();
            	List<HistoryBean> list = dao.findOrderedByCustomerCode(customer.getCode());
                request.setAttribute("histories", list);
                ReviewDAO2 dao2 = new ReviewDAO2();
                List<ReviewBean>  list2 = dao2.reviewByCustomer(customer.getCode());
                request.setAttribute("review", list2);
                gotoPage(request, response, "/history.jsp");
            } else if (action.equals("adminCustomerIDSearch")) {
            	
            	String  strid = request.getParameter("customer_code");
            	if(strid == null || strid.length() == 0 ) {
            		showNotEnterdError(out);
    				return;
            	}
            	int id = Integer.parseInt(strid);
            	HistoryDAO dao = new HistoryDAO();
            	List<HistoryBean> list = dao.findOrderedByCustomerCode(id);
                request.setAttribute("histories", list);
                gotoPage(request, response, "/reservationConfirm.jsp"); 
            
            } else if (action.equals("reserve")) {
            	HistoryDAO dao = new HistoryDAO();
            	int i = Integer.parseInt(request.getParameter("item_code"));
            	int o = Integer.parseInt(request.getParameter("ordered_code"));
            	dao.reserveCancel(i,o);
            	request.setAttribute("icode", i);
            	request.setAttribute("ocode", o);
            	request.setAttribute("name", request.getParameter("name"));
            	request.setAttribute("customer", request.getParameter("customer"));
            	request.setAttribute("message", "予約取り消し");
                gotoPage(request, response, "/statusUp.jsp");
                
                
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
        
        private void showNotEnterdError(PrintWriter out) {
    		out.println("<html><head><title>Plus</title></head><body>");
    		out.println("<h1>入力してください</h1>");
    		out.println("</body></html>");
    		
    	
    }
}