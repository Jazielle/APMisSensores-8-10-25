package com.missensores;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter; // (ya no se usa, puedes eliminarla si quieres)
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listaSensores;
    private Button btnActualizar;
    private SensorManager gestorSensores;
    private SensorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listaSensores = findViewById(R.id.listaSensores);
        btnActualizar = findViewById(R.id.btnActualizar);
        listaSensores.setLayoutManager(new GridLayoutManager(this, 2));
        gestorSensores = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Cargar sensores inicial
        cargarSensores();
        btnActualizar.setOnClickListener(v -> {
            cargarSensores();
            Toast.makeText(MainActivity.this, "Sensores cargados", Toast.LENGTH_SHORT).show();
        });
    }
    private void cargarSensores() {
        List<Sensor> lista = gestorSensores.getSensorList(Sensor.TYPE_ALL);
        if (adapter == null) {
            adapter = new SensorAdapter(lista);
            listaSensores.setAdapter(adapter);
        } else {
            adapter = new SensorAdapter(lista);
            listaSensores.setAdapter(adapter);
        }
    }
}
