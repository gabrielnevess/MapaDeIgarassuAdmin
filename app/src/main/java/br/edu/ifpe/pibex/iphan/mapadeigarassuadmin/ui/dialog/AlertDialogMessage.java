package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.dialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.R;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.ConnectionFireBaseModel;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.other.InvokeAddMarkerMapOther;

public class AlertDialogMessage {

    private static AlertDialog alertDialog;
    private static ProgressDialog progressDialog;

    /**
     * Método de alert
     *
     * @param context
     * @param title
     * @param message
     */
    public static void alertDialogMessage(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                alertDialog.dismiss();
            }
        });

        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

    }

    /**
     * Método para exibir um janela de progresso(Dialog)
     *
     * @param context
     * @param title
     * @param message
     */
    public static void progressDialogStart(Context context, String title, String message) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    //Método para parar de exibir o dialog
    public static void progressDialogDismiss() {
        progressDialog.cancel();
    }

    public static void alertDialogMarker(final Context context, final int _id, final String name, final String address,
                                         final String description, final double latitude, final double longitude) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View popUpView = layoutInflater.inflate(R.layout.alert_edit_marker, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(popUpView);

        final EditText editTextName = (EditText) popUpView.findViewById(R.id.editTextName);
        final EditText editTextAddress = (EditText) popUpView.findViewById(R.id.editTextAddress);
        final EditText editTextDescription = (EditText) popUpView.findViewById(R.id.editTextDescription);

        editTextName.setText(name);
        editTextAddress.setText(address);
        editTextDescription.setText(description);

        builder.setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                loginConfirm(context, _id, String.valueOf(editTextName.getText()), String.valueOf(editTextAddress.getText()),
                                        String.valueOf(editTextDescription.getText()), latitude, longitude);
                            }
                        })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    public static void loginConfirm(final Context context, final int _id, final String name, final String address,
                                    final String description, final double latitude, final double longitude) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View popUpViewConfirm = layoutInflater.inflate(R.layout.confirm_login, null);

        final AlertDialog.Builder builderConfirm = new AlertDialog.Builder(context);
        builderConfirm.setView(popUpViewConfirm);

        final EditText editTextEmail = (EditText) popUpViewConfirm.findViewById(R.id.editTextEmail);
        final EditText editTextPassword = (EditText) popUpViewConfirm.findViewById(R.id.editTextPassoword);

        builderConfirm.setCancelable(false)
                .setPositiveButton("Confirmar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Map<String, Object> location = new HashMap<String, Object>();
                                location.put("name", name);
                                location.put("address", address);
                                location.put("description", description);
                                location.put("latitude", latitude);
                                location.put("longitude", longitude);

                                //update no pontos
                                ConnectionFireBaseModel.getReferenceFirebase()
                                        .child("locations")
                                        .child(String.valueOf(_id - 1)).updateChildren(location);

                                InvokeAddMarkerMapOther invokeAddMarkerMapOther = new InvokeAddMarkerMapOther(context);
                                invokeAddMarkerMapOther.onAddMarkerFirebase();

                            }

                        })

                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = builderConfirm.create();
        alertDialog.show();

    }
}
