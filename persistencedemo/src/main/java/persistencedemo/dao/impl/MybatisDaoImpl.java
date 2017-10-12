package persistencedemo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import persistencedemo.dao.BaseDao;
import persistencedemo.dto.PageClickInfo;

@Repository
public class MybatisDaoImpl implements BaseDao<PageClickInfo> {

	@Override
	public List<PageClickInfo> findByConfition(PageClickInfo param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(PageClickInfo t) {
		// TODO Auto-generated method stub
		
	}

}
