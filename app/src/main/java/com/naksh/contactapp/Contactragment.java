package com.naksh.contactapp;////package com.naksh.contactapp;
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
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class Contactragment extends Fragment {
    private RecyclerView recyclerView;
    private ContactsAdapter contactsAdapter;
    private List<Contact> contactList;
    private List<Contact> mobileContactList;
    private List<Contact> firebaseContactList;
    private DatabaseReference databaseContacts;
    private SearchView searchView;
    private FloatingActionButton fab;
    private ImageView voiceSearchButton;

    String name;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    private static final int PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int PERMISSIONS_REQUEST_VOICE_SEARCH = 2;
    private static final int VOICE_SEARCH_REQUEST_CODE = 3;
    private static final int REQUEST_CODE_READ_CALL_LOG = 9;
    private static final int MULTIPLE_PERMISSIONS_REQUEST_CODE = 123;
    private static final String[] REQUIRED_PERMISSIONS = new String[]{
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.RECORD_AUDIO
    };

    public Contactragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contactragment, container, false);

        voiceSearchButton = view.findViewById(R.id.voiceSearchButton);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contactList = new ArrayList<>();
        mobileContactList = new ArrayList<>();
        firebaseContactList = new ArrayList<>();
        contactsAdapter = new ContactsAdapter(contactList);
        recyclerView.setAdapter(contactsAdapter);
        fab = view.findViewById(R.id.f);

        fab.setOnClickListener(v -> {
            DialPadBottomSheetFragment dialPadFragment = new DialPadBottomSheetFragment();
            assert getFragmentManager() != null;
            dialPadFragment.show(getFragmentManager(), "dialPadFragment");
        });

        searchView = view.findViewById(R.id.searchView);
        searchView.setOnClickListener(v -> {
            searchView.setIconified(false);
            searchView.requestFocusFromTouch();
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterContacts(newText);
                return true;
            }
        });

        voiceSearchButton.setOnClickListener(v -> startVoiceSearch());

        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts");
        databaseContacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (permissionsGranted) {
                    firebaseContactList.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Contact contact = postSnapshot.getValue(Contact.class);
                        assert contact != null;
                        contact.setId(postSnapshot.getKey());
                        firebaseContactList.add(contact);
                    }
                    mergeAndSetContacts();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        if (!hasPermissions(getContext(), REQUIRED_PERMISSIONS)) {
            ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS, MULTIPLE_PERMISSIONS_REQUEST_CODE);
        } else {
            permissionsGranted = true;
            loadContacts(); // Load contacts if permissions were already granted
        }

        contactsAdapter = new ContactsAdapter(contactList, new ContactsAdapter.OnUpdateClickListener() {
            @Override
            public void onUpdateClick(Contact contact) {
                showUpdateDialog(contact);
            }
        }, new ContactsAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(Contact contact) {
                deleteContact(contact);
            }
        });

        recyclerView.setAdapter(contactsAdapter);

        return view;
    }

//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_contactragment, container, false);
//
//        voiceSearchButton = view.findViewById(R.id.voiceSearchButton);
//        recyclerView = view.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        contactList = new ArrayList<>();
//        mobileContactList = new ArrayList<>();
//        firebaseContactList = new ArrayList<>();
//        contactsAdapter = new ContactsAdapter(contactList);
//        recyclerView.setAdapter(contactsAdapter);
//        fab = view.findViewById(R.id.f);
//        // Set a click listener for the FAB
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialPadBottomSheetFragment dialPadFragment = new DialPadBottomSheetFragment();
//                assert getFragmentManager() != null;
//                dialPadFragment.show(getFragmentManager(), "dialPadFragment");
//            }
//        });
//
//        searchView = view.findViewById(R.id.searchView);
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchView.setIconified(false);
//                searchView.requestFocusFromTouch();
//            }
//        });
//
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
//        voiceSearchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startVoiceSearch();
//            }
//        });
//
//        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts");
//        databaseContacts.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                firebaseContactList.clear();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Contact contact = postSnapshot.getValue(Contact.class);
//                    assert contact != null;
//                    contact.setId(postSnapshot.getKey());  // Set the ID from the database key
//                    firebaseContactList.add(contact);
//                }
//                mergeAndSetContacts();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle possible errors.
//            }
//        });
//
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false; // We don't want drag & drop functionality, just swipe
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
//                Contact contact = contactsAdapter.getContactAtPosition(position);
//                makeCall(contact.getPhoneNumber());
//                contactsAdapter.notifyItemChanged(position); // Reset the item state after swiping
//            }
//
//            @Override
//            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
//                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
//                                    int actionState, boolean isCurrentlyActive) {
//                // Customize the swipe background, icons, etc. here
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//            }
//        });
//
//        itemTouchHelper.attachToRecyclerView(recyclerView);
//
//        if (!hasPermissions(getContext(), REQUIRED_PERMISSIONS)) {
//            ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS, MULTIPLE_PERMISSIONS_REQUEST_CODE);
//        } else {
//            readContacts();
//        }
//
//        contactsAdapter = new ContactsAdapter(contactList, new ContactsAdapter.OnUpdateClickListener() {
//            @Override
//            public void onUpdateClick(Contact contact) {
//                showUpdateDialog(contact);
//            }
//        }, new ContactsAdapter.OnDeleteClickListener() {
//            @Override
//            public void onDeleteClick(Contact contact) {
//                deleteContact(contact);
//            }
//        });
//
//        recyclerView.setAdapter(contactsAdapter);
//
//
//        recyclerView.setAdapter(contactsAdapter);
//
//        return view;
//    }
//    private void showUpdateDialog(final Contact contact) {
//        if (contact.getId() == null) {
//            Toast.makeText(getContext(), "Contact ID is missing", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        final Dialog dialog = new Dialog(getContext());
//        dialog.setContentView(R.layout.fragment_update_delete); // Create a layout resource file for this dialog
//        EditText etName = dialog.findViewById(R.id.etName);
//        EditText etPhoneNumber = dialog.findViewById(R.id.etPhoneNumber);
//        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);
//
//        etName.setText(contact.getName());
//        etPhoneNumber.setText(contact.getPhoneNumber());
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String newName = etName.getText().toString();
//                String newPhoneNumber = etPhoneNumber.getText().toString();
//
//                contact.setName(newName);
//                contact.setPhoneNumber(newPhoneNumber);
//
//                // Update the contact in Firebase
//                DatabaseReference contactRef = databaseContacts.child(contact.getId());
//                contactRef.setValue(contact);
//
//                // Update the contact in the local list and notify the adapter
//                int index = contactList.indexOf(contact);
//                contactList.set(index, contact);
//                contactsAdapter.notifyItemChanged(index);
//
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }

    private void showUpdateDialog(final Contact contact) {
        if (contact.getId() == null) {
            Toast.makeText(getContext(), "Contact ID is missing", Toast.LENGTH_SHORT).show();
            return;
        }

        UpdateDeleteFragment updateDeleteFragment = new UpdateDeleteFragment(contact, new UpdateDeleteFragment.OnUpdateContactListener() {
            @Override
            public void onUpdateContact(Contact updatedContact) {
                // Update the contact in Firebase
                DatabaseReference contactRef = databaseContacts.child(updatedContact.getId());
                contactRef.setValue(updatedContact);

                // Update the contact in the local list and notify the adapter
                int index = contactList.indexOf(updatedContact);
                contactList.set(index, updatedContact);
                contactsAdapter.notifyItemChanged(index);
            }
        });

        updateDeleteFragment.show(getFragmentManager(), "updateDeleteFragment");
    }

    private void deleteContact(final Contact contact) {
        if (contact.getId() == null) {
            Toast.makeText(getContext(), "Contact ID is missing", Toast.LENGTH_SHORT).show();
            return;
        }

        // Delete the contact from Firebase
        DatabaseReference contactRef = databaseContacts.child(contact.getId());
        contactRef.removeValue();

        // Remove the contact from the adapter
        contactsAdapter.removeContact(contact);
    }


    private boolean hasPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void makeCall(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }
    }

    private void startVoiceSearch() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST_VOICE_SEARCH);
        } else {
            launchVoiceSearch();
        }
    }

    private void launchVoiceSearch() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now");

        try {
            startActivityForResult(intent, VOICE_SEARCH_REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "Your device doesn't support speech input", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VOICE_SEARCH_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && !result.isEmpty()) {
                String voiceQuery = result.get(0);
                searchView.setQuery(voiceQuery, true);
            }
        }
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


//    void readContacts() {
//        Cursor phones = getActivity().getContentResolver().query(
//                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
//
//        if (phones != null) {
//            int nameIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
//            int numberIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//
//            if (nameIndex == -1 || numberIndex == -1) {
//                phones.close();
//                return;
//            }
//
//            mobileContactList.clear(); // Clear the list before adding new contacts
//
//            while (phones.moveToNext()) {
//                String name = phones.getString(nameIndex);
//                String phoneNumber = phones.getString(numberIndex);
////                Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
//                // Add the contact to the list
//                String id = UUID.randomUUID().toString();
//
//                mobileContactList.add(new Contact(id, name, phoneNumber));
//            }
//            phones.close();
//            mergeAndSetContacts();
//        }
//    }

    void readContacts() {
        Cursor phones = getActivity().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        if (phones != null) {
            int nameIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            if (nameIndex == -1 || numberIndex == -1) {
                phones.close();
                return;
            }

            mobileContactList.clear(); // Clear the list before adding new contacts

            while (phones.moveToNext()) {
                String name = phones.getString(nameIndex);
                String phoneNumber = phones.getString(numberIndex);
                // Add the contact to the list
                String id = UUID.randomUUID().toString();

                mobileContactList.add(new Contact(id, name, phoneNumber));
            }
            phones.close();
            mergeAndSetContacts();  // Refresh contacts in UI
        }
    }


//    void mergeAndSetContacts() {
//        contactList.clear();
//        contactList.addAll(mobileContactList);
//        contactList.addAll(firebaseContactList);
//
//        // Sort the contact list alphabetically
//        Collections.sort(contactList, new Comparator<Contact>() {
//            @Override
//            public int compare(Contact c1, Contact c2) {
//                return c1.getName().compareToIgnoreCase(c2.getName());
//            }
//        });
//
//        contactsAdapter.notifyDataSetChanged();
//
//        // Save merged contactList to Firebase
////        databaseContacts.setValue(contactList);  // Assuming your databaseContacts reference points to the correct location
//    }

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

        contactsAdapter.notifyDataSetChanged();  // Notify adapter of data change
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                readContacts();
//            }
//        } else if (requestCode == 1) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(getContext(), "Permission granted to make calls", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getContext(), "Permission denied to make calls", Toast.LENGTH_SHORT).show();
//            }
//        } else if (requestCode == PERMISSIONS_REQUEST_SEND_SMS) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(getContext(), "Permission granted to send SMS", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getContext(), "Permission denied to send SMS", Toast.LENGTH_SHORT).show();
//            }
//        } else if (requestCode == PERMISSIONS_REQUEST_VOICE_SEARCH) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                launchVoiceSearch();
//            } else {
//                Toast.makeText(getContext(), "Permission denied to use voice search", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//}


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == MULTIPLE_PERMISSIONS_REQUEST_CODE) {
//            Map<String, Integer> perms = new HashMap<>();
//            for (String permission : REQUIRED_PERMISSIONS) {
//                perms.put(permission, PackageManager.PERMISSION_GRANTED);
//            }
//
//            if (grantResults.length > 0) {
//                for (int i = 0; i < permissions.length; i++) {
//                    perms.put(permissions[i], grantResults[i]);
//                }
//
//                boolean allPermissionsGranted = true;
//                for (String permission : REQUIRED_PERMISSIONS) {
//                    if (perms.get(permission) != PackageManager.PERMISSION_GRANTED) {
//                        allPermissionsGranted = false;
//                        break;
//                    }
//                }
//                if (allPermissionsGranted) {
//                    readContacts();
//                    // Trigger fetching of call logs here if needed
//                } else {
//                    Toast.makeText(getContext(), "Permissions are required for this app to function properly", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
//}

    private boolean permissionsGranted = false;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MULTIPLE_PERMISSIONS_REQUEST_CODE) {
            Map<String, Integer> perms = new HashMap<>();
            for (String permission : REQUIRED_PERMISSIONS) {
                perms.put(permission, PackageManager.PERMISSION_GRANTED);
            }

            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; i++) {
                    perms.put(permissions[i], grantResults[i]);
                }

                boolean allPermissionsGranted = true;
                for (String permission : REQUIRED_PERMISSIONS) {
                    if (perms.get(permission) != PackageManager.PERMISSION_GRANTED) {
                        allPermissionsGranted = false;
                        break;
                    }
                }
                if (allPermissionsGranted) {
                    permissionsGranted = true;
                    loadContacts(); // Load contacts after permissions are granted
                } else {
                    Toast.makeText(getContext(), "Permissions are required for this app to function properly", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void loadContacts() {
        if (permissionsGranted) {
            readContacts(); // Load phone contacts
        }
    }
}