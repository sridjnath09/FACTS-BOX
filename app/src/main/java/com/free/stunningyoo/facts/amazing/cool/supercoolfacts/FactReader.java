package com.free.stunningyoo.facts.amazing.cool.supercoolfacts;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class FactReader {

    private Context con;
    String fact[];
    int i=0;
    public FactReader(Context context) {
        con = context; //<-- fill it with the Context you are passed
    }

    public String ReadFacts() throws IOException {

        AssetManager am = con.getAssets();
        Properties prop=new Properties();
        Random rand = new Random();
        int randomNum=0;

        try {
            //InputStream is = am.open("Data.txt");
            InputStream is = am.open("Data.properties");
            //BufferedReader reader = new BufferedReader(new InputStreamReader(is));
           // fact=reader.readLine().split("$");
            //System.out.print(fact);
            prop.load(is);
           fact=prop.getProperty("facts").split("#");
            i++;
            if(i>=fact.length){
                i=0;
            }
            randomNum=rand.nextInt(((fact.length-1) - 0) + 1) + 0;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return fact[randomNum];

    }
}
