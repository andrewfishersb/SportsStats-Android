package fisher.andrew.sportstats.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.adapter.FirebasePlayerStatsViewHolder;
import fisher.andrew.sportstats.model.Player;

//This is the activity where the game happens
public class TrackStatActivity extends AppCompatActivity {
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private DatabaseReference mPlayerReference;

    //will this need to be final
    String team;

    @Bind(R.id.playerStatRecyclerView) RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_track_stat);

        ButterKnife.bind(this);

        //get users players
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//
//
//        mPlayerReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS).child(uid);

        Intent intent = getIntent();
         team = intent.getStringExtra("teamId");

        //didnt have parameter
        setUpFirebaseAdapter(team);



        //when game ends, loop through all players of this team id, then reset stats and add to overall...maybe have methods for these




    }

    //may use player adapter instead to send the correct players and have team detail send here as well so if
    //before didnt have a parameter
    private void setUpFirebaseAdapter(final String teamId) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        Query query = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS)
                .child(uid).orderByChild("teamId").equalTo(teamId);



        //MAYBE IT HAS TO DO WITH THE POSITION
        //mFirebaseAdapter = new FirebaseRecyclerAdapter<Player, FirebasePlayerStatsViewHolder>(Player.class, R.layout.single_player_stat, FirebasePlayerStatsViewHolder.class,mPlayerReference){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Player, FirebasePlayerStatsViewHolder>(Player.class, R.layout.single_player_stat, FirebasePlayerStatsViewHolder.class,query){
            //this was moved in the last lesson so I do not know if it will be moved later?
            @Override
            protected void populateViewHolder(FirebasePlayerStatsViewHolder viewHolder, Player model, int position){

                    viewHolder.bindPlayer(model,team);
            }
        };
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}




//    private void setUpFirebaseAdapter(final String teamId) {
//
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Player, FirebasePlayerStatsViewHolder>(Player.class, R.layout.single_player_stat, FirebasePlayerStatsViewHolder.class,mPlayerReference){
//            //this was moved in the last lesson so I do not know if it will be moved later?
//            @Override
//            protected void populateViewHolder(FirebasePlayerStatsViewHolder viewHolder, Player model, int position){
//                viewHolder.bindPlayer(model);
//            }
//        };
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//    }