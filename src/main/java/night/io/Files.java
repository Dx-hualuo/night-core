package night.io;
import java.io.*;

/**
 *  提供文件操作
 * @author dx_hualuo
 */

public class Files {
    /**
     *   判断文件或目录是否存在
     * @param url 地址
     * @return boolean
     */
	public static boolean isExist(String url){
	    return new File(url).exists();
    }

    /**
     *      获得文件输出流
     * @param url   文件地址
     * @return  FileOutputStream对象
     */
    public static FileOutputStream getFileOutputStream(String url, boolean append)  {
        File file = new File(url);
        if(file.exists()){
            try {
                return new FileOutputStream(file, append);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        int index;
        if((index = url.lastIndexOf("/")) > -1){
            String substring = url.substring(index);
            File parentFile;
            if(substring.lastIndexOf(".") > -1 && !((parentFile = file.getParentFile()).exists()) && !parentFile.mkdirs()){
                throw new RuntimeException("文件路径创建失败！");
            }
        }else if((index = url.lastIndexOf("\\")) > -1){
            String substring = url.substring(index);
            File parentFile;
            if(substring.lastIndexOf(".") > -1 && !((parentFile = file.getParentFile()).exists()) && !parentFile.mkdirs()){
                throw new RuntimeException("文件路径创建失败！");
            }
        }
        try {
            return new FileOutputStream(file, append);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  获得文件input流并在里面处理异常
     * @param file 文件
     * @return FileInputStream
     */
    public static InputStream getInputStream(File file){
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  获得文件输入流
     * @param url   文件地址
     * @return  FileOutputStream对象
     */
    public static FileInputStream getFileInputStream(String url) {
        try {
            return new FileInputStream(url);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  创建目录，包括子目录
     * @param url   完整目录名
     * @return  是否创建成功
     */
    public static boolean mkdirs(String url){
        return new File(url).mkdirs();
    }

    /**
     *  获得指定文件的扩展名
     * @param file 文件对象
     * @return 扩展名
     */
    public static String getFileExtensionName(File file){
        if(file == null){
            return null;
        }
        return getFileExtensionName(file.getName());
    }

    /**
     *  从文件名中提取扩展名
     * @param fileName 文件名
     * @return 扩展名
     */
    public static String getFileExtensionName(String fileName){
        if(fileName == null){
            return null;
        }
        int index = fileName.lastIndexOf(".");
        if(index == -1 || fileName.length() - 1 == index){
            return null;
        }else{
            return fileName.substring(index + 1);
        }
    }
}
