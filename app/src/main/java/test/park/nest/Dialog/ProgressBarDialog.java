package test.park.nest.Dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import test.park.nest.R;

public class ProgressBarDialog extends Dialog {
    String mMsg;
    ImageView imageView;
    Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.progress_dialog);
//        imageView = (ImageView) findViewById(R.id.iv_loading);
//
//        Glide.with(mContext)
//                .load(R.drawable.intro_test)
//                .asGif()
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(imageView);
        TextView tv = (TextView) findViewById(R.id.tv_loading_message);
        if (mMsg != null && !mMsg.isEmpty()) {
            tv.setText(mMsg);
        }
    }

    public ProgressBarDialog(Context context) {
        // Dialog 배경을 투명 처리 해준다.
        super(context, R.style.ProgressDialogTheme);
        mContext = context;
    }

    public ProgressBarDialog(Context context, String msg) {
        // Dialog 배경을 투명 처리 해준다.
        super(context, R.style.ProgressDialogTheme);
        mContext = context;

        mMsg = msg;
    }
}
