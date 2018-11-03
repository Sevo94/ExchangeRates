package am.app.exchangerates.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import am.app.exchangerates.R;
import am.app.exchangerates.models.BankDetailBase;
import am.app.exchangerates.network.GetDataService;
import am.app.exchangerates.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_detail);


        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<BankDetailBase> call = service.getBankDetails("5ee70183-87fe-4799-802e-ef7f5e7323db");
        call.enqueue(new Callback<BankDetailBase>() {
            @Override
            public void onResponse(Call<BankDetailBase> call, Response<BankDetailBase> response) {
                Log.i("Sevo", "");
            }

            @Override
            public void onFailure(Call<BankDetailBase> call, Throwable t) {
                Log.i("Sevo", "");
            }
        });
    }
}
