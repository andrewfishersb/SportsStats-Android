package fisher.andrew.sportstats.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.model.Team;

public class CreatePlayerActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<String> playerHeightOptions = new ArrayList<>();
    @Bind(R.id.heightSpinner) Spinner mPlayerHeightSpinner;
    @Bind(R.id.addAgeEditText) EditText mAddAgeEditText;
    @Bind(R.id.addNameEditText) EditText mAddNameEditText;
    @Bind(R.id.addPlayerButton) Button mAddPlayerButton;

    //assigned team
    Team playersTeam;



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
//        mPlayerHeightSpinner.setSelection(39); <-this may be used to set an initial position value
        mPlayerHeightSpinner.setAdapter(heightAdapter);



        mAddPlayerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        //update to hold current user when this includes authentication
        if(v == mAddPlayerButton){
            String name = mAddNameEditText.getText().toString();
            mAddNameEditText.setText("");
            int age = Integer.parseInt(mAddAgeEditText.getText().toString());
            mAddAgeEditText.setText("");
            String height = mPlayerHeightSpinner.getSelectedItem().toString();

            playersTeam = Parcels.unwrap(getIntent().getParcelableExtra("add_to_team"));

            Player newPlayer = new Player(name,height,age);

            //Data Structure lesson change this area
            DatabaseReference playerReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS);

            DatabaseReference pushRef = playerReference.push();
            String pushId = pushRef.getKey();
            newPlayer.setPushId(pushId);

            //adds a players pushId to an arraylist of players from the Team model
            playersTeam.addPlayer(newPlayer.getPushId());
            //assigns a team id to the player object
            newPlayer.setTeamId(playersTeam.getPushId());

            //adds player to firebase
            pushRef.setValue(newPlayer);

            //over writes same team in firebase this time with an arraylist of players
            DatabaseReference teamPlayerReference = FirebaseDatabase.getInstance()
                    .getReference(Constants.FIREBASE_CHILD_TEAMS)
                    .child(playersTeam.getPushId());
            teamPlayerReference.setValue(playersTeam);
        }

    }


}
