package tsou.cn.lib_hxgokhttp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/20 0020.
 */

public class URLUtil {
    /**
     * 拼接参数
     */
    public static String jointParams(String url,Map<String,Object> params){
        if (params==null||params.size()<=0){
            return url;
        }
        StringBuffer stringBuffer=new StringBuffer(url);
        if (!url.contains("?")){
            stringBuffer.append("?");
        }else {
            if (!url.endsWith("?")){
                stringBuffer.append("&");
            }
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            stringBuffer.append(entry.getKey()+"="+entry.getValue()+"&");
        }
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        return stringBuffer.toString();
    }
    /**
     * 解析一个类上面的class信息
     */
    public static Class<?> analysisClazzInfo(Object object){
        Type getType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) getType).getActualTypeArguments();
        return (Class<?>) params[0];
    }
}
