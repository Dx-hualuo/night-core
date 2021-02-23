package night;

import night.platform.SystemPlatform;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author night
 */
public class App {
    public static void main(String[] args) throws Exception {
//        ProcessBuilder processBuilder = new ProcessBuilder("java","-jar","-Xms500m", "-Xmx1000m", "C:\\Users\\17868\\Desktop\\1.16.5\\server.jar");
//        processBuilder.directory(new File("C:\\Users\\17868\\Desktop\\1.16.5\\"));
//        processBuilder.redirectErrorStream(true);
//        Process start = processBuilder.start();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream(), Charset.forName("GBK")));
//        String line = null;
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(start.getOutputStream(), Charset.forName("GBK")));
//        Thread outThread = new Thread(()->{
//            String str = null;
//            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
//            Scanner scanner = new Scanner(System.in);
//            while(start.isAlive()){
//                try {
//                    str = console.readLine();
//                    if(start.isAlive()){
//                        writer.write(str);
//                        writer.newLine();
//                        writer.flush();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        outThread.start();
//        while (start.isAlive() && (line = bufferedReader.readLine()) != null){
//            System.out.println(line);
//        }
    }
}
