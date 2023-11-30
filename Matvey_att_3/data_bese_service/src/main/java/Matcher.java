import org.example.model.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Matcher {
    private MappingCls mapper;
    private QuaeriesForDB quaeries;
    private ConnectionManeger connection;
    private Scanner scanner;

    public Matcher() {
        this.mapper = new MappingCls();
        this.quaeries = new QuaeriesForDB();
        this.connection = new ConnectionManeger();
        this.scanner = new Scanner(System.in);
    }


    private static final List<String> commands = List.of(
            "all",
            "find",
            "insert",
            "delete",
            "update"
    );
    private static final Map<String, Class> tables = Map.of(
            Agent.class.getSimpleName(), Agent.class,
            Game.class.getSimpleName(), Game.class,
            Location.class.getSimpleName(), Location.class,
            Player.class.getSimpleName(), Player.class,
            Skin.class.getSimpleName(), Skin.class
    );

    public void help(){
        System.out.println("**************************************************************");
        System.out.println("command list:");
        for (String command: commands) {
            System.out.println(command+" table_name");
        }
        System.out.println("help");
        System.out.println("tables");
        System.out.println("close");
        System.out.println("**************************************************************");
    }
    public void tables(){
        System.out.println("**************************************************************");
        System.out.println("Tables list:");
        System.out.println(tables.keySet());
        System.out.println("**************************************************************");
    }

    public void read() throws Exception {
        help();
        tables();
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equals("help"))
                help();
            else if (command.equals("tables")) {
                tables();
            } else if (command.equals("close")) {
                return;
            } else{
                compileCommand(command);
                System.out.println();
            }
        }

    }
    public void compileCommand(String command) throws Exception {
        String[] criterias = command.split("\\s+");
        criterias[0] = criterias[0].trim();
        criterias[1] = criterias[1].trim();
        int count = 0;
        if(!command.contains(criterias[0])) {
            System.out.println(String.format("COMMAND winh name <%s> not found", criterias[0]));
            count+=1;
        }
        if(!tables.keySet().contains(criterias[1])){
            System.out.println(String.format("TABLE with name <%s> not found", criterias[1]));
            count+=1;
        }
        if (count > 0)
            return;


        if(criterias[0].toLowerCase().trim().equals("all")) {
            String quaery = quaeries.findAll(criterias[1]);
            showMaps(mapper.toMap(
                    connection.executeQuaery(quaery),
                    tables.get(criterias[1])
            ));
        } else if (criterias[0].toLowerCase().trim().equals("find")) {
            Map<String, Object> map = getMap(tables.get(criterias[1]));
            String quaery = quaeries.find(
                    criterias[1],
                    map
            );
            System.out.println("condition");
            System.out.println(map);
            System.out.println();
            showMaps(mapper.toMap(
                    connection.executeQuaery(quaery),
                    tables.get(criterias[1])
            ));
        } else if (criterias[0].toLowerCase().trim().equals("insert")) {
            Map<String, Object> map = getMap(tables.get(criterias[1]));
            String quaery = quaeries.insert(
                    criterias[1],
                    map
            );
            System.out.println("condition");
            System.out.println(map);
            System.out.println();
            connection.execute(quaery);

        } else if (criterias[0].toLowerCase().trim().equals("delete")) {
            Map<String, Object> map = getMap(tables.get(criterias[1]));
            String quaery = quaeries.delete(
                    criterias[1],
                    map
            );
            System.out.println("condition");
            System.out.println(map);
            System.out.println();
            connection.execute(quaery);

        } else if (criterias[0].toLowerCase().trim().equals("update")) {
            System.out.println("LAST:");
            Map<String, Object> map = getMap(tables.get(criterias[1]));
            System.out.println();
            System.out.println("NEW:");
            Map<String, Object> newMap = getMap(tables.get(criterias[1]));
            System.out.println();
            System.out.println("condition");
            System.out.println(map);
            System.out.println(newMap);
            String quaery = quaeries.update(
                    criterias[1],
                    newMap,
                    map
            );
            connection.execute(quaery);
        }


    }

    public Map<String, Object> getMap(Class clz) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Map<String, Object> map = new HashMap<>();
        for (Field f: mapper.getFieldsByClass(clz)) {
            System.out.print(f.getName()+": ");
            String val = scanner.nextLine();
            if(val.equals(""))
                continue;
            map.put(
                    f.getName(),
                    mapper.castVlaue(val, f.getType())
            );
        }
        return map;
    }
    private String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private void showMaps(List<Map<String, Object>> maps){
        for (Map<String, Object> map: maps) {
            System.out.println(map);
        }
    }
}
