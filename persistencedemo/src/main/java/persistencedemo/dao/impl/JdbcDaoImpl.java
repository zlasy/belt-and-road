package persistencedemo.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import common.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import persistencedemo.dao.BaseDao;
import persistencedemo.dto.PageClickInfo;

@Repository
@Slf4j
public class JdbcDaoImpl implements BaseDao<PageClickInfo> {

	private Connection con;
	private final String SQL =
			"select * from page_click_info where permanent_id = '20160805211219507822292486348440414'";

	private void openDb() {
		try {
			Class.forName(ConfigUtil.getSettings("jdbc.orderSourceTrace0.driver"));
			String url = ConfigUtil.getSettings("jdbc.orderSourceTrace0.url");
			String username = ConfigUtil.getSettings("jdbc.orderSourceTrace0.username");
			String password = ConfigUtil.getSettings("jdbc.orderSourceTrace0.password");
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			log.error("failed to open db", e);
		} catch (SQLException e) {
			log.error("failed to connect db", e);
		}
	}

	private void closeDb() {
		if (con != null) { // 关闭连接对象
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public List<PageClickInfo> findByConfition(PageClickInfo param) {
		openDb();
		List<PageClickInfo> list = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(SQL);ResultSet rSet = pstmt.executeQuery();){
			while (rSet.next()) {
				persistencedemo.dto.PageClickInfo info = new PageClickInfo();
				System.out.println(rSet.getString(1) + "\t" + rSet.getString(2) + "\t" + rSet.getString(3));
				list.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDb();
		}
		return list;
	}

	@Override
	public void save(PageClickInfo t) {

	}
}
