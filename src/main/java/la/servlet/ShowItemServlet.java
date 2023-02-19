package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.CategoryBean;
import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

@WebServlet("/ShowItemServlet")
public class ShowItemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ItemDAO dao = new ItemDAO();
			String c_value = request.getParameter("category_choise");
			String query = request.getParameter("user_query");
			if (c_value == null || c_value.length() == 0) {
				request.setAttribute("message", "検索カテゴリを選択してください");
				RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
				rd.forward(request, response);
			} else if (c_value.equals("1")) {
				List<ItemBean> list = dao.findByName(query);
				request.setAttribute("items", list);
				RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
				rd.forward(request, response);
			} else if (c_value.equals("2")) {
				List<ItemBean> list = dao.findByAuthor(query);
				request.setAttribute("items", list);
				RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
				rd.forward(request, response);
			} else if (c_value.equals("3")) {
				List<ItemBean> list = dao.findByPublisher(query);
				request.setAttribute("items", list);
				RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
				rd.forward(request, response);
			}

			else {
				request.setAttribute("message", "正しく操作してください");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	public void init() throws ServletException {
		try {
			ItemDAO dao = new ItemDAO();
			List<CategoryBean> list = dao.findAllCategory();
			getServletContext().setAttribute("categories", list);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		doGet(request, response);
	}


}
