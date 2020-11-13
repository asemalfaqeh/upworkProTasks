package com.af.upworktasks.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtils {


    public static void replaceFragmentUtil(FragmentManager fragmentManager, Fragment fragment, int layout, Bundle bundle){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    }

    public static void replaceFragmentUtil(FragmentManager fragmentManager, Fragment fragment, int layout){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // and add the transaction to the back stack if needed
        fragmentTransaction.replace(layout, fragment);
        // Commit the transaction
        fragmentTransaction.commit();
    }

    public static void backToPreviousScreen(final Activity activity, View view){
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnKeyListener((v, keyCode, event) -> {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    showAlertDialog(activity);
                    return true;
                }
                return false;
            });
    }


    public static void showAlertDialog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false);
        builder.setMessage("are you sure you want to exit ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            activity.finish();
        });

        builder.setNegativeButton("No", (dialogInterface, i) -> {
        });

        AlertDialog alertDialog = builder.create();


        if (!alertDialog.isShowing()){
            alertDialog.show();
        }

    }

}
