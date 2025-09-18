package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class RenameCityFragment extends DialogFragment {
    interface RenameCityDialogListener {
        void RenameCity(City city, int position);
    }

    private RenameCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof RenameCityDialogListener) {
            listener = (RenameCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement RenameCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_rename_city, null);

        EditText renameCityName = view.findViewById(R.id.rename_text_city_text);
        EditText renameProvinceName = view.findViewById(R.id.rename_text_province_text);

        Bundle args = getArguments();
        int position = -1;
        if (args != null) {
            renameCityName.setText(args.getString("cityName"));
            renameProvinceName.setText(args.getString("provinceName"));
            position = args.getInt("position", -1);
        }

        int finalPosition = position;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Rename city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Confirm", (dialog, which) -> {
                    String cityName = renameCityName.getText().toString();
                    String provinceName = renameProvinceName.getText().toString();
                    listener.RenameCity(new City(cityName, provinceName), finalPosition);
                })
                .create();
    }
}
