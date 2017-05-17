package persistencedemo.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import persistencedemo.dao.AbstractHBaseDao;
import persistencedemo.dto.PageClickInfo;

@Service
@Slf4j
public class HbaseFactoryDaoImpl extends AbstractHBaseDao {

    private final String tableName = "page_click_info";
    private final String DefaultCf = "base";

    @Override
    public Object select(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(PageClickInfo t) {
        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            Put put = new Put(Bytes.toBytes(String.valueOf(t.getId())));
            put.addColumn(Bytes.toBytes(DefaultCf), Bytes.toBytes("permanent_id"), Bytes.toBytes(t.getPermanentId()));
            put.addColumn(Bytes.toBytes(DefaultCf), Bytes.toBytes("current_page"), Bytes.toBytes(t.getCurrentPage()));
            table.put(put);
        } catch (IOException e) {
            log.error("failed to insert " + t, e);
        }
    }

    public void insertBatch(List<PageClickInfo> list) {
        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            List<Put> putList = new ArrayList<Put>();
            for (PageClickInfo t : list) {
                Put put = new Put(Bytes.toBytes(String.valueOf(t.getId())));
                put.addColumn(Bytes.toBytes(DefaultCf), Bytes.toBytes("permanent_id"),
                        Bytes.toBytes(t.getPermanentId()));
                put.addColumn(Bytes.toBytes(DefaultCf), Bytes.toBytes("current_page"),
                        Bytes.toBytes(t.getCurrentPage()));
                put.addColumn(Bytes.toBytes(DefaultCf), Bytes.toBytes("action_date"),
                        Bytes.toBytes(DateFormatUtils.format(t.getActionDate(), "yyyy-MM-dd HH:mm:ss.S")));
                putList.add(put);
            }
            table.put(putList);
        } catch (IOException e) {
            log.error("failed to insertBatch " + list.size(), e);
        }
    }


    public void getData(String rowkey) {
        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
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
        } catch (IOException e) {
            log.error("failed to getData,rowkey:" + rowkey, e);
        }
    }

    public void delete(String rowkey) {
        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            Delete delete = new Delete(Bytes.toBytes(rowkey));
            table.delete(delete);
        } catch (IOException e) {
            log.error("failed to delete " + rowkey, e);
        }
    }
}
