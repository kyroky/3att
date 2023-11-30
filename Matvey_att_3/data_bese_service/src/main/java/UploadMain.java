
import org.example.FileWritener;
import org.example.FileReader;
import org.example.Generator;
import org.example.model.*;



import java.sql.SQLException;
import java.util.List;


public class UploadMain {
    private static ConnectionManeger connection = new ConnectionManeger();
    private  static MappingCls mapping = new MappingCls();
    private  static QuaeriesForDB quaeries = new QuaeriesForDB();
    public static void writeToDb() throws Exception {
        List<Player> players = new FileReader().readFile("f.json");

        for (Player p:players) {

            connection.execute(
                    quaeries.insert(
                            "Player",
                            mapping.toMap(p)
                    )
            );


            connection.execute(
                    quaeries.insert(
                            "Location",
                            mapping.toMap(p.getLocation())
                    )
            );
            for (Skin s :p.getSkins()) {
                connection.execute(
                        quaeries.insert(
                                "Skin",
                                mapping.toMap(s)
                        )
                );
            }

            for (Game g :p.getGameHistory()) {
                connection.execute(
                        quaeries.insert(
                                "Game",
                                mapping.toMap(g)
                        )
                );
            }
            for (Agent a :p.getAgents()) {
                connection.execute(
                        quaeries.insert(
                                "Agent",
                                mapping.toMap(a)
                        )
                );
            }
        }
    }

    public static void main(String[] args) throws Exception {



        connection.execute(
                QuaeriesForDB.dropGameTable
        );

        connection.execute(
                QuaeriesForDB.dropAgentTable
        );

        connection.execute(
                QuaeriesForDB.dropLocationTable
        );

        connection.execute(
                QuaeriesForDB.dropSkinTable
        );

        connection.execute(
                QuaeriesForDB.dropPlayerTable
        );

        connection.execute(
                QuaeriesForDB.createGameTable
        );

        connection.execute(
                QuaeriesForDB.createAgentTable
        );

        connection.execute(
                QuaeriesForDB.createLocationTable
        );

        connection.execute(
                QuaeriesForDB.createSkinTable
        );

        connection.execute(
                QuaeriesForDB.createPlayerTable
        );

        FileWritener fileWriter = new FileWritener();
        fileWriter.writeToJsonFile("f.json", new Generator(10).generate());

        //writeToDb();
    }
}
