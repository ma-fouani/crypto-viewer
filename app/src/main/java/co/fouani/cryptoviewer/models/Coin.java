package co.fouani.cryptoviewer.models;

//this will be used in an array to get coins from server
public class Coin {
    private String uuid;
    private String name;
    private String symbol;
    private String iconUrl;
    private String price;
    private String marketCap;
    private String change;
    private float[] sparkline;

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getPrice() {
        return price;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public String getChange() {
        return change;
    }

    public float[] getSparkline() {
        return sparkline;
    }
}
