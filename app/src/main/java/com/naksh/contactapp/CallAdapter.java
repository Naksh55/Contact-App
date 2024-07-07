package com.naksh.contactapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.ViewHolder> {
    Context context;
    ArrayList<CallModel> callModels;
    private static final int REQUEST_CODE_CALL_PHONE = 1;

    public CallAdapter(Context context, ArrayList<CallModel> callModels) {
        this.context = context;
        this.callModels = callModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.call_logs,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(callModels.get(position).getName());
        holder.callduration.setText(callModels.get(position).getCallduration());
        holder.date.setText(callModels.get(position).getTime());
        holder.calltype.setText(callModels.get(position).getCalltype());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    String number = callModels.get(adapterPosition).getCalltype();
                    Context context = v.getContext();
                    if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_CALL_PHONE);
                    } else {
                        Intent i = new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse("tel:" + number));
                        context.startActivity(i);
                    }
                }
            }
        });





    }

    @Override
    public int getItemCount() {
        return callModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView calltype,name,callduration,date;
        ImageView call;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            calltype=itemView.findViewById(R.id.callType);
            callduration=itemView.findViewById(R.id.duration);
            date=itemView.findViewById(R.id.date);
            call=itemView.findViewById(R.id.ivCall);
        }
    }
}
