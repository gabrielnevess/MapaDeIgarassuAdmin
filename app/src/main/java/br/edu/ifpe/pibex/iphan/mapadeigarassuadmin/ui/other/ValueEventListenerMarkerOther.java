package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.other;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.ConnectionFireBaseModel;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.GoogleMapsModel;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.LocationModel;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.dialog.AlertDialogMessage;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.util.DataBaseUtil;

public class ValueEventListenerMarkerOther implements ValueEventListener{

    private DataBaseUtil dataBaseUtil;

    public ValueEventListenerMarkerOther(DataBaseUtil dataBaseUtil) {
        this.dataBaseUtil = dataBaseUtil;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        GoogleMapsModel.getMap().clear(); /*Limpando o mapa*/
        this.dataBaseUtil.dropTable(); //drop table
        Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();

        for (DataSnapshot dataSnapshot1 : dataSnapshots) { /*Inserindo pontos ao mapa*/

            final LocationModel local = dataSnapshot1.getValue(LocationModel.class);
            this.dataBaseUtil.insertLocation(local); /*Inserindo pontos marcados no mapa para o banco local*/
            MarkerOther.marker(local.getName(), local.getLatitude(), local.getLongitude()); //Add marker

        }

        AlertDialogMessage.progressDialogDismiss(); //Fechando progress Dialog

    }

    @Override
    public void onCancelled(DatabaseError error) {}
}
