package co.fouani.cryptoviewer.data;

import co.fouani.cryptoviewer.models.CoinsResponseModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//this class will help us get data using CoinsInterface which in turn uses retrofit.
public class CoinsClient {
    //base url from which we will retrieve data
    private static final String BASE_URL = "https://api.coinranking.com/v2/";

    //in order to not re-instantiate the
    private static CoinsClient INSTANCE;
    private CoinsInterface coinsInterface;
    private String token = "coinranking13a9a8f2116da1b54c88516ef66059c0bb64626fd6f604b4";

    //initialize retrofit object, and coinsInterface
    public CoinsClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        coinsInterface = retrofit.create(CoinsInterface.class);
    }

    //make sure we only initialize INSTANCE only once
    public static CoinsClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CoinsClient();
        }
        return INSTANCE;
    }

    //this call actually will make the call to get the coins, using the annotated tag.
    public Call<CoinsResponseModel> getCoins() {
        return coinsInterface.getCoins();
    }
}
