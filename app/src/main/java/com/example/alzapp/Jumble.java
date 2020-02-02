package com.example.alzapp;

import java.util.Random;
/*******
 Created on: 21/01/2020

 By: B.Priyatham sai chand


 ********/

public class Jumble {


    public static final Random RANDOM = new Random();
    public static final String[] WORDS = {"AIM", "BIRD",
            "PEN", "SET", "GUN", "FAN", "CUT", "BOOK", "MIC", "ACE",
             "FIT", "YES", "ONE", "WORK", "LAP", "PAW",
            "BUY", "MET", "SKIP", "JAM", "JAR", "ROW","OIL","SHE","LEG","SAD","DRY","DOT","FOG","HIM","FUN","LAW","SEA","SEE","DAD",
            "MOP", "ZOO", "WET", "TRY", "YES","MAIN","MAP","RAT","RISK","KILL","PAWN","LIKE","SIR","MAM","JOY","JUG","HAT","KITE","MEN","NUT","MAX","TAX",};

    public static String randomWord() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }

    public static String shuffleWord(String word) {
        if (word != null  &&  !"".equals(word)) {
            char a[] = word.toCharArray();

            for (int i = 0; i < a.length; i++) {
                int j = RANDOM.nextInt(a.length);
                char tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }

            return new String(a);
        }

        return word;
    }
}
