package common;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil {
    private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class.getCanonicalName());

    private static Properties props = new Properties();

    static {
        initAllConfig();
    }

    /**
     * 加载应用配置文件
     * 
     * @Title: initAllConfig
     * @Description: 加载应用配置文件
     */
    private static void initAllConfig() {
        String path = "";
        try {
            path = PathUtil.getTomcatConfPath();
            initAllConfig(path);
        } catch (Exception e) {
            logger.debug("tomcat.conf加载失败，path：" + path);
            try {
                path = PathUtil.getAppConfPath();
                initAllConfig(path);
            } catch (Exception ex) {
                logger.error("conf加载失败，path：" + path, ex);
            }
        }
    }

    /**
     * 加载.properties 配置文件
     * 
     * @Title: initAllConfig
     * @Description: 加载.properties 配置文件
     * @param path 路径
     * @throws IOException
     */
    private static void initAllConfig(String path) throws IOException, RuntimeException {
        Properties tempProps = new Properties();
        File configFolder = new File(path);
        FilenameFilter filter = new PropertiesFileFilter();
        File[] propertiesFiles = configFolder.listFiles(filter);
        for (File propertiesFile : propertiesFiles)
            if ((!propertiesFile.getName().toLowerCase().contains("db"))
                    && (!propertiesFile.getName().toLowerCase().contains("ds"))
                    && (!propertiesFile.getName().toLowerCase().contains("datasource"))) {
                tempProps = new Properties();
                InputStream in = new BufferedInputStream(new FileInputStream(propertiesFile));

                tempProps.load(in);
                props.putAll(tempProps);
            }
    }

    /**
     * 
     * @Title: getSettings
     * @Description: 从conf目录下的所有properties配置文件中获取配置
     * @param key properties文件中的键
     * @return value
     */
    public static String getSettings(String key) {
        return getSettings(key, "");
    }


    /**
     * 从conf目录下的所有properties配置文件中获取配置
     * 
     * @Title: getSettings
     * @Description: 从conf目录下的所有properties配置文件中获取配置
     * @param key 键值
     * @param defaultValue 默认值
     * @return
     */
    public static String getSettings(String key, String defaultValue) {
        try {
            String result = props.getProperty(key);
            if (result == null) {
                return defaultValue;
            }
            return result;
        } catch (Exception ex) {}
        return defaultValue;
    }


}


class PropertiesFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith("properties");
    }
}
