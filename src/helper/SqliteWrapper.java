package helper;


import constants.ApplicationConstants;
import model.Category;
import model.Word;
import model.sql.SqlFieldTraits;
import model.sql.SqlFieldTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Deprecated
    private String createTableCreationString(String table, HashMap<String, List<String>> columns) {
        String sql = "CREATE TABLE IF NOT EXISTS " + table + "(";
        /* the hash map contains the column name as key and the properties as the values
        e.g.: Username, (TEXT, UNIQUE, NOT NULL) */
        // outer loop !!!!!
        for (String columnName : columns.keySet()) {
            sql += columnName + " ";
            for (String columnProperty : columns.get(columnName)) {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createTablePlayers() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_PLAYERS +
                "(" +
                ApplicationConstants.TABLE_PLAYERS_USERNAME_COLUMN + " TEXT UNIQUE NOT NULL, " +
                ApplicationConstants.TABLE_PLAYERS_PASSWORD_COLUMN + " TEXT NOT NULL" +
                ");";
        createTable(sql);
    }

    private void createTableCategories() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_CATEGORIES +
                "(" + ApplicationConstants.TABLE_CATEGORIES_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ApplicationConstants.TABLE_CATEGORIES_NAME_COLUMN + " TEXT UNIQUE NOT NULL" +
                ");";
        createTable(sql);
    }

    private void createTableWords() {
        String sql = "CREATE TABLE IF NOT EXISTS " + ApplicationConstants.TABLE_WORDS +
                "(" + ApplicationConstants.TABLE_WORDS_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ApplicationConstants.TABLE_WORDS_NAME_COLUMN + " TEXT UNIQUE NOT NULL," +
                ApplicationConstants.TABLE_WORDS_HINT_COLUMN + " TEXT," +
                ApplicationConstants.TABLE_WORDS_CATEGORY_ID_COLUMN + " INTEGER NOT NULL" +
                ")";
        createTable(sql);
    }

    public void createAllTables() {
        createTablePlayers();
        createTableCategories();
        createTableWords();
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM " + ApplicationConstants.TABLE_CATEGORIES;
        List<Category> categories = new ArrayList<>();
        Connection conn = this.connect();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                categories.add(
                        new Category(
                                resultSet.getInt(ApplicationConstants.TABLE_CATEGORIES_ID_COLUMN),
                                resultSet.getString(ApplicationConstants.TABLE_CATEGORIES_NAME_COLUMN)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public List<Word> getAllWords() {
        String sql = "SELECT * FROM " + ApplicationConstants.TABLE_WORDS;
        List<Word> words = new ArrayList<>();
        Connection conn = this.connect();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                words.add(
                        new Word(
                                resultSet.getInt(ApplicationConstants.TABLE_WORDS_ID_COLUMN),
                                resultSet.getString(ApplicationConstants.TABLE_WORDS_NAME_COLUMN),
                                resultSet.getString(ApplicationConstants.TABLE_WORDS_HINT_COLUMN)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return words;
    }

    public void insertCategory(Category category) {
        String sql = "INSERT INTO " + ApplicationConstants.TABLE_CATEGORIES +
                "(" + ApplicationConstants.TABLE_CATEGORIES_NAME_COLUMN + ")" +
                " VALUES(?);";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, category.toString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertWord(Word word, int categoryId) {
        String sql = "INSERT INTO " + ApplicationConstants.TABLE_WORDS +
                "(" +
                ApplicationConstants.TABLE_WORDS_NAME_COLUMN + "," +
                ApplicationConstants.TABLE_WORDS_HINT_COLUMN + "," +
                ApplicationConstants.TABLE_WORDS_CATEGORY_ID_COLUMN +
                ")" +
                " VALUES(?,?,?);";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, word.getName());
            statement.setString(2, word.getHint());
            statement.setInt(3, categoryId);

            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Deprecated
    private void createPlayersTableComplicated() {
        HashMap<String, List<String>> columns = new HashMap<>();

        columns.put(ApplicationConstants.TABLE_PLAYERS_USERNAME_COLUMN,
                new ArrayList<>(
                        Arrays.asList(
                                SqlFieldTypes.TEXT.toString(),
                                SqlFieldTraits.UNIQUE.toString(),
                                SqlFieldTraits.NOT_NULL.toString())));

        columns.put(ApplicationConstants.TABLE_PLAYERS_PASSWORD_COLUMN,
                new ArrayList<>(
                        Arrays.asList(
                                SqlFieldTypes.TEXT.toString(),
                                SqlFieldTraits.NOT_NULL.toString())));


        String sql = createTableCreationString(ApplicationConstants.TABLE_PLAYERS, columns);

    }
}
