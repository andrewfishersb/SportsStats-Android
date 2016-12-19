package fisher.andrew.sportstats.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.R;

public class CreatePlayerActivity extends AppCompatActivity {
    private ArrayList<String> playerHeightOptions = new ArrayList<>();
    @Bind(R.id.heightSpinner) Spinner mPlayerHeightSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        ButterKnife.bind(this);

        //will load an array and format heights from 36 inches to 91 inches in format 3'0" to 7'7"
        for(int i=36;i<92;i++){
            int feet = i/12;
            int inches = i%12;
            String height = feet + "' "+inches+ "\"";
            playerHeightOptions.add(height);
        }

        //attaches the heights to a spinner
        ArrayAdapter<String> heightAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,playerHeightOptions);


        mPlayerHeightSpinner.setSelection(39);
        mPlayerHeightSpinner.setAdapter(heightAdapter);




    }


}
//min 36 inches
//max 91 inches

//maybe its own method?