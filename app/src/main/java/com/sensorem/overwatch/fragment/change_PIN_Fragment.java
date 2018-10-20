package com.sensorem.overwatch.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sensorem.overwatch.R;

public class change_PIN_Fragment extends DialogFragment {

    private EditText oldPasscodeEditText;
    private EditText newPasscodeEditText;
    private Button changePasscodeSaveButton;
    private Button changePasscodeCancelButton;

    @Override
    public View onCreateView(LayoutInflater inflator, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflator.inflate(R.layout.activity_change__pin__fragment, container, false);

        oldPasscodeEditText = view.findViewById(R.id.oldPasscodeEditText);
        newPasscodeEditText = view.findViewById(R.id.newPasscodeEditText);
        changePasscodeSaveButton = view.findViewById(R.id.changePasscodeSaveButton);
        changePasscodeCancelButton = view.findViewById(R.id.changePasscodeCancelButton);

        return view;


    }
}
