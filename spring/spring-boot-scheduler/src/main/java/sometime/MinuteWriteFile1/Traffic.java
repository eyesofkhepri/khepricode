package sometime.MinuteWriteFile1;

import lombok.Data;

@Data
public class Traffic {
    private String datetime;
    private String dspInfoId;
    private String bidRequests;
    private String bidResponses;
    private String winNotices;
    private String clicks;
    private String avgBidPrice;
    private String avgWinPrice;

    public Traffic(String datetime, String dspInfoId, String bidRequests, String bidResponses, String winNotices, String clicks, String avgBidPrice, String avgWinPrice) {
        this.datetime = datetime;
        this.dspInfoId = dspInfoId;
        this.bidRequests = bidRequests;
        this.bidResponses = bidResponses;
        this.winNotices = winNotices;
        this.clicks = clicks;
        this.avgBidPrice = avgBidPrice;
        this.avgWinPrice = avgWinPrice;
    }
}
