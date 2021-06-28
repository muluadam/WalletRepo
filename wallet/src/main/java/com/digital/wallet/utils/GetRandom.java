package com.digital.wallet.utils;

import java.util.Random;

public class GetRandom {

    public static String generate(int length){
        if(length < 1)
            return String.valueOf(0);
        int random;

        Random r = new Random();
        random = r.nextInt((9*(int) Math.pow(10,length -1)) - 1) +
                (int)Math.pow(10, length -1);

        return String.valueOf(random);
    }
}
