package com.pullrueepe.ui.monthlyTarget.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pullrueepe.R;
import com.pullrueepe.model.monthlyTarget.AllMonthlyTargets;
import com.pullrueepe.model.monthlyTarget.GetAllMonthlyTarget;
import com.pullrueepe.ui.monthlyTarget.viewHolder.HeaderViewHolder;
import com.pullrueepe.ui.monthlyTarget.viewHolder.ItemViewHolder;

import java.util.List;

import retrofit2.http.HEAD;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = HistoryAdapter.class.getSimpleName();
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_ITEM = 2;
    private GetAllMonthlyTarget getAllMonthlyTarget;
    Context context;

    public HistoryAdapter(Context context, GetAllMonthlyTarget getAllMonthlyTarget) {
        this.context=context;
        this.getAllMonthlyTarget = getAllMonthlyTarget;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       /* if (viewType == TYPE_HEADER) {
            View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_header_layout, viewGroup, false);
            return new HeaderViewHolder(layoutView);
        } else if (viewType == TYPE_ITEM) {*/
            View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_list_layout, viewGroup, false);
            return new ItemViewHolder(layoutView);
        }
      //  throw new RuntimeException("No match for " + viewType + ".");
   // }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
       /* if(viewHolder instanceof HeaderViewHolder){
            ((HeaderViewHolder) viewHolder).onBind(position);
        }else {*/
            ((ItemViewHolder) viewHolder).onBind(position, getAllMonthlyTarget);
       // }
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return getAllMonthlyTarget.getPullrupeedata().size();
    }
}
