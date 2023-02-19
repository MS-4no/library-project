package la.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.CustomerBean;
import la.bean.ReviewBean;

public class ReviewDAO2 {
	//URL、ユーザ、パスワードの準備
	private String url = "jdbc:postgresql:library";
	private String user = "student";
	private String pass = "himitu";
	
	public ReviewDAO2() throws DAOException {
        try {
            // JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録の失敗しました。");
        }
    }
	
	public List<ReviewBean> displayByReview(int review_code
			) throws DAOException {
		//SQL文の作成
		String sql = "SELECT * FROM review WHERE item_code = ?";
		
		try (// データベースへの接続
	             Connection con = DriverManager.getConnection(url, user, pass);
				 // PreparedStatementオブジェクトの取得
				 PreparedStatement st = con.prepareStatement(sql);) {
				st.setInt(1, review_code);
		
				try (ResultSet rs = st.executeQuery();){
	            // 結果の取得および表示
				List<ReviewBean> list = new ArrayList<ReviewBean>();
				while (rs.next()) {
				    int code = rs.getInt("code");
				    int item = rs.getInt("item_code");
				    String content = rs.getString("content");
				    int custom = rs.getInt("custom_code");
				    String review_date = rs.getString("review_date");
				    int review_score = rs.getInt("review_score");
				    ReviewBean bean = new ReviewBean(code, item, content,custom, review_date, review_score);
				    list.add(bean);
				}
				
				//listで返す。
				return list;
			}catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			} 
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} 
	}
	
	public int addReview(int item_code, String content,int review_score, CustomerBean customer)throws DAOException{
		//SQL文の作成
		//String sql = "INSERT INTO review(item_code, content, review_score) VALUES(?,?,?)";
		String sql = "INSERT INTO review(item_code, content, custom_code, review_date, review_score) VALUES(?,?,?,?,?)";
		
		try(//データベース接続
			Connection con = DriverManager.getConnection(url, user, pass);
			// PreparedStatementオブジェクトの取得
			PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, item_code);
			st.setString(2, content);
			st.setInt(3, customer.getCode());
			Date today = new Date(System.currentTimeMillis());
			st.setDate(4, today);
			st.setInt(5, review_score);
			//SQLの実行
			int rows = st.executeUpdate();
			return rows;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		}
	}
	
	
	public List<ReviewBean> displayAllReview() throws DAOException {
		//SQL文の作成
		String sql = "select r.code,r.item_code,i.name,r.content,r.custom_code,r.review_date,r.review_score from review r join item i on r.item_code = i.code";
		
		try (// データベースへの接続
	             Connection con = DriverManager.getConnection(url, user, pass);
				 // PreparedStatementオブジェクトの取得
				 PreparedStatement st = con.prepareStatement(sql);) {
				ResultSet rs = st.executeQuery();
	            // 結果の取得および表示
				List<ReviewBean> list = new ArrayList<ReviewBean>();
				while (rs.next()) {
				    int code = rs.getInt("code");
				    int item = rs.getInt("item_code");
				    String item_name =rs.getString("name");
				    String content = rs.getString("content");
				    int custom = rs.getInt("custom_code");
				    String review_date = rs.getString("review_date");
				    int review_score = rs.getInt("review_score");
				    ReviewBean bean = new ReviewBean(code, item,item_name, content,custom, review_date, review_score);
				    list.add(bean);
				}
				
				//listで返す。
				return list;
			}catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			} 
		
	}
	public int deleteReview(int review_code)throws DAOException{
		//SQL文の作成
		//String sql = "INSERT INTO review(item_code, content, review_score) VALUES(?,?,?)";
		String sql = "delete from review where code = ?";
		
		try(//データベース接続
			Connection con = DriverManager.getConnection(url, user, pass);
			// PreparedStatementオブジェクトの取得
			PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, review_code);
			
			//SQLの実行
			int rows = st.executeUpdate();
			return rows;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		}
	}
	public double calcScore(int item_code) throws DAOException {
		//SQL文の作成
		String sql = " select  avg(review_score) from review WHERE item_code = ?";
		
		try (// データベースへの接続
	             Connection con = DriverManager.getConnection(url, user, pass);
				 // PreparedStatementオブジェクトの取得
				 PreparedStatement st = con.prepareStatement(sql);) {
				st.setInt(1, item_code);
		
				try (ResultSet rs = st.executeQuery();){
	            // 結果の取得および表示
				double a;
				if(rs.next()){
					a = rs.getDouble("avg");
					
					return a;
					}else {
						return 0;
					}
			}catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			} 
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} 
	}
	 public String findByPrimaryKey(int key) throws DAOException {
	        // SQL文の作成
	        String sql = "SELECT name FROM item WHERE code = ?";
			
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
						String name = rs.getString("name");
				        return name; // 主キーに該当するレコードを返す
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
	 public List<ReviewBean> reviewByCustomer(int customer) throws DAOException {
			//SQL文の作成
			String sql = " select r.code,r.item_code,i.name,r.content,r.custom_code,r.review_date,r.review_score from review r join item i on r.item_code = i.code WHERE custom_code = ?";
			
			try (// データベースへの接続
		             Connection con = DriverManager.getConnection(url, user, pass);
					 // PreparedStatementオブジェクトの取得
					 PreparedStatement st = con.prepareStatement(sql);) {
					st.setInt(1, customer);
			
					try (ResultSet rs = st.executeQuery();){
		            // 結果の取得および表示
					List<ReviewBean> list = new ArrayList<ReviewBean>();
					while (rs.next()) {
					    int code = rs.getInt("code");
					    int item = rs.getInt("item_code");
					    String item_name =rs.getString("name");
					    String content = rs.getString("content");
					    int custom = rs.getInt("custom_code");
					    String review_date = rs.getString("review_date");
					    int review_score = rs.getInt("review_score");
					    ReviewBean bean = new ReviewBean(code, item, item_name,content,custom, review_date, review_score);
					    list.add(bean);
					}
					
					//listで返す。
					return list;
				}catch (SQLException e) {
					e.printStackTrace();
					throw new DAOException("レコードの取得に失敗しました。");
				} 
			}catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			} 
		}
	 public List<ReviewBean> reviewByContent(String comment) throws DAOException {
			//SQL文の作成
			String sql = " select r.code,r.item_code,i.name,r.content,r.custom_code,r.review_date,r.review_score from review r join item i on r.item_code = i.code WHERE content like ?";
			
			try (// データベースへの接続
		             Connection con = DriverManager.getConnection(url, user, pass);
					 // PreparedStatementオブジェクトの取得
					 PreparedStatement st = con.prepareStatement(sql);) {
					st.setString(1, "%"+comment+"%");
			
					try (ResultSet rs = st.executeQuery();){
		            // 結果の取得および表示
					List<ReviewBean> list = new ArrayList<ReviewBean>();
					while (rs.next()) {
					    int code = rs.getInt("code");
					    int item = rs.getInt("item_code");
					    String item_name =rs.getString("name");
					    String content = rs.getString("content");
					    int custom = rs.getInt("custom_code");
					    String review_date = rs.getString("review_date");
					    int review_score = rs.getInt("review_score");
					    ReviewBean bean = new ReviewBean(code, item, item_name,content,custom, review_date, review_score);
					    list.add(bean);
					}
					
					//listで返す。
					return list;
				}catch (SQLException e) {
					e.printStackTrace();
					throw new DAOException("レコードの取得に失敗しました。");
				} 
			}catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			} 
		}
	 
	 
}

