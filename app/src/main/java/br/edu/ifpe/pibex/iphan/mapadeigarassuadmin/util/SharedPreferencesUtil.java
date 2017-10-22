package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.util;


import android.content.Context;
import android.content.SharedPreferences;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.constants.Constants;

public class SharedPreferencesUtil {

    public static SharedPreferences.Editor editor;

    public static SharedPreferences sharedPreferences(Context context) {
        return context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Método para guardar status logado ou não logado
     *
     * @param context
     * @param status
     */
    public static void isLogged(Context context, boolean status) {
        editor = sharedPreferences(context).edit();
        editor.putBoolean("isLogged", status);
        editor.commit();
    }

    /**
     * Método que retorna se o usuário está logado ou não
     *
     * @param context
     * @return
     */
    public static boolean isLogged(Context context) {
        return sharedPreferences(context).getBoolean("isLogged", false);
    }

    /**
     * Método para setar o tipo do mapa
     *
     * @param context
     * @param type
     */
    public static void setTypeMaps(Context context, int type) {
        editor = sharedPreferences(context).edit();
        editor.putInt("typeMaps", type);
        editor.commit();
    }

    /**
     * Método para pegar o tipo do mapa
     *
     * @param context
     * @return
     */
    public static int getTypeMaps(Context context) {
        return sharedPreferences(context).getInt("typeMaps", 0);
    }

    public static void email(Context context, String email){
        editor = sharedPreferences(context).edit();
        editor.putString("email", email);
        editor.commit();
    }

    public static String email(Context context){
        return sharedPreferences(context).getString("email", "");
    }

    public static void password(Context context, String password){
        editor = sharedPreferences(context).edit();
        editor.putString("password", password);
        editor.commit();
    }

    public static String password(Context context){
        return sharedPreferences(context).getString("password", "");
    }

    public static SharedPreferences.Editor remove(){
        return editor;
    }

}
