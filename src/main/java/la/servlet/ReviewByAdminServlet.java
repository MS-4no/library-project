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
import la.bean.ReviewBean;
import la.dao.DAOException;
import la.dao.ReviewDAO2;

@WebServlet("/ReviewByAdminServlet")
public class ReviewByAdminServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			
			// パラメータの解析
			String action = request.getParameter("action");

			ReviewDAO2 dao = new ReviewDAO2();
			if (action == null || action.length() == 0) {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
				return;
				// 追加
			} else if (action.equals("showAll")) {

				List<ReviewBean> list = dao.displayAllReview();
//				request.setAttribute("review_code", review_code);
				request.setAttribute("items", list);
				request.setAttribute("key", 1);

				gotoPage(request, response, "/adminReview.jsp");
			} else if (action.equals("show")) {
				int review_code = Integer.parseInt(request.getParameter("item_code"));
				List<ReviewBean> list = dao.displayByReview(review_code);

				request.setAttribute("items", list);
				request.setAttribute("key", 2);
				double avg = dao.calcScore(review_code);
				request.setAttribute("avg", avg);
				String name = dao.findByPrimaryKey(review_code);
				request.setAttribute("name", name);
				gotoPage(request, response, "/adminReview.jsp");
			} else if (action.equals("delete")) {
				int review_code = Integer.parseInt(request.getParameter("review_code"));
				dao.deleteReview(review_code);
				int item_code = Integer.parseInt(request.getParameter("item_code"));
				List<ReviewBean> list = dao.displayByReview(item_code);
				request.setAttribute("items", list);
				double avg = dao.calcScore(item_code);
				request.setAttribute("avg", avg);
				String name = dao.findByPrimaryKey(item_code);
				request.setAttribute("name", name);
				request.setAttribute("key", 2);
				gotoPage(request, response, "/adminReview.jsp");
			} else if (action.equals("deleteByAll")) {
				int review_code = Integer.parseInt(request.getParameter("review_code"));
				dao.deleteReview(review_code);
				List<ReviewBean> list = dao.displayAllReview();
				request.setAttribute("items", list);

				request.setAttribute("key", 1);
				gotoPage(request, response, "/adminReview.jsp");
			} else if (action.equals("deleteByCustomer")) {
				HttpSession session = request.getSession(false);
				CustomerBean customer = (CustomerBean) session.getAttribute("customerInfo");
				if (customer == null) {
					request.setAttribute("message", "ログインしてください。");
					gotoPage(request, response, "/errInternal.jsp");
					return;
				}
				int review_code = Integer.parseInt(request.getParameter("review_code"));
				dao.deleteReview(review_code);

				gotoPage(request, response, "/AdminRServlet?action=customerIDSearch");
			} else if (action.equals("IDSearch")) {
				String  strid = request.getParameter("id");
            	if(strid == null || strid.length() == 0 ) {
            		showNotEnterdError(out);
    				return;
            	}
            	int id = Integer.parseInt(strid);

				List<ReviewBean> list = dao.reviewByCustomer(id);
//				request.setAttribute("review_code", review_code);
				request.setAttribute("items", list);
				request.setAttribute("key", 1);

				gotoPage(request, response, "/adminReview.jsp");
			} else if (action.equals("commentSearch")) {

				List<ReviewBean> list = dao.reviewByContent(request.getParameter("comment"));
//				request.setAttribute("review_code", review_code);
				request.setAttribute("items", list);
				request.setAttribute("key", 1);

				gotoPage(request, response, "/adminReview.jsp");
			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "エラーです");
			gotoPage(request, response, "/errInternal.jsp");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("message", "正しく操作してください");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	// try {
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
	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	private void showNotEnterdError(PrintWriter out) {
		out.println("<html><head><title>Plus</title></head><body>");
		out.println("<h1>IDを入力してください</h1>");
		out.println("</body></html>");
		
	}

}