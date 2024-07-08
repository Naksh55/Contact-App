// DialPadBottomSheetFragment.java
package com.naksh.contactapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DialPadBottomSheetFragment extends BottomSheetDialogFragment {

    private SearchView searchViewDialPad;
    private RecyclerView recyclerViewContacts;
    private ContactsAdapter contactsAdapter;
    private List<Contact> contactList;
    private EditText numberInput;

    public DialPadBottomSheetFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);
        numberInput = view.findViewById(R.id.numberInput);
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

        view.findViewById(R.id.callButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle call button click
            }
        });

        return view;
    }
}
//    private void filterContacts(String query) {
//        ArrayList<Contact> filteredList = new ArrayList<>();
//        for (Contact contact : contactList) {
//            if (contact.getName().toLowerCase().contains(query.toLowerCase()) ||
//                    contact.getPhoneNumber().contains(query)) {
//                filteredList.add(contact);
//            }
//        }
//        contactsAdapter.updateList(filteredList);
//    }

