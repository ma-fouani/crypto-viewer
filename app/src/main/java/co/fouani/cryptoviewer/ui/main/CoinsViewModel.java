package co.fouani.cryptoviewer.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import co.fouani.cryptoviewer.data.CoinsClient;
import co.fouani.cryptoviewer.models.CoinsResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinsViewModel extends ViewModel {

    //response from the API will be filled in this variable.
    MutableLiveData<CoinsResponseModel> coinsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> failed = new MutableLiveData<>();

    public void getCoins() {
        //using Retrofit, we enqueue a new request to the network to get data.
        CoinsClient.getInstance().getCoins().enqueue(new Callback<CoinsResponseModel>() {


            @Override
            public void onResponse(Call<CoinsResponseModel> call, Response<CoinsResponseModel> response) {
                //success! we have new data! let's save them.
                coinsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CoinsResponseModel> call, Throwable t) {
                //unfortunately, something prevented us from receiving new data.
                failed.setValue(t);
            }
        });
    }

}
