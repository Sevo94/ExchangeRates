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

import am.app.exchangerates.ExchangeCurrency;
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
    private ExchangeRatesAdapter exchangeRatesAdapter;
    private Spinner ratesSpinner;
    private boolean isStarted;
    private String lastSelectedCurrency = ExchangeCurrency.USD.getCurrency();

    public ExchangeRatesFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        isStarted = true;
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
    public void onViewCreated(final @NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView exchangeRateRV = view.findViewById(R.id.rates_change_rv);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ExchangeRateBaseModel> call = service.getAllExchangeRate();
        call.enqueue(new Callback<ExchangeRateBaseModel>() {
            @Override
            public void onResponse(@NonNull Call<ExchangeRateBaseModel> call, @NonNull Response<ExchangeRateBaseModel> response) {
                Log.i(TAG, "onResponse");

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
                        exchangeRatesAdapter = new ExchangeRatesAdapter(getActivity(), organizationList, new ItemClickListener() {
                            @Override
                            public void onItemClick(String id) {
                                if (itemClickListener != null) {
                                    itemClickListener.onItemClick(id);
                                }
                            }
                        });
                        exchangeRateRV.setAdapter(exchangeRatesAdapter);
                        setUpSpinner(view);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ExchangeRateBaseModel> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();

        isStarted = false;
    }

    private void setUpSpinner(View view) {
        ratesSpinner = view.findViewById(R.id.rates_spinner);
        ratesSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> ratesAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.rates_exchange_array, android.R.layout.simple_spinner_item);

        ratesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratesSpinner.setAdapter(ratesAdapter);

        for (int i = 0; i < ExchangeCurrency.values().length; i++) {
            if (lastSelectedCurrency.equals(String.valueOf(ExchangeCurrency.values()[i]))) {
                ratesSpinner.setSelection(i);
                break;
            }
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if (isStarted) {
            isStarted = false;
            return;
        }
        lastSelectedCurrency = (String) parent.getItemAtPosition(pos);
        exchangeRatesAdapter.changeRates(lastSelectedCurrency);
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}
