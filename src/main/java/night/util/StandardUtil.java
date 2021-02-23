package night.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

/**
 *  包含了整个包内可直接调用的标准工具
 * @author dx_hualuo
 */
@SuppressWarnings("ALL")
public class StandardUtil {
    /**
     *  判断一个对象是否为空
     * @param obj 对象
     * @return 是否为空
     */
    public static boolean isEmpty(Object obj){
        if (obj == null) {return true;}
        if (obj instanceof CharSequence) {return ((CharSequence) obj).length() == 0;}
        if (obj instanceof Collection) {return ((Collection) obj).isEmpty();}
        if (obj instanceof Map) {return ((Map) obj).isEmpty();}
        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (Object o : object) {
                if (!isEmpty(o)) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public static void readMe(){
        StringBuilder builder = new StringBuilder();
        builder.append("这个类包含了一堆零散的工具方法，因为方法特别零散，不适合单建一个类，才有了这个类！").append("\n");
        builder.append("实际上这个包就很逗，我这整个类库都是个工具类库，工具类库里的工具？emmmmmm").append("\n");
        builder.append("工具里的工具，这就很Nice！").append("\n");
        System.out.print(builder);
    }
}
