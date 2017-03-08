package fisher.andrew.sportstats.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.adapter.PlayerStatPagerAdapter;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.model.Team;

//need to send back to previous page but cant do it through the manifest???
public class PlayerProfileActivity extends AppCompatActivity {

    //may not need if i stop this fragment bizz
    FragmentPagerAdapter adapterViewPager;


    private Player currentPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);

        //retrieves player object
        currentPlayer = Parcels.unwrap(getIntent().getParcelableExtra("player"));

        //creates the view pager and assists in retrieving the fragments
        ViewPager myPager;
        myPager = (ViewPager) findViewById(R.id.playerViewPager);
        adapterViewPager = new PlayerStatPagerAdapter(getSupportFragmentManager(),currentPlayer);
        myPager.setAdapter(adapterViewPager);
//        myPager.setCurrentItem(3);

        TextView mProfileName = (TextView) findViewById(R.id.profileName);
        TextView mProfileHeight = (TextView) findViewById(R.id.profileHeight);
        TextView mProfileAge = (TextView) findViewById(R.id.profileAge);
        final TextView mProfileTeam = (TextView) findViewById(R.id.profileTeam);

        mProfileName.setText(currentPlayer.getName());
        mProfileHeight.setText("Height " +currentPlayer.getHeight()+"");
        mProfileAge.setText("Age: " + Integer.toString(currentPlayer.getAge())+"");

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference teamRef = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_TEAMS).child(uid)
                .child(currentPlayer.getTeamId());

        //what is this line for? - to retrieve the team name
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
