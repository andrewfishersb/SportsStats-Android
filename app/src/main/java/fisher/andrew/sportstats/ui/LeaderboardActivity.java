package fisher.andrew.sportstats.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;

public class LeaderboardActivity extends FragmentActivity {//usually extends AppCompatActivity
    @Bind(R.id.leaderBoard) ListView mLeaderBoard;
//    ViewPager mViewPager;


    private ArrayList<String> rank;
    //firebase user and uid
    private FirebaseUser user;
    private String uid;
    private DatabaseReference mUserReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        ButterKnife.bind(this);

rank = new ArrayList<>();

        //step one access current user
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
//



        mUserReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS).child(uid);

        mUserReference.orderByChild("overallPoints").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Log.d("test",snapshot.child("name").getValue().toString() + "\t" + snapshot.child("overallPoints").getValue() + " Points Overall");
                    rank.add(snapshot.child("name").getValue().toString() + "\t" + snapshot.child("overallPoints").getValue() + " Points Overall");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



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

