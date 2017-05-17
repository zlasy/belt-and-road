package persistencedemo.dao.impl;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.exceptions.HBaseException;
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import lombok.Setter;
import persistencedemo.dao.BaseDao;
import persistencedemo.dto.PageClickInfo;

@Repository
public class HbaseDaoImpl implements BaseDao<PageClickInfo> {

	@Setter
	private static Configuration conf;

	@Override
	public List<PageClickInfo> findByConfition(PageClickInfo param) {
		return null;
	}

	@Override
	public void save(PageClickInfo t) {

	}

	private static final String TABLE_NAME = "PAGE_CLICK_INFO";
	private static final String CF_DEFAULT = "DEFAULT_CF";

	public static void createOrOverwrite(Admin admin, HTableDescriptor table) throws IOException {
		if (admin.tableExists(table.getTableName())) {
			admin.disableTable(table.getTableName());
			admin.deleteTable(table.getTableName());
		}
		admin.createTable(table);
	}

	public static void createSchemaTables(Configuration config) throws IOException {
		try (Connection connection = ConnectionFactory.createConnection(config); Admin admin = connection.getAdmin()) {

			HTableDescriptor table = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
			table.addFamily(new HColumnDescriptor(CF_DEFAULT).setCompressionType(Algorithm.NONE));

			System.out.print("Creating table. ");
			createOrOverwrite(admin, table);
			System.out.println(" Done.");
		}
	}

	public static void modifySchema(Configuration config) throws IOException, HBaseException {
		try (Connection connection = ConnectionFactory.createConnection(config); Admin admin = connection.getAdmin()) {

			TableName tableName = TableName.valueOf(TABLE_NAME);
			if (!admin.tableExists(tableName)) {
				System.out.println("Table does not exist.");
				throw new HBaseException("table does not exist");
			}

			HTableDescriptor table = admin.getTableDescriptor(tableName);

			// Update existing table
			HColumnDescriptor newColumn = new HColumnDescriptor("NEWCF");
			newColumn.setCompactionCompressionType(Algorithm.GZ);
			newColumn.setMaxVersions(HConstants.ALL_VERSIONS);
			admin.addColumn(tableName, newColumn);

			// Update existing column family
			HColumnDescriptor existingColumn = new HColumnDescriptor(CF_DEFAULT);
			existingColumn.setCompactionCompressionType(Algorithm.GZ);
			existingColumn.setMaxVersions(HConstants.ALL_VERSIONS);
			table.modifyFamily(existingColumn);
			admin.modifyTable(tableName, table);

			// Disable an existing table
			admin.disableTable(tableName);

			// Delete an existing column family
			admin.deleteColumn(tableName, CF_DEFAULT.getBytes("UTF-8"));

			// Delete a table (Need to be disabled first)
			admin.deleteTable(tableName);
		}
	}

	public static void insert(PageClickInfo t) {
		try (Connection connection = ConnectionFactory.createConnection(conf)) {
			Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
			Put put = new Put(Bytes.toBytes(String.valueOf(t.getId())));
			put.addColumn(Bytes.toBytes(CF_DEFAULT), Bytes.toBytes("permanent_id"), Bytes.toBytes(t.getPermanentId()));
			put.addColumn(Bytes.toBytes(CF_DEFAULT), Bytes.toBytes("current_page"), Bytes.toBytes(t.getCurrentPage()));
			table.put(put);

			// 批量插入
			/*
			 * List<Put> putList = new ArrayList<Put>(); puts.add(put); table.put(putList);
			 */
			table.close();
		} catch (Exception e) {}
	}

	public static void listTables() throws IOException {
		try (Connection connection = ConnectionFactory.createConnection(conf); Admin admin = connection.getAdmin()) {
			HTableDescriptor hTableDescriptors[] = admin.listTables();
			for (HTableDescriptor hTableDescriptor : hTableDescriptors) {
				System.out.println(hTableDescriptor.getNameAsString());
			}
		}
	}

	public static void getData(String rowkey) throws IOException {
		try (Connection connection = ConnectionFactory.createConnection(conf); Admin admin = connection.getAdmin()) {
			Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
			Get get = new Get(Bytes.toBytes(rowkey));
			// 获取指定列族数据
			// get.addFamily(Bytes.toBytes(colFamily));
			// 获取指定列数据
			// get.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
			Result result = table.get(get);

			Cell[] cells = result.rawCells();
			for (Cell cell : cells) {
				System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
				System.out.println("Timetamp:" + cell.getTimestamp() + " ");
				System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
				System.out.println("row Name:" + new String(CellUtil.cloneQualifier(cell)) + " ");
				System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
			}
			table.close();
		}
	}

	// 批量查找数据
	public static void scanData(String tableName, String startRow, String stopRow) throws IOException {
		try (Connection connection = ConnectionFactory.createConnection(conf); Admin admin = connection.getAdmin()) {
			Table table = connection.getTable(TableName.valueOf(tableName));
			Scan scan = new Scan();
			// scan.setStartRow(Bytes.toBytes(startRow));
			// scan.setStopRow(Bytes.toBytes(stopRow));
			ResultScanner resultScanner = table.getScanner(scan);
			for (Result result : resultScanner) {
				Cell[] cells = result.rawCells();
				for (Cell cell : cells) {
					System.out.println("RowName:" + new String(CellUtil.cloneRow(cell)) + " ");
					System.out.println("Timetamp:" + cell.getTimestamp() + " ");
					System.out.println("column Family:" + new String(CellUtil.cloneFamily(cell)) + " ");
					System.out.println("row Name:" + new String(CellUtil.cloneQualifier(cell)) + " ");
					System.out.println("value:" + new String(CellUtil.cloneValue(cell)) + " ");
				}
			}
			table.close();
		}
	}
}
