package org.ncarneiro.testeauto.combat;

import android.util.Log;

/**
 * Created by Nikolas on 31/08/2015.
 */
public class Dice {

    private static final String TAG = "DICE";

    public static int roll() {
        return roll(6);
    }

    public static int roll(int diceFaces) {
        return (int)(Math.random()*diceFaces+1);
    }

    public static int rollHand(int numberOfDices, int dicesFaces) {
        int acc = 0;
        for (int i = 0; i < numberOfDices; i++) {
            acc += roll(dicesFaces);
        }
        return acc;
    }

    public static int rollHand(int numberOfDices) {
        return rollHand(numberOfDices, 6);
    }

    public static int rollHand(int[] faces, int[] dices) {
        if (faces.length==dices.length) {
            int acc = 0;
            for (int i = 0; i < faces.length; i++) {
                for (int j = 0; j < dices[i]; j++) {
                    acc += roll(faces[i]);
                }
            }
            return acc;
        } else {
            Log.println(Log.ERROR, TAG, "Array length not matching.");
            return 0;
        }
    }

}