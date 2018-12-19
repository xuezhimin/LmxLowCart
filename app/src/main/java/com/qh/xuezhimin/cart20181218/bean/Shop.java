package com.qh.xuezhimin.cart20181218.bean;

import java.util.List;

/**
 * @author dingtao
 * @date 2018/12/18 16:23
 * qq:1940870847
 */
public class Shop {
    List<Goods> list;
    String sellerName;
    String sellerid;

    boolean check;

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }
}
