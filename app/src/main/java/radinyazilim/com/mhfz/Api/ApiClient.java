package radinyazilim.com.mhfz.Api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit=null;
    private static String BaseUrl ="https://mhfzapi.azurewebsites.net/";

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();
            return retrofit;
        }
        return retrofit;
    }

}
