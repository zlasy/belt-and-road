package persistencedemo.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MybatisDemo {

	Logger logger = LoggerFactory.getLogger(MybatisDemo.class);

	public int findDemo() {
		System.out.println("start finddemo");
		logger.info("findDemo MybatisDemo for logback");
		return 1;
	}

}
