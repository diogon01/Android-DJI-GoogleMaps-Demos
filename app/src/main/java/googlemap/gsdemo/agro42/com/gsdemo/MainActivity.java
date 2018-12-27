package googlemap.gsdemo.agro42.com.gsdemo;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends FragmentActivity implements View.OnClickListener,
        GoogleMap.OnMapClickListener, OnMapReadyCallback {

    protected static final String TAG = "MainActivity";
    private GoogleMap gMap;
    private Button locate, add, clear;
    private Button config, upload, start, stop;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * @Description : FUNÇÃO DE RESPOSTA AO BTN DE RETORNO
     */
    public void onReturn(View view) {
        Log.d(TAG, "onReturn");
        this.finish();
    }

    private void initUI() {
        locate = (Button) findViewById(R.id.locate);
        add = (Button) findViewById(R.id.add);
        clear = (Button) findViewById(R.id.clear);
        config = (Button) findViewById(R.id.config);
        upload = (Button) findViewById(R.id.upload);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        locate.setOnClickListener(this);
        add.setOnClickListener(this);
        clear.setOnClickListener(this);
        config.setOnClickListener(this);
        upload.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Quando a versão de compilação e destino for maior que 22, solicite o
        // seguindo as permissões em tempo de execução para garantir
        // SDK funciona bem.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.VIBRATE,
                            Manifest.permission.INTERNET, Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.WAKE_LOCK, Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SYSTEM_ALERT_WINDOW,
                            Manifest.permission.READ_PHONE_STATE,

                    }, 1);
        }

        setContentView(R.layout.activity_main);
        initUI();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void showSettingDialog() {
        LinearLayout wayPointSettings = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.dialog_waypointsetting, null);
        final TextView wpAltitude_TV = (TextView) wayPointSettings.findViewById(R.id.altitude);
        RadioGroup speed_RG = (RadioGroup) wayPointSettings.findViewById(R.id.speed);
        RadioGroup actionAfterFinished_RG = (RadioGroup) wayPointSettings.findViewById(R.id.actionAfterFinished);
        RadioGroup heading_RG = (RadioGroup) wayPointSettings.findViewById(R.id.heading);

        speed_RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO o método foi gerado automaticamente
                Log.d(TAG, "Selecione o acabamento de velocidade");
            }
        });

        actionAfterFinished_RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO o método foi gerado automaticamente
                Log.d(TAG, "Selecione opção  depois da finalizaçao");
            }
        });

        heading_RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                // TODO o método foi gerado automaticamente
                Log.d(TAG, "Selecione o top");
            }
        });

        new AlertDialog.Builder(this)
                .setTitle("")
                .setView(wayPointSettings)
                .setPositiveButton(R.string.dialog_finish, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO o método foi gerado automaticamente
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO o método foi gerado automaticamente
                        dialog.cancel();
                    }
                })
                .create()
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.config: {
                showSettingDialog();
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // TODO o método foi gerado automaticamente
        //Inicializando o objeto do Mapa
        if (gMap == null) {
            gMap = googleMap;
            setUpMap();

        }

    }

    private void setUpMap() {
        gMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

}
