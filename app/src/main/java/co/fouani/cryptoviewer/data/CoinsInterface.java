package co.fouani.cryptoviewer.data;

import co.fouani.cryptoviewer.models.CoinsResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CoinsInterface {

    //performs a HTTP GET to /coins" using a respective retrofit object
    @GET("coins")
    public Call<CoinsResponseModel> getCoins();
}
