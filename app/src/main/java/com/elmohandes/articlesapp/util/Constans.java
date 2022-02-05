package com.elmohandes.articlesapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constans {

    public static final String API_KEY ="6076ff04dd194395858c339a41cecac8";

    public static Date parse(String input) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd 'T' HH:mm.ssz");

        if (input.endsWith("z")){
            input = input.substring(0,input.length() - 1)+ "GMT-00:00";
        }else {
            int inset = 6;
            String s0 = input.substring(0 , input.length() - inset);
            String s1 = input.substring(input.length() -  inset , input.length());
            input = s0 + "GMT" + s1;
        }

        return dateFormat.parse(input);
    }

}
