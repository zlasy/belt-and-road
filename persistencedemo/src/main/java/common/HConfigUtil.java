package common;

import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;


public class HConfigUtil {
	public static final String HBASE_CONFIG = "/hbase-site.xml";
	
	public static URL getHBaseUrl(){
		return HConfigUtil.class.getResource(HBASE_CONFIG);
	}
	public static HBaseConfiguration makeHBaseConfig(){
		return (HBaseConfiguration) HBaseConfiguration.create();
	}
	
	public static Configuration makeConfig(){
		return new Configuration();
	}
	
}