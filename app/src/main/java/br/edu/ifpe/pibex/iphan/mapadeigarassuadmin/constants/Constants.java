package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.constants;

import com.google.android.gms.maps.model.LatLng;

public class Constants {

    //constante do SharedPreferences
    public static final String PREF_NAME = "PREF_NAME";

    //constantes para os tipos de mapa
    public static final int MAP_TYPE_NONE = 0;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;
    public static final int MAP_TYPE_HYBRID = 4;

    //constante center location para o mapa
    public static final LatLng CENTER_LOCATION = new LatLng(-7.834195, -34.906142);
}
