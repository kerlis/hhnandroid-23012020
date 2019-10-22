package me.doapps.appdhn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by William_ST on 23/09/15.
 */
public class AdapterTips {/*extends RecyclerView.Adapter<AdapterTips.CardsViewHolder> *){

    /*
    static Context context;

    private static List<Tip> alertItems;

    public AdapterHistoryAlert(List<HistoryAlertItem> alertItems, Context context) {
        this.alertItems = alertItems;
        this.context = context;
    }

    @Override
    public AdapterHistoryAlert.CardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_alert, parent, false);
        return new CardsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardsViewHolder holder, int position) {
        try{
            holder.textViewCreditCardNumber.setText(alertItems.get(position).getCreditCardNumber());
            holder.textViewBankName.setText(alertItems.get(position).getBankName());
            holder.textViewDateAlert.setText(alertItems.get(position).getDateAlert());
        }catch (Exception e){
            Log.e("E-AdapterHistoryAlert, onBindViewHolder", e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return alertItems.size();
    }

    public static class CardsViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewCreditCardNumber, textViewBankName, textViewDateAlert;

        public CardsViewHolder(View v) {
            super(v);

            textViewCreditCardNumber = (TextView) itemView.findViewById(R.id.iha_text_view_credit_card_number);
            textViewBankName = (TextView) itemView.findViewById(R.id.iha_text_view_bank_name);
            textViewDateAlert = (TextView) itemView.findViewById(R.id.iha_text_view_date_alert);

        }

    }
    */
}
