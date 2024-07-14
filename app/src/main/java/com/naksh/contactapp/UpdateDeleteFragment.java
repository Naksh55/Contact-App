package com.naksh.contactapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateDeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateDeleteFragment extends DialogFragment {
    private EditText etName, etPhoneNumber;
    private Button btnUpdate;
    private Contact contact;
    private OnUpdateContactListener listener;

    public interface OnUpdateContactListener {
        void onUpdateContact(Contact contact);
    }

    public UpdateDeleteFragment(Contact contact, OnUpdateContactListener listener) {
        this.contact = contact;
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_delete, container, false);

        etName = view.findViewById(R.id.etName);
        etPhoneNumber = view.findViewById(R.id.etPhoneNumber);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        etName.setText(contact.getName());
        etPhoneNumber.setText(contact.getPhoneNumber());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact.setName(etName.getText().toString().trim());
                contact.setPhoneNumber(etPhoneNumber.getText().toString().trim());
                listener.onUpdateContact(contact);
                dismiss();
            }
        });

        return view;
    }
}
