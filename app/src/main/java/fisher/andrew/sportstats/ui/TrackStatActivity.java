package fisher.andrew.sportstats.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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


//MAYBE RESET THE FIREADAPTER after the page loads

//This is the activity where the game happens
public class TrackStatActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private DatabaseReference mPlayerReference;

    String team ;

    @Bind(R.id.playerStatRecyclerView) RecyclerView mStatRecyclerView;
    @Bind(R.id.finishGameButton) Button mEndGame;

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
//        Toast.makeText(this, team, Toast.LENGTH_SHORT).show();
        //didnt have parameter

        if(!team.isEmpty()){//this seems to work THE MANIFEST BUTTON IS FUCKING UP
            setUpFirebaseAdapter(team); //this works if i force a value
        }






        //when game ends, loop through all players of this team id, then reset stats and add to overall

    mEndGame.setOnClickListener(this);

    }




    //end game
    @Override
    public void onClick(View v){
        Intent intent = new Intent(this,ViewTeamsActivity.class);
        //this resets the value so a new team can be set each time this activity is called
        team=null;
        startActivity(intent);

    }

    //may use player adapter instead to send the correct players and have team detail send here as well so if
    //before didnt have a parameter
    private void setUpFirebaseAdapter(final String teamId) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Toast.makeText(this, "Team id: " + teamId, Toast.LENGTH_SHORT).show();
        Query query = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS)
                .child(uid).orderByChild("teamId").equalTo(teamId);



        //MAYBE IT HAS TO DO WITH THE POSITION
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Player, FirebasePlayerStatsViewHolder>(Player.class, R.layout.single_player_stat, FirebasePlayerStatsViewHolder.class,mPlayerReference){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Player, FirebasePlayerStatsViewHolder>(Player.class, R.layout.single_player_stat, FirebasePlayerStatsViewHolder.class,query){
            //this was moved in the last lesson so I do not know if it will be moved later?
            @Override
            protected void populateViewHolder(FirebasePlayerStatsViewHolder viewHolder, Player model, int position){

                    viewHolder.bindPlayer(model,teamId);
            }
        };
        mStatRecyclerView.setHasFixedSize(true);
        mStatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mStatRecyclerView.setAdapter(mFirebaseAdapter);
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