/**
 * Created by thomas on 7/15/17.
 */
public enum BidLabels {

    BIDUID ("BidUID"),
    AUCTIONUID ("AuctionUID"),
    USERID ("UserID"),
    BIDPRICE ("BidPrice"),
    BIDTIMESTAMP ("BidTimeStamp");

    private final String value;

    private BidLabels(final String value) {
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
