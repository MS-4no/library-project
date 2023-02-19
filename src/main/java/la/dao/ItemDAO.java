package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.CategoryBean;
import la.bean.ItemBean;

public class ItemDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:library";
	private String user = "student";
	private String pass = "himitu";

	public ItemDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	public List<CategoryBean> findAllCategory() throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM category";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			// 結果の取得および表示
			List<CategoryBean> list = new ArrayList<CategoryBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				CategoryBean bean = new CategoryBean(code, name);
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

//	public List<ItemBean> findbyItem() throws DAOException {
//		// SQL文の作成
//		String sql = "SELECT * FROM item";
//
//		try (// データベースへの接続
//				Connection con = DriverManager.getConnection(url, user, pass);
//				// PreparedStatementオブジェクトの取得
//				PreparedStatement st = con.prepareStatement(sql);
//				// SQLの実行
//				ResultSet rs = st.executeQuery();) {
//			// 結果の取得および表示
//			List<ItemBean> list = new ArrayList<ItemBean>();
//			while (rs.next()) {
//				int code = rs.getInt("code");
//				int category_code = rs.getInt("category_code");
//				String name = rs.getString("name");
//				String author = rs.getString("author");
//				String publisher = rs.getString("publisher");
//				String status = rs.getString("status");
//				String img = rs.getString("img");
//				ItemBean bean = new ItemBean(code, category_code, name, author, publisher, status, img);
//				list.add(bean);
//			}
//			// カテゴリ一覧をListとして返す
//			return list;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DAOException("レコードの取得に失敗しました。");
//		}
//
//	}

	public List<ItemBean> findByName(String name) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM item WHERE name LIKE ? order by code ";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%" + name + "%");

			try (ResultSet rs = st.executeQuery();) {
				List<ItemBean> list = new ArrayList<ItemBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					int category_code = rs.getInt("category_code");
					String name1 = rs.getString("name");
					String author = rs.getString("author");
					String publisher = rs.getString("publisher");
					String status = rs.getString("status");
					String img = rs.getString("image");
					ItemBean bean = new ItemBean(code, category_code, name1, author, publisher, status, img);
					list.add(bean);
				}
				// カテゴリ一覧をListとして返す
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

	public List<ItemBean> findByAuthor(String author) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM item WHERE author LIKE ? order by code ";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, "%" + author + "%");

			try (ResultSet rs = st.executeQuery();) {
				List<ItemBean> list = new ArrayList<ItemBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					int category_code = rs.getInt("category_code");
					String name = rs.getString("name");
					String author1 = rs.getString("author");
					String publisher = rs.getString("publisher");
					String status = rs.getString("status");
					String img = rs.getString("image");
					ItemBean bean = new ItemBean(code, category_code, name, author1, publisher, status, img);
					list.add(bean);
				}
				// カテゴリ一覧をListとして返す
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

	public List<ItemBean> findByPublisher(String publisher) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM item WHERE publisher LIKE ? order by code";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%" + publisher + "%");

			try (ResultSet rs = st.executeQuery();) {
				List<ItemBean> list = new ArrayList<ItemBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					int category_code = rs.getInt("category_code");
					String name = rs.getString("name");
					String author = rs.getString("author");
					String publisher1 = rs.getString("publisher");
					String status = rs.getString("status");
					String img = rs.getString("image");
					ItemBean bean = new ItemBean(code, category_code, name, author, publisher1, status, img);
					list.add(bean);
				}
				// カテゴリ一覧をListとして返す
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

	public List<ItemBean> findByCategory(int categoryCode) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM item WHERE category_code = ? ORDER BY code";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			st.setInt(1, categoryCode);

			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				List<ItemBean> list = new ArrayList<ItemBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					int category_code = rs.getInt("category_code");
					String name = rs.getString("name");
					String author = rs.getString("author");
					String publisher1 = rs.getString("publisher");
					String status = rs.getString("status");
					String img = rs.getString("img");
					ItemBean bean = new ItemBean(code, category_code, name, author, publisher1, status, img);
					list.add(bean);
				}
				// 商品一覧をListとして返す
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


    public ItemBean findByPrimaryKey(int key) throws DAOException {
        // SQL文の作成
        String sql = "SELECT * FROM item WHERE code = ?";
		
        try (// データベースへの接続
             Connection con = DriverManager.getConnection(url, user, pass);
			 // PreparedStatementオブジェクトの取得
			 PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			st.setInt(1, key);
			
			try (// SQLの実行
			     ResultSet rs = st.executeQuery();) {
			    // 結果の取得および表示
			    if (rs.next()) {
			    	int code = rs.getInt("code");
					int category_code = rs.getInt("category_code");
					String name = rs.getString("name");
					String author = rs.getString("author");
					String publisher1 = rs.getString("publisher");
					String status = rs.getString("status");
					String img = rs.getString("image");
					ItemBean bean = new ItemBean(code, category_code, name, author, publisher1, status, img);
			        return bean; // 主キーに該当するレコードを返す
                } else {
			        return null; // 主キーに該当するレコードなし
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
    
    public int registItem(String name, String author, String publisher, String image) throws DAOException {

		// 顧客番号の取得 Serial型の暗黙シーケンスから取得
		int itemCode = 0;
		String sql = "SELECT nextval('item_code_seq')";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			if (rs.next()) {
				itemCode = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}

		// 顧客情報の追加SQL文
		sql = "INSERT INTO item(code,name,author,publisher,status,image) VALUES(?, ?, ?, ?, ?, ?)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダーの設定
			st.setInt(1, itemCode); // 自動生成のコード
			st.setString(2, name);
			st.setString(3, author);
			st.setString(4, publisher);
			st.setString(5, "予約可");
			st.setString(6, image);
			// SQLの実行
			st.executeUpdate();

			// ユーザーIDを返す(登録完了画面に表示）
			return itemCode;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}

	}
    public void update(int code, String name, String author, String publisher, String image) throws DAOException {
		// SQL文の作成
//		String sql = "UPDATE customer SET code=?,name=?,tel=?,address=?," + "email=?, pass=?";

		
		String sql = "UPDATE item SET name=?,author=?,publisher=?,image=? WHERE code=?";
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			
			st.setString(1, name);
			st.setString(2, author);
			st.setString(3, publisher);
			st.setString(4, image);
			st.setInt(5, code);
			
			// SQLの実行
			//更新件数表示
			 st.executeUpdate();
			return ;
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
    public void delete(int code) throws DAOException {
		// SQL文の作成

		
		String sql = "delete  from item where code = ?";
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダーの設定
			st.setInt(1, code);
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		 sql = "delete  from ordered_detail where item_code = ?";
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダーの設定
			st.setInt(1, code);
			st.executeUpdate();
			 

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		 sql = "delete  from review where item_code = ?";
			try (// データベースへの接続
					Connection con = DriverManager.getConnection(url, user, pass);
					// PreparedStatementオブジェクトの取得
					PreparedStatement st = con.prepareStatement(sql);) {
				// プレースホルダーの設定
				st.setInt(1, code);
				st.executeUpdate();
				return ;

			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
	}

}