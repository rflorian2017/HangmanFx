package helper;


import constants.ApplicationConstants;
import model.sql.SqlFieldTraits;
import model.sql.SqlFieldTypes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SqliteWrapper {

    private Connection connect() {
        // connect to the database
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(ApplicationConstants.DB_CONNECTION_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }

    private String createTableCreationString(String table, HashMap<String, List<String>> columns) {
        String sql = "CREATE TABLE IF NOT EXISTS " + table + "(";
        /* the hash map contains the column name as key and the properties as the values
        e.g.: Username, (TEXT, UNIQUE, NOT NULL) */
        // outer loop !!!!!
        for (String columnName : columns.keySet()) {
            sql += columnName + " " ;
            for (String columnProperty: columns.get(columnName)) {
                sql += columnProperty + " ";
            }
            sql += ",";
        }
        //remove the last comma from the sql string, because we do not check in the outer for loop for the last element
        sql = sql.substring(0, sql.length() - 2); // -1 is the last character
        sql += ");";

        return sql;
    }

    private void createTable(String sql) {
        try {
            Connection conn = this.connect();
            // the query I want to feed the sql query to
            Statement statement = conn.createStatement();
            // executed on the db defined by the connection
            statement.execute(sql);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createPlayersTable() {
        HashMap<String, List<String>> columns = new HashMap<>();
        List<String> col
        columns.put(ApplicationConstants.TABLE_PLAYERS_USERNAME_COLUMN, new ArrayList<String>() );
        createTable(createTableCreationString(ApplicationConstants.TABLE_PLAYERS, ));
    }
}
