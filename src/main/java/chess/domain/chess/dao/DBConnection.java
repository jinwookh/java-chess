package chess.domain.chess.dao;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static BasicDataSource basicDataSource = new BasicDataSource();

    private static String server = "127.0.0.1";
    private static String database = "chess";
    private static String USERNAME = "Ole";
    private static String PASSWORD = "123";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";

    private static int MIN_IDLE = 5;
    private static int MAX_IDLE = 10;
    private static int MAX_PREPARED_STATEMENTS = 100;

    static {
        basicDataSource.setDriverClassName(DRIVER);
        basicDataSource.setUsername(USERNAME);
        basicDataSource.setPassword(PASSWORD);
        basicDataSource.setUrl(URL);
        basicDataSource.setMinIdle(MIN_IDLE);
        basicDataSource.setMaxIdle(MAX_IDLE);
        basicDataSource.setMaxOpenPreparedStatements(MAX_PREPARED_STATEMENTS);
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("DB 연결을 종료합니다.");
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public static void commit(Connection connection) throws SQLException {
        connection.commit();
    }

    public static void rollback(Connection connection) throws SQLException {
        connection.rollback();
    }
}