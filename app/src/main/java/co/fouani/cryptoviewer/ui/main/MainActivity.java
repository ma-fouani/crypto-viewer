package co.fouani.cryptoviewer.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import co.fouani.cryptoviewer.R;

public class MainActivity extends AppCompatActivity {
    CoinsViewModel coinsViewModel;

    RecyclerView recyclerView;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Main Activity views are allocated here.
        progress = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recycler);

        //view model will handle
        coinsViewModel = ViewModelProviders.of(this).get(CoinsViewModel.class);

        //start the network request to get new data.
        coinsViewModel.getCoins();

        //our recyclerview will be a vertical list.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //create the adapter, and set it to the recycler view.
        CoinsRecyclerAdapter adapter = new CoinsRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        //when viewmode.getcoins succeeds, we need to observe the retrieved data,
        //in order to immediately update the UI.
        coinsViewModel.coinsMutableLiveData.observe(this, coinsResponseModel -> {
            //there is no more background processes going on.
            //hide the progress bar.
            progress.setVisibility(View.GONE);

            //add the retrieved data to the view model.
            adapter.setList(coinsResponseModel.data.coins, getBaseContext());

            //refreshes content in the recyclerview.
            adapter.notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();
        });

        //We have to observe "failed" in order to check for failure.
        coinsViewModel.failed.observe(this, throwable -> {
            Toast.makeText(this, "Failed to get data. \nReason: "
                    + throwable.getMessage(), Toast.LENGTH_LONG).show();
            //there is no more background processes going on.
            //hide the progress bar.
            progress.setVisibility(View.GONE);
        });
    }
}