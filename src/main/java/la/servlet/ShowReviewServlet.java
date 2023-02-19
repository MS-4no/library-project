package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CustomerBean;
import la.bean.ReviewBean;
import la.dao.DAOException;
import la.dao.ReviewDAO2;


@WebServlet("/ShowReviewServlet")
public class ShowReviewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
			request.setCharacterEncoding("UTF-8");
			//パラメータの解析
			String action = request.getParameter("action");
			int review_code = Integer.parseInt(request.getParameter("review_code"));
			ReviewDAO2 dao = new ReviewDAO2();
			if(action == null || action.length() == 0) {
				List<ReviewBean> list = dao.displayByReview(review_code);
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				double avg = dao.calcScore(review_code);
				request.setAttribute("avg", avg);
				String name = dao.findByPrimaryKey(review_code);
				request.setAttribute("name", name);
				HttpSession session = request.getSession();
				session.setAttribute("review_code", review_code);
				gotoPage(request, response, "/review2.jsp");
				//追加
			}else if(action.equals("add")) {
				HttpSession session = request.getSession(false);
		    	 CustomerBean c = (CustomerBean)session.getAttribute("customerInfo");
		        if (c == null) { // セッションオブジェクトなし（ログイン前）
		            request.setAttribute("message",
		                "まだログインしていません。ログインから操作してください。");
		            gotoPage(request, response, "/errInternal.jsp");
		            return;
		        }
				//int item_code = Integer.parseInt(request.getParameter("item_code"));
				String content = request.getParameter("content");
				int review_score = Integer.parseInt(request.getParameter("review_score"));
				dao.addReview(review_code, content, review_score, c);
				// Listをリクエストスコープに入れてJSPへフォーワードする
				List<ReviewBean> list = dao.displayByReview(review_code);
//				request.setAttribute("review_code", review_code);
				request.setAttribute("items", list);
				double avg = dao.calcScore(review_code);
				request.setAttribute("avg", avg);
				String name = dao.findByPrimaryKey(review_code);
				request.setAttribute("name", name);
				gotoPage(request, response, "/review2.jsp");
			}
		}catch(DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "エラーです");
			gotoPage(request, response, "/errInternal.jsp");
		}catch(NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("message", "正しく操作してください");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}
		
		
		//		try {
//            // パラメータの解析
//            String action = request.getParameter("action");
//            if (action.equals("review")){
//                int categoryCode = Integer.parseInt(request.getParameter("code"));
//                ReviewDAO dao = new ReviewDAO();
//                List<ReviewBean> list = dao.displayByReview(categoryCode);
//                // Listをリクエストスコープに入れてJSPへフォーワードする
//                request.setAttribute("review", list);
//                gotoPage(request, response, "/review.jsp");
//            } else {
//                request.setAttribute("message", "正しく操作してください。");
//                gotoPage(request, response, "/errInternal.jsp");
//            }
//        } catch (DAOException e) {
//            e.printStackTrace();
//            request.setAttribute("message", "内部エラーが発生しました。");
//            gotoPage(request, response, "/errInternal.jsp");
//        }		
//		
//	}
//
	private void gotoPage(HttpServletRequest request,
            HttpServletResponse response, String page) throws ServletException,
            IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}