package am.app.exchangerates.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import am.app.exchangerates.ItemClickListener;
import am.app.exchangerates.R;

public class ExchangeRatesActivity extends AppCompatActivity implements ItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onItemClick(String id) {
        Intent bankDetailIntent = new Intent(this, BankDetailActivity.class);
        startActivity(bankDetailIntent);
    }
}
