import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuaeriesForDB {

    public QuaeriesForDB() {
    }

    public static final String createAgentTable =
            "CREATE TABLE IF NOT EXISTS Agent (\n" +
                    "            id BIGINT PRIMARY KEY,\n" +
                    "            playerId BIGINT,\n" +
                    "            name VARCHAR(256)\n" +
                    "   );";
    public static final String dropAgentTable =
            "DROP TABLE IF EXISTS Agent;";





    public static final String createGameTable =
            "CREATE TABLE IF NOT EXISTS Game (\n" +
                    "            id BIGINT PRIMARY KEY,\n" +
                    "            playerId BIGINT,\n" +
                    "            kill BIGINT,\n" +
                    "            deaths BIGINT,\n" +
                    "            assists BIGINT\n" +
                    "    );";
    public static final String dropGameTable =
            "DROP TABLE IF EXISTS Game;";




    public static final String createLocationTable =
            "CREATE TABLE IF NOT EXISTS Location (\n" +
                    "            playerId BIGINT,\n" +
                    "            country VARCHAR(256),\n" +
                    "            port BIGINT\n" +
                    "   );";
    public static final String dropLocationTable =
            "DROP TABLE IF EXISTS Location;";





    public static final String createPlayerTable =
            "CREATE TABLE IF NOT EXISTS Player (\n" +
                    "           id BIGINT PRIMARY KEY,\n" +
                    "           nickname VARCHAR(256)\n" +
                    "   );";
    public static final String dropPlayerTable =
            "DROP TABLE IF EXISTS Player;";






    public static final String createSkinTable =
            "CREATE TABLE IF NOT EXISTS Skin (\n" +
                    "            id BIGINT PRIMARY KEY,\n" +
                    "            playerId BIGINT,\n" +
                    "            name VARCHAR(256),\n" +
                    "            cost BIGINT\n" +
                    "   );";
    public static final String dropSkinTable =
            "DROP TABLE IF EXISTS Skin;";






    public String findAll(String tableName){
        return String.format("SELECT * FROM %s;", tableName);
    }

    public String find(String tableName, Map<String, Object> values) throws Exception {
        String quaery = String.format("SELECT * FROM %s WHERE ", tableName);

        List<String> vls = new ArrayList<>(values.keySet());
        for (int i = 0; i < values.size(); i++) {
            quaery+= String.format("%s = %s", vls.get(i), getPerformance(values.get(vls.get(i))));
            quaery+= i != vls.size()-1 ? " and " : ";";
        }
        return quaery;
    }

    public String insert(String tableName, Map<String, Object> values){
        String quaery = String.format("INSERT INTO %s VALUES (", tableName);
        List<String> vls = new ArrayList<>(values.keySet());
        for (int i = 0; i < vls.size(); i++) {
            Object o = values.get(vls.get(i));
            quaery+= getPerformance(o);
            quaery+= i != values.size()-1 ? "," : ");";
        }
        return quaery;
    }

    public String delete(String tableName, Map<String, Object> values){
        String quaery = String.format("DELETE FROM %s WHERE ", tableName);

        List<String> vls = new ArrayList<>(values.keySet());
        for (int i = 0; i < vls.size(); i++) {
            quaery+= String.format("%s = %s", vls.get(i), getPerformance(values.get(vls.get(i))));
            quaery+= i != vls.size()-1 ? " and " : ";";
        }
        return quaery;
    }

    public String update(String tableName, Map<String, Object> values, Map<String, Object> newValues) throws Exception {
        String quaery = String.format("UPDATE %s SET ", tableName);

        List<String> vls = new ArrayList<>(values.keySet());
        for (int i = 0; i < vls.size(); i++) {
            quaery+= String.format("%s = %s", vls.get(i), getPerformance(values.get(vls.get(i))));
            quaery+= i != vls.size()-1 ? "," : " WHERE ";
        }

        vls = new ArrayList<>(newValues.keySet());
        for (int i = 0; i < vls.size(); i++) {
            quaery+= String.format("%s = %s", vls.get(i), getPerformance(newValues.get(vls.get(i))));
            quaery+= i != vls.size()-1 ? " and " : ";";
        }

        return quaery;
    }




    private String getPerformance(Object o) {
        return o.getClass().equals(String.class) ? String.format("\'%s\'", o.toString()) : o.toString();
    }



}
