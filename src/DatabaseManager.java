import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseManager {	
private static DatabaseManager instance;	
	public static DatabaseManager getInstance() {
		if(instance != null) {
			return instance;
		}
		instance = new DatabaseManager();
		return instance;
	}	
	public Connection getDatabaseConnection(final int port, final String database) throws SQLException {
        System.out.println(String.format("[mysql]: Connecting to MySQL:%s - %s", port, database));
        return DriverManager.getConnection(
            String.format("jdbc:mysql://localhost:%s/%s?serverTimezone=UTC&useSSL=false", port, database),
            "root",
            "bichl601"
        );
    }	
	public void releaseConnection(final Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }	
	public void insertIntoDatabase(final Connection connection, LocalDate date ,double closeValue,String aktie ) throws SQLException {
		String createTable ="CREATE TABLE IF NOT EXISTS " + aktie + " (ID int auto_increment primary key, Datum varchar(50), close varchar(10));";
		try (PreparedStatement statement = connection.prepareStatement(createTable)) {
			statement.execute(createTable);
			statement.executeUpdate();
		}
		final String insertInto = "insert into "+aktie+" (Datum, close) values (?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(insertInto)) {
			statement.setString(1, date.toString());
			statement.setDouble(2, closeValue);
			statement.executeUpdate();
		}
	}
}