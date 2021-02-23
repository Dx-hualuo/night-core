package night.io;

import java.io.*;
import java.util.Properties;

/**
 *  配置文件管理器
 * @author dx_hualuo
 */
public class PropertiesManager extends Properties{

    /**
     *  通过绝对路径path来读取配置文件
     * @param path 配置文件的绝对路径
     */
    public PropertiesManager(String path) {
        this(new File(path));
    }

    /**
     *  通过File对象来读取配置文件
     * @param file 配置文件
     */
    public PropertiesManager(File file){
        this(Files.getInputStream(file));
    }

    /**
     *  通过流读取配置文件
     * @param stream 配置文件
     */
    public PropertiesManager(InputStream stream){
        try {
            this.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  获得配置文件指定key的value值
     * @param key 键
     * @return 值
     */
    public String getValue(String key){
        return this.getProperty(key);
    }

    /**
     *  临时设置配置文件指定key的value值，如果没有则添加。
     * @param key 键
     * @param value 值
     */
    public void setValue(String key,String value){
        this.setProperty(key, value);
    }

    /**
     *  保存到文件
     * @param path 文件路径
     * @throws IOException 保存时可能发生的IO异常
     */
    public void saveToFile(String path) throws IOException {
        FileOutputStream os = new FileOutputStream(new File(path));
        this.store(os, null);
    }
}
