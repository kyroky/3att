import org.example.model.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappingCls {

    private List<Field> agentFealds;
    private List<Field> gameFealds;
    private List<Field> locationFealds;
    private List<Field> playerFealds;
    private List<Field> skinFealds;

    public MappingCls() {
        this.agentFealds = getDefultFields(Agent.class);
        this.gameFealds = getDefultFields(Game.class);
        this.locationFealds = getDefultFields(Location.class);
        this.playerFealds = getDefultFields(Player.class);
        this.skinFealds = getDefultFields(Skin.class);
    }



    public List<Map<String, Object>> toMap(ResultSet resultSet, Class clz) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, SQLException {
        List<Field> fields = getFieldsByClass(clz);
        List<Map<String, Object>> maps = new ArrayList<>();

        while (resultSet.next()){
            Map<String, Object> map = new HashMap<>();
            for (Field f:fields) {
                map.put(
                        f.getName(),
                        castVlaue(resultSet.getString(f.getName()), f.getType())
                );
            }
            maps.add(map);
        }
        return maps;
    }

    public Map<String, Object> toMap(Object obj) throws Exception {
        List<Field> fields = getFieldsByClass(obj.getClass());
        System.out.println(fields);
        Map<String, Object> map = new HashMap<>();
        for (Field f : fields) {
            map.put(
                    f.getName(),
                    getFieldValue(obj, f.getName())
            );
        }


        return map;
    }
    public List<Object> fromMap(List<Map<String, Object>> maps, Class clz) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, SQLException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
        List<Object> objects = new ArrayList<>();

        for (Map<String, Object> map:maps) {
            Object obj = createObj(clz);
            for (String feldName:map.keySet()) {
                Object value = map.getOrDefault(feldName, null);
                if(value == null)
                    continue;
                setFieldValue(obj, feldName, value);
            }
            objects.add(obj);
        }
        return objects;
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Method getter = clazz.getMethod("get" + capitalizeFirstLetter(fieldName));
        return getter.invoke(object);
    }
    private String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


    public Object createObj(Class clz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class c = Class.forName(String.format("org.example.%s", (clz.getSimpleName())));
        return c.newInstance();
    }

    public Object createObj(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class c = Class.forName(String.format("org.example.%s", name));
        return c.newInstance();
    }

    public Class createClass(String name) throws ClassNotFoundException {
        Class c = Class.forName(String.format("org.example.%s", name));
        return c;
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
    public Object castVlaue(String val, Class clz){
        if(clz.equals(Long.class) || clz.equals(long.class)){
            return Long.parseLong(val);
        } else if (clz.equals(Integer.class) || clz.equals(int.class) ) {
            return Integer.parseInt(val);
        } else if (clz.equals(String.class)) {
            return val+"";
        }
        return null;
    }

    private List<Field> getDefultFields(Class cls){
        List<Field> fields = new ArrayList<>();

        for (Field f:cls.getDeclaredFields()) {
            Class<?> type = f.getType();
            if (type.equals(Long.class) ||
                    type.equals(String.class) ||
                    type.equals(Integer.class) ||
                    type.equals(int.class) ||
                    type.equals(long.class)
            )
                fields.add(f);
        }
        return fields;
    }

    public List<Field> getAgentFealds() {
        return agentFealds;
    }

    public List<Field> getGameFealds() {
        return gameFealds;
    }

    public List<Field> getLocationFealds() {
        return locationFealds;
    }

    public List<Field> getPlayerFealds() {
        return playerFealds;
    }

    public List<Field> getSkinFealds() {
        return skinFealds;
    }

    public List<Field> getFieldsByClass(Class clz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = this.getClass();
        Method getter = clazz.getMethod("get" + clz.getSimpleName()+"Fealds");
        return (List<Field>) getter.invoke(this);
    }
}
