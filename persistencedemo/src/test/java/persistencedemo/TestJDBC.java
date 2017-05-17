package persistencedemo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


import persistencedemo.dao.BaseDao;
import persistencedemo.dao.impl.JdbcDaoImpl;
import persistencedemo.dto.PageClickInfo;

public class TestJDBC {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring.xml");

        BaseDao dao = ctx.getBean(JdbcDaoImpl.class);
        List<PageClickInfo> list = (List<PageClickInfo>) dao.findByConfition(new PageClickInfo());
        
        System.out.println(list.size());
    }

}
