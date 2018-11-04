package am.app.exchangerates.screens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import am.app.exchangerates.Constants;
import am.app.exchangerates.R;
import am.app.exchangerates.models.BankDetail;
import am.app.exchangerates.models.BankDetailBase;
import am.app.exchangerates.models.WorkHours;
import am.app.exchangerates.network.GetDataService;
import am.app.exchangerates.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankDetailFragment extends Fragment {

    private static final String TAG = BankDetailActivity.class.getCanonicalName();

    private TextView bankNameTv;
    private TextView bankCityTv;
    private TextView bankLocationTv;
    private TextView phoneNumberTv;
    private TextView phoneNumberTitle;

    public BankDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bank_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUIElements(view);
        final String organizationId = getArguments() != null ? getArguments().getString(Constants.ORGANIZATION_ID) : "";

        if (organizationId != null && !organizationId.isEmpty()) {
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<BankDetailBase> call = service.getBankDetails(organizationId);
            call.enqueue(new Callback<BankDetailBase>() {
                @Override
                public void onResponse(Call<BankDetailBase> call, Response<BankDetailBase> response) {
                    Log.i(TAG, "onResponse");

                    if (isAdded()) {
                        BankDetailBase bankDetailBase = response.body();
                        if (bankDetailBase != null) {
                        /* since the Api does not give be the bank information with specified organization id thereore
                         iam choosing first value fromm map after converting to array to display on screen
                         */
                            Object[] values = bankDetailBase.getBankDetailMap().values().toArray();
                            BankDetail bankDetail = (BankDetail) values[0];

                            Log.i(TAG, "update ui");

                            bankNameTv.setText(bankDetail.getTitle().getAm());
                            bankCityTv.setText(bankDetail.getAddress().getAm());
                            phoneNumberTv.setText(bankDetail.getContacts());

                            phoneNumberTitle.setText(getResources().getString(R.string.contact_number));

                            LinearLayout linearLayout = view.findViewById(R.id.work_hours_layout);
                            for (int i = 0; i < bankDetail.getWorkHoursList().size(); i++) {
                                WorkHours workHours = bankDetail.getWorkHoursList().get(i);

                                TextView textView = new TextView(getContext());
                                textView.setText(workHours.getDays() + " " + workHours.getHours());

                                linearLayout.addView(textView);

                                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
                                params.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                                params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                                textView.setLayoutParams(params);
                            }
                            TextView workingDaysTitleTv = new TextView(getContext());
                            workingDaysTitleTv.setText(getResources().getString(R.string.working_days));

                            linearLayout.addView(workingDaysTitleTv);

                            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) workingDaysTitleTv.getLayoutParams();
                            params.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                            params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                            workingDaysTitleTv.setLayoutParams(params);
                        }
                    }
                }

                @Override
                public void onFailure(Call<BankDetailBase> call, Throwable t) {
                    Log.i(TAG, "onFailure = " + t.toString());
                }
            });
        }
    }

    private void initUIElements(View view) {
        bankNameTv = view.findViewById(R.id.bank_name_tv);
        bankCityTv = view.findViewById(R.id.bank_city_tv);
        bankLocationTv = view.findViewById(R.id.bank_location_tv);
        phoneNumberTv = view.findViewById(R.id.phone_number_tv);
        phoneNumberTitle = view.findViewById(R.id.phone_number_text_tv);
    }
}
