package com.qh.xuezhimin.cart20181218.data.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qh.xuezhimin.cart20181218.bean.Result;
import com.qh.xuezhimin.cart20181218.bean.Shop;
import com.qh.xuezhimin.cart20181218.okhttp.OkHttpUtils;

import java.lang.reflect.Type;
import java.util.List;

public class DataModel {


    public static Result goodsList() {

        String resultString = OkHttpUtils.get("http://www.zhaoapi.cn/product/getCarts?uid=71");
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<Result<List<Shop>>>() {
            }.getType();
            Result result = gson.fromJson(resultString, type);
            return result;
        } catch (Exception e) {

        }
        Result result = new Result();
        result.setCode(-1);
        result.setMsg("数据解析异常");
        return result;
    }


}
