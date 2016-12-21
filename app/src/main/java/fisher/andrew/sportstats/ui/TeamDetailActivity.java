package fisher.andrew.sportstats.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.adapter.FirebasePlayerViewHolder;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.model.Team;


//eventually create menu to add new players
public class TeamDetailActivity extends AppCompatActivity {
    private DatabaseReference mPlayerReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.teamNameDetailTextView) TextView mTeamName;
    @Bind(R.id.allPlayersRecyclerView) RecyclerView mAllPlayerRecyclerView;
    private Team currentTeam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        ButterKnife.bind(this);
        //unwrap player
        currentTeam = Parcels.unwrap(getIntent().getParcelableExtra("team"));
        mTeamName.setText(currentTeam.getName());











        mPlayerReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS);
        setUpFirebaseAdapter();

    }


    private void setUpFirebaseAdapter(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Player, FirebasePlayerViewHolder>(Player.class,R.layout.single_player_recyclerview, FirebasePlayerViewHolder.class,mPlayerReference){

            @Override
            protected void populateViewHolder(FirebasePlayerViewHolder viewHolder,
                                              Player model, int position) {
                viewHolder.bindPlayer(model);
            }
        };
        mAllPlayerRecyclerView.setHasFixedSize(true);
        mAllPlayerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAllPlayerRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }









    //add a new player to the team
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        MenuItem item = menu.findItem(R.id.action_addMenu);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(TeamDetailActivity.this,CreatePlayerActivity.class);
                intent.putExtra("add_to_team", Parcels.wrap(currentTeam));
                startActivity(intent);
                return false;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
