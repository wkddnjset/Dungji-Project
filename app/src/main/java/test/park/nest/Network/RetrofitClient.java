package test.park.nest.Network;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.park.nest.Model.ResponseHeaderModel;
import test.park.nest.Model.search.SearchRecyclerModel;
import test.park.nest.Model.search.SearchResultModel;


/**
 * Retrofit 라이브러리 싱글톤 클래스
 */
public class RetrofitClient {

    private RetrofitApiService apiService;
    private static Retrofit retrofit;
    private static Context mContext;

    /**
     * 싱글톤 객체 홀더 설정
     */
    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient(mContext);
    }

    /**
     * 생성자
     * @param context
     */
    private RetrofitClient(Context context){

        // retrofit 네트워크 로그를 보기 위한 객체 생성
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        // gson lenient 에러 방지용 객체 생성
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://54.180.53.96/")
                .client(client)
                .build();
    }


    /**
     * 클라이언트 싱글톤 객체 반환
     * @param context
     * @return
     */
    public static RetrofitClient getInstance(Context context) {

        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }


    /**
     * 클라이언트 api 서비스 객체를 생성한 뒤
     * 클라이언트 객체를 반환한다
     * @return
     */
    public RetrofitClient createBaseApi() {

        apiService = create(RetrofitApiService.class);

        return this;

    }

    /**
     * retrofit api 서비스를 생성하기 위한 함수
     * @param service
     * @param <T>
     * @return
     */
    private <T> T create(final Class<T> service) {

        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }

        return retrofit.create(service);

    }

    /**
     * 응답 code 값을 확인하여 성공인 경우에만
     * type으로 넘겨준 클래스로 data로 넘겨준 키워드를 파싱 후
     * 리턴해주는 함수
     *
     * @param header retrofit 에서 준 응답
     * @param type 파싱할 클래스
     * @param data data 부분에 실려오는 키값
     * @return
     */
    private <T> T checkResponseData(ResponseHeaderModel header, Class<T> type, String data){

        Gson gson = new Gson();

        if(header != null){

            if(header.getCode().equals("0000")){

                String jsonData = gson.toJson(header.getData().get(data));

                Log.d("DATA", jsonData);

                return gson.fromJson(jsonData, type);
            }

            return null;
        }else{
            return null;
        }
    }


    /**
     * 검색 필터값들 가져오는 api 호출
     * @param callback
     */
    public void callGetSearchFilter(final RetrofitApiCallback callback){

        apiService.callGetSearchFilter().enqueue(new Callback<ResponseHeaderModel>() {
            @Override
            public void onResponse(Call<ResponseHeaderModel> call, Response<ResponseHeaderModel> response) {
                if(response.isSuccessful()){

                    Object result = checkResponseData(response.body(), SearchRecyclerModel.class, "filter");

                    if(result != null)
                        callback.onSuccess(response.code(), result);
                    else
                        callback.onFailed(Integer.parseInt(response.body().getCode()), response.body().getMessage());

                }else{
                    callback.onFailed(response.code(), "네트워크 통신 에러");
                }

            }

            @Override
            public void onFailure(Call<ResponseHeaderModel> call, Throwable t) {
                callback.onError(t);
            }
        });

    }


    public void callPostSearchResult(final RetrofitApiCallback callback, JsonObject body){

        apiService.callPostSearchResult(body).enqueue(new Callback<ResponseHeaderModel>() {
            @Override
            public void onResponse(Call<ResponseHeaderModel> call, Response<ResponseHeaderModel> response) {
                if(response.isSuccessful()){

                    Object result = checkResponseData(response.body(), SearchResultModel.class, "shelter");

                    if(result != null)
                        callback.onSuccess(response.code(), result);
                    else
                        callback.onFailed(Integer.parseInt(response.body().getCode()), response.body().getMessage());

                }else{
                    callback.onFailed(response.code(), "네트워크 통신 에러");
                }

            }

            @Override
            public void onFailure(Call<ResponseHeaderModel> call, Throwable t) {

            }
        });
    }
}