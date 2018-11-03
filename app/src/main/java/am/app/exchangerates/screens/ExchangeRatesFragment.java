package am.app.exchangerates.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import am.app.exchangerates.R;
import am.app.exchangerates.models.ExchangeRateBaseModel;
import am.app.exchangerates.network.GetDataService;
import am.app.exchangerates.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExchangeRatesFragment extends Fragment {

    public ExchangeRatesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exchanger_rates_fragment_layout ,container, false);

        /*Retrofit request*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ExchangeRateBaseModel> call = service.getAllExchangeRate();
        call.enqueue(new Callback<ExchangeRateBaseModel>() {
            @Override
            public void onResponse(Call<ExchangeRateBaseModel> call, Response<ExchangeRateBaseModel> response) {
                Log.i("Sevo", "");

                String rawResponse = "";
                try {
                    if (response.body() != null) {
//                        rawResponse = response.body().string();
                    }
                } catch (Exception e) {

                }
                try {
                    JSONObject jsonObject = new JSONObject(rawResponse);
                    Iterator<String> keys = jsonObject.keys();

//                    while (keys.hasNext()) {
//                        String key = keys.next();
//                        if (jsonObject.get(key) instanceof JSONObject) {
//                            JSONObject jsonObject1 = (JSONObject) jsonObject.get(key);
//                            if (jsonObject1.has("list")) {
//                                JSONObject jsonObject2 = (JSONObject) jsonObject1.get("list");
//
//                                Iterator<String> keys1 = jsonObject2.keys();
//                                while (keys1.hasNext()) {
//                                    String key1 = keys1.next();
//                                    if (jsonObject2.get(key1) instanceof JSONObject) {
//                                        JSONObject jsonObject3 = (JSONObject) jsonObject2.get(key1);
//
//                                        Iterator<String> keys2 = jsonObject3.keys();
//                                        while (keys2.hasNext()) {
//                                            String key2 = keys2.next();
//                                            if (jsonObject3.get(key2) instanceof JSONObject) {
//                                                JSONObject jsonObject4 = (JSONObject) jsonObject3.get(key2);
//                                                if (jsonObject4.has("buy")) {
//                                                    String buy = jsonObject4.getString("buy");
//                                                }
//
//                                                if (jsonObject4.has("sell")) {
//                                                    String sell = jsonObject4.getString("sell");
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
                } catch (JSONException e) {

                }


            }

            @Override
            public void onFailure(Call<ExchangeRateBaseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
