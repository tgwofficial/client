package theseinitiatives.atma.client.Utils;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import theseinitiatives.atma.client.AllConstants;
import theseinitiatives.atma.client.R;
import theseinitiatives.atma.client.db.DbHelper;
import theseinitiatives.atma.client.db.DbManager;

public class FilterActivity extends Activity {
    private Button button;
    private CheckBox checkbox;
    private Spinner htpSpinner;
    private Spinner dusunSpinner;
    private DbManager dbManager;
    private String iddesa;
    private int mode=0;

    private TextView filterTextView;

    private String filterByDusun =  null;
    private String filterByHPHT = null;
    private boolean filterByRisti = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        setTitle("Menu Filter");

        this.dbManager = new DbManager(getApplicationContext());
        this.iddesa = getIntent().getStringExtra("iddesa");
        this.mode = getIntent().getIntExtra("source",0);

        initComponenets();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }

    private void initComponenets(){
        htpSpinner = (Spinner) findViewById(R.id.filter_by_HTP);
        filterTextView = (TextView) findViewById(R.id.filter2textview);
        if(mode<0){
            htpSpinner.setVisibility(View.INVISIBLE);
            filterTextView.setVisibility(View.INVISIBLE);
            mode = 1;
        }
        filterTextView.setText("Tampilkan hanya "+textView()[mode]);
        htpSpinner.setAdapter(spinnerAdapter(stringPool[mode][0]));
        htpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filterByHPHT = stringPool[mode][1][i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                filterByHPHT = null;
            }
        });

        dusunSpinner = (Spinner) findViewById(R.id.filter_by_dusun);
        dusunSpinner.setAdapter(spinnerAdapter(dusunString()[0]));
        dusunSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filterByDusun = dusunString()[1][i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                filterByDusun = null;
            }
        });

        checkbox = (CheckBox) findViewById(R.id.filter_check);
        checkbox.setVisibility(mode==0 ? View.VISIBLE : View.INVISIBLE);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                filterByRisti = b;
            }
        });
        button = (Button) findViewById(R.id.filter_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filterByHPHT == null)
                    filterByHPHT = "~";
                if(filterByDusun == null)
                    filterByDusun =  "~";
                AllConstants.params = String.format("%s%s%s%s%s",
                        filterByHPHT,
                        AllConstants.FLAG_SEPARATOR,
                        filterByDusun,
                        AllConstants.FLAG_SEPARATOR,
                        filterByRisti?"yes":"no");
                finish();
            }
        });

        Button clear = (Button) findViewById(R.id.filter_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllConstants.params = "####";
                finish();
            }
        });


    }

    private ArrayAdapter<String> spinnerAdapter(String data[]){
        return new ArrayAdapter<>(this.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, data);
    }

    private String [][] htpString(){
        return new String[][]{
                {"---", "januari", "februari", "maret",    "april",    "mei",  "juni", "juli", "agustus",  "september",    "oktober",  "november", "desember"},
                {"~", "-01-",      "-02-",     "-03-",     "-04-",     "-05-", "-06-", "-07-", "-08-",     "-09-",         "-10-",     "-11-",     "-12-"}
        };
    }

    private String[][] vehicleString(){
        return new String[][]{
                {"---","Mobil","Motor","Cidomo","Pick Up","Lainnya"},
                {"~","mobil","motor","cidomo","pickup","lainnya"}
        };
    }

    private String [][] bloodString(){
        return new String[][]{
                {"---","A","B","AB","O"},
                {"~","a -","b -%' AND "+DbHelper.GOL_DARAH + " NOT LIKE 'a%","ab -","o -"}
        };
    }

    private String [] textView(){
        return new String[]{"HTP Bulan:","Kendaraan Berjenis:","Darah Bergolongan:"};
    }

    private String [][][] stringPool = {htpString(),vehicleString(),bloodString()};

    private String [][]dusunString(){
        dbManager.open();
        dbManager.clearClause();
        dbManager.setSelection(DbHelper.PARENT_LOCATION+" = ?");
        dbManager.setSelectionArgs(new String[]{iddesa});
        Cursor c = dbManager.fetchLocationTree();
        dbManager.clearClause();
        c.moveToFirst();
        String temp[][] = new String[2][c.getCount()+1];
        temp[0][0] = "---";temp[1][0]="~";
        for(int i=0;i<c.getCount();i++){
            temp[0][i+1] = temp[1][i+1] = c.getString(c.getColumnIndexOrThrow(DbHelper.LOCATION_NAME));
            c.moveToNext();
        }
        dbManager.close();
        return temp;
    }

}
