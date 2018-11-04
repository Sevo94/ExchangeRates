package am.app.exchangerates.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.app.exchangerates.Constants;
import am.app.exchangerates.R;
import am.app.exchangerates.models.BankDetailBase;
import am.app.exchangerates.network.GetDataService;
import am.app.exchangerates.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankDetailFragment extends Fragment {

    private static final String TAG = BankDetailActivity.class.getCanonicalName();

    public BankDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bank_detail_fragment, container, false);

        String organizationId = getArguments() != null ? getArguments().getString(Constants.ORGANIZATION_ID) : "";

        if (organizationId != null && !organizationId.isEmpty()) {
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<BankDetailBase> call = service.getBankDetails(organizationId);
            call.enqueue(new Callback<BankDetailBase>() {
                @Override
                public void onResponse(Call<BankDetailBase> call, Response<BankDetailBase> response) {
                    Log.i(TAG, "onResponse");
                }

                @Override
                public void onFailure(Call<BankDetailBase> call, Throwable t) {
                    Log.i(TAG, "onFailure");
                }
            });
        }
        return view;
    }
}
