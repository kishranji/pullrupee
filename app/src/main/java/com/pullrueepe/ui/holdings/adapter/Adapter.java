package com.pullrueepe.ui.holdings.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pullrueepe.R;
import com.pullrueepe.model.holdings.HoldingResult;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HoldingResult holdingResult;
    private Context context;
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;

    public Adapter(HoldingResult holdingResult, Context context) {
        this.holdingResult = holdingResult;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        /*switch (viewType) {
            case TYPE_ONE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holdings_head_layout, parent, false);
                return new HoldingsHeaderViewHolder(view);
            case TYPE_TWO:*/
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holdings_head_layout, parent, false);
        return new HoldingsDataViewHolder(view);

        //return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HoldingsDataViewHolder) holder).symbolTextView.setText(holdingResult.getPullrupeedata().get(position).getSymbol());
        ((HoldingsDataViewHolder) holder).symbolNameTv.setText(holdingResult.getPullrupeedata().get(position).getSymbol());
        ((HoldingsDataViewHolder) holder).buyQtyTv.setText(holdingResult.getPullrupeedata().get(position).getBuyqty());
        ((HoldingsDataViewHolder) holder).buyAvgTextView.setText(holdingResult.getPullrupeedata().get(position).getBuyavg());
        ((HoldingsDataViewHolder) holder).miniSellTextView.setText(holdingResult.getPullrupeedata().get(position).getMinimumsellavg());
        ((HoldingsDataViewHolder) holder).ltpTextView.setText(holdingResult.getPullrupeedata().get(position).getLtp());
        ((HoldingsDataViewHolder) holder).pLRsTv.setText(holdingResult.getPullrupeedata().get(position).getProfitloss());
        ((HoldingsDataViewHolder) holder).pLPerTv.setText("54%");
    }

    @Override
    public int getItemCount() {
        return holdingResult.getPullrupeedata().size();
    }

    /*@Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_ONE;

        return TYPE_TWO;


    }*/

    public static class HoldingsHeaderViewHolder extends RecyclerView.ViewHolder {
        public HoldingsHeaderViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    public static class HoldingsDataViewHolder extends RecyclerView.ViewHolder {
        TextView symbolTextView, buyAvgTextView, miniSellTextView, ltpTextView, symbolNameTv,
                buyQtyTv, pLRsTv, pLPerTv;

        public HoldingsDataViewHolder(@NonNull View itemView) {
            super(itemView);

            symbolTextView = itemView.findViewById(R.id.symbol);
            buyAvgTextView = itemView.findViewById(R.id.buy_avg);
            miniSellTextView = itemView.findViewById(R.id.mini_sell_avg);
            ltpTextView = itemView.findViewById(R.id.txt_ltp);
            buyQtyTv = itemView.findViewById(R.id.buy_qty);
            pLRsTv = itemView.findViewById(R.id.txt_plrs);
            symbolNameTv = itemView.findViewById(R.id.symbol_name);
            pLPerTv = itemView.findViewById(R.id.txt_plper);

        }

    }
}


