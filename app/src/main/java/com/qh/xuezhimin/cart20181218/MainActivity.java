package com.qh.xuezhimin.cart20181218;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qh.xuezhimin.cart20181218.adapter.CartAdapter;
import com.qh.xuezhimin.cart20181218.bean.Goods;
import com.qh.xuezhimin.cart20181218.bean.Result;
import com.qh.xuezhimin.cart20181218.bean.Shop;
import com.qh.xuezhimin.cart20181218.data.core.DataCall;
import com.qh.xuezhimin.cart20181218.data.presenter.DataPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        DataCall<List<Shop>>, CartAdapter.TotalPriceListener {

    private ExpandableListView mListCart;
    /**
     * 全选
     */
    private CheckBox mCheckAll;
    /**
     * 价格：0￥
     */
    private TextView mGoodsSumPrice;
    //实例化p层
    private DataPresenter dataPresenter = new DataPresenter(this);
    private CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //适配器
        cartAdapter = new CartAdapter(this);
        mListCart.setAdapter(cartAdapter);
        //p层请求数据
        dataPresenter.requestData();
        //设置总价回调器
        cartAdapter.setTotalPriceListener(this);
        //设置去除二级列表的三角形
        mListCart.setGroupIndicator(null);

        //让其group不能被点击
        mListCart.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });

        //全选操作
        mCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cartAdapter.checkAll(isChecked);
            }
        });


    }

    private void initView() {
        mListCart = findViewById(R.id.list_cart);
        mCheckAll = findViewById(R.id.check_all);
        mGoodsSumPrice = findViewById(R.id.goods_sum_price);
    }


    //接口实现的方法
    @Override
    public void success(List<Shop> data) {
        cartAdapter.addAll(data);
        //遍历所有group,将所有项设置成默认展开
        int groupCount = data.size();
        for (int i = 0; i < groupCount; i++) {
            mListCart.expandGroup(i);
        }
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(Result result) {
        Toast.makeText(this, result.getCode() + "   " + result.getMsg(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void totalPrice(double totalPrice) {
        mGoodsSumPrice.setText(String.valueOf(totalPrice));
    }
}
