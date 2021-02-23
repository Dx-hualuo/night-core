package night.reflect.entity;

import night.data.collection.MappingList;
import night.reflect.OverloadMethod;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dx_hualuo
 */
public class Methods implements Serializable {
    private boolean complete = false;
    private final MappingList<String, Method> method;
    private final Set<Method> values;

    public Methods() {
        this.method = new MappingList<>();
        this.values = new HashSet<>();
    }

    public Methods(Method method) {
        this.method = new MappingList<>();
        this.values = new HashSet<>();
        addMethod(method);
    }

    public Methods(Method[] method, boolean complete) {
        this.method = new MappingList<>();
        this.values = new HashSet<>();
        addMethod(method);
        this.complete = complete;
    }

    public Methods(Method[] methods) {
        this.method = new MappingList<>();
        this.values = new HashSet<>();
        addMethod(methods);
    }

    public boolean isComplete(){
        return complete;
    }

    public void setComplete(){
        complete = true;
    }

    public Method[] getMethod(String methodName) throws NoSuchMethodException {
        List<Method> list = method.get(methodName);
        if(list == null || list.size() == 0){
            throw new NoSuchMethodException();
        }
        Method[] me = new Method[list.size()];
        me = list.toArray(me);
        return me;
    }
    public Method getMethod(String methodName, Class<?>... parType) throws NoSuchMethodException {
        return OverloadMethod.getMethod(getMethod(methodName), parType);
    }

    public Method[] getMethods(){
        Method[] result = new Method[values.size()];
        result = values.toArray(result);
        return result;
    }

    public void addMethod(Method method){
        if(isComplete()){
            return;
        }
        this.method.put(method.getName(), method);
        this.values.add(method);
    }

    public void addMethod(Method[] method){
        for (Method me: method) {
            addMethod(me);
        }
    }
}
