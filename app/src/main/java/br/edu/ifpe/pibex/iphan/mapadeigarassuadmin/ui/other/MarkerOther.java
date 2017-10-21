package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.other;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.R;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.GoogleMapsModel;

public class MarkerOther {
    public static void marker(String title, Double latitude, Double longitude){
        GoogleMapsModel.getMap().addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(title)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_map)));
    }
}
