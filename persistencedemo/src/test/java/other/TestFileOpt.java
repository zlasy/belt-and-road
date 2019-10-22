package other;

import java.io.File;

public class TestFileOpt {

    public static void main(String[] args) {
        getFileListame("I:\\张乐文件\\backup\\F");  
    }

    public static void getFileListame(String strPath) {  
        File dir = new File(strPath);  
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组 
        if (files != null) {  
            for (int i = 0; i < files.length; i++) {  
//                System.out.println(files[i].getAbsolutePath());  
                if (files[i].isDirectory()) { // 判断是文件还是文件夹  
                    getFileListame(files[i].getAbsolutePath()); // 获取文件绝对路径  
//                    System.out.println(files[i].getAbsolutePath() + files[i].getName());  
                } else {
                    String suffix = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1);
                    if (!suffix.equals("db") && !suffix.equals("ass") && !suffix.equals("jpg") && !suffix.equals("torrent")) {
                        String filename = files[i].getName();
                        if (filename.contains("SOE")) {
                            System.out.println(files[i].getAbsolutePath()); 
                        }
                    }
                }
            }
        }
    }
}
