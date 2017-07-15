/**
 * Created by thomas on 7/15/17.
 */
public enum AuctionLabels {

    AUCTIONUID("AuctionUID"),
    OWNERID("UserID"),
    S3POINTER("PtrImageS3"),
    ITEMDESC("ItemDescription"),
    AUCTIONSTART("AuctionTimeStart"),
    AUCTIONEND("AuctionTimeEnd"),
    TOTALBIDS("TotalBids"),
    HIGHESTBIDUID("HighestBidID");

    private final String value;

    private AuctionLabels(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }

}
