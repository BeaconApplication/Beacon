package com.codepath.beacon.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.beacon.Models.Pin;
import com.codepath.beacon.R;

import java.util.List;

public class PinsAdapter extends RecyclerView.Adapter<PinsAdapter.ViewHolder>{

    private Context context;
    private List<Pin> pins;

    public PinsAdapter(Context context, List<Pin> pins) {
        this.context = context;
        this.pins = pins;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pin pin = pins.get(position);
        holder.bind(pin);
    }

    @Override
    public int getItemCount() {
        return pins.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPinName;
        private TextView tvPinCaption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPinName = itemView.findViewById(R.id.tvPinName);
            tvPinCaption = itemView.findViewById(R.id.tvPinCaption);
        }

        public void bind(Pin pin) {
            tvPinName.setText(pin.getPinName());
            tvPinCaption.setText(pin.getPinCaption());
        }
    }
}
