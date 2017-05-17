package persistencedemo;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import persistencedemo.dto.PageClickInfo;

public class TestMybatisDao {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            PageClickInfo pc =
                    (PageClickInfo) session.selectOne("persistencedemo.dto.PageClickInfo.getById", 585);
            System.out.println(pc.getId());
            System.out.println(pc.getPermanentId());
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
