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

public class passcodeFragment extends DialogFragment {

    private EditText passcodeEditText;
    private Button passcodeConfirmButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflator, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflator.inflate(R.layout.activity_passcode_fragment, container, false);

        passcodeEditText = view.findViewById(R.id.passcodeEditText);
        passcodeConfirmButton = view.findViewById(R.id.passcodeConfirmButton);



        return view;
    }


}
