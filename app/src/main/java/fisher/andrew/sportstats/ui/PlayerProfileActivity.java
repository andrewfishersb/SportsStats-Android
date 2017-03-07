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

import butterknife.Bind;
import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.adapter.PlayerStatPagerAdapter;
import fisher.andrew.sportstats.model.Player;

//need to send back to previous page but cant do it through the manifest???
public class PlayerProfileActivity extends AppCompatActivity {

    //may not need if i stop this fragment bizz
    FragmentPagerAdapter adapterViewPager;


    @Bind(R.id.profileName) TextView mProfileName;
    @Bind(R.id.profileAge) TextView mProfileAge;
    @Bind(R.id.profileHeight) TextView mProfileHeight;
    @Bind(R.id.profileTeam) TextView mProfileTeam;

    //MAY HAVE TO CHANGE ID NAMES SO I CAN REUSE for the grid layout
    @Bind(R.id.player2Pts) TextView mTwoPointers;
    @Bind(R.id.playerPoints) TextView mPlayerPoints;
    @Bind(R.id.player3Pts) TextView mThreePointers;
    @Bind(R.id.playerFT) TextView mFreeThrows;
    @Bind(R.id.playerReb) TextView mRebounds;
    @Bind(R.id.playerAst) TextView mAssists;
    @Bind(R.id.playerStl) TextView mSteals;
    @Bind(R.id.playerBLK) TextView mBlocks;
    @Bind(R.id.playerName) TextView mStatHeader;

    private Player currentPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
//        ButterKnife.bind(this);


        //testing the stat swipe
        ViewPager myPager;

//        PlayerStatPagerAdapter adapter = new PlayerStatPagerAdapter();
        myPager = (ViewPager) findViewById(R.id.playerViewPager);
        adapterViewPager = new PlayerStatPagerAdapter(getSupportFragmentManager());



        myPager.setAdapter(adapterViewPager);
        myPager.setCurrentItem(3);


        //end of test stat swipe

        currentPlayer = Parcels.unwrap(getIntent().getParcelableExtra("player"));

//        mProfileName.setText(currentPlayer.getName());
//        mProfileHeight.setText("Height " +currentPlayer.getHeight());
//        mProfileAge.setText("Age: " + Integer.toString(currentPlayer.getAge()));

//the brute force option
//        mTwoPointers.setText("Games\n"+currentPlayer.getGamesPlayed()+"");
//        mThreePointers.setText("3pt PG.\n"+ round(currentPlayer.getOverallThreePointers(),currentPlayer.getGamesPlayed())+"");
//        mFreeThrows.setText("FTPG\n" + round(currentPlayer.getOverallFreeThrows(),currentPlayer.getGamesPlayed())+"");
//        mRebounds.setText("RPG\n" + round(currentPlayer.getOverallRebounds(),currentPlayer.getGamesPlayed())+"");
//        mAssists.setText("APG\n"+round(currentPlayer.getOverallAssists(),currentPlayer.getGamesPlayed())+"");
//        mSteals.setText("STLPG\n" + round(currentPlayer.getOverallSteals(),currentPlayer.getGamesPlayed())+"");
//        mBlocks.setText("BLKPG\n"+round(currentPlayer.getOverallBlocks(),currentPlayer.getGamesPlayed())+"");
//        mPlayerPoints.setText("PPG\n"+ round(currentPlayer.getOverallPoints(),currentPlayer.getGamesPlayed())+"");
//        mStatHeader.setText(currentPlayer.getName()+"'s Career Stats");

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();



        DatabaseReference teamRef = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_TEAMS).child(uid)
                .child(currentPlayer.getTeamId());


        //what is this line for?
        teamRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                mProfileTeam.setText(dataSnapshot.getValue(Team.class).getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public double round(int topNumber, int bottomNumber){
//        return  Math.round((topNumber/bottomNumber * 100)*10)/10.0;
        return (double) Math.round(topNumber/(float) bottomNumber * 10) /10;
    }
}
