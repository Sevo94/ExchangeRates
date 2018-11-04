package am.app.exchangerates.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import am.app.exchangerates.ItemClickListener;
import am.app.exchangerates.R;
import am.app.exchangerates.models.BankInfo;
import am.app.exchangerates.models.Organization;

public class ExchangeRatesAdapter extends RecyclerView.Adapter<ExchangeRatesAdapter.MyViewHolder> {

    private Context context;
    private List<Organization> organizations;
    private ItemClickListener itemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView bankName, distance, buyRate, sellRate;
        public ImageView bankLogo;
        public RelativeLayout itemContainer;

        public MyViewHolder(View view) {
            super(view);
            bankName = view.findViewById(R.id.bank_name_tv);
            distance = view.findViewById(R.id.distance_tv);
            buyRate = view.findViewById(R.id.buy_rate_tv);
            sellRate = view.findViewById(R.id.sell_rate_tv);
            bankLogo = view.findViewById(R.id.bank_logo_iv);
            itemContainer = view.findViewById(R.id.item_container);
        }
    }


    public ExchangeRatesAdapter(Context context, List<Organization> organizations, ItemClickListener itemClickListener) {
        this.context = context;
        this.organizations = organizations;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exchange_rate_item, parent, false);

        final MyViewHolder myViewHolder = new MyViewHolder(itemView);
        myViewHolder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(organizations.get(myViewHolder.getAdapterPosition()).getOrganizationId());
                }
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Organization organization = organizations.get(position);
        BankInfo bankInfo = organization.getBankInfo();

        holder.bankName.setText(bankInfo.getTitle());

//        Glide.with(context)
//                .load(bankInfo.getLogo())
//                .into(holder.bankLogo);
    }

    @Override
    public int getItemCount() {
        return organizations.size();
    }
}
