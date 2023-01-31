package com.android.monopolist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class StartSelectPlayersDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] playersArray = new String[4];
        playersArray[0] = Players.RED.toString();
        playersArray[1] = Players.GREEN.toString();
        playersArray[2] = Players.BLUE.toString();
        playersArray[3] = Players.YELLOW.toString();

        final boolean[] checkedPlayersArray = {false, false, false, false};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select players")
                .setMultiChoiceItems(playersArray, checkedPlayersArray,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                checkedPlayersArray[which] = isChecked;
                            }
                        })
                .setPositiveButton("Start",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Bundle result = new Bundle();
                                result.putBooleanArray("checkedPlayersArray", checkedPlayersArray);
                                getParentFragmentManager().setFragmentResult("selectPlayers", result);

                                Toast.makeText(getActivity(), "Starting new game", Toast.LENGTH_LONG).show();
                            }
                        })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });

        return builder.create();
    }
}
