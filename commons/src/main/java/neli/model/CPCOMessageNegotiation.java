package neli.model;

public class CPCOMessageNegotiation {
    String contentId;
    double price;
    boolean cpAgreed;
    boolean coAgreed;


    public CPCOMessageNegotiation() {
    }

    public CPCOMessageNegotiation(String contentId, double price, boolean cpAgreed, boolean coAgreed) {
        this.contentId = contentId;
        this.price = price;
        this.cpAgreed = cpAgreed;
        this.coAgreed = coAgreed;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "COInboundMessage{" +
                "contentId='" + contentId + '\'' +
                ", price=" + price +
                '}';
    }

    public boolean isCpAgreed() {
        return cpAgreed;
    }

    public void setCpAgreed(boolean cpAgreed) {
        this.cpAgreed = cpAgreed;
    }

    public boolean isCoAgreed() {
        return coAgreed;
    }

    public void setCoAgreed(boolean coAgreed) {
        this.coAgreed = coAgreed;
    }
}
