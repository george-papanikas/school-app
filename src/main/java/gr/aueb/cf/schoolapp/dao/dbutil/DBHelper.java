package gr.aueb.cf.schoolapp.dao.dbutil;

import gr.aueb.cf.schoolapp.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//utility class
public class DBHelper {

    private DBHelper() {}

    public static void eraseData() throws SQLException {
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            conn.prepareStatement("SET @@foreign_key_checks = 0").executeUpdate(); //deactivate FKs Constraint, 0 means false
            //special identifiers begin with @@ at mysql
            rs = conn //chaining
                    .prepareStatement("SELECT TABLE_NAME FROM information_schema.tables WHERE TABLE_SCHEMA = 'schooldb'")
                    .executeQuery();
            List<String> tables = mapRsToList(rs);

            for (String table : tables) {
                conn.prepareStatement("DELETE FROM " + table).executeUpdate();
                conn.prepareStatement("ALTER TABLE " + table + " AUTO_INCREMENT=1").executeUpdate();
            }
            conn.prepareStatement("SET @@foreign_key_checks = 1").executeUpdate();
        } finally {
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> mapRsToList(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<>();

        while (rs.next()) {
            list.add(rs.getString("TABLE_NAME"));
        }
        return list;
    }
}
