package fisher.andrew.sportstats.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;

import butterknife.ButterKnife;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.adapter.LeaderPagerAdapter;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.model.Team;

public class LeaderboardActivity extends FragmentActivity {//usually extends AppCompatActivity
//    @Bind(R.id.leaderBoard) ListView mLeaderBoard;
    FragmentPagerAdapter adapterViewPager;


    private ArrayList<Player> players;
    private ArrayList<Team> teams;
//    //firebase user and uid
//    private FirebaseUser user;
//    private String uid;
//    private DatabaseReference mUserReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        ButterKnife.bind(this);

        if(getIntent().getExtras()!=null) {
            Intent intent = getIntent();

            players= (ArrayList<Player>) intent.getSerializableExtra("playerList");
            teams= (ArrayList<Team>) intent.getSerializableExtra("teamList");


            ViewPager myPager;
            myPager = (ViewPager) findViewById(R.id.leaderBoardViewPager);
            Log.d("EmptyEmpty",players+"");
            adapterViewPager = new LeaderPagerAdapter(getSupportFragmentManager(), players,teams);
            myPager.setAdapter(adapterViewPager);




        }

        }







    }

