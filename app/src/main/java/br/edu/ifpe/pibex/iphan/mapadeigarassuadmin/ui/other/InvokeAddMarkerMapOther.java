package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.other;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.ConnectionFireBaseModel;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.dialog.AlertDialogMessage;

public class InvokeAddMarkerMapOther implements OnMapReadyCallback{

    private Context context;

    public InvokeAddMarkerMapOther(Context context) {
        this.context = context;
    }

    public void onAddMarkerFirebase() {

        ConnectionFireBaseModel.getReferenceFirebase().onDisconnect();

        AlertDialogMessage.progressDialogStart(context, "Aguarde", "Os pontos estão sendo carregados..."); //Exibindo janela de progresso
        ConnectionFireBaseModel.getReferenceFirebase()
                .child("locations")
                .addValueEventListener(new ValueEventListenerMarkerOther(this.context));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        onAddMarkerFirebase();
    }



}
