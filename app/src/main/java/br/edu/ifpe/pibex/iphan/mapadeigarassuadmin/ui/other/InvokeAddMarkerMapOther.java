package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.other;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.ConnectionFireBaseModel;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.dialog.AlertDialogMessage;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.util.DataBaseUtil;

public class InvokeAddMarkerMapOther implements OnMapReadyCallback{

    private Context context;
    private DataBaseUtil dataBaseUtil;

    public InvokeAddMarkerMapOther(Context context) {
        this.context = context;
        dataBaseUtil = new DataBaseUtil(context);
    }

    public void onAddMarkerFirebase() {

        ConnectionFireBaseModel.getReferenceFirebase().onDisconnect();

        AlertDialogMessage.progressDialogStart(context, "Aguarde", "Os pontos estão sendo carregados..."); //Exibindo janela de progresso
        ConnectionFireBaseModel.getReferenceFirebase()
                .child("locations")
                .addValueEventListener(new ValueEventListenerMarkerOther(this.dataBaseUtil));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        onAddMarkerFirebase();
    }

}
