package br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.R;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.constants.Constants;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.model.GoogleMapsModel;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.adapter.GoogleInfoWindowAdapter;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.fragments.DialogTypeMapsFragment;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.ui.other.InvokeAddMarkerMapOther;
import br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.util.SharedPreferencesUtil;

import static br.edu.ifpe.pibex.iphan.mapadeigarassuadmin.R.id.map;

public class HomeActivity extends AppCompatActivity
        implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    private InvokeAddMarkerMapOther invokeAddMarkerMapOther;
    private View markerView;
    private final Context context;

    public HomeActivity() {
        this.context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        /*SupportMapFragment ==> Mapa*/
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*Inflate para o pop-up dos markers(Janela em cima do marker)*/
        this.markerView = getLayoutInflater().inflate(R.layout.marker_view, null);

        invokeAddMarkerMapOther = new InvokeAddMarkerMapOther(this.context);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        GoogleMapsModel.setMap(googleMap);

         /*Verificação de tipos de mapa*/
        if (Constants.MAP_TYPE_HYBRID == SharedPreferencesUtil.getTypeMaps(this)) {
            GoogleMapsModel.getMap().setMapType(Constants.MAP_TYPE_HYBRID);
        } else {
            GoogleMapsModel.getMap().setMapType(Constants.MAP_TYPE_NORMAL);
        }

        invokeAddMarkerMapOther.onAddMarkerFirebase(); //adicionando os marcados diretamente do firebase
        GoogleMapsModel.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.CENTER_LOCATION, 16)); /*Centro do mapa*/
        /*Botões de Zoom*/
        GoogleMapsModel.getMap().getUiSettings().setZoomControlsEnabled(true);

        infoWindow();

    }

    /*Método infoWindow, colocar pop-up para todos os marker*/
    private void infoWindow() {

        if (GoogleMapsModel.getMap() != null) {
            GoogleMapsModel.getMap().setInfoWindowAdapter(new GoogleInfoWindowAdapter(markerView));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            SharedPreferencesUtil.isLogged(this, false);
            Intent intent = new Intent(HomeActivity.this, Login.class);
            startActivity(intent);
        }else if (id == R.id.nav_type_maps) { /*Alert Dialog para escolher o tipo do mapa*/
            DialogTypeMapsFragment.alertDialog(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
