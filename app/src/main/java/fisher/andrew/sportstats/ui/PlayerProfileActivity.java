package fisher.andrew.sportstats.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.model.Team;

//need to send back to previous page but cant do it through the manifest???
public class PlayerProfileActivity extends AppCompatActivity {
    @Bind(R.id.profileName) TextView mProfileName;
    @Bind(R.id.profileAge) TextView mProfileAge;
    @Bind(R.id.profileHeight) TextView mProfileHeight;
    @Bind(R.id.profileTeam) TextView mProfileTeam;

    private Player currentPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        ButterKnife.bind(this);

        currentPlayer = Parcels.unwrap(getIntent().getParcelableExtra("player"));

        mProfileName.setText(currentPlayer.getName());
        mProfileHeight.setText(currentPlayer.getHeight());
        mProfileAge.setText(Integer.toString(currentPlayer.getAge()));

        DatabaseReference teamRef = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_TEAMS)
                .child(currentPlayer.getTeamId());


        //do i have to loop to get an item out of firebase even if its just one item???
        teamRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mProfileTeam.setText(dataSnapshot.getValue(Team.class).getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
