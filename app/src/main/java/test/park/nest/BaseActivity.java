package test.park.nest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import test.park.nest.Dialog.ProgressBarDialog;
import test.park.nest.Network.RetrofitClient;

public class BaseActivity extends AppCompatActivity {


    public final String TAG = this.getClass().getSimpleName();

    private int mContentView = -1;
    private int mLeftIconRes = -1;
    private boolean isRootingFlag = false;
    private TextView mTitleView;
    private ImageButton mLeftIcon, mRightIcon;
    private ProgressBarDialog mProgressDialog;
    private View.OnClickListener mLeftClickListener, mRightClickListener;

    public RetrofitClient networkClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentView());

        initHeaderBar();


        networkClient = RetrofitClient.getInstance(this).createBaseApi();
    }



    private void initHeaderBar() {
        mTitleView = (TextView) findViewById(R.id.title);
        mLeftIcon = (ImageButton) findViewById(R.id.left_btn);
        mRightIcon = (ImageButton) findViewById(R.id.right_btn);



        if (mTitleView != null && getTitleRes() != -1) {
            mTitleView.setText(getTitleRes());
            mTitleView.setVisibility(View.VISIBLE);
        }else{

        }

        if (mLeftIcon != null && getLeftIconRes() != -1) {
            mLeftIcon.setBackgroundResource(getLeftIconRes());
            if (mLeftClickListener != null)
                mLeftIcon.setOnClickListener(mLeftClickListener);
        } else {
            if (mLeftIcon != null)
                mLeftIcon.setVisibility(View.GONE);
        }

        if (mRightIcon != null && getRightIconRes() != -1) {
            mRightIcon.setBackgroundResource(getRightIconRes());
            if (mRightClickListener != null)
                mRightIcon.setOnClickListener(mRightClickListener);
        } else {
            if (mRightIcon != null)
                mRightIcon.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected int getContentView() {
        return mContentView;
    }

    protected int getTitleRes() {
        return -1;
    }

    protected int getLeftIconRes() {
        return mLeftIconRes;
    }

    protected int getRightIconRes() {
        return -1;
    }


    protected void setLeftIconRes(int res){
        mLeftIconRes = res;
    }


    protected void setLeftIconClickListener(View.OnClickListener listener) {
        mLeftClickListener = listener;
        if (mLeftIcon != null)
            mLeftIcon.setOnClickListener(mLeftClickListener);
    }

    protected void setRightIconClickListener(View.OnClickListener listener) {
        mRightClickListener = listener;
        if (mRightIcon != null)
            mRightIcon.setOnClickListener(mRightClickListener);
    }


    @Override
    public void setContentView(int contentView) {
        this.mContentView = contentView;
        super.setContentView(contentView);
    }

    @Override
    public void setTitle(int res) {
        mTitleView.setText(res);
    }
}
