import org.example.model.*;

import java.util.List;

public class ServletMain {
    public static void main(String[] args) {

        ConnectionManeger connection = new ConnectionManeger();
        MappingCls mapping = new MappingCls();
        QuaeriesForDB quaeries = new QuaeriesForDB();
        ServletsContainer servletsContainer = new ServletsContainer();
        servletsContainer.start(
                List.of(Agent.class, Game.class, Location.class, Player.class, Skin.class),
                mapping, connection, quaeries
        );

    }
}
