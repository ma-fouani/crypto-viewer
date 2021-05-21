package co.fouani.cryptoviewer.ui.main;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.robinhood.spark.SparkAdapter;
import com.robinhood.spark.SparkView;

import java.util.ArrayList;

import co.fouani.cryptoviewer.R;
import co.fouani.cryptoviewer.models.Coin;

import static co.fouani.cryptoviewer.Utils.convertMoney;
import static co.fouani.cryptoviewer.Utils.convertMoneyBig;
import static co.fouani.cryptoviewer.Utils.convertPercentage;

public class CoinsRecyclerAdapter extends RecyclerView.Adapter<CoinsRecyclerAdapter.CoinsViewHolder> {
    private ArrayList<Coin> list = new ArrayList<>();
    private int lastPosition = 0;
    private Context context;


    //clear animation
    @Override
    public void onViewDetachedFromWindow(@NonNull CoinsRecyclerAdapter.CoinsViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @NonNull
    @Override
    public CoinsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //using layout coin_item we inflate our item
        return new CoinsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CoinsRecyclerAdapter.CoinsViewHolder holder, int position) {
        //add values to the inflated item
        holder.name.setText(list.get(position).getName());
        holder.position.setText(position + 1 + "");
        holder.symbol.setText(list.get(position).getSymbol());
        holder.marketCap.setText(convertMoneyBig(list.get(position).getMarketCap()));
        holder.price.setText(convertMoney(list.get(position).getPrice()));
        String changeRate = convertPercentage(list.get(position).getChange());
        holder.changeRate.setText(changeRate);
        holder.spark.setAdapter(new MySparkAdapter(list.get(position).getSparkline()));

        //this specifically is to load an SVG file
        //from a url into an ImageView.
        GlideToVectorYou
                .init()
                .with(holder.image.getContext())
                .load(Uri.parse(list.get(position).getIconUrl()), holder.image);

        //in case this icon is dipping, let's color it red.
        if (changeRate.contains("-")) {
            holder.spark.setLineColor(context.getResources().getColor(R.color.red));
            holder.changeRate.setTextColor(context.getResources().getColor(R.color.red));
        }
        //animate scrolling up and down
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    //call this methoed to initialize context and the list once it's retrieved.
    public void setList(ArrayList<Coin> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    //View Holder. will help us load data into the inflated item
    public class CoinsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView position;
        TextView name;
        TextView symbol;
        TextView marketCap;
        TextView changeRate;
        TextView price;
        SparkView spark;

        public CoinsViewHolder(@NonNull View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.index);
            image = itemView.findViewById(R.id.coin_image);
            price = itemView.findViewById(R.id.price_tv);
            name = itemView.findViewById(R.id.name_tv);
            symbol = itemView.findViewById(R.id.symbol_tv);
            spark = itemView.findViewById(R.id.spark);
            marketCap = itemView.findViewById(R.id.market_cap_tv);
            changeRate = itemView.findViewById(R.id.change_tv);
        }
    }

    //The SparkView needs its special adapter.
    public class MySparkAdapter extends SparkAdapter {
        private float[] yData;

        public MySparkAdapter(float[] yData) {
            this.yData = yData;
        }

        @Override
        public int getCount() {
            return yData.length;
        }

        @Override
        public Object getItem(int index) {
            return yData[index];
        }

        @Override
        public float getY(int index) {
            return yData[index];
        }
    }
}
