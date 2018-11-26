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
import android.widget.Toast;

import com.sensorem.overwatch.CodesSharedPreferences;
import com.sensorem.overwatch.R;

public class change_PIN_Fragment extends DialogFragment {

    private EditText oldPasscodeEditText;
    private EditText newPasscodeEditText;
    private Button changePasscodeSaveButton;
    private Button changePasscodeCancelButton;
    private CodesSharedPreferences codesSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflator, @Nullable ViewGroup container, final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflator.inflate(R.layout.activity_change__pin__fragment, container, false);

        oldPasscodeEditText = view.findViewById(R.id.oldPasscodeEditText);
        newPasscodeEditText = view.findViewById(R.id.newPasscodeEditText);
        changePasscodeSaveButton = view.findViewById(R.id.changePasscodeSaveButton);
        changePasscodeCancelButton = view.findViewById(R.id.changePasscodeCancelButton);

        codesSharedPreferences = new CodesSharedPreferences(getActivity());

        if(codesSharedPreferences.getArmDisarmPasscode() == null ||codesSharedPreferences.getArmDisarmPasscode().isEmpty())
            Toast.makeText(getActivity(), "Currently no pin saved, please just enter a new password", Toast.LENGTH_LONG).show();

        changePasscodeCancelButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                cancelPasscodeFragment();
            }
        });

        changePasscodeSaveButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String oldPin = oldPasscodeEditText.getText().toString();
                String newPin = newPasscodeEditText.getText().toString();
                String savedPin = codesSharedPreferences.getArmDisarmPasscode();

                if(savedPin == null || savedPin.isEmpty())
                {
                    if(newPin.length() != 5)
                        Toast.makeText(getActivity(), "Please enter a pin of lenght 5", Toast.LENGTH_LONG).show();
                    else
                    {
                        codesSharedPreferences.saveArmDisarmPasscode(newPin);
                        Toast.makeText(getActivity(), "New pin saved", Toast.LENGTH_SHORT).show();
                        cancelPasscodeFragment();
                    }
                }
                else
                {
                    if(oldPin.length() != 5 || newPin.length() != 5)
                        Toast.makeText(getActivity(), "Please enter a pin of lenght 5", Toast.LENGTH_LONG).show();
                    else if(!oldPin.equals(savedPin))
                        Toast.makeText(getActivity(), "Old password is invalid", Toast.LENGTH_SHORT).show();
                    else
                    {
                        codesSharedPreferences.saveArmDisarmPasscode(newPin);
                        Toast.makeText(getActivity(), "New pin saved", Toast.LENGTH_SHORT).show();
                        cancelPasscodeFragment();
                    }
                }

            }
        });

        return view;


    }

    public void cancelPasscodeFragment(){
        getDialog().dismiss();
    }

    public void savePasscodeFragment(){
        //Function to be made
        //Will save the information inputted
    }
}
