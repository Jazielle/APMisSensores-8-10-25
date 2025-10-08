package com.missensores;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listaSensores;
    private Button btnActualizar;
    private SensorManager gestorSensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listaSensores = findViewById(R.id.listaSensores);
        btnActualizar = findViewById(R.id.btnActualizar);

        //llamar al servicio de sensores
        gestorSensores = (SensorManager) getSystemService(SENSOR_SERVICE);

        //METODO CARGAR SENSORES
        cargarSensores();
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarSensores();
                Toast.makeText(MainActivity.this,"sensores cargados",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarSensores() {
        List<Sensor> lista = gestorSensores.getSensorList(Sensor.TYPE_ALL);
        List<String> nombreSensors = new ArrayList<>();
        for (Sensor sensor : lista)
        {
            nombreSensors.add(sensor.getName());
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nombreSensors
        );
        listaSensores.setAdapter(adaptador);
    }
}