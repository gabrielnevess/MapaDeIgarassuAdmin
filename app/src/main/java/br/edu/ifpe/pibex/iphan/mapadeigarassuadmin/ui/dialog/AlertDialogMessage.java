package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.dialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogMessage {

    private static AlertDialog alertDialog;
    private static AlertDialog.Builder builder;
    private static ProgressDialog progressDialog;

    /**
     * Método de alert
     *
     * @param context
     * @param title
     * @param message
     */
    public static void alertDialogMessage(Context context, String title, String message) {
        builder = new AlertDialog.Builder(context);
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

}
