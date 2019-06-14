package com.example.mathfun;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.Random;

public class Utils {

    public static void popAlertDialog(String title, String message, final Context ctx) {
        AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public static int[] getRandomNumbers(int quantity) {
        int[] n = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            int rn = random(10);
            if (!exists(n, rn)) {
                n[i] = rn;
            } else {
                i--;
            }
        }
        return n;
    }

    public static int[] getRandomWithMandatory(int validAnswer) {
        int[] rn = getRandomNumbers(3);
        if (exists(rn, validAnswer)) {
            return rn;
        } else {
            int validAnswerIndex = random(3);
            rn[validAnswerIndex] = validAnswer;
            return rn;
        }
    }

    public static int random(int bound) {
        Random r = new Random();
        return r.nextInt(bound);
    }

    private static boolean exists(int[] array, int n) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == n) {
                return true;
            }
        }
        return false;
    }
}
