package co.fouani.cryptoviewer.models;

import java.util.ArrayList;

//the structure of "/Coins" response
public class CoinsResponseModel {
    public String status;
    public ResponseData data;

    public class ResponseData {
        public ArrayList<Coin> coins;
    }

}
