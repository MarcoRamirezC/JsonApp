package com.example.marc.jsonapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;

import android.view.View;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends ListActivity {
    private Context context;
    private static String url = "http://192.168.1.79/app/bbdd.json";

    static final String KEY_ID_MATERIA = "id_materia";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_CREDITOS = "creditos";
    static final String KEY_SEMESTRE = "semestre";

    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ProgressTask(MainActivity.this).execute();
    }

    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;

        private ListActivity activity;

        // private List<Message> messages;
        public ProgressTask(ListActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }
        private Context context;

        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ListAdapter adapter = new SimpleAdapter(context, jsonlist,
                    R.layout.lista_indi, new String[]{KEY_ID_MATERIA, KEY_NOMBRE, KEY_CREDITOS, KEY_SEMESTRE}, new int[]{
                    R.id.id_v_id_materia, R.id.v_nombre, R.id.v_nombre, R.id.v_creditos, R.id.v_semestre});

            setListAdapter(adapter);

            // selecting single ListView item
            lv = getListView();
            lv.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // getting values from selected ListItem
                    String tx_id_materia = ((TextView) view.findViewById(R.id.id_v_id_materia)).getText().toString();
                    String tx_nombre = ((TextView) view.findViewById(R.id.v_nombre)).getText().toString();
                    String txt_creditos = ((TextView) view.findViewById(R.id.v_creditos)).getText().toString();
                    String txt_semestre = ((TextView) view.findViewById(R.id.v_semestre)).getText().toString();

                    // Starting new intent
                    Intent in = new Intent(getApplicationContext(), Vista_Indiv.class);
                    in.putExtra(KEY_ID_MATERIA, tx_id_materia);
                    in.putExtra(KEY_NOMBRE, tx_nombre);
                    in.putExtra(KEY_CREDITOS, txt_creditos);
                    in.putExtra(KEY_SEMESTRE, txt_semestre);

                    startActivity(in);

                }
            });

        }
        protected Boolean doInBackground(final String... args) {

            JsonParse jParser = new JsonParse();

            // getting JSON string from URL
            JSONArray json = jParser.getJSONFromUrl(url);

            for (int i = 0; i < json.length(); i++) {

                try {
                    JSONObject c = json.getJSONObject(i);
                    String cv_prest = c.getString(KEY_ID_MATERIA);
                    String nom_sol = c.getString(KEY_NOMBRE);
                    String fecha = c.getString(KEY_CREDITOS);
                    String area_sol = c.getString(KEY_SEMESTRE);

                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(KEY_ID_MATERIA, cv_prest);
                    map.put(KEY_NOMBRE, nom_sol);
                    map.put(KEY_CREDITOS, fecha);
                    map.put(KEY_SEMESTRE, area_sol);
                    jsonlist.add(map);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return null;

        }

    }


       }
