//package com.naksh.contactapp;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.telephony.SmsManager;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {
//
//    private List<Contact> contactList;
//
//    public ContactsAdapter(List<Contact> contactList) {
//        this.contactList = contactList;
//    }
//
//    public void updateList(ArrayList<Contact> newList) {
//        contactList = newList;
//        notifyDataSetChanged();
//        Log.d("Adapter", "Dataset size after update: " + getItemCount());
//    }
//
//    @NonNull
//    @Override
//    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
//        return new ContactViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
//        Contact contact = contactList.get(position);
//        holder.tvName.setText(contact.getName());
//        holder.tvPhoneNumber.setText(contact.getPhoneNumber());
//
//        holder.call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int adapterPosition = holder.getAdapterPosition();
//                if (adapterPosition != RecyclerView.NO_POSITION) {
//                    String number = contactList.get(adapterPosition).getPhoneNumber();
//                    // Check if CALL_PHONE permission is granted
//                    if (ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        // Request permission
//                        ActivityCompat.requestPermissions((Activity) v.getContext(), new String[]{Manifest.permission.CALL_PHONE}, 1);
//                    } else {
//                        // Permission is already granted, make the call
//                        Intent i = new Intent(Intent.ACTION_CALL);
//                        i.setData(Uri.parse("tel:" + number));
//                        v.getContext().startActivity(i);
//                    }
//                }
//            }
//        });
//
//        holder.sms.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int adapterPosition = holder.getAdapterPosition();
//                if (adapterPosition != RecyclerView.NO_POSITION) {
//                    final EditText message = new EditText(v.getContext());
//                    message.setHint("Enter your message....");
//
//                    final AlertDialog.Builder sending_sms = new AlertDialog.Builder(v.getContext());
//                    sending_sms.setIcon(R.drawable.baseline_sms_24)
//                            .setTitle("Send to: " + contactList.get(adapterPosition).getName())
//                            .setView(message)
//                            .setPositiveButton("Send", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    String msg = message.getText().toString();
//                                    String number = contactList.get(adapterPosition).getPhoneNumber();
//                                    sendSMS(v.getContext(), number, msg);
//                                }
//                            })
//                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            }).create();
//                    sending_sms.show();
//                }
//            }
//        });
//    }
//
//    private void sendSMS(Context context, String phnumber, String message) {
//        SmsManager smsManager = SmsManager.getDefault();
//        smsManager.sendTextMessage(phnumber, null, message, null, null);
//        Toast.makeText(context, "Message sent to " + phnumber, Toast.LENGTH_SHORT).show();
//
//    }
//    @Override
//    public int getItemCount() {
//        return contactList.size();
//    }
//
//    static class ContactViewHolder extends RecyclerView.ViewHolder {
//        TextView tvName, tvPhoneNumber;
//        ImageView call, sms;
//
//        public ContactViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvName = itemView.findViewById(R.id.tvName);
//            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
//            call = itemView.findViewById(R.id.ivCall);
//            sms = itemView.findViewById(R.id.ivSms);
//        }
//    }
//
//
//
//}
//

package com.naksh.contactapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private List<Contact> contactList;
    private static final int REQUEST_CODE_CALL_PHONE = 1;
    private static final int REQUEST_CODE_SEND_SMS = 2;

    public ContactsAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public void updateList(ArrayList<Contact> newList) {
        contactList = newList;
        notifyDataSetChanged();
        Log.d("Adapter", "Dataset size after update: " + getItemCount());
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.tvName.setText(contact.getName());
        holder.tvPhoneNumber.setText(contact.getPhoneNumber());

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    String number = contactList.get(adapterPosition).getPhoneNumber();
                    Context context = v.getContext();
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_CALL_PHONE);
                    } else {
                        Intent i = new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse("tel:" + number));
                        context.startActivity(i);
                    }
                }
            }
        });

        holder.sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    final EditText message = new EditText(v.getContext());
                    message.setHint("Enter your message....");

                    final AlertDialog.Builder sending_sms = new AlertDialog.Builder(v.getContext());
                    sending_sms.setIcon(R.drawable.baseline_sms_24)
                            .setTitle("Send to: " + contactList.get(adapterPosition).getName())
                            .setView(message)
                            .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String msg = message.getText().toString();
                                    String number = contactList.get(adapterPosition).getPhoneNumber();
                                    Context context = v.getContext();
                                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.SEND_SMS}, REQUEST_CODE_SEND_SMS);
                                    } else {
                                        sendSMS(context, number, msg);
                                    }
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();
                    sending_sms.show();
                }
            }
        });
    }

    private void sendSMS(Context context, String phnumber, String message) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phnumber, null, message, null, null);
        Toast.makeText(context, "Message sent to " + phnumber, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public Contact getContactAtPosition(int position) {
        return contactList.get(position);
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhoneNumber;
        ImageView call, sms;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            call = itemView.findViewById(R.id.ivCall);
            sms = itemView.findViewById(R.id.ivSms);
        }
    }
}
