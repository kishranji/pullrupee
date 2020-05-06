package com.pullrueepe.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.pullrueepe.R;


public class ProgressDialog extends Dialog {

    private FragmentActivity myContext;
    private Context myAppContext;
    private TextView myLoadingTxt;
    private ProgressWheel myProgressWheel;

    public ProgressDialog(FragmentActivity context) {
        super(context);
        myContext = context;
        try {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.setCancelable(false);
            this.setContentView(R.layout.custom_dialog_box);
            this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myLoadingTxt = (TextView) this.findViewById(R.id.custom_dialog_box_TXT_loading);
            myProgressWheel = (ProgressWheel) this.findViewById(R.id.custom_dialog_box_PB_loading);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ProgressDialog(Context context) {
        super(context);
        myAppContext = context;
        try {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.setCancelable(false);
            this.setContentView(R.layout.custom_dialog_box);
            this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myLoadingTxt = (TextView) this.findViewById(R.id.custom_dialog_box_TXT_loading);
            myProgressWheel = (ProgressWheel) this.findViewById(R.id.custom_dialog_box_PB_loading);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ProgressDialog(Activity context) {
        super(context);
        myAppContext = context;
        try {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.setCancelable(false);
            this.setContentView(R.layout.custom_dialog_box);
            this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myLoadingTxt = (TextView) this.findViewById(R.id.custom_dialog_box_TXT_loading);
            myProgressWheel = (ProgressWheel) this.findViewById(R.id.custom_dialog_box_PB_loading);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Loading text
     *
     * @param aLoadingText
     */
    public void setMessage(String aLoadingText) {
        myLoadingTxt.setText(aLoadingText);
    }

    /*@Override
    public void show() {
        super.show();
    }*/

}
