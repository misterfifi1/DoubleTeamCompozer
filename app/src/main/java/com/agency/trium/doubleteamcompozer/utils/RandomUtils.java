package com.agency.trium.doubleteamcompozer.utils;

/**
 * Created by dev-w8-gfi on 06/08/2014.
 */
public class RandomUtils {

    public static int randomNumber(int min, int max){
        return (int)(min + (Math.random() * (max -min)));
    }


    public static int randomColor(){
        int number = randomNumber(0,5);
        switch (number){
            case 0:
                return android.R.color.holo_blue_dark;
            case 1:
                return android.R.color.holo_green_dark;
            case 2:
                return android.R.color.holo_orange_dark;
            case 3:
                return android.R.color.holo_red_dark;
            case 4:
                return android.R.color.holo_blue_light;
            default:
                return android.R.color.holo_purple;


        }
    }
}
