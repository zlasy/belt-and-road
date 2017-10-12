package persistencedemo;

import java.io.IOException;

import org.apache.hadoop.hbase.exceptions.HBaseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import persistencedemo.biz.ApplicationFactory;
import persistencedemo.biz.HbaseDemo;
import persistencedemo.dao.BaseDao;
import persistencedemo.dto.PageClickInfo;
import persistencedemo.dto.PersistenceTypeEnum;

public class PersistenceMain {

	public static void main(String[] args) throws IOException, HBaseException {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring.xml");
		
		ApplicationFactory app = ctx.getBean(ApplicationFactory.class);
		BaseDao<PageClickInfo> dao = app.getDao(PersistenceTypeEnum.JDBC);
		dao.findByConfition(new PageClickInfo());
		
	}

}
