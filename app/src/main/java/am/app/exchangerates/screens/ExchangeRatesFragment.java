package am.app.exchangerates.screens;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import am.app.exchangerates.ItemClickListener;
import am.app.exchangerates.R;
import am.app.exchangerates.adapter.ExchangeRatesAdapter;
import am.app.exchangerates.models.BankInfo;
import am.app.exchangerates.models.ExchangeRateBaseModel;
import am.app.exchangerates.models.Organization;
import am.app.exchangerates.network.GetDataService;
import am.app.exchangerates.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExchangeRatesFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = ExchangeRatesFragment.class.getCanonicalName();

    private List<Organization> organizationList = new ArrayList<>();
    private ItemClickListener itemClickListener;
    private Spinner ratesSpinner;

    public ExchangeRatesFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            itemClickListener = (ItemClickListener) context;
        } catch (ClassCastException e) {
            Log.i(TAG, "host activity should implement this interface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.exchanger_rates_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpSpinner(view);

        final RecyclerView exchangeRateRV = view.findViewById(R.id.rates_change_rv);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ExchangeRateBaseModel> call = service.getAllExchangeRate();
        call.enqueue(new Callback<ExchangeRateBaseModel>() {
            @Override
            public void onResponse(@NonNull Call<ExchangeRateBaseModel> call, @NonNull Response<ExchangeRateBaseModel> response) {
                Log.i("Sevo", "onResponse");

                ExchangeRateBaseModel exchangeRateBaseModel = response.body();

                if (exchangeRateBaseModel != null) {
                    Iterator it = exchangeRateBaseModel.getBankInfoMap().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        organizationList.add(new Organization((String) pair.getKey(), (BankInfo) pair.getValue()));
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    exchangeRateRV.setHasFixedSize(true);
                    exchangeRateRV.setLayoutManager(linearLayoutManager);

                    if (isAdded()) {
                        ExchangeRatesAdapter exchangeRatesAdapter = new ExchangeRatesAdapter(getActivity(), organizationList, new ItemClickListener() {
                            @Override
                            public void onItemClick(String id) {
                                if (itemClickListener != null) {
                                    itemClickListener.onItemClick(id);
                                }
                            }
                        });
                        exchangeRateRV.setAdapter(exchangeRatesAdapter);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ExchangeRateBaseModel> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpSpinner(View view) {
        ratesSpinner = view.findViewById(R.id.rates_spinner);
        ratesSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> ratesAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.rates_exchange_array, android.R.layout.simple_spinner_item);

        ratesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratesSpinner.setAdapter(ratesAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    //    try {
//        JSONObject jsonObject = new JSONObject(rawResponse);
//        Iterator<String> keys = jsonObject.keys();
//
////                    while (keys.hasNext()) {
////                        String key = keys.next();
////                        if (jsonObject.get(key) instanceof JSONObject) {
////                            JSONObject jsonObject1 = (JSONObject) jsonObject.get(key);
////                            if (jsonObject1.has("list")) {
////                                JSONObject jsonObject2 = (JSONObject) jsonObject1.get("list");
////
////                                Iterator<String> keys1 = jsonObject2.keys();
////                                while (keys1.hasNext()) {
////                                    String key1 = keys1.next();
////                                    if (jsonObject2.get(key1) instanceof JSONObject) {
////                                        JSONObject jsonObject3 = (JSONObject) jsonObject2.get(key1);
////
////                                        Iterator<String> keys2 = jsonObject3.keys();
////                                        while (keys2.hasNext()) {
////                                            String key2 = keys2.next();
////                                            if (jsonObject3.get(key2) instanceof JSONObject) {
////                                                JSONObject jsonObject4 = (JSONObject) jsonObject3.get(key2);
////                                                if (jsonObject4.has("buy")) {
////                                                    String buy = jsonObject4.getString("buy");
////                                                }
////
////                                                if (jsonObject4.has("sell")) {
////                                                    String sell = jsonObject4.getString("sell");
////                                                }
////                                            }
////                                        }
////                                    }
////                                }
////                            }
////                        }
////                    }
//    } catch (JSONException e) {
//
//    }
}
