package neli.model;

public class CPSPMessageNegotiation {
    String contentId;
    double price;
    boolean spAgreed;
    CPCOMessageNegotiation cpcoMessageNegotiation;

    public CPSPMessageNegotiation() {
    }

    public CPCOMessageNegotiation getCpcoMessageNegotiation() {
        return cpcoMessageNegotiation;
    }

    public void setCpcoMessageNegotiation(CPCOMessageNegotiation cpcoMessageNegotiation) {
        this.cpcoMessageNegotiation = cpcoMessageNegotiation;
    }



    public CPSPMessageNegotiation(String contentId, double price) {
        this.contentId = contentId;
        this.price = price;
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

    public boolean isSpAgreed() {
        return spAgreed;
    }

    public void setSpAgreed(boolean spAgreed) {
        this.spAgreed = spAgreed;
    }

    public boolean isCpAgreed() {
        return cpAgreed;
    }

    public void setCpAgreed(boolean cpAgreed) {
        this.cpAgreed = cpAgreed;
    }

    boolean cpAgreed;
}
