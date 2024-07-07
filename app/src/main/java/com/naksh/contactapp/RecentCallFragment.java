//package com.naksh.contactapp;
//
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.SearchView;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.provider.CallLog;
//import android.telecom.Call;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.google.firebase.database.DatabaseReference;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link RecentCallFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class RecentCallFragment extends Fragment {
//
//
//    private RecyclerView recyclerView;
//    private CallAdapter callAdapter;
//    private ArrayList<CallModel> callModels;
////    private List<Contact> mobileContactList;
////    private List<Contact> firebaseContactList;
//    private DatabaseReference databaseContacts;
//    private SearchView searchView;
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//    private static final int REQUEST_CODE_READ_CALL_LOG = 3;
//
//    public RecentCallFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment RecentCallFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static RecentCallFragment newInstance(String param1, String param2) {
//        RecentCallFragment fragment = new RecentCallFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_recent_call, container, false);
//        recyclerView = v.findViewById(R.id.recyclerView2);
//
//        callModels = new ArrayList<>();
//        callAdapter = new CallAdapter(getContext(), callModels);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(callAdapter);
//
//        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_CODE_READ_CALL_LOG);
//        } else {
//            getCallDetails(requireContext(), callModels, callAdapter);
//        }
//
//        return v;
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_READ_CALL_LOG) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getCallDetails(requireContext(), callModels, callAdapter);
//            } else {
//                Toast.makeText(requireContext(), "Permission denied to read call logs", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private static void getCallDetails(Context context, ArrayList<CallModel> arrayList, CallAdapter callAdapter) {
//        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
//        if (cursor != null) {
//            int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
//            int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
//            int date = cursor.getColumnIndex(CallLog.Calls.DATE);
//            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
//            while (cursor.moveToNext()) {
//                String phNumber = cursor.getString(number);
//                String callType = cursor.getString(type);
//                String callDate = cursor.getString(date);
//                Date callDayTime = new Date(Long.parseLong(callDate));
//                String callDuration = cursor.getString(duration);
//                String dir = null;
//                int dircode = Integer.parseInt(callType);
//                switch (dircode) {
//                    case CallLog.Calls.OUTGOING_TYPE:
//                        dir = "OUTGOING";
//                        break;
//                    case CallLog.Calls.INCOMING_TYPE:
//                        dir = "INCOMING";
//                        break;
//                    case CallLog.Calls.MISSED_TYPE:
//                        dir = "MISSED";
//                        break;
//                }
//                arrayList.add(new CallModel(phNumber, dir, callDuration, callDate));
//            }
//            callAdapter.notifyDataSetChanged();
//            cursor.close();
//        }
//    }
//}

package com.naksh.contactapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecentCallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecentCallFragment extends Fragment {

    private RecyclerView recyclerView;
    private CallAdapter callAdapter;
    private ArrayList<CallModel> callModels;
    private DatabaseReference databaseContacts;
    private SearchView searchView;
    private static final int REQUEST_CODE_READ_CALL_LOG = 3;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public RecentCallFragment() {
        // Required empty public constructor
    }

    public static RecentCallFragment newInstance(String param1, String param2) {
        RecentCallFragment fragment = new RecentCallFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recent_call, container, false);
        recyclerView = v.findViewById(R.id.recyclerView2);

        callModels = new ArrayList<>();
        callAdapter = new CallAdapter(getContext(), callModels);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(callAdapter);

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_CODE_READ_CALL_LOG);
        } else {
            getCallDetails(requireContext(), callModels, callAdapter);
        }

        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_READ_CALL_LOG) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCallDetails(requireContext(), callModels, callAdapter);
            } else {
                Toast.makeText(requireContext(), "Permission denied to read call logs", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static void getCallDetails(Context context, ArrayList<CallModel> arrayList, CallAdapter callAdapter) {
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
        if (cursor != null) {
            int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = cursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
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

                int min=Integer.parseInt(callDuration)/60;
                int seconds=Integer.parseInt(callDuration)-min*60;
                arrayList.add(new CallModel(phNumber, dir,String.valueOf(min)+":"+String.valueOf(seconds), formattedDate));
            }
            callAdapter.notifyDataSetChanged();
            cursor.close();
        }
    }
}