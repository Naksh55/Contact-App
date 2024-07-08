////package com.naksh.contactapp;
////
////import android.Manifest;
////import android.content.pm.PackageManager;
////import android.database.Cursor;
////import android.os.Bundle;
////import androidx.annotation.NonNull;
////import androidx.annotation.Nullable;
////import androidx.appcompat.widget.SearchView;
////import androidx.core.app.ActivityCompat;
////import androidx.core.content.ContextCompat;
////import androidx.fragment.app.Fragment;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////
////import android.provider.ContactsContract;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.ImageView;
////
////import com.google.firebase.database.DataSnapshot;
////import com.google.firebase.database.DatabaseError;
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
////import com.google.firebase.database.ValueEventListener;
////
////import java.util.ArrayList;
////import java.util.List;
////
////public class Contactragment extends Fragment {
////    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
////
////    private RecyclerView recyclerView;
////    private ContactsAdapter contactsAdapter;
////    private List<Contact> contactList;
////    private DatabaseReference databaseContacts;
////    private SearchView searchView;
////
////    public Contactragment() {
////        // Required empty public constructor
////    }
////
////    @Nullable
////    @Override
////    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
////                             @Nullable Bundle savedInstanceState) {
////        View view = inflater.inflate(R.layout.fragment_contactragment, container, false);
////
////        recyclerView = view.findViewById(R.id.recyclerView);
////        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
////        contactList = new ArrayList<>();
////        contactsAdapter = new ContactsAdapter(contactList);
////        recyclerView.setAdapter(contactsAdapter);
////
////        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts");
////        databaseContacts.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                contactList.clear();
////                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
////                    Contact contact = postSnapshot.getValue(Contact.class);
////                    contactList.add(contact);
////                }
////                contactsAdapter.notifyDataSetChanged();
////
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError databaseError) {
////                // Handle possible errors.
////            }
////        });
////
////        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_CONTACTS)
////                != PackageManager.PERMISSION_GRANTED) {
////            ActivityCompat.requestPermissions(getActivity(),
////                    new String[]{android.Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
////        } else {
////            // If permission is already granted, read contacts
////            readContacts();
////        }
////
////        return view;
////    }
////
////    void readContacts() {
////        Cursor phones = getActivity().getContentResolver().query(
////                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
////
////        if (phones != null) {
////            int nameIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
////            int numberIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
////
////            if (nameIndex == -1 || numberIndex == -1) {
////                // Handle the error: column index not found
////                phones.close();
////                return;
////            }
////
////            while (phones.moveToNext()) {
////                String name = phones.getString(nameIndex);
////                String phoneNumber = phones.getString(numberIndex);
////
////                contactList.add(new Contact(name,phoneNumber));
////                // Do something with the name and phoneNumber
////            }
////            contactsAdapter.notifyDataSetChanged();
////
////            phones.close();
////        }
////    }
////
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
////                                           @NonNull int[] grantResults) {
////        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
////            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                // Permission granted, read contacts
////                readContacts();
////            } else {
////                // Permission denied, handle accordingly
////            }
////        }
////    }
////
////}
//
//package com.naksh.contactapp;
//
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.os.Bundle;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.widget.SearchView;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.provider.ContactsContract;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Contactragment extends Fragment {
//
//    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
//
//    private RecyclerView recyclerView;
//    private ContactsAdapter contactsAdapter;
//    private List<Contact> contactList;
//    private DatabaseReference databaseContacts;
//    private SearchView searchView;
//
//    public Contactragment() {
//        // Required empty public constructor
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_contactragment, container, false);
//
//        recyclerView = view.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        contactList = new ArrayList<>();
//        contactsAdapter = new ContactsAdapter(contactList);
//        recyclerView.setAdapter(contactsAdapter);
//
//        searchView = view.findViewById(R.id.searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Perform final search
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Filter the contacts as user types
//                filterContacts(newText);
//                return true;
//            }
//        });
//
//        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts");
//        databaseContacts.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                contactList.clear();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Contact contact = postSnapshot.getValue(Contact.class);
//                    contactList.add(contact);
//                }
//                contactsAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle possible errors.
//            }
//        });
//
//        // Check for permissions
//        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_CONTACTS)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(getActivity(),
//                    new String[]{android.Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
//        } else {
//            // If permission is already granted, read contacts
//            readContacts();
//        }
//
//        return view;
//    }
//
//    private void filterContacts(String query) {
//        ArrayList<Contact> filteredList = new ArrayList<>();
//        for (Contact contact : contactList) {
//            if (contact.getName().toLowerCase().contains(query.toLowerCase())) {
//                filteredList.add(contact);
//            }
//        }
//        contactsAdapter.updateList(filteredList);
//    }
//
//    void readContacts() {
//        Cursor phones = getActivity().getContentResolver().query(
//                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
//
//        if (phones != null) {
//            int nameIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
//            int numberIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//
//            if (nameIndex == -1 || numberIndex == -1) {
//                // Handle the error: column index not found
//                phones.close();
//                return;
//            }
//
//            while (phones.moveToNext()) {
//                String name = phones.getString(nameIndex);
//                String phoneNumber = phones.getString(numberIndex);
//                // Do something with the name and phoneNumber
//            }
//            phones.close();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, read contacts
//                readContacts();
//            } else {
//                // Permission denied, handle accordingly
//            }
//        }
//    }
//}
//



package com.naksh.contactapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Contactragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static final int PERMISSIONS_REQUEST_SEND_SMS = 101;


    private RecyclerView recyclerView;
    private ContactsAdapter contactsAdapter;
    private List<Contact> contactList;
    private List<Contact> mobileContactList;
    private List<Contact> firebaseContactList;
    private DatabaseReference databaseContacts;
    private SearchView searchView;
    private FloatingActionButton fab;

    public Contactragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contactragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contactList = new ArrayList<>();
        mobileContactList = new ArrayList<>();
        firebaseContactList = new ArrayList<>();
        contactsAdapter = new ContactsAdapter(contactList);
        recyclerView.setAdapter(contactsAdapter);
        fab = view.findViewById(R.id.f);
        // Set a click listener for the FAB
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialPadBottomSheetFragment dialPadFragment = new DialPadBottomSheetFragment();
                assert getFragmentManager() != null;
                dialPadFragment.show(getFragmentManager(), "dialPadFragment");
            }
        });

        searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform final search
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the contacts as user types
                filterContacts(newText);
                return true;
            }
        });

        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts");
        databaseContacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                firebaseContactList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Contact contact = postSnapshot.getValue(Contact.class);
                    firebaseContactList.add(contact);
                }
                mergeAndSetContacts();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.
            }
        });

        // Check for permissions
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            // If permission is already granted, read contacts
            readContacts();
        }

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_REQUEST_SEND_SMS);
        }

        return view;
    }

    private void filterContacts(String query) {
        ArrayList<Contact> filteredList = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase()) ||
                    contact.getPhoneNumber().contains(query)) {
                filteredList.add(contact);
            }
        }
        contactsAdapter.updateList(filteredList);
    }

    void readContacts() {
        Cursor phones = getActivity().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        if (phones != null) {
            int nameIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            if (nameIndex == -1 || numberIndex == -1) {
                // Handle the error: column index not found
                phones.close();
                return;
            }

            mobileContactList.clear(); // Clear the list before adding new contacts

            while (phones.moveToNext()) {
                String name = phones.getString(nameIndex);
                String phoneNumber = phones.getString(numberIndex);

                // Add the contact to the list
                mobileContactList.add(new Contact(name, phoneNumber));
            }
            phones.close();
            mergeAndSetContacts();
        }
    }

    void mergeAndSetContacts() {
        contactList.clear();
        contactList.addAll(mobileContactList);
        contactList.addAll(firebaseContactList);

        // Sort the contact list alphabetically
        Collections.sort(contactList, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getName().compareToIgnoreCase(c2.getName());
            }
        });

        contactsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readContacts();
            }
        } else if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Permission granted to make calls", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Permission denied to make calls", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PERMISSIONS_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Permission granted to send SMS", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Permission denied to send SMS", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


