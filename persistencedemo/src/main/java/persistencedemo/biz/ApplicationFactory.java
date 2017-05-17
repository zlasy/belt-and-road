package persistencedemo.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import persistencedemo.dao.BaseDao;
import persistencedemo.dao.impl.HbaseDaoImpl;
import persistencedemo.dao.impl.JdbcDaoImpl;
import persistencedemo.dao.impl.MybatisDaoImpl;
import persistencedemo.dto.PageClickInfo;
import persistencedemo.dto.PersistenceTypeEnum;

@Component
public class ApplicationFactory {

	@Resource
	JdbcDaoImpl jdbcDao;
	@Resource
	MybatisDaoImpl mybatisDao;
	@Resource
	HbaseDaoImpl hbaseDao;

	public BaseDao getDao(PersistenceTypeEnum type) {
		switch (type) {
			case JDBC:
				return jdbcDao;
			case MYBATIS:
				return mybatisDao;
			case HBASE:
				return hbaseDao;
			default:
				return null;
		}
	}

	public void findByConfition() {

		jdbcDao.findByConfition(new PageClickInfo());
	}
}
