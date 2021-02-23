package night.data.json.tools;

import com.alibaba.fastjson.JSON;
import night.data.json.SupportJsonConversion;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 *  JSP的值转换为JavaScript变量的转换器
 *      凭兴趣写的，实际使用会不安全
 * @author dx_hualuo
 */
public class JavaScriptConverter {
    /**
     *  转换request参数
     * @param request 请求request
     * @return 参数JSON
     */
    public static String convertParameter(HttpServletRequest request){
        StringBuilder builder = new StringBuilder("{");
        ArrayList<String> names = new ArrayList<>();
        Enumeration<String> attributeNames = request.getParameterNames();
        while(attributeNames.hasMoreElements()){
            names.add(attributeNames.nextElement());
        }
        for (String name : names) {
            builder.append("'").append(name.replace('.','_')).append("':");
            try{
                builder.append(JSON.toJSONString(request.getParameterValues(name).length == 1? request.getParameter(name): request.getParameterValues(name))).append(",");
            }catch (Exception e){
                builder.delete(builder.length()- (name.length()+3), builder.length());
            }
        }
        builder.append("}");
        return builder.toString();
    }
    /**
     *  转换request属性
     * @param request 请求request
     * @return 参数JSON
     */
    public static String convertAttribute(HttpServletRequest request){
        StringBuilder builder = new StringBuilder("{");
        ArrayList<String> names = new ArrayList<>();
        Enumeration<String> attributeNames = request.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            names.add(attributeNames.nextElement());
        }
        for (String name : names) {
            builder.append("'").append(name.replace('.','_')).append("':");
            try{
                builder.append(JSON.toJSONString(request.getAttribute(name))).append(",");
            }catch (Exception e){
                builder.delete(builder.length()- (name.length()+3), builder.length());
            }
        }
        builder.append("}");
        return builder.toString();
    }
    /**
     *  转换session属性
     * @param session session
     * @return 参数JSON
     */
    public static String convertAttribute(HttpSession session){
        StringBuilder builder = new StringBuilder("{");
        ArrayList<String> names = new ArrayList<>();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            names.add(attributeNames.nextElement());
        }
        for (String name : names) {
            builder.append("'").append(name.replace('.','_')).append("':");
            try{
                builder.append(JSON.toJSONString(session.getAttribute(name))).append(",");
            }catch (Exception e){
                builder.delete(builder.length()- (name.length()+3), builder.length());
            }
        }
        builder.append("}");
        return builder.toString();
    }
    /**
     *  转换application属性
     * @param application application
     * @return 参数JSON
     */
    public static String convertAttribute(ServletContext application){
        StringBuilder builder = new StringBuilder("{");
        ArrayList<String> names = new ArrayList<>();
        Enumeration<String> attributeNames = application.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            names.add(attributeNames.nextElement());
        }
        for (String name : names) {
            builder.append("'").append(name.replace('.','_')).append("':");
            try{
                builder.append(JSON.toJSONString(application.getAttribute(name))).append(",");
            }catch (Exception e){
                builder.delete(builder.length()- (name.length()+3), builder.length());
            }
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     *  将对象转换为JSONString
     * @param obj obj
     * @return 结果
     */
    public static String convert(Object obj){
        if(obj instanceof SupportJsonConversion){
            return ((SupportJsonConversion)obj).toJson();
        }
        if(obj instanceof HttpServletRequest){
            return "{" + "\"parameter\":" + convertParameter((HttpServletRequest) obj) + "," + "\"attribute\":" + convertAttribute((HttpServletRequest) obj) + "," + "}";
        }else if(obj instanceof HttpSession){
            return "{" + "\"attribute\":" + convertAttribute((HttpSession) obj) + "}";
        }else if(obj instanceof ServletContext){
            return "{" + "\"attribute\":" + convertAttribute((ServletContext) obj) + "}";
        }else{
            return JSON.toJSONString(obj);
        }
    }
}
