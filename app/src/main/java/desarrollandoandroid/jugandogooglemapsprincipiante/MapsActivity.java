package desarrollandoandroid.jugandogooglemapsprincipiante;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private CameraUpdate mCamera; // CameraUpdate nos permite el movimiento de la camara.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        // Creamos Markers para añadirlos a nuestro Mapa.
        setMarker(new LatLng(40.067589, -2.135677), "Cafetería", "Cafés a buen precio", 0.9F, 0.1F, 0.1F, R.drawable.cafeteria);
        setMarker(new LatLng(40.071180, -2.135145), "Restaurante", "Comida típica de Cuenca", 0.5F, 0.5F, 0.5F, R.drawable.restaurante);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.MenuOpcion1:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); // Establecemos el mapa normal
                return true;

            case R.id.MenuOpcion2:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); // Establecemos el mapa satelite
                return true;

            case R.id.MenuOpcion3:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); // Establecemos el mapa terrestre
                return true;

            case R.id.MenuOpcion4:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); // Establecemos el mapa hibrido
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true); // Habilitamos nuestra localización en el mapa
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); // Inicializamos la app con el mapa hibrido
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        // mMap.addMarker(new MarkerOptions().position(new LatLng(40.070823, -2.137360)).title("Cuenca")
        //         .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
        //         .icon(BitmapDescriptorFactory.fromResource(R.drawable.cafeteria))
        //         .snippet("Cidudad Patrimonio de la Humanidad"));
        mCamera = CameraUpdateFactory.newLatLngZoom(new LatLng(40.070823, -2.137360), 14);
        mMap.animateCamera(mCamera);
    }
    /**
     * Podemos añadir a nuestro mapa tantos Markers como queramos, solo tenemos que declararlos en el
     * método onCreate.
     *
     * @param position Es la latitud y longitud de donde va a estar situado nuestro Marker en el mapa.
     * @param title Es el título del Marker.
     * @param info Información adicional en la etiqueta del Marker.
     * @param opacity Opacidad del Marker.
     * @param dimension1 Alto del Marker.
     * @param dimension2 Ancho del Marker.
     * @param icon Es la imagen de nuestro Marker (Icono).
     */
    private void setMarker(LatLng position, String title, String info, float opacity, float dimension1, float dimension2, int icon){
        // Agregamos un marcador para indicar sitios de interes.
        mMap.addMarker(new MarkerOptions()
                .position(position)     // Posicion del marcador
                .title(title)           // Agrega titulo al marcador
                .snippet(info)          // Agrega información detalle relacionada con el marcador
                .alpha(opacity)         // Opacidad del icono
                .anchor(dimension1, dimension2)     // Tamaño del icono (alto y ancho)
                .icon(BitmapDescriptorFactory.fromResource(icon)));
    }
}
