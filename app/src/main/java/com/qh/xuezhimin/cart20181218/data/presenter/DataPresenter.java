package com.qh.xuezhimin.cart20181218.data.presenter;

import com.qh.xuezhimin.cart20181218.bean.Result;
import com.qh.xuezhimin.cart20181218.data.core.BasePresenter;
import com.qh.xuezhimin.cart20181218.data.core.DataCall;
import com.qh.xuezhimin.cart20181218.data.model.DataModel;

public class DataPresenter extends BasePresenter {


    public DataPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Result getData(Object... args) {
        Result result = DataModel.goodsList();//调用网络请求获取数据
        return result;
    }
}
