package com.pullrueepe.ui.monthlyTarget.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.pullrueepe.R;
import com.pullrueepe.model.monthlyTarget.AllMonthlyTargets;
import com.pullrueepe.model.monthlyTarget.GetAllMonthlyTarget;
import com.pullrueepe.model.monthlyTarget.Pullrupeedatum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView txtYear, txtMonth, txtAmount;

    public ItemViewHolder(View itemView) {
        super(itemView);
        txtYear = (TextView) itemView.findViewById(R.id.history_txt_year);
        txtMonth = (TextView) itemView.findViewById(R.id.history_txt_month);
        txtAmount = (TextView) itemView.findViewById(R.id.history_txt_amount);
    }

    public void onBind(int position, Object object) {
        GetAllMonthlyTarget getAllMonthlyTarget = (GetAllMonthlyTarget) object;
    txtYear.setText(getAllMonthlyTarget.getPullrupeedata().get(position).getDisplaymonth());
        txtMonth.setText("\u20B9"+getAllMonthlyTarget.getPullrupeedata().get(position).getTargetamount());
        txtAmount.setText("\u20B9" + getAllMonthlyTarget.getPullrupeedata().get(position).getAchievedamount());
    }
}




