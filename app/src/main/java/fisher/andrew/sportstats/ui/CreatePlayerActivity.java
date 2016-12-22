package fisher.andrew.sportstats.ui;

import android.content.Intent;
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
import android.widget.Toast;

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




        mAddPlayerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        //update to hold current user when this includes authentication
        if(v == mAddPlayerButton){
            String name = mAddNameEditText.getText().toString();

            if(name.equals("")||mAddAgeEditText.getText().toString().equals("")){
                Toast.makeText(CreatePlayerActivity.this, "Please Fill Out All Fields", Toast.LENGTH_SHORT).show();
            }else{
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

//                //probably wont work as they dont know where i an sending them, need to send back the proper team to display, may first try and use a dialog instead
//                Intent intent = new Intent(CreatePlayerActivity.this,TeamDetailActivity.class);
//                startActivity(intent);
            }


        }

    }


}
