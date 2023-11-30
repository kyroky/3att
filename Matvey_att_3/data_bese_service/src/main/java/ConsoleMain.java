public class ConsoleMain {
    public static void main(String[] args) throws Exception {
        ConnectionManeger connection = new ConnectionManeger();
        MappingCls mapping = new MappingCls();
        QuaeriesForDB quaeries = new QuaeriesForDB();

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


        Matcher matcher = new Matcher();
        matcher.read();
        /*CRUD crud = new CRUD();

        System.out.println(crud.dropQuaery(Agent.class));
        System.out.println(crud.dropQuaery(Game.class));
        System.out.println(crud.dropQuaery(Location.class));
        System.out.println(crud.dropQuaery(Player.class));
        System.out.println(crud.dropQuaery(Skin.class));*/
        /*System.out.println(crud.createQuaery(Agent.class));
        System.out.println(crud.createQuaery(Game.class));
        System.out.println(crud.createQuaery(Location.class));
        System.out.println(crud.createQuaery(Player.class));
        System.out.println(crud.createQuaery(Skin.class));*/
    }
}
