package o.yurchenko.homeexercise.network.di;

import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import o.yurchenko.homeexercise.COMMON;
import o.yurchenko.homeexercise.network.Api;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    @Provides
    public GsonConverterFactory jsonConverterFactory() {
        return GsonConverterFactory.create(new GsonBuilder().setLenient().create());
    }

    @Singleton
    @Provides
    public RxJava3CallAdapterFactory callAdapterFactory() {
        return RxJava3CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public HttpLoggingInterceptor loggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    public Retrofit retrofit(
            GsonConverterFactory jsonConverterFactory,
            RxJava3CallAdapterFactory callAdapterFactory,
            OkHttpClient httpClient
    ) {
        return new Retrofit.Builder()
                .baseUrl(COMMON.BASE_URL) // todo provide named argument
                .addConverterFactory(jsonConverterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(httpClient)
                .build();
    }

    @Singleton
    @Provides
    public Api api(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}
