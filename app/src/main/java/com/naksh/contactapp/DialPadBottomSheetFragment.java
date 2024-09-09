//// DialPadBottomSheetFragment.java
package com.naksh.contactapp;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
//
//public class DialPadBottomSheetFragment extends BottomSheetDialogFragment {
//
//    private SearchView searchViewDialPad;
//    private RecyclerView recyclerViewContacts;
//    private ContactsAdapter contactsAdapter;
//    private List<Contact> contactList;
//    private EditText numberInput;
//    private EditText nameInput;
//    private DatabaseReference databaseContacts;
//    ImageView deleteButton ;
//
//
//    public DialPadBottomSheetFragment() {
//        // Required empty public constructor
//    }
//
//    @SuppressLint("MissingInflatedId")
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);
//        numberInput = view.findViewById(R.id.numberInput);
//        nameInput = view.findViewById(R.id.numberName);
//         deleteButton =view.findViewById(R.id.deleteButton);
//
//        // Initialize Firebase Database reference
//        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts");
//        View.OnClickListener numberClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Button button = (Button) v;
//                numberInput.append(button.getText().toString());
//            }
//        };
//        view.findViewById(R.id.button0).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button1).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button2).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button3).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button4).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button5).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button6).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button7).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button8).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button9).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.buttonStar).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.buttonHash).setOnClickListener(numberClickListener);
//
//        view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveContact();
//            }
//        });
//
//        return view;
//    }
//
//    private void saveContact() {
//        String number = numberInput.getText().toString().trim();
//        String name = nameInput.getText().toString().trim();
//
//        if (TextUtils.isEmpty(name)) {
//            nameInput.setError("Name is required");
//            return;
//        }
//
//        if (TextUtils.isEmpty(number)) {
//            numberInput.setError("Number is required");
//            return;
//        }
//
//        String id = databaseContacts.push().getKey();
//        Contact contact = new Contact(id,name, number);
//        assert id != null;
//        databaseContacts.child(id).setValue(contact)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(getActivity(), "Contact saved", Toast.LENGTH_SHORT).show();
//                        numberInput.setText("");
//                        nameInput.setText("");
//                    } else {
//                        Toast.makeText(getActivity(), "Failed to save contact", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//}
////    private void filterContacts(String query) {
////        ArrayList<Contact> filteredList = new ArrayList<>();
////        for (Contact contact : contactList) {
////            if (contact.getName().toLowerCase().contains(query.toLowerCase()) ||
////                    contact.getPhoneNumber().contains(query)) {
////                filteredList.add(contact);
////            }
////        }
////        contactsAdapter.updateList(filteredList);
////    }
//


//public class DialPadBottomSheetFragment extends BottomSheetDialogFragment {
//
//    private EditText numberInput;
//    private EditText nameInput;
//    private DatabaseReference databaseContacts;
//    private ImageView deleteButton;
//
//    public DialPadBottomSheetFragment() {
//        // Required empty public constructor
//    }
//
//    @SuppressLint("MissingInflatedId")
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);
//        numberInput = view.findViewById(R.id.numberInput);
//        nameInput = view.findViewById(R.id.numberName);
//        deleteButton = view.findViewById(R.id.deleteButton);
//
//        // Initialize Firebase Database reference
//        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts");
//
//        // Set up number button click listeners
//        setNumberButtonClickListeners(view);
//
//        // Set up delete button click listener
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String currentText = numberInput.getText().toString();
//                if (!currentText.isEmpty()) {
//                    numberInput.setText(currentText.substring(0, currentText.length() - 1));
//                    numberInput.setSelection(numberInput.getText().length());
//                }
//            }
//        });
//
//        // Set up save button click listener
//        view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveContact();
//            }
//        });
//
//        return view;
//    }
//
//    private void setNumberButtonClickListeners(View view) {
//        View.OnClickListener numberClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Ensure the number input field is focused
//                numberInput.requestFocus();
//
//                Button button = (Button) v;
//                numberInput.append(button.getText().toString());
//            }
//        };
//        view.findViewById(R.id.button0).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button1).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button2).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button3).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button4).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button5).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button6).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button7).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button8).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.button9).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.buttonStar).setOnClickListener(numberClickListener);
//        view.findViewById(R.id.buttonHash).setOnClickListener(numberClickListener);
//    }
//
//
//    private void saveContact() {
//        String number = numberInput.getText().toString().trim();
//        String name = nameInput.getText().toString().trim();
//
//        if (TextUtils.isEmpty(name)) {
//            nameInput.setError("Name is required");
//            return;
//        }
//
//   if (TextUtils.isEmpty(number)) {
//        // Show error for empty number
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(getActivity(), "Number is required", Toast.LENGTH_SHORT).show();
//            }
//        });
//        return; // Exit method if number is empty
//    }
//
//    // Validate number format
//    if (!isValidPhoneNumber(number)) {
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(getActivity(), "Please enter a valid 10-digit number", Toast.LENGTH_SHORT).show();
//            }
//        });
//        return; // Exit method if number is not valid
//    }
//        // Check if the contact already exists in Firebase
//        databaseContacts.orderByChild("phoneNumber").equalTo(number).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    // Contact with this number already exists in Firebase
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            numberInput.setError("Contact with this number already exists");
//                            Toast.makeText(getActivity(), "Contact with this number already exists", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } else {
//                    // Proceed to save contact to Firebase
//                    String id = databaseContacts.push().getKey();
//                    if (id != null) {
//                        Contact contact = new Contact(id, name, number);
//                        databaseContacts.child(id).setValue(contact)
//                                .addOnCompleteListener(task -> {
//                                    if (task.isSuccessful()) {
//                                        getActivity().runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Toast.makeText(getActivity(), "Contact saved", Toast.LENGTH_SHORT).show();
//                                                numberInput.setText("");
//                                                nameInput.setText("");
//                                            }
//                                        });
//                                    } else {
//                                        getActivity().runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Toast.makeText(getActivity(), "Failed to save contact", Toast.LENGTH_SHORT).show();
//                                            }
//                                        });
//                                    }
//                                });
//                    } else {
//                        // Handle the case where id is null
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(getActivity(), "Failed to generate contact ID", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle potential errors
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getActivity(), "Failed to check contact existence: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//    }
//
//
//    private boolean isValidPhoneNumber(String phoneNumber) {
//        // Check if the phone number is exactly 10 digits
//        return phoneNumber.length() == 10 && TextUtils.isDigitsOnly(phoneNumber);
//    }
//}


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DialPadBottomSheetFragment extends BottomSheetDialogFragment {

    private EditText numberInput;
    private EditText nameInput;
    private DatabaseReference databaseContacts;
    private ImageView deleteButton;

    public DialPadBottomSheetFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);
        numberInput = view.findViewById(R.id.numberInput);
        nameInput = view.findViewById(R.id.numberName);
        deleteButton = view.findViewById(R.id.deleteButton);

        // Initialize Firebase Database reference
        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts");

        // Set up number button click listeners
        setNumberButtonClickListeners(view);

        // Set up delete button click listener
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = numberInput.getText().toString();
                if (!currentText.isEmpty()) {
                    numberInput.setText(currentText.substring(0, currentText.length() - 1));
                    numberInput.setSelection(numberInput.getText().length());
                }
            }
        });

        // Set up save button click listener
        view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });

        // Set focus change listener for nameInput
        nameInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        return view;
    }

    private void setNumberButtonClickListeners(View view) {
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                numberInput.append(button.getText().toString());
            }
        };
        view.findViewById(R.id.button0).setOnClickListener(numberClickListener);
        view.findViewById(R.id.button1).setOnClickListener(numberClickListener);
        view.findViewById(R.id.button2).setOnClickListener(numberClickListener);
        view.findViewById(R.id.button3).setOnClickListener(numberClickListener);
        view.findViewById(R.id.button4).setOnClickListener(numberClickListener);
        view.findViewById(R.id.button5).setOnClickListener(numberClickListener);
        view.findViewById(R.id.button6).setOnClickListener(numberClickListener);
        view.findViewById(R.id.button7).setOnClickListener(numberClickListener);
        view.findViewById(R.id.button8).setOnClickListener(numberClickListener);
        view.findViewById(R.id.button9).setOnClickListener(numberClickListener);
        view.findViewById(R.id.buttonStar).setOnClickListener(numberClickListener);
        view.findViewById(R.id.buttonHash).setOnClickListener(numberClickListener);
    }

    private void saveContact() {
        String number = numberInput.getText().toString().trim();
        String name = nameInput.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            nameInput.setError("Name is required");
            return;
        }

        if (TextUtils.isEmpty(number)) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "Number is required", Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }

        if (!isValidPhoneNumber(number)) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "Please enter a valid 10-digit number", Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }

        databaseContacts.orderByChild("phoneNumber").equalTo(number).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            numberInput.setError("Contact with this number already exists");
                            Toast.makeText(getActivity(), "Contact with this number already exists", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    String id = databaseContacts.push().getKey();
                    if (id != null) {
                        Contact contact = new Contact(id, name, number);
                        databaseContacts.child(id).setValue(contact)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getActivity(), "Contact saved", Toast.LENGTH_SHORT).show();
                                                numberInput.setText("");
                                                nameInput.setText("");
                                            }
                                        });
                                    } else {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getActivity(), "Failed to save contact", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });
                    } else {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Failed to generate contact ID", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Failed to check contact existence: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 10 && TextUtils.isDigitsOnly(phoneNumber);
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
