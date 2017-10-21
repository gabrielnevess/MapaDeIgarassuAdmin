package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.other;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.ConnectionFireBaseModel;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.GoogleMapsModel;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.LocationModel;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.dialog.AlertDialogMessage;

public class ValueEventListenerMarkerOther implements ValueEventListener{

    private Context context;
    /**
     * Método de Listener(esse método ficará ouvindo um evento se por acaso ouver alguma mudança no firebase
     * por exemplo: a adição de um novo ponto).
     *
     */

    public ValueEventListenerMarkerOther(Context context) {
        this.context = context;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
        GoogleMapsModel.getMap().clear(); /*Limpando o mapa*/

        for (DataSnapshot dataSnapshot1 : dataSnapshots) { /*Inserindo pontos ao mapa*/

            final LocationModel local = dataSnapshot1.getValue(LocationModel.class);
            MarkerOther.marker(local.getName(), local.getLatitude(), local.getLongitude()); //Add marker

        }

        AlertDialogMessage.progressDialogDismiss(); //Fechando progress Dialog
        ConnectionFireBaseModel.getReferenceFirebase().onDisconnect(); //Fechando conexão do firebase.

    }

    @Override
    public void onCancelled(DatabaseError error) {
    }

}
