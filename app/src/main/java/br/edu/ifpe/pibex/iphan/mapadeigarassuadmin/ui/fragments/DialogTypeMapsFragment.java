package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.R;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.constants.Constants;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.GoogleMapsModel;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.util.SharedPreferencesUtil;

public class DialogTypeMapsFragment {
    private static AlertDialog.Builder builder;

    public static void alertDialog(final Context context) {
        builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.type_maps)
                .setItems(R.array.type_maps, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            GoogleMapsModel.getMap().setMapType(Constants.MAP_TYPE_NORMAL);
                            SharedPreferencesUtil.setTypeMaps(context, Constants.MAP_TYPE_NORMAL);
                        } else if (which == 1) {
                            GoogleMapsModel.getMap().setMapType(Constants.MAP_TYPE_HYBRID);
                            SharedPreferencesUtil.setTypeMaps(context, Constants.MAP_TYPE_HYBRID);
                        }
                    }
                });
        builder.show();
    }

}
