package persistencedemo.dao;

import java.util.List;

public interface BaseDao<T> {

	public List<T> findByConfition(T param);
	
	public void save(T t);
}
