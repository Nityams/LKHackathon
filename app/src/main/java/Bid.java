import com.amazonaws.regions.Regions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * Created by thomas on 7/14/17.
 */
public class Bid {
    private static final String DATABASE = "AuctionSwaggerDB";
    private static final String TABLE = "BidTable";
    private static final String MASTER_KEY = "BidUID";

    private int bidUID;
    private int auctionUID;
    private int userID;
    private double bidPrice;
    private long bidTimestamp;

    private SQLConnector sql;

    public Bid(User user) {
        bidUID = Integer.parseInt(UUID.randomUUID().toString());;
        auctionUID = 0;
        userID = 0;
        bidPrice = 0;
        bidTimestamp = 0;

        sql = new SQLConnector(
                "auctiondata123.cy4zo813ahh8.us-east-1.rds.amazonaws.com",
                "3310",
                "AuctionSwaggerDB",
                "root",
                "npcompete",
                "src/main/resources/credentials",
                Regions.US_EAST_1
        );
    }

    public Bid(Auction auc, User user, double bidPrice, long bidTimestamp) {
        this.bidUID = Integer.parseInt(UUID.randomUUID().toString());;
        this.auctionUID = auc.getAuctionUID();
        this.userID = user.getUID();
        this.bidPrice = bidPrice;
        this.bidTimestamp = bidTimestamp;

        sql = new SQLConnector(
                "auctiondata123.cy4zo813ahh8.us-east-1.rds.amazonaws.com",
                "3310",
                "AuctionSwaggerDB",
                "root",
                "npcompete",
                "src/main/resources/credentials",
                Regions.US_EAST_1
        );
    }

    public void updateLocal(int bidUID) {
        try {
            // Create and execute query
            Statement statement = sql.getConnection().createStatement();
            String sqlQuery = "SELECT * FROM " + TABLE + " WHERE " + MASTER_KEY + " = " + bidUID + "";
            System.out.println(sqlQuery);
            ResultSet rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                bidUID = rs.getInt(BidLabels.BIDUID.getValue());
                auctionUID = rs.getInt(BidLabels.AUCTIONUID.getValue());
                userID = rs.getInt(BidLabels.USERID.getValue());
                bidPrice = rs.getInt(BidLabels.BIDPRICE.getValue());
                bidTimestamp = rs.getInt(BidLabels.BIDTIMESTAMP.getValue());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query Failed");
        }
    }

    public void createBidSQL() {
        try {
            // Create and execute query
            Statement statement = sql.getConnection().createStatement();
            String sqlQuery = "INSERT INTO " + TABLE + " (" +
                    BidLabels.BIDUID.getValue() + ", " +
                    BidLabels.AUCTIONUID.getValue() + ", " +
                    BidLabels.USERID.getValue() + ", " +
                    BidLabels.BIDPRICE.getValue() + ", " +
                    BidLabels.BIDTIMESTAMP.getValue() + ") VALUES (" +
                    bidUID + ", " +
                    auctionUID + ", " +
                    userID + ", " +
                    bidPrice + ", " +
                    bidTimestamp + ")";

            System.out.println(sqlQuery);
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException("Query Failed");
        }
    }

    public String readBidSQL(BidLabels colName, String bidID) {
        String value = "";
        try {
            // Create and execute query
            Statement statement = sql.getConnection().createStatement();
            String sqlQuery = "SELECT " + colName + " FROM " + TABLE + " WHERE " + MASTER_KEY + " = " + bidID;
            System.out.println(sqlQuery);
            ResultSet rs = statement.executeQuery(sqlQuery);

            //Check if the database is empty
            if (!rs.first()) {
                return null;
            }

            value = rs.getObject(1) + "";

        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException("Query Failed");
        }

        return value;
    }

    public void updateBidSQL(BidLabels colName, String value, String id) {
        try {
            // Create and execute query
            Statement statement = sql.getConnection().createStatement();
            String sqlQuery = "UPDATE " + TABLE + " SET " + colName + " = " + "\"" + value + "\"" + " WHERE " + MASTER_KEY + " = " + id;
            System.out.println(sqlQuery);
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException("Query Failed");
        }
    }

    public void deleteBidSQL(int idToDelete) {
        System.out.println("DELETE");
        try {
            // Create and execute query
            Statement statement = sql.getConnection().createStatement();
            String sqlQuery = "DELETE FROM " + TABLE + " WHERE " + MASTER_KEY + " = " + idToDelete;
            System.out.println(sqlQuery);
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException("Query Failed");
        }
    }

    public String toString(){
        String stringForm =
                bidUID + "," +
                auctionUID + "," +
                userID + "," +
                bidPrice + "," +
                bidTimestamp;

        return stringForm;
    }

    public int getBidUID() {
        return bidUID;
    }

    public void setBidUID(int bidUID) {
        this.bidUID = bidUID;
    }

    public int getAuctionUID() {
        return auctionUID;
    }

    public void setAuctionUID(int auctionUID) {
        this.auctionUID = auctionUID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public long getBidTimestamp() {
        return bidTimestamp;
    }

    public void setBidTimestamp(long bidTimestamp) {
        this.bidTimestamp = bidTimestamp;
    }

    public SQLConnector getSql() {
        return sql;
    }

    public void setSql(SQLConnector sql) {
        this.sql = sql;
    }

}
