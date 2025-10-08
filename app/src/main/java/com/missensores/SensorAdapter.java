package com.missensores;

import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorVH> {
    private final List<Sensor> sensores;
    public SensorAdapter(List<Sensor> sensores) {
        this.sensores = sensores;
    }
    @NonNull
    @Override
    public SensorVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sensor, parent, false);
        return new SensorVH(v);
    }
    @Override
    public void onBindViewHolder(@NonNull SensorVH holder, int position) {
        Sensor s = sensores.get(position);

        holder.txtNombre.setText(s.getName());
        holder.txtTipo.setText("Tipo: " + typeToString(s.getType()));
        holder.txtVendor.setText("Fabricante: " + s.getVendor());
        holder.txtPower.setText(String.format("Potencia: %.2f mA", s.getPower()));
    }
    @Override
    public int getItemCount() {
        return sensores != null ? sensores.size() : 0;
    }
    static class SensorVH extends RecyclerView.ViewHolder {
        TextView txtNombre, txtTipo, txtVendor, txtPower;

        public SensorVH(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtTipo   = itemView.findViewById(R.id.txtTipo);
            txtVendor = itemView.findViewById(R.id.txtVendor);
            txtPower  = itemView.findViewById(R.id.txtPower);
        }
    }
    //mapeo
    private static String typeToString(int type) {
        switch (type) {
            case Sensor.TYPE_ACCELEROMETER: return "Acelerómetro";
            case Sensor.TYPE_GYROSCOPE: return "Giroscopio";
            case Sensor.TYPE_MAGNETIC_FIELD: return "Campo magnético";
            case Sensor.TYPE_GRAVITY: return "Gravedad";
            case Sensor.TYPE_LINEAR_ACCELERATION: return "Aceleración lineal";
            case Sensor.TYPE_ROTATION_VECTOR: return "Vector de rotación";
            case Sensor.TYPE_PROXIMITY: return "Proximidad";
            case Sensor.TYPE_LIGHT: return "Luz";
            case Sensor.TYPE_PRESSURE: return "Presión";
            case Sensor.TYPE_AMBIENT_TEMPERATURE: return "Temperatura ambiente";
            case Sensor.TYPE_RELATIVE_HUMIDITY: return "Humedad relativa";
            case Sensor.TYPE_HEART_RATE: return "Frecuencia cardiaca";
            case Sensor.TYPE_STEP_COUNTER: return "Contador de pasos";
            case Sensor.TYPE_STEP_DETECTOR: return "Detector de pasos";
            default: return "Tipo " + type;
        }
    }
}
