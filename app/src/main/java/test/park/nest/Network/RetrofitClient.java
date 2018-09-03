package test.park.nest.Network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
                .baseUrl("http://13.209.214.110:8080/yourMentor/")
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
     * Test 용 api 호출
     * 방식 : GET
     * @param callback
     */
    public void callGetTest(final RetrofitApiCallback callback){

        apiService.getTest().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.code(), response.body());
                }else{
                    callback.onFailed(response.code());
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onError(t);
            }
        });

    }
}
