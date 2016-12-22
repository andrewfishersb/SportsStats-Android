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
import org.w3c.dom.Text;

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
    @Bind(R.id.player2Pts) TextView mTwoPointers;
    @Bind(R.id.playerPoints) TextView mPlayerPointers;
    @Bind(R.id.player3Pts) TextView mThreePointers;
    @Bind(R.id.playerFT) TextView mFreeThrows;
    @Bind(R.id.playerReb) TextView mRebounds;
    @Bind(R.id.playerAst) TextView mAssists;
    @Bind(R.id.playerStl) TextView mSteals;
    @Bind(R.id.playerBLK) TextView mBlocks;


    private Player currentPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        ButterKnife.bind(this);

        currentPlayer = Parcels.unwrap(getIntent().getParcelableExtra("player"));

        mProfileName.setText(currentPlayer.getName());
        mProfileHeight.setText("Height " +currentPlayer.getHeight());
        mProfileAge.setText("Age: " + Integer.toString(currentPlayer.getAge()));
        mTwoPointers.setText("2Pts.\n"+currentPlayer.getTwoPointers()+"");
        mThreePointers.setText("3pts.\n"+ currentPlayer.getThreePointers()+"");
        mFreeThrows.setText("FT\n" + currentPlayer.getFreeThrows()+"");
        mRebounds.setText("REB\n" + currentPlayer.getRebounds()+"");
        mAssists.setText("AST\n"+currentPlayer.getAssists()+"");
        mSteals.setText("STL\n" + currentPlayer.getSteals()+"");
        mBlocks.setText("BLK\n"+currentPlayer.getBlocks()+"");
        mPlayerPointers.setText("TOT\n"+ currentPlayer.getTotalPoints()+"");




        DatabaseReference teamRef = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_TEAMS)
                .child(currentPlayer.getTeamId());


        //what is this line for?
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
