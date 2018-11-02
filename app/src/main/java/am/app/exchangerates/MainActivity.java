package am.app.exchangerates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import am.app.exchangerates.models.ExchangeRateBaseModel;
import am.app.exchangerates.network.GetDataService;
import am.app.exchangerates.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Retrofit request*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<ExchangeRateBaseModel>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<ExchangeRateBaseModel>>() {
            @Override
            public void onResponse(Call<List<ExchangeRateBaseModel>> call, Response<List<ExchangeRateBaseModel>> response) {
                if (response.body() != null) {
                    Log.i("Sevo", "i made it");
                }
            }

            @Override
            public void onFailure(Call<List<ExchangeRateBaseModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
