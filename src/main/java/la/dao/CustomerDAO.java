package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.CustomerBean;

public class CustomerDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:library";
	private String user = "student";
	private String pass = "himitu";

	public CustomerDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	// 引数なし全件検索
	public List<CustomerBean> findAllCustomer() throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM customer ORDER BY code";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			// 結果の取得および表示
			List<CustomerBean> list = new ArrayList<CustomerBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				String pass = rs.getString("pass");

				CustomerBean bean = new CustomerBean(code, name, address, tel, email, pass);
				list.add(bean);
			}
			// ユーザー情報をListとして返す
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	// ログイン時
	public CustomerBean findCustomer(int id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM customer WHERE code = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			st.setInt(1, id);

			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				if(rs.next()){
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				String pass = rs.getString("pass");
				

				CustomerBean bean = new CustomerBean(code, name, address, tel, email, pass);

				// ユーザー情報を返す
				return bean;
				}else {
					return null;
				}
			}

			catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	// 登録時
	public int registCustomer(CustomerBean customer) throws DAOException {

		// 顧客番号の取得 Serial型の暗黙シーケンスから取得
		int customerNumber = 0;
		String sql = "SELECT nextval('customer_code_seq')";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			if (rs.next()) {
				customerNumber = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}

		// 顧客情報の追加SQL文
		sql = "INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダーの設定
			st.setInt(1, customerNumber); // 自動生成のコード
			st.setString(2, customer.getName());
			st.setString(3, customer.getTel());
			st.setString(4, customer.getAddress());
			st.setString(5, customer.getEmail());
			st.setString(6, customer.getPass());
			// SQLの実行
			st.executeUpdate();

			// ユーザーIDを返す(登録完了画面に表示）
			return customerNumber;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}

	}

	// 更新時
	public int updateCustomer(int customerCode,CustomerBean customer) throws DAOException {
		// SQL文の作成
//		String sql = "UPDATE customer SET code=?,name=?,tel=?,address=?," + "email=?, pass=?";

		
		String sql = "UPDATE customer SET name=?,tel=?,address=?," + "email=?, pass=? WHERE code=?";
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			// プレースホルダーの設定
			
			st.setString(1, customer.getName());
			st.setString(2, customer.getTel());
			st.setString(3, customer.getAddress());
			st.setString(4, customer.getEmail());
			st.setString(5, customer.getPass());
			st.setInt(6, customerCode);// ログイン時に取得したコード名
			
			// SQLの実行
			//更新件数表示
			int number = st.executeUpdate();
			return number;
			

			// ユーザーIDを返す（更新完了画面に表示）
//			return customer.getCode();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public CustomerBean findByID(int id) throws DAOException {
		String sql = "SELECT * FROM customer WHERE code = ? ";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);

			try (ResultSet rs = st.executeQuery();) {
				if(rs.next()) { //1行目飛ばす
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				CustomerBean bean = new CustomerBean(code, name, address, tel, email);

				return bean;
			}else {
				return null;
			}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public List<CustomerBean> findByName(String customerName) throws DAOException {
		String sql = "SELECT * FROM customer WHERE name like ? ORDER BY code";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%" + customerName + "%");

			try (ResultSet rs = st.executeQuery();) {
				List<CustomerBean> list = new ArrayList<CustomerBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					String email = rs.getString("email");
					CustomerBean bean = new CustomerBean(code, name, address, tel, email);
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

	public List<CustomerBean> findByTel(String telNumber) throws DAOException {
		String sql = "SELECT * FROM customer WHERE tel like ? ORDER BY code";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%"+telNumber+"%");

			try (ResultSet rs = st.executeQuery();) {
				List<CustomerBean> list = new ArrayList<CustomerBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					String email = rs.getString("email");
					CustomerBean bean = new CustomerBean(code, name, address, tel, email);
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
	
	public int updateCustomerByAdmin(CustomerBean customer) throws DAOException {
		// SQL文の作成
//		String sql = "UPDATE customer SET code=?,name=?,tel=?,address=?," + "email=?, pass=?";

		
		String sql = "UPDATE customer SET code = ?,name=?,tel=?,address=?,email=? WHERE code=?";
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			// プレースホルダーの設定
			st.setInt(1, customer.getCode());
			st.setString(2, customer.getName());
			st.setString(3, customer.getTel());
			st.setString(4, customer.getAddress());
			st.setString(5, customer.getEmail());
			st.setInt(6, customer.getCode());// ログイン時に取得したコード名
			
			// SQLの実行
			//更新件数表示
			st.executeUpdate();
//			return number;
			

//			 ユーザーIDを返す（更新完了画面に表示）
			return customer.getCode();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
	public boolean deleteCustomerByAdmin(int customerCode) throws DAOException {
		// SQL文の作成
//		String sql = "UPDATE customer SET code=?,name=?,tel=?,address=?," + "email=?, pass=?";

		
		String sql = "delete  from customer where code = ?";
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダーの設定
			st.setInt(1, customerCode);
			st.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

}
