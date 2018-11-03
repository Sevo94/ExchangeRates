package am.app.exchangerates.network;

import am.app.exchangerates.models.BankDetailBase;
import am.app.exchangerates.models.ExchangeRateBaseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("rates.ashx?lang=en")
    Call<ExchangeRateBaseModel> getAllExchangeRate();

    @GET("branches.ashx")
    Call<BankDetailBase> getBankDetails(@Query("id") String organizationId);
}
