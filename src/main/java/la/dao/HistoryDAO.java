package la.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.HistoryBean;

public class HistoryDAO {
	private String url = "jdbc:postgresql:library";
	private String user = "student";
	private String pass = "himitu";

	public HistoryDAO() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録の失敗しました。");
		}
	}

	public List<HistoryBean> findOrderedByCustomerCode(int customerCode) throws DAOException {
		String sql = "select o.code,o.customer_code,i.code,i.name,i.author,i.publisher,od.ordered_date,od.reserve_date,od.checkout_date,od.return_date from ordered_detail od join ordered o on od.ordered_code = o.code join item i on od.item_code = i.code WHERE customer_code = ? ORDER BY o.code desc";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, customerCode);

			try (ResultSet rs = st.executeQuery();) {
				List<HistoryBean> list = new ArrayList<HistoryBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					int customer = rs.getInt("customer_code");
					String name = rs.getString("name");
					String author = rs.getString("author");
					String publisher = rs.getString("publisher");
					String orderedDate = rs.getString("ordered_date");
					String reserveDate = rs.getString("reserve_date");
					String checkoutDate = rs.getString("checkout_date");
					String returnDate = rs.getString("return_date");
					HistoryBean bean = new HistoryBean(code, customer, name, author, publisher, orderedDate,
							reserveDate, checkoutDate, returnDate);
					list.add(bean);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public List<HistoryBean> findOrderedByName(String itemName) throws DAOException {
		String sql = "select o.code,o.customer_code,i.code,i.name,i.author,i.publisher,od.ordered_date,od.reserve_date,od.checkout_date,od.return_date,i.status from ordered_detail od join ordered o on od.ordered_code = o.code join item i on od.item_code = i.code WHERE name like ? ORDER BY o.code ";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%" + itemName + "%");

			try (ResultSet rs = st.executeQuery();) {
				List<HistoryBean> list = new ArrayList<HistoryBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					int customer = rs.getInt("customer_code");
					int itemCode = rs.getInt(3);
					String name = rs.getString("name");
					String author = rs.getString("author");
					String publisher = rs.getString("publisher");
					String orderedDate = rs.getString("ordered_date");
					String reserveDate = rs.getString("reserve_date");
					String checkoutDate = rs.getString("checkout_date");
					String returnDate = rs.getString("return_date");
					String status = rs.getString("status");
					HistoryBean bean = new HistoryBean(code, customer,itemCode, name, author, publisher, orderedDate,
							reserveDate, checkoutDate, returnDate, status);
					list.add(bean);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public List<HistoryBean> findOrderedByAuthor(String authorName) throws DAOException {
		String sql = "select o.code,o.customer_code,i.code,i.name,i.author,i.publisher,od.ordered_date,od.reserve_date,od.checkout_date,od.return_date,i.status from ordered_detail od join ordered o on od.ordered_code = o.code join item i on od.item_code = i.code WHERE author like ? ORDER BY o.code ";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%" + authorName + "%");

			try (ResultSet rs = st.executeQuery();) {
				List<HistoryBean> list = new ArrayList<HistoryBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					int customer = rs.getInt("customer_code");
					int itemCode = rs.getInt(3);
					String name = rs.getString("name");
					String author = rs.getString("author");
					String publisher = rs.getString("publisher");
					String orderedDate = rs.getString("ordered_date");
					String reserveDate = rs.getString("reserve_date");
					String checkoutDate = rs.getString("checkout_date");
					String returnDate = rs.getString("return_date");
					String status = rs.getString("status");
					HistoryBean bean = new HistoryBean(code, customer, itemCode,name, author, publisher, orderedDate,
							reserveDate, checkoutDate, returnDate, status);
					list.add(bean);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public List<HistoryBean> findOrderedByPublisher(String publisherName) throws DAOException {
		String sql = "select o.code,o.customer_code,i.code,i.name,i.author,i.publisher,od.ordered_date,od.reserve_date,od.checkout_date,od.return_date,i.status from ordered_detail od join ordered o on od.ordered_code = o.code join item i on od.item_code = i.code WHERE publisher like ? ORDER BY o.code ";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%" + publisherName + "%");

			try (ResultSet rs = st.executeQuery();) {
				List<HistoryBean> list = new ArrayList<HistoryBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					int customer = rs.getInt("customer_code");
					int itemCode = rs.getInt(3);
					String name = rs.getString("name");
					String author = rs.getString("author");
					String publisher = rs.getString("publisher");
					String orderedDate = rs.getString("ordered_date");
					String reserveDate = rs.getString("reserve_date");
					String checkoutDate = rs.getString("checkout_date");
					String returnDate = rs.getString("return_date");
					String status = rs.getString("status");
					HistoryBean bean = new HistoryBean(code, customer, itemCode,name, author, publisher, orderedDate,
							reserveDate, checkoutDate, returnDate, status);
					list.add(bean);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public List<HistoryBean> findOrderedByStatus(String itemStatus) throws DAOException {
		if (itemStatus.equals("予約済")) {
			String sql = "select o.code,o.customer_code,i.code,i.name,i.author,i.publisher,od.ordered_date,od.reserve_date,od.checkout_date,od.return_date,i.status from ordered_detail od join ordered o on od.ordered_code = o.code join item i on od.item_code = i.code WHERE status = '予約済' and checkout_date is null ORDER BY o.code ";

			try (Connection con = DriverManager.getConnection(url, user, pass);
					PreparedStatement st = con.prepareStatement(sql);) {
				ResultSet rs = st.executeQuery();
				List<HistoryBean> list = new ArrayList<HistoryBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					int customer = rs.getInt("customer_code");
					int itemCode = rs.getInt(3);
					String name = rs.getString("name");
					String author = rs.getString("author");
					String publisher = rs.getString("publisher");
					String orderedDate = rs.getString("ordered_date");
					String reserveDate = rs.getString("reserve_date");
					String checkoutDate = rs.getString("checkout_date");
					String returnDate = rs.getString("return_date");
					String status = rs.getString("status");
					HistoryBean bean = new HistoryBean(code, customer, itemCode,name, author, publisher, orderedDate,
							reserveDate, checkoutDate, returnDate, status);
					list.add(bean);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} else {
			String sql = "select o.code,o.customer_code,i.code,i.name,i.author,i.publisher,od.ordered_date,od.reserve_date,od.checkout_date,od.return_date,i.status from ordered_detail od join ordered o on od.ordered_code = o.code join item i on od.item_code = i.code WHERE status = '貸出中' and checkout_date is not null and return_date is null ORDER BY o.code ";

			try (Connection con = DriverManager.getConnection(url, user, pass);
					PreparedStatement st = con.prepareStatement(sql);) {
				ResultSet rs = st.executeQuery();
				List<HistoryBean> list = new ArrayList<HistoryBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					int customer = rs.getInt("customer_code");
					int itemCode = rs.getInt(3);
					String name = rs.getString("name");
					String author = rs.getString("author");
					String publisher = rs.getString("publisher");
					String orderedDate = rs.getString("ordered_date");
					String reserveDate = rs.getString("reserve_date");
					String checkoutDate = rs.getString("checkout_date");
					String returnDate = rs.getString("return_date");
					String status = rs.getString("status");
					HistoryBean bean = new HistoryBean(code, customer, itemCode,name, author, publisher, orderedDate,
							reserveDate, checkoutDate, returnDate, status);
					list.add(bean);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		}
	}

	public boolean checkout(int itemCode, int orderedCode) throws DAOException {
		String sql = "update item set status = '貸出中' where code = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, itemCode);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		sql = "update ordered_detail set checkout_date = ? where ordered_code = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			Date today = new Date(System.currentTimeMillis());
			st.setDate(1, today);
			st.setInt(2, orderedCode);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public boolean returns(int itemCode, int orderedCode) throws DAOException {
		String sql = "update item set status = '予約可' where code = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, itemCode);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		sql = "update ordered_detail set return_date = ? where ordered_code = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			Date today = new Date(System.currentTimeMillis());
			st.setDate(1, today);
			st.setInt(2, orderedCode);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
	public boolean reserveCancel(int itemCode, int orderedCode) throws DAOException {
		String sql = "update item set status = '予約可' where code = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, itemCode);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		sql = "delete from ordered_detail  where ordered_code = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, orderedCode);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		sql = "delete from ordered  where code = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, orderedCode);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
	public List<HistoryBean> findOrderedByCode(int orderCode) throws DAOException {
		String sql = "select o.code,o.customer_code,i.code,i.name,i.author,i.publisher,od.ordered_date,od.reserve_date,od.checkout_date,od.return_date,i.status from ordered_detail od join ordered o on od.ordered_code = o.code join item i on od.item_code = i.code WHERE o.code = ? ORDER BY o.code ";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1,orderCode);

			try (ResultSet rs = st.executeQuery();) {
				List<HistoryBean> list = new ArrayList<HistoryBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					int customer = rs.getInt("customer_code");
					int itemCode = rs.getInt(3);
					String name = rs.getString("name");
					String author = rs.getString("author");
					String publisher = rs.getString("publisher");
					String orderedDate = rs.getString("ordered_date");
					String reserveDate = rs.getString("reserve_date");
					String checkoutDate = rs.getString("checkout_date");
					String returnDate = rs.getString("return_date");
					String status = rs.getString("status");
					HistoryBean bean = new HistoryBean(code, customer, itemCode,name, author, publisher, orderedDate,
							reserveDate, checkoutDate, returnDate, status);
					list.add(bean);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
	public List<HistoryBean> findOrderedAll() throws DAOException {
			String sql = "select o.code,o.customer_code,i.code,i.name,i.author,i.publisher,od.ordered_date,od.reserve_date,od.checkout_date,od.return_date,i.status from ordered_detail od join ordered o on od.ordered_code = o.code join item i on od.item_code = i.code  ORDER BY o.code desc";

			try (Connection con = DriverManager.getConnection(url, user, pass);
					PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();){
				List<HistoryBean> list = new ArrayList<HistoryBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					int customer = rs.getInt("customer_code");
					int itemCode = rs.getInt(3);
					String name = rs.getString("name");
					String author = rs.getString("author");
					String publisher = rs.getString("publisher");
					String orderedDate = rs.getString("ordered_date");
					String reserveDate = rs.getString("reserve_date");
					String checkoutDate = rs.getString("checkout_date");
					String returnDate = rs.getString("return_date");
					String status = rs.getString("status");
					HistoryBean bean = new HistoryBean(code, customer, itemCode,name, author, publisher, orderedDate,
							reserveDate, checkoutDate, returnDate, status);
					list.add(bean);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
	}
	

}