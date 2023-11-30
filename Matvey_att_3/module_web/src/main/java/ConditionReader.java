import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ConditionReader {


    public static Map<String, Object> read(MappingCls mappingCls, HttpServletRequest request, Class c) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        return read(mappingCls, request,c,"");
    }
    public static Map<String, Object> read(MappingCls mappingCls, HttpServletRequest request, Class c, String prefix) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        Map<String, Object> map = new HashMap<>();

        for (Field f:mappingCls.getFieldsByClass(c)) {
            String val = request.getParameter(prefix + f.getName());
            if(val == null)
                continue;
            Object value = mappingCls.castVlaue(val, f.getType());
            map.put(f.getName(), value);
        }

        return map;
    }


}
