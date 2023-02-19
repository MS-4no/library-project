package la.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import la.bean.CartBean;
import la.bean.CustomerBean;
import la.bean.ItemBean;

public class OrderDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:library";
	private String user = "student";
	private String pass = "himitu";

	public OrderDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録の失敗しました。");
		}
	}

	public int saveOrder(CustomerBean customer, CartBean cart,Date reserveDate) throws DAOException {

		// 注文番号の取得 Serial型の暗黙シーケンスから取得
		int orderNumber = 0;
		String sql = "SELECT nextval('ordered_code_seq')";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			if (rs.next()) {
				orderNumber = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}

		// 注文情報のOrderedテーブルへの追加
		sql = "INSERT INTO ordered VALUES(?, ?)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダーの設定
			st.setInt(1, orderNumber);
			st.setInt(2, customer.getCode());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}

		// 注文明細情報のOrderedDetailテーブルへの追加
		// 商品ごとに複数レコード追加
		sql = "INSERT INTO ordered_detail VALUES(?, ?, ?,?)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			List<ItemBean> items = cart.getItems();
			for (ItemBean item : items) {
				st.setInt(1, orderNumber);
				st.setInt(2, item.getCode());
				Date today = new Date(System.currentTimeMillis());
				st.setDate(3, today);
				st.setDate(4, reserveDate);
				st.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
		sql = "update item set status = '予約済' where code = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			List<ItemBean> items = cart.getItems();
			for (ItemBean item : items) {
				st.setInt(1, item.getCode());
				st.executeUpdate();
			}
			return orderNumber;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}

	}

	public int reserveByAdmin(int customerCode, int itemCode, Date reserveDate) throws DAOException {

// 注文番号の取得 Serial型の暗黙シーケンスから取得
		int orderNumber = 0;
		String sql = "SELECT nextval('ordered_code_seq')";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
// SQLの実行
				ResultSet rs = st.executeQuery();) {
			if (rs.next()) {
				orderNumber = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}

// 注文情報のOrderedテーブルへの追加
		sql = "INSERT INTO ordered VALUES(?, ?)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
// プレースホルダーの設定
			st.setInt(1, orderNumber);
			st.setInt(2, customerCode);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}

// 注文明細情報のOrderedDetailテーブルへの追加
// 商品ごとに複数レコード追加
		sql = "INSERT INTO ordered_detail VALUES(?, ?, ?,?)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			
				st.setInt(1, orderNumber);
				st.setInt(2, itemCode);
				Date today = new Date(System.currentTimeMillis());
				st.setDate(3, today);
				st.setDate(4, reserveDate);
				st.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
		sql = "update item set status = '予約済' where code = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			
				st.setInt(1, itemCode);
				st.executeUpdate();
			
			return orderNumber;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}

	}
	public long calcDate(int year, int month, int date) {
		long a;
		LocalDate today = LocalDate.now();

		LocalDate checkdate = LocalDate.of(year, month, date);

		a = ChronoUnit.DAYS.between(today,checkdate);
		
		return a;
	}
}