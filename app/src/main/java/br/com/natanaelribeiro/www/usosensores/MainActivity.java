package br.com.natanaelribeiro.www.usosensores;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private List<Sensor> sensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensores = sensorManager.getSensorList(Sensor.TYPE_ALL);
        List<String> nomesSensores = new ArrayList<String>();

        for(Sensor sensor : sensores){
            nomesSensores.add(sensor.getName());
        }

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                nomesSensores)
        );
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(this, DetalheSensorActivity.class);
        i.putExtra("tipo", sensores.get(position).getType());
        startActivity(i);
    }
}
