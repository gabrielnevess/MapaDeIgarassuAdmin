package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.Util;


import android.content.Context;
import android.content.SharedPreferences;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.Constants.Constants;

public class SharedPreferencesUtil {

    private static SharedPreferences.Editor editor;

    public static SharedPreferences sharedPreferences(Context context){
        return context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void isLogged(Context context, boolean status){
        editor = sharedPreferences(context).edit();
        editor.putBoolean("isLogged", status);
        editor.commit();
    }

    public static boolean isLogged(Context context){
        return sharedPreferences(context).getBoolean("isLogged", false);
    }

}
