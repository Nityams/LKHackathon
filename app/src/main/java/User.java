///**
// * Created by thomas on 7/14/17.
// */
//import com.amazonaws.regions.Regions;
//
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.UUID;
//
//public class User {
//    private static final String DATABASE = "AuctionSwaggerDB";
//    private static final String TABLE = "UserTable";
//    private static final String MASTER_KEY = "UserID";
//
//    SQLConnector sql;
//
//
//    public int ID;
//
//    public User() {
//        ID = Integer.parseInt(UUID.randomUUID().toString());
//
//        sql = new SQLConnector(
//                "auctiondata123.cy4zo813ahh8.us-east-1.rds.amazonaws.com",
//                "3310",
//                "AuctionSwaggerDB",
//                "root",
//                "npcompete",
//                "src/main/resources/credentials",
//                Regions.US_EAST_1
//        );
//    }
//
//    public void createUserSQL() {
//        try {
//            // Create and execute query
//            Statement statement = sql.getConnection().createStatement();
//            String sqlQuery = "INSERT INTO " + TABLE + " (" +
//                    MASTER_KEY + ") VALUES (" +
//                    ID + ")";
//
//            System.out.println(sqlQuery);
//            statement.executeUpdate(sqlQuery);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            //throw new RuntimeException("Query Failed");
//        }
//    }
//
//    //Add auction to user
//    public void addAuction(String s3Pointer, String itemDesc, long auctionStart, long auctionEnd) {
//        Auction auc = new Auction(this, s3Pointer, itemDesc, auctionStart, auctionEnd);
//        auc.createAuctionSQL();
//    }
//
//    //Add bid to user
//    public void addBid(Auction auction, int amount) {
//        Bid bid = new Bid(auction, this, amount, System.currentTimeMillis());
//        bid.createBidSQL();
//
//        auction.updateAuctionSQL(AuctionLabels.HIGHESTBIDUID, Integer.toString(bid.getBidUID()), Integer.toString(auction.getAuctionUID()));
//    }
//
//
//    public int getUID() {
//        return ID;
//    }
//
//}
