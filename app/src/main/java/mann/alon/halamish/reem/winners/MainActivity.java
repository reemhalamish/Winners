package mann.alon.halamish.reem.winners;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScoreManeger manager = ScoreManeger.getManeger();


        Button divine_score = (Button) findViewById(R.id.button);
        Spinner spinnerA, spinnerB;
        spinnerA = (Spinner) findViewById(R.id.spn_group_a);
        spinnerB = (Spinner) findViewById(R.id.spn_group_b);
        ArrayAdapter<String> adapterA, adapterB;
        adapterA = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                manager.all_groups_names
        );
        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerA.setAdapter(adapterA);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
