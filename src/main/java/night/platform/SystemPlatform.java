package night.platform;

import java.util.Locale;

/**
 *  SystemPlatform 枚举运行的系统平台
 * @author night
 * @date 2021/1/26 18:00
 */
public class SystemPlatform {
    /**
     *  系统平台枚举
     */
    public enum Platform{
        /**
         *  Windows平台
         */
        Windows("Windows"),
        /**
         *  Linux平台
         */
        Linux("Linux"),
        /**
         *  Mac OS平台
         */
        Mac_OS("Mac OS"),
        /**
         *  其他平台
         */
        Another("Another");

        Platform(String name){
            this.name = name;
        }
        private final String name;

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     *  本地平台类型
     */
    private static final Platform localPlatform = getLocalPlatform();

    /**
     *  判断系统平台是否为给定平台
     * @param os 给定平台
     * @return 是否是给定平台
     */
    public static boolean is(Platform os){
        return localPlatform == os;
    }

    /**
     *  获得当前本地平台
     * @return 平台
     */
    public static Platform getLocalPlatform(){
        if(localPlatform != null){
            return localPlatform;
        }
        String os = System.getProperty("os.name");
        os = os.split(" ")[0].toLowerCase(Locale.ROOT);
        switch (os){
            case "windows":
                return Platform.Windows;
            case "linux":
                return Platform.Linux;
            case "mac":
                return Platform.Mac_OS;
            default:
                return Platform.Another;
        }
    }

}
