package am.app.exchangerates.screens;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import am.app.exchangerates.Constants;
import am.app.exchangerates.R;

public class BankDetailActivity extends AppCompatActivity {

    private static final String TAG = BankDetailActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_detail);

        BankDetailFragment bandDetailFragment = new BankDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putString(Constants.ORGANIZATION_ID, getIntent().getStringExtra(Constants.ORGANIZATION_ID));
        bandDetailFragment.setArguments(bundle);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_fragment, bandDetailFragment);
        transaction.commit();
    }
}
