

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
    private Class usableClass;
    private MappingCls mappingCls;
    private ConnectionManeger connectionManeger;
    private QuaeriesForDB quaeries;

    public MyServlet(Class usableClass, MappingCls mappingCls, ConnectionManeger connectionManeger, QuaeriesForDB quaeries) {
        this.usableClass = usableClass;
        this.mappingCls = mappingCls;
        this.connectionManeger = connectionManeger;
        this.quaeries = quaeries;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> values;
        try {
            values = ConditionReader.read(mappingCls, request, usableClass);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        ResultSet rs = null;
        if(values.keySet().size() == 0) {
            try {
                rs = connectionManeger.executeQuaery(quaeries.findAll(usableClass.getSimpleName()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                rs = connectionManeger.executeQuaery(quaeries.find(usableClass.getSimpleName(), values));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        String ans = null;
        try {
            ans = fromRsToSt(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (ans.length() == 0){
            response.getWriter().println(ans);
            return;
        }

        response.getWriter().println(ans);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> values;
        try {
            values = ConditionReader.read(mappingCls, request, usableClass);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            connectionManeger.execute(quaeries.insert(usableClass.getSimpleName(), values));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> values;
        try {
            values = ConditionReader.read(mappingCls, request, usableClass);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            connectionManeger.execute(
                    quaeries.delete(
                            usableClass.getSimpleName(), values)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> values;
        Map<String, Object> conditionValues;
        try {
            values = ConditionReader.read(mappingCls, request, usableClass);
            conditionValues = ConditionReader.read(mappingCls, request, usableClass, "c");
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            connectionManeger.execute(quaeries.update(usableClass.getSimpleName(), conditionValues, values));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String fromRsToSt(ResultSet resultSet) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        String s = "";
        for (Map<String, Object> map: mappingCls.toMap(resultSet, usableClass)) {
            s+=map.toString()+"\n";
            s+="________________________________________";
        }
        return s;
    }

}