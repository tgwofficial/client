package tgwofficial.atma.client.sync;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tgwofficial.atma.client.model.ApiModel;

public interface ApiService {

    @GET("api/pull?location-id=Dusun_test&update-id=0&batch-size=100")
   Call<List<ApiModel>> getData();
   // Call<ApiModel> getAnswers();

/*
    @POST("api/push")
    @Headers({"Content-Type: application/json"})
    Call<ResponseBody> savePost(@Body JSONArray post);*/

    @POST("api/push")
    @Headers("Accept: application/json")
    Call<String> savePost(@Body String post);
}