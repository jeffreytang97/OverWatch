package com.sensorem.overwatch.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sensorem.overwatch.R;

public class arm_alarm_Fragment extends DialogFragment {

    private EditText armFragmentHourEditText;
    private EditText armFragmentMinuteEditText;
    private Button armFragmentSaveButton;
    private Button armFragmentCancelButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflator, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflator.inflate(R.layout.activity_arm_alarm__fragment, container, false);

        armFragmentHourEditText = view.findViewById(R.id.armFragmentHourEditText);
        armFragmentMinuteEditText = view.findViewById(R.id.armFragmentMinuteEditText);
        armFragmentSaveButton = view.findViewById(R.id.armFragmentSaveButton);
        armFragmentCancelButton = view.findViewById(R.id.armFragmentCancelButton);


        return view;
    }
}
