package night.platform;

/**
 *  关于运行环境配置的类
 * @author dx_hualuo
 */
public class JavaVirtualMachine {
    /**
     *  允许虚拟机自动保存生成的文件
     */
    public static void enableSaveGeneratedFiles(){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }

    /**
     *  不允许虚拟机自动保存生成的文件
     */
    public static void disableSaveGeneratedFiles(){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "false");
    }
}
