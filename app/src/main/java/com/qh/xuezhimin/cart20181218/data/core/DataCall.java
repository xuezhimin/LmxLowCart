package com.qh.xuezhimin.cart20181218.data.core;

import com.qh.xuezhimin.cart20181218.bean.Result;

public interface DataCall<T> {

    void success(T data);

    void fail(Result result);


}
