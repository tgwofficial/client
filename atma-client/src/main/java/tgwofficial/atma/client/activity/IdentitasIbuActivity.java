package tgwofficial.atma.client.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.vijay.jsonwizard.activities.JsonFormActivity;

import cz.msebera.android.httpclient.Header;
import tgwofficial.atma.client.NavigationmenuController;
import tgwofficial.atma.client.R;
import tgwofficial.atma.client.adapter.IdentitasibuCursorAdapter;
import tgwofficial.atma.client.db.DbHelper;
import tgwofficial.atma.client.db.DbManager;

public class IdentitasIbuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DbManager dbManager;
    private DbHelper dbHelper;
    private Context context;
    private static final int    REQUEST_CODE_GET_JSON = 1;
    private static final String DATA_JSON_PATH        = "identitasibu.json";
    private Menu mymenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identitas_ibu_main_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // pushToServer.getResults();

        dbHelper = new DbHelper(this);
        dbManager = new DbManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchIbu();
        Log.d("CURSORS", cursor.toString());
       // cur2Json(cursor);

        // Find ListView to populate
        ListView lvItems = (ListView) findViewById(R.id.list_view);
        // Setup cursor adapter using cursor from last step
        IdentitasibuCursorAdapter todoAdapter = new IdentitasibuCursorAdapter(this, cursor);
        // Attach cursor adapter to the ListView
        lvItems.setAdapter(todoAdapter);


        todoAdapter.changeCursor(cursor);

        dbManager.close();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, JsonFormActivity.class);
                String json = "Your complete JSON";
                intent.putExtra("json", json);
                startActivityForResult(intent, REQUEST_CODE_GET_JSON);

                //Snackbar.make(view, "Untuk Tambah Patient Baru", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
            }
        });

        /*TextView img = (TextView) findViewById(R.id.name);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(IdentitasIbuActivity.this, IdentitasIbuDetailActivity.class);
                startActivity(myIntent);
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        mymenu = menu;
      //  ImageView syncIcon = (ImageView) menu.findItem(R.id.action_refresh).getActionView();
       // syncIcon.setImageResource(R.drawable.baseline_sync_black_18dp);
//        syncIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_refresh));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ImageView iv = (ImageView)inflater.inflate(R.layout.iv_refresh, null);
            Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_refresh);
            rotation.setRepeatCount(Animation.INFINITE);
            iv.startAnimation(rotation);
            item.setActionView(iv);



            new UpdateTask(this).execute();
            refreshView();
            return true;
        }
        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        NavigationmenuController  navi= new NavigationmenuController(this);
        int id = item.getItemId();
       // MenuItem register = R.id.nav_identitas_ibu;
        if (id == R.id.nav_identitas_ibu) {
            Intent myIntent = new Intent(IdentitasIbuActivity.this, IdentitasIbuDetailActivity.class);
            startActivity(myIntent);
        }
        if (id == R.id.nav_transportasi) {
            navi.startTransportasi();
        }

        if (id == R.id.nav_bank_darah) {
            navi.startBankDarah();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void resetUpdating()
    {
        // Get our refresh item from the menu
        MenuItem m = mymenu.findItem(R.id.action_refresh);
        if(m.getActionView()!=null)
        {
            // Remove the animation.
            m.getActionView().clearAnimation();
            m.setActionView(null);
        }
    }

    private void refreshView(){
        ListView list = findViewById(R.id.list_view);
        list.requestLayout();

    }


    public void pull(){

        SyncHttpClient client = new SyncHttpClient();
        client.get(
                "https://atma.theseforall.org/api/pull?location-id=Dusun_test&update-id=0&batch-size=100",
                new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
                     //   dbManager = new DbManager(this);
                        dbManager.open();
                       dbManager.saveTodb(response, statusCode);
                       dbManager.close();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                    }

                });
    }


    class UpdateTask extends AsyncTask<Void, Void, Void> {
       // private IdentitasIbuActivity identitasIbuActivity;
        //  AsyncHttpClient client = new AsyncHttpClient("https://atma.theseforall.org");
        private Context mCon;

        public UpdateTask(Context con)
        {
            mCon = con;
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Set a time to simulate a long update process.
            try {
                Thread.sleep(4000);

            } catch (Exception e) {
                return null;
            }
            pull();
            return null;

        }

        @Override
        protected void onPostExecute(Void param) {
            // Give some feedback on the UI.
            Toast.makeText(mCon, "Sync Finished!",
                    Toast.LENGTH_LONG).show();

            // Change the menu back
            ((IdentitasIbuActivity) mCon).resetUpdating();
            //((IdentitasIbuActivity) mCon).refreshView();
            finish();
            startActivity(getIntent());
        }


    }

}
