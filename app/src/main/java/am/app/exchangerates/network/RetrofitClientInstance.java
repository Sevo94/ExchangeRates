package am.app.exchangerates.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import am.app.exchangerates.DataParser;
import am.app.exchangerates.models.ExchangeRateBaseModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://rate.am/ws/mobile/v2/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
//                    .setLenient()
                    .enableComplexMapKeySerialization()
                    .registerTypeAdapter(ExchangeRateBaseModel.class, new DataParser())
//                    .registerTypeAdapterFactory(new CustomTypeAdapterFactory(ExchangeRateBaseModel.class))
                    .create();


            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

//    public static Gson buildGson() {
//
//        final GsonBuilder gson = new GsonBuilder();
//
//        //gson.setPrettyPrinting();
//        gson.serializeNulls();
////        gson.excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT, Modifier.VOLATILE);
//
//        // register the type adapter factories
//        final CustomTypeAdapterFactory creator = new CustomTypeAdapterFactory();
//        for (final TypeAdapterFactory factory : creator.getAdapters()) {
//            gson.registerTypeAdapterFactory(factory);
//        }
//
//        return gson.create();
//    }
}
