package com.naksh.contactapp;

import android.os.AsyncTask;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


 public class RetrieveCallLogsTask extends AsyncTask<Void, List<CallModel>, List<CallModel>> {
    private Context context;
    private ArrayList<CallModel> callModels;
    private CallAdapter callAdapter;

    public RetrieveCallLogsTask(Context context, ArrayList<CallModel> callModels, CallAdapter callAdapter) {
        this.context = context;
        this.callModels = callModels;
        this.callAdapter = callAdapter;
    }

    @Override
    protected List<CallModel> doInBackground(Void... voids) {
        return getCallDetails(context);
    }

    @Override
    protected void onProgressUpdate(List<CallModel>... values) {
        if (values[0] != null) {
            callModels.addAll(values[0]);
            callAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onPostExecute(List<CallModel> callModels) {
        this.callModels.clear();
        this.callModels.addAll(callModels);
        callAdapter.notifyDataSetChanged();
    }

    private List<CallModel> getCallDetails(Context context) {
        ArrayList<CallModel> arrayList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
        if (cursor != null) {
            int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = cursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

            if (number == -1 || type == -1 || date == -1 || duration == -1) {
                cursor.close();
                return arrayList;
            }

            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault());

            while (cursor.moveToNext()) {
                String phNumber = cursor.getString(number);
                String callType = cursor.getString(type);
                String callDate = cursor.getString(date);
                Date callDayTime = new Date(Long.parseLong(callDate));
                String formattedDate = dateFormat.format(callDayTime);
                String callDuration = cursor.getString(duration);
                String dir = null;
                int dircode = Integer.parseInt(callType);
                switch (dircode) {
                    case CallLog.Calls.OUTGOING_TYPE:
                        dir = "OUTGOING";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        dir = "INCOMING";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        dir = "MISSED";
                        break;
                }

                int min = Integer.parseInt(callDuration) / 60;
                int seconds = Integer.parseInt(callDuration) - min * 60;

                String contactName = getContactName(context, phNumber);

                arrayList.add(new CallModel(phNumber, dir, String.valueOf(min) + ":" + String.valueOf(seconds), formattedDate, contactName));

                if (arrayList.size() % 20 == 0) { // Update UI every 20 items
                    publishProgress(new ArrayList<>(arrayList));
                    arrayList.clear();
                }
            }
            cursor.close();
        }
        return arrayList;
    }

    private String getContactName(Context context, String phoneNumber) {
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        String contactName = null;

        Cursor cursor = context.getContentResolver().query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
                if (nameIndex != -1) {
                    contactName = cursor.getString(nameIndex);
                }
            }
            cursor.close();
        }
        return contactName != null ? contactName : "Unknown";
    }
}
