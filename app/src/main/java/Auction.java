import com.amazonaws.regions.Regions;
import com.amazonaws.services.rds.AmazonRDS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;


/**
 * Created by thomas on 7/14/17.
 */
public class Auction {
    private static final String DATABASE = "AuctionSwaggerDB";
    private static final String TABLE = "AuctionTable";
    private static final String MASTER_KEY = "AuctionUID";

    private int auctionUID;
    private int ownerUID;
    private String s3Pointer;
    private String itemDesc;
    private long auctionStart;
    private long auctionEnd;
    private int totalBids;
    private int highestBidUID;

    SQLConnector sql;

    public Auction() {
        auctionUID = Integer.parseInt(UUID.randomUUID().toString());;
        ownerUID = 0;
        s3Pointer = "";
        itemDesc = "";
        auctionStart = 0;
        auctionEnd = 0;
        totalBids = 0;
        highestBidUID = 0;

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

    public Auction(User user, String s3Pointer, String itemDesc, long auctionStart, long auctionEnd) {
        this.auctionUID = Integer.parseInt(UUID.randomUUID().toString());;
        this.ownerUID = user.getUID();
        this.s3Pointer = s3Pointer;
        this.itemDesc = itemDesc;
        this.auctionStart = auctionStart;
        this.auctionEnd = auctionEnd;
        this.totalBids = 0;
        this.highestBidUID = 0;

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

    public void updateLocal(int auctionID) {
        try {
            // Create and execute query
            Statement statement = sql.getConnection().createStatement();
            String sqlQuery = "SELECT * FROM " + TABLE + " WHERE " + MASTER_KEY + " = " + auctionID + "";
            System.out.println(sqlQuery);
            ResultSet rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                auctionUID = rs.getInt(AuctionLabels.AUCTIONUID.getValue());
                ownerUID = rs.getInt(AuctionLabels.OWNERID.getValue());
                s3Pointer = rs.getString(AuctionLabels.S3POINTER.getValue());
                itemDesc = rs.getString(AuctionLabels.ITEMDESC.getValue());
                auctionStart = rs.getLong(AuctionLabels.AUCTIONSTART.getValue());
                auctionEnd = rs.getLong(AuctionLabels.AUCTIONEND.getValue());
                totalBids = rs.getInt(AuctionLabels.TOTALBIDS.getValue());
                highestBidUID = rs.getInt(AuctionLabels.HIGHESTBIDUID.getValue());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query Failed");
        }
    }

    public void createAuctionSQL() {
        try {
            // Create and execute query
            Statement statement = sql.getConnection().createStatement();
            String sqlQuery = "INSERT INTO " + TABLE + " (" +
                    AuctionLabels.AUCTIONUID.getValue() + ", " +
                    AuctionLabels.OWNERID.getValue() + ", " +
                    AuctionLabels.S3POINTER.getValue() + ", " +
                    AuctionLabels.ITEMDESC.getValue() + ", " +
                    AuctionLabels.AUCTIONSTART.getValue() + ", " +
                    AuctionLabels.AUCTIONEND.getValue() + ", " +
                    AuctionLabels.TOTALBIDS.getValue() + ", " +
                    AuctionLabels.HIGHESTBIDUID.getValue() + ") VALUES (" +
                    auctionUID + ", " +
                    ownerUID + ", " +
                    "'" + s3Pointer + "'" + ", " +
                    "'" + itemDesc + "'" + ", " +
                    auctionStart + ", " +
                    auctionEnd + ", " +
                    totalBids + ", " +
                    highestBidUID + ")";

            sqlQuery.replace("'","\\'");
            System.out.println(sqlQuery);
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException("Query Failed");
        }
    }

    public String readAuctionSQL(AuctionLabels colName, String auctionID) {
        String value = "";
        try {
            // Create and execute query
            Statement statement = sql.getConnection().createStatement();
            String sqlQuery = "SELECT " + colName + " FROM " + TABLE + " WHERE " + MASTER_KEY + " = " + auctionID;
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

    public void updateAuctionSQL(AuctionLabels colName, String value, String id) {
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

    public void deleteAuctionSQL(int idToDelete) {
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
        auctionUID + "," +
        ownerUID + "," +
        s3Pointer + "," +
        itemDesc + "," +
        auctionStart + "," +
        auctionEnd + "," +
        totalBids + "," +
        highestBidUID;

        return stringForm;
    }

    public int getAuctionUID() {
        return auctionUID;
    }

    public void setAuctionUID(int auctionUID) {
        this.auctionUID = auctionUID;
    }

    public int getOwnerUID() {
        return ownerUID;
    }

    public void setOwnerUID(int ownerUID) {
        this.ownerUID = ownerUID;
    }

    public String getS3Pointer() {
        return s3Pointer;
    }

    public void setS3Pointer(String s3Pointer) {
        this.s3Pointer = s3Pointer;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public long getAuctionStart() {
        return auctionStart;
    }

    public void setAuctionStart(long auctionStart) {
        this.auctionStart = auctionStart;
    }

    public long getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(long auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    public int getTotalBids() {
        return totalBids;
    }

    public void setTotalBids(int totalBids) {
        this.totalBids = totalBids;
    }

    public int getHighestBidUID() {
        return highestBidUID;
    }

    public void setHighestBidUID(int highestBidUID) {
        this.highestBidUID = highestBidUID;
    }

    public SQLConnector getSql() {
        return sql;
    }

    public void setSql(SQLConnector sql) {
        this.sql = sql;
    }
}
