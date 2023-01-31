package com.android.monopolist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class SelectReceiverDialog extends DialogFragment {
    String selectedItem = "";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] choosePlayersArray = new String[MainActivity.playersArray.size()-1];
        int j = 0;
        for (int i = 0; i < MainActivity.playersArray.size(); i++) {
            if(MainActivity.playersArray.get(i) == MainActivity.currentPlayer) {
                continue;
            } else if(MainActivity.playersArray.get(i) == null) {
                continue;
            } else {
                choosePlayersArray[j] = MainActivity.playersArray.get(i).toString();
                j++;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select money receiver")
                // добавляем переключатели
                .setSingleChoiceItems(choosePlayersArray, -1,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int item) {
                                selectedItem = choosePlayersArray[item];
                            }
                        })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Bundle result = new Bundle();
                        result.putString("moneyReceiver", selectedItem);
                        getParentFragmentManager().setFragmentResult("selectReceiver", result);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();

    }
}
