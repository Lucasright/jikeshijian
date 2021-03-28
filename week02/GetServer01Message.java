package com;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GetServer01Message2 {
	
    public static void main(String[] args) throws IOException{
    	new Thread(
    		    new Runnable(){
    		               @Override
    		            public void run() {
    		  OkHttpClient client=new OkHttpClient();
    		  Request request=new Request
    	                 .Builder()
    	                 .url("http://localhost:8801")//要访问的链接
    	                 .build();
    		  Call call=client.newCall(request);
    		  call.enqueue(new okhttp3.Callback() {
                  @Override
                  public void onFailure(Call call, IOException e) {

                  }

                  @Override
                  public void onResponse(Call call, okhttp3.Response response) throws IOException {
                     String res=response.body().string();
                      System.out.println(res);
                  }
              });
    		            }
    		}
    		).start();
    }
   
}