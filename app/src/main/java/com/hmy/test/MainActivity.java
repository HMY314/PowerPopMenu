package com.hmy.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hmy.adapter.BaseRecyclerViewAdapter;
import com.hmy.powerpopmenu.PowerPopMenu;
import com.hmy.powerpopmenu.PowerPopMenuModel;
import com.hmy.powerpopmenu.R;
import com.hmy.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    private Context mContext;

    private Button mUpHBtn;
    private Button mUpVBtn;
    private Button mDownHBtn;
    private Button mDownVBtn;

    private LinearLayout mEmptyView;

    private PowerPopMenu mPowerPopMenu;

    private List<PowerPopMenuModel> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_pop_window);

        initData();
        initView();
    }

    private void initData() {
        mContext = this;
        mList = new ArrayList<>();

        PowerPopMenuModel item1 = new PowerPopMenuModel();
        item1.text = "aaa";
        item1.resid = R.mipmap.icon1;
        mList.add(item1);
        PowerPopMenuModel item2 = new PowerPopMenuModel();
        item2.text = "bbb";
        item2.resid = R.mipmap.icon2;
        mList.add(item2);
        PowerPopMenuModel item3 = new PowerPopMenuModel();
        item3.text = "ccc";
        mList.add(item3);

    }

    private void initView() {
        mUpHBtn = (Button) findViewById(R.id.btn_up_h);
        mUpVBtn = (Button) findViewById(R.id.btn_up_v);
        mDownHBtn = (Button) findViewById(R.id.btn_down_h);
        mDownVBtn = (Button) findViewById(R.id.btn_down_v);
        mEmptyView = (LinearLayout) View.inflate(mContext, R.layout.view_empty, null);

        mUpHBtn.setOnClickListener(this);
        mUpVBtn.setOnClickListener(this);
        mDownHBtn.setOnClickListener(this);
        mDownVBtn.setOnClickListener(this);
    }

    private class OnItemClickLis implements BaseRecyclerViewAdapter.OnItemClickListener {

        @Override
        public void onItemClick(View view, int position) {
            ToastUtils.showMessage(mContext, mList.get(position).text);
            mPowerPopMenu.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_up_h:
                mPowerPopMenu = new PowerPopMenu(mContext, LinearLayoutManager.HORIZONTAL, PowerPopMenu
                        .POP_UP_TO_DOWN);
                //必须放在setListResource之前
                mPowerPopMenu.setIsShowIcon(true);
                mPowerPopMenu.setListResource(mList);
                mPowerPopMenu.setOnItemClickListener(new OnItemClickLis());
                mPowerPopMenu.addView(mEmptyView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                        .MATCH_PARENT, 50));
                mPowerPopMenu.show();

                break;
            case R.id.btn_up_v:
                mPowerPopMenu = new PowerPopMenu(mContext, LinearLayoutManager.VERTICAL, PowerPopMenu.POP_UP_TO_DOWN);
                mPowerPopMenu.setIsShowIcon(true);
                mPowerPopMenu.setListResource(mList);
                mPowerPopMenu.setOnItemClickListener(new OnItemClickLis());
                mPowerPopMenu.show(v);
                break;
            case R.id.btn_down_h:
                mPowerPopMenu = new PowerPopMenu(mContext, LinearLayoutManager.HORIZONTAL, PowerPopMenu.POP_DOWN_TO_UP);
                mPowerPopMenu.setIsShowIcon(true);
                mPowerPopMenu.setListResource(mList);
                mPowerPopMenu.addView(mEmptyView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                        .MATCH_PARENT, 50));
                mPowerPopMenu.setOnItemClickListener(new OnItemClickLis());
                mPowerPopMenu.show(v);
                break;
            case R.id.btn_down_v:
                mPowerPopMenu = new PowerPopMenu(mContext, LinearLayoutManager.VERTICAL, PowerPopMenu.POP_DOWN_TO_UP);
                mPowerPopMenu.setIsShowIcon(true);
                mPowerPopMenu.setListResource(mList);
                mPowerPopMenu.setOnItemClickListener(new OnItemClickLis());
                mPowerPopMenu.show(v);
                break;
        }
    }
}