package persistencedemo.dao;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import lombok.extern.slf4j.Slf4j;
import persistencedemo.dto.PageClickInfo;

/**
 * HBase DBO
 * 
 * @author zhangle
 */
@Slf4j
public abstract class AbstractHBaseDao {
	private Configuration config = null;
	protected Connection connection = null;

	public AbstractHBaseDao() {
		try {
			config = HBaseConfiguration.create();
			connection = ConnectionFactory.createConnection(config);
		} catch (IOException e) {
			log.error("failed to create connection",e);
		}
	}

	public AbstractHBaseDao(Configuration config) {
		try {
			this.config = config;
			connection = ConnectionFactory.createConnection(config);
		} catch (IOException e) {
			log.error("failed to create connection",e);
		}
	}

	public Configuration getConfig() {
		return config;
	}



	/**
	 * find out table is already exist.
	 * 
	 * @param table
	 * @return
	 */
	public boolean isTableExist(final String table) {
		boolean isExist = false;
		try (Admin admin = ConnectionFactory.createConnection().getAdmin()){
			isExist = admin.tableExists(TableName.valueOf(table));
		} catch (Exception e) {
			log.error("HBaseAdmin error", e);
		}
		return isExist;
	}

	public void createOrOverwrite(Admin admin, HTableDescriptor table) throws IOException {
		if (admin.tableExists(table.getTableName())) {
			admin.disableTable(table.getTableName());
			admin.deleteTable(table.getTableName());
		}
		admin.createTable(table);
	}

	public void createSchemaTables(String tablename, String... columnFamily) throws IOException {
		try ( Admin admin = connection.getAdmin()) {

			HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tablename));
			for (String cf : columnFamily) {
				HColumnDescriptor col = new HColumnDescriptor(cf.getBytes());
				table.addFamily(col);
			}

			System.out.print("Creating table. " + columnFamily);
			createOrOverwrite(admin, table);
			System.out.println(" Done.");
		}
	}
	
	public abstract void insert(PageClickInfo t);
	
	public abstract Object select(String id);

}