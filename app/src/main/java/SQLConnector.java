import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClient;
import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * Created by thomas on 7/14/17.
 */
public class SQLConnector {

    private String database;
    private String password;
    private String user;
    private String domain;
    private String port;
    private Connection con;

    public AmazonRDS RDSClient;
    private String credentialsFilePath;
    private Regions region;

    public SQLConnector(String credentialsFilePath) {
        ProfileCredentialsProvider credProvider = new ProfileCredentialsProvider(credentialsFilePath, "default");
        credProvider.getCredentials();
        RDSClient = AmazonRDSClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(Regions.US_WEST_1)
                .build();
    }

    public SQLConnector(String credentialsFilePath, Regions region) {
        ProfileCredentialsProvider credProvider = new ProfileCredentialsProvider(credentialsFilePath, "default");
        credProvider.getCredentials();
        RDSClient = AmazonRDSClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(region)
                .build();
    }

    public SQLConnector(String domain, String port, String database, String user, String password, String credentialsFilePath, Regions region) {
        ProfileCredentialsProvider credProvider = new ProfileCredentialsProvider(credentialsFilePath, "default");
        credProvider.getCredentials();
        RDSClient = AmazonRDSClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(region)
                .build();

        this.domain = domain;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        connect();
    }

    public void connect() {
        System.out.println("jdbc:mysql://" + domain + ":" + port + "/" + database + " " + user + " " + password);
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + domain + ":" + port + "/" + database, user, password);
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            throw new RuntimeException("Connection Failed: DriverManager.getConnection()");

        }
    }

    public Connection getConnection() {
        return con;
    }

    public void create(String id, String key, String hash, String table) {
        String sql = "INSERT INTO " + table + " VALUES ('" + id + "','" + hash + "')";
        System.out.println("Executed SQL Query: " + sql);
        executeSQL(sql);
    }


    public void delete(String id, String table) {
        String sql = "DELETE FROM " + table + " WHERE id = " + id;
        executeSQL(sql);
    }

    public void executeSQL(String query) {
        try {
            Statement statement = con.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException("Failed Query: " + query);
        }
    }

    public SQLConnector clone() {
        return new SQLConnector(domain, port, database, user, password, credentialsFilePath, region);
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException("Connection failed close: " + con.toString());
        }
    }

    public static void main(String[] args) {
        //SQLConnector test = new SQLConnector("src/main/resources/credentials", Regions.US_WEST_1);

        SQLConnector tester = new SQLConnector(
                "#key",
                "PortID",
                "AuctionSwaggerDB",
                "root",
                "npcompete",
                "src/main/resources/credentials",
                Regions.US_EAST_1
        );
    }

}
