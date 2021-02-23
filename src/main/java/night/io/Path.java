package night.io;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 *  管理路径
 * @author dx_hualuo
 */
public class Path {
    /**
     *   获得Web工程根目录真实路径
     * @param httpServletRequest request
     * @return  Web根目录真实路径
     */
    public static String webProjectRealPath(HttpServletRequest httpServletRequest){
        return httpServletRequest.getServletContext().getRealPath("/");
    }
    /**
     *  获得项目的resource路径
     * @return resource路径
     */
    public static String resourcePath(){
        return resourcePath("");
    }
    /**
     *   获得项目的resource路径
     * @return  resource路径
     */
    public static String resourcePath(String fileName){
        URL resourceUrl;
        String resultPath;
        try {
            resourceUrl = Class.forName("dx.Init").getClassLoader().getResource("");
        } catch (ClassNotFoundException e) {
            resourceUrl = Path.class.getClassLoader().getResource("");
        }
        assert resourceUrl != null;
        resultPath = resourceUrl.getFile();
        resultPath = resultPath.substring(1) + fileName;
        File file = new File(resultPath);
        if(!file.exists()){
            File parentFile = file.getParentFile();
            String newFileName = parentFile.getParentFile().getPath()+"\\classes\\"+fileName;
            if(parentFile.exists() && (parentFile.getName().equals("test-classes"))){
                file = new File(newFileName);
                if(file.exists()){
                    return newFileName;
                }
            }
        }
        return resultPath;
    }

    /**
     *  获得项目resource目录下指定文件的输入流
     * @param fileName 文件名
     * @return 输入流
     */
    public static InputStream resourcePathAsStream(String fileName){
        return Files.getFileInputStream(resourcePath(fileName));
    }

    /**
     *  获得IDEA开发时Web工程的resource路径（可能不存在）
     * @param request HttpServletRequest
     * @return Web工程的Resource的文件夹
     */
    public static String webDevelopmentResourcePath(HttpServletRequest request){
        String path = webProjectRealPath(request);
        path += "\\src\\main\\resources";
        if (Files.isExist(path)) {
            return path;
        }
        return null;
    }

    /**
     *  IDEA 开发时获得项目路径（其他IDE可能不生效）
     * @param request HttpServletRequest
     * @return IDEA项目路径
     */
    public static String webDevelopmentProjectPath(HttpServletRequest request){
        String path = webProjectRealPath(request);
        for (int i = 0; i < 3; i++) {
            path = path.substring(0, path.lastIndexOf("\\"));
        }
        return path;
    }
}
