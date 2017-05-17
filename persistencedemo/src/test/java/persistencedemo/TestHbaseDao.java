package persistencedemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import persistencedemo.dao.impl.HbaseFactoryDaoImpl;
import persistencedemo.dto.PageClickInfo;

public class TestHbaseDao {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring.xml");
		
		HbaseFactoryDaoImpl dao = ctx.getBean(HbaseFactoryDaoImpl.class);
		boolean isExist = dao.isTableExist("page_click_info");
		System.out.println(isExist);
		
		PageClickInfo p = new PageClickInfo();
		p.setPermanentId("ttttt");
		p.setId(3L);
		p.setCurrentPage("1002");
		p.setActionDate(new Date());
//		dao.insert(p);
//		dao.delete("3");
		dao.getData("3");
		
//		List<PageClickInfo> list = prepareData();
//		dao.insertBatch(list);
	}

	private static List<PageClickInfo> prepareData() {
		List<PageClickInfo> list = new ArrayList<>();
		for(int i = 1; i<20; i++){
			PageClickInfo t = new PageClickInfo();
			t.setId(Long.valueOf(i));
			t.setPermanentId("abcd" + i);
			t.setCurrentPage(String.valueOf(1020 + i));
			t.setActionDate(new Date());
			list.add(t);
		}
		return list;
	}

}
