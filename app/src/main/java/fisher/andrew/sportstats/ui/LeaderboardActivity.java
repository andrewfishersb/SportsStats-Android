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

public class LeaderboardActivity extends FragmentActivity {//usually extends AppCompatActivity
//    @Bind(R.id.leaderBoard) ListView mLeaderBoard;
    FragmentPagerAdapter adapterViewPager;


    private ArrayList<Player> players;
//    //firebase user and uid
//    private FirebaseUser user;
//    private String uid;
//    private DatabaseReference mUserReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        players = (ArrayList<Player>) intent.getSerializableExtra("playerList");


//        players = new ArrayList<>();
        Log.d("did this come",""+ players.get(0).getName());




//
//
//        rank = new ArrayList<>();
//
//        //step one access current user
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        uid = user.getUid();
//////
////
////
////
//        mUserReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS).child(uid);
//
//
//
//        mUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    Player player = snapshot.getValue(Player.class);
//
//                    Log.d("Test",player+"");
//
//
//                    players.add(player);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        //Once I smooth out the array this should work

        ViewPager myPager;
        myPager = (ViewPager) findViewById(R.id.leaderBoardViewPager);
        adapterViewPager = new LeaderPagerAdapter(getSupportFragmentManager(),players);
        myPager.setAdapter(adapterViewPager);




//        Collections.reverse(rank);

//        String[] rankings = rank.toArray(new String[rank.size()]);
//
////                for(String ranking : rank){
////            Log.d("Test",ranking);
////        }
//        ArrayAdapter leaderAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, rankings);
//        mLeaderBoard.setAdapter(leaderAdapter);

//        mUserReference.orderByChild("overallPoints")



        }




//





//        //        mViewPager= (ViewPager) findViewById(R.id.pager);
//        PagerAdapter padapter = new PagerAdapter(getSupportFragmentManager());
//        mViewPager.setAdapter(padapter);





    }

