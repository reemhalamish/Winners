package mann.alon.halamish.reem.winners;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static int indA=0, indB=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ScoreManeger manager = ScoreManeger.getManeger();


        Button divine_score = (Button) findViewById(R.id.button);
        final TextView tvScoreA, tvScoreB, tvSEM_A, tvSEM_B;
        final Spinner spinnerA, spinnerB;
        spinnerA = (Spinner) findViewById(R.id.spn_group_a);
        spinnerB = (Spinner) findViewById(R.id.spn_group_b);
        final ArrayAdapter<String> adapterA, adapterB;
        AdapterView.OnItemSelectedListener listenerA, listenerB;

        tvScoreA= (TextView) findViewById(R.id.tv_score_a);
        tvScoreB= (TextView) findViewById(R.id.tv_score_b);
        tvSEM_A= (TextView) findViewById(R.id.tv_sem_a);
        tvSEM_B= (TextView) findViewById(R.id.tv_sem_b);







        adapterA = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                manager.all_groups_names
        );
        adapterA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerA.setAdapter(adapterA);


        adapterB = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                manager.all_groups_names
        );
        adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerB.setAdapter(adapterB);

        listenerA=new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indA=position;
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        };

        listenerB=new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indB=position;
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        };

        spinnerA.setOnItemSelectedListener(listenerA);
        spinnerB.setOnItemSelectedListener(listenerB);

        divine_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                1. get the info into Games variables
                2. show the info to the screen using the text views;
                 */
                String groupA,groupB;
                groupA=adapterA.getItem(indA);
                groupB=adapterB.getItem(indB);
                if (groupA.equals(groupB)) {
                    Toast.makeText(App.app,"האלו!? מה, משחקים נגד עצמינו עכשיו??",Toast.LENGTH_SHORT).show();
                    return;
                }
                Games relevant;
                relevant= manager.getRelevantGames(groupA,groupB,false);
                if (relevant==null) {
                    Toast.makeText(App.app,"גבר, המשחק לא קרה. תחשוב לבד מה אנחנו טוטו?",Toast.LENGTH_SHORT).show();
                    return;
                }
                tvScoreA.setText(relevant.meanA+"");
                tvScoreB.setText(relevant.meanB+"");
                tvSEM_A.setText("+-"+relevant.SEM_A);
                tvSEM_B.setText("+-"+relevant.SEM_B);

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
