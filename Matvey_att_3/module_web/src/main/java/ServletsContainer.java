import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.List;

public class ServletsContainer {

    private static final int PORT = 8080;

    public void start(List<Class> classes, MappingCls mappingCls, ConnectionManeger connectionManeger, QuaeriesForDB quaeries){
        Server server = new Server(PORT);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        for (Class c:classes) {
            context.addServlet(new ServletHolder( new MyServlet( c, mappingCls, connectionManeger, quaeries) ),"/%s".formatted(c.getSimpleName().toLowerCase()));
        }

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { context });
        server.setHandler(handlers);

        try {
            server.start();
            System.out.println("Listening port : " + PORT );

            server.join();
        } catch (Exception e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }


}