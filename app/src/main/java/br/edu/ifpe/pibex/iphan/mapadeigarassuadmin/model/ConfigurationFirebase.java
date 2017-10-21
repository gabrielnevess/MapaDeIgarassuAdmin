package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigurationFirebase {

    private static DatabaseReference referenceFirebase;
    private static FirebaseAuth authenticationFirebase;

    public static DatabaseReference getReferenceFirebase(){
        if(referenceFirebase == null){
            referenceFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenceFirebase;
    }

    public static FirebaseAuth getFirebaseAuth(){
        if(authenticationFirebase == null){
            authenticationFirebase = FirebaseAuth.getInstance();
        }
        return authenticationFirebase;
    }

}
