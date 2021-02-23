package night.io;

import night.io.exception.NotJarFileException;

import java.io.File;

/**
 *  JarFile
 * @author night
 * @date 2021/1/24 19:43
 */
public class JarFile extends File {
    /**
     *  Jar文件的扩展名
     */
    public static final String JAR_FILE_EXTENSION = "jar";

    /**
     *  通过指定URL，读取Jar文件
     * @param pathname 路径
     */
    public JarFile(String pathname) {
        super(pathname);
        if (!JAR_FILE_EXTENSION.equalsIgnoreCase(Files.getFileExtensionName(this))) {
            throw new NotJarFileException();
        }
    }

}
