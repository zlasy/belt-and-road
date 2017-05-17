package persistencedemo.biz;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.exceptions.HBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import persistencedemo.dao.impl.HbaseDaoImpl;
import persistencedemo.dto.PageClickInfo;


@Service
public class HbaseDemo {

	Logger logger = LoggerFactory.getLogger(HbaseDemo.class);

	@Resource
	HbaseDaoImpl hbaseDao;

	public static void start() throws IOException, HBaseException {
		Configuration config = HBaseConfiguration.create();
		config.addResource(new Path(System.getenv("HBASE_CONF_DIR"), "hbase-site.xml"));
		config.addResource(new Path(System.getenv("HADOOP_CONF_DIR"), "core-site.xml"));
		Connection connection = ConnectionFactory.createConnection(config);
		Table table = connection.getTable(TableName.valueOf("mytable"));
		try {
			// table.get(...);
		} finally {
			table.close();
			connection.close();
		}

		config.set("hbase.zookeeper.quorum", "10.255.209.211");
		config.set("hbase.zookeeper.property.clientPort", "2182");
		config.set("zookeeper.znode.parent", "/hbase");
		HbaseDaoImpl.setConf(config);
		HbaseDaoImpl.createSchemaTables(config);
		PageClickInfo info = new PageClickInfo();
		info.setId(1L);
		info.setPermanentId("1356425556568");
		info.setCurrentPage("1010");
		HbaseDaoImpl.insert(info);
		// HbaseDaoImpl.modifySchema(config);
		HbaseDaoImpl.listTables();
		HbaseDaoImpl.getData("1");
	}
}
