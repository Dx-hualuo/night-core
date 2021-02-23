package night.data.util;

import com.alibaba.fastjson.JSON;

/**
 *  ToJSON
 * @author night
 * @date 2021/1/22 15:29
 */
public interface ToJSON {
    /**
     *  给指定类加入toJson功能
     * @return JSON 字符串
     */
    default String toJSON(){
        return JSON.toJSONString(this);
    }
}
