package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.Util;


import android.content.Context;
import android.content.SharedPreferences;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.Constants.Constants;

public class SharedPreferencesUtil {

    private static SharedPreferences.Editor editor;

    public static SharedPreferences sharedPreferences(Context context){
        return context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Método para guardar status logado ou não logado
     * @param context
     * @param status
     */
    public static void isLogged(Context context, boolean status){
        editor = sharedPreferences(context).edit();
        editor.putBoolean("isLogged", status);
        editor.commit();
    }

    /**
     * Método que retorna se o usuário está logado ou não
     * @param context
     * @return
     */
    public static boolean isLogged(Context context){
        return sharedPreferences(context).getBoolean("isLogged", false);
    }

}
