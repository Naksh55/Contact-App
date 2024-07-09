package com.naksh.contactapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactsAdapter contactsAdapter;
    private List<Contact> contactList;
    private ImageView addImg;
    private DatabaseReference databaseContacts;
    private SearchView searchView;



    String name="naksh";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView=findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts");

        recyclerView = findViewById(R.id.recyclerView);
        addImg = findViewById(R.id.addimg);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactList = new ArrayList<>();
        contactsAdapter = new ContactsAdapter(contactList);
        recyclerView.setAdapter(contactsAdapter);

        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddContactDialog();
            }
        });

        databaseContacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contactList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Contact contact = postSnapshot.getValue(Contact.class);
                    contactList.add(contact);
                }
                contactsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    private void filter(String query) {
        ArrayList<Contact> filteredList = new ArrayList<>();
        for (Contact item : contactList) {
            // Filter logic based on the name and phone number attributes
            if (item.getName().toLowerCase().contains(query.toLowerCase()) ||
                    item.getPhoneNumber().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        // Update the dataset with the filtered results
        contactsAdapter.updateList(filteredList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("SearchDebug", "onCreateOptionsMenu called");
        Toast.makeText(this, "onCreateOptionsMenu called", Toast.LENGTH_SHORT).show();
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        assert searchView != null;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SearchDebug", "onQueryTextSubmit: " + query);
                Toast.makeText(MainActivity.this, "Query submitted: " + query, Toast.LENGTH_SHORT).show();
                filter(query);
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SearchDebug", "onQueryTextChange: " + newText);
                Toast.makeText(MainActivity.this, "Query changed: " + newText, Toast.LENGTH_SHORT).show();
                filter(newText);
                txtSearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("contacts");

        // Query the database to find contacts with names or phone numbers that start with the search string
        Query queryByName = databaseReference.orderByChild("name")
                .startAt(str)
                .endAt(str + "\uf8ff");

        Query queryByPhoneNumber = databaseReference.orderByChild("phoneNumber")
                .startAt(str)
                .endAt(str + "\uf8ff");

        queryByName.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Contact> filteredList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Contact contact = snapshot.getValue(Contact.class);
                    if (contact != null && contact.getName() != null &&
                            contact.getName().toLowerCase().startsWith(str.toLowerCase())) {
                        filteredList.add(contact);
                    }
                }
                queryByPhoneNumber.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Contact contact = snapshot.getValue(Contact.class);
                            if (contact != null && contact.getPhoneNumber() != null &&
                                    contact.getPhoneNumber().toLowerCase().startsWith(str.toLowerCase())) {
                                if (!filteredList.contains(contact)) {
                                    filteredList.add(contact);
                                }
                            }
                        }
                        // Update the dataset with the filtered results
                        contactsAdapter.updateList(filteredList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("MainActivity", "Database query cancelled.", databaseError.toException());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity", "Database query cancelled.", databaseError.toException());
            }
        });
    }


    private void showAddContactDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_contact, null);
        builder.setView(dialogView);

        final EditText etDialogName = dialogView.findViewById(R.id.etDialogName);
        final EditText etDialogPhoneNumber = dialogView.findViewById(R.id.etDialogPhoneNumber);
        Button btnDialogAdd = dialogView.findViewById(R.id.btnDialogAdd);

        final AlertDialog dialog = builder.create();

        btnDialogAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etDialogName.getText().toString().trim();
                String phoneNumber = etDialogPhoneNumber.getText().toString().trim();

                if (!name.isEmpty() && !phoneNumber.isEmpty()) {
                    String id = databaseContacts.push().getKey();
                    Contact contact = new Contact(id, name, phoneNumber);
                    assert id != null;
                    databaseContacts.child(id).setValue(contact);
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter both name and phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }
}
