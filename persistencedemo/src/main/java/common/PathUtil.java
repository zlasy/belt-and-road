package common;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class PathUtil {
  private static final Logger log = LoggerFactory.getLogger(PathUtil.class);

  /**
   * 获取App运行所在路径<br>
   * 注意：如果上级目录为bin或者lib，获取的是上级目录
   * 
   * @return
   */
  public static String getAppPath() {
    String path = getPropAppPath();
    if (path.endsWith("\\lib")) {
      path = path.substring(0, path.length() - 4);
    }
    if (path.endsWith("/lib")) {
      path = path.substring(0, path.length() - 4);
    }
    if (path.endsWith(":lib")) {
      path = path.substring(0, path.length() - 4);
    }
    if (path.endsWith("\\bin")) {
      path = path.substring(0, path.length() - 4);
    }
    if (path.endsWith("/bin")) {
      path = path.substring(0, path.length() - 4);
    }
    if (path.endsWith(":bin")) {
      path = path.substring(0, path.length() - 4);
    }
    return path;
  }

  /**
   * 获取App运行所在路径。<br>
   * 相对于getAppPath()，该方法获得是原生的路径，没有任何后期处理。
   * 
   * @return
   */
  public static String getPropAppPath() {
    String path = "";
    try {
      File directory = new File("");
      path = directory.getCanonicalPath();
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
    return path;
  }

  /**
   * 获取应用conf路径。
   * 
   * @return
   */
  public static String getAppConfPath() {
    return getAppPath().concat(getSeparator()).concat("conf");
  }

  public static String getTomcatConfPath() {
    return PathUtil.class.getClassLoader().getResource("").getPath().concat("conf");
  }

  /**
   * 获取路径分隔符（兼容windows和linux）。
   * 
   * @return
   */
  public static String getSeparator() {
    String se = System.getProperty("file.separator");
    return StringUtils.isEmpty(se) ? "\\" : se;
  }

  /**
   * 获取web项目class物理路径<br>
   * Thread.currentThread().getContextClassLoader().getResource("").getPath()
   */
  public static String getWebClassPath() {
    return Thread.currentThread().getContextClassLoader().getResource("").getPath();
  }

  /**
   * linux path 处理。<br>
   * 将路径中的"\"统一换成"/"。
   * 
   * @param path
   * @return
   */
  public static String getPath(String path) {
    String pathTemp = path;
    String osName = System.getProperty("os.name");
    if (!osName.toUpperCase().startsWith("WIN")) {
      pathTemp = pathTemp.replaceAll("\\\\", "/");
      pathTemp = pathTemp.replaceAll("\\/", "//");
    }
    return pathTemp;
  }
}
