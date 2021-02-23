package night.net;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *  对于端口的工具类 Port
 * @author night
 * @date 2021/1/24 20:38
 */
public class Port {
    /**
     *  获得指定端口是否被占用
     * @param port 指定端口
     * @return 是否被占用
     */
    public static boolean isFree(int port){
        try {
            ServerSocket socket = new ServerSocket(port);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static int getFree(int startPort, int endPort){
        return 1;
    }
}
