package employee.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.io.InputStream;

public class Conn {

    Connection connection;
    Statement statement;

    public Conn() {
        try {
            // Load config.properties from src/
            Properties props = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("⚠️ config.properties not found in classpath");
            }

            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String pass = props.getProperty("db.password");

            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement();

            System.out.println("✅ Database connected successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Database not connected");
        }
    }
}
