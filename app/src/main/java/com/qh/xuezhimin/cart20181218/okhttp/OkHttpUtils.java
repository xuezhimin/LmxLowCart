package com.qh.xuezhimin.cart20181218.okhttp;

import java.io.File;
import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {

    public static String get(String urlString){

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url(urlString).get().build();

        try {
            Response response = okHttpClient.newCall(request).execute();

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String postForm(String url,String[] name,String[] value){

        OkHttpClient okHttpClient = new OkHttpClient();

        FormBody.Builder formBuild = new FormBody.Builder();
        for (int i = 0; i < name.length; i++) {
            formBuild.add(name[i],value[i]);
        }

        Request request = new Request.Builder().url(url).post(formBuild.build()).build();

        try {
            Response response = okHttpClient.newCall(request).execute();

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String postFile(String url,String[] name,String[] value,String fileParamName,File file){

        OkHttpClient okHttpClient = new OkHttpClient();

        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(file != null){
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为: 文件参数名 ，文件名称 ， RequestBody
            requestBody.addFormDataPart(fileParamName, "jpg", body);
        }
        if (name!=null) {
            for (int i = 0; i < name.length; i++) {
                requestBody.addFormDataPart(name[i], value[i]);
            }
        }

        Request request = new Request.Builder().url(url).post(requestBody.build()).build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code()==200) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String postJson(String url,String jsonString){

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),jsonString);

        Request request = new Request.Builder().url(url).post(requestBody).build();

        try {
            Response response = okHttpClient.newCall(request).execute();

            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
