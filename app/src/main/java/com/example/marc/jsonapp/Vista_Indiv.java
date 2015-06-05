package com.example.marc.jsonapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Vista_Indiv extends Activity {

    static final String KEY_ID_MATERIA = "id_materia";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_CREDITOS = "creditos";
    static final String KEY_SEMESTRE = "semestre";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista__indiv);

        Intent in = getIntent();

        // Get XML values from previous intent
        String id_materia = in.getStringExtra(KEY_ID_MATERIA);
        String nombre = in.getStringExtra(KEY_NOMBRE);
        String creditos= in.getStringExtra(KEY_CREDITOS);
        String semestre= in.getStringExtra(KEY_SEMESTRE);


        // Displaying all values on the screen
        TextView lb_id = (TextView) findViewById(R.id.id_materia);
        TextView lb_nombre = (TextView) findViewById(R.id.nombre);
        TextView lb_creditos = (TextView) findViewById(R.id.creditos);
        TextView lb_semestre = (TextView) findViewById(R.id.semestre);

        lb_id.setText(id_materia);
        lb_nombre.setText(nombre);
        lb_creditos.setText(creditos);
        lb_semestre.setText(semestre);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vista__indiv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
