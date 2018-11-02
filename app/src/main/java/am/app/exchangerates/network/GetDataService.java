package am.app.exchangerates.network;

import java.util.List;

import am.app.exchangerates.models.ExchangeRateBaseModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/rates.ashx?lang=en")
    Call<List<ExchangeRateBaseModel>> getAllPhotos();
}
