package fisher.andrew.sportstats.ui;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.adapter.FirebasePlayerViewHolder;
import fisher.andrew.sportstats.adapter.PlayerAdapter;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.model.Team;


public class TeamDetailActivity extends AppCompatActivity {
    private DatabaseReference mPlayerReference;
    private PlayerAdapter playerAdapter;
    final ArrayList<Player>filterPlayer = new ArrayList<>();
    @Bind(R.id.teamNameDetailTextView) TextView mTeamName;
    @Bind(R.id.teamPlayersRecyclerView) RecyclerView mRecyclerView;
    private Team currentTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        ButterKnife.bind(this);

        currentTeam = Parcels.unwrap(getIntent().getParcelableExtra("team"));
        mTeamName.setText(currentTeam.getName());

        mPlayerReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS);

        //this block of code will find players on a set team
        mPlayerReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    //gets the team id of the current player and compares
                    if(currentTeam.getPushId().equals(snapshot.getValue(Player.class).getTeamId())){
                        filterPlayer.add(snapshot.getValue(Player.class));
                    }
                }
                playerAdapter = new PlayerAdapter(TeamDetailActivity.this,filterPlayer);
                mRecyclerView.setHasFixedSize(true);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(TeamDetailActivity.this,2);
                mRecyclerView.setLayoutManager(gridLayoutManager);
                mRecyclerView.setAdapter(playerAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    //add a new player to the team with dialog
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        MenuItem item = menu.findItem(R.id.action_addMenu);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                //Builds alert and connects it to the view
                AlertDialog.Builder createPlayerDialogBuilder = new AlertDialog.Builder(TeamDetailActivity.this);
                View createPlayerDialogView = getLayoutInflater().inflate(R.layout.create_a_player_dialog,null);

                //user provided information
                final Spinner mPlayerHeightSpinner = (Spinner) createPlayerDialogView.findViewById(R.id.heightSpinner);
                final EditText mAddAgeEditText = (EditText) createPlayerDialogView.findViewById(R.id.addAgeEditText);
                final EditText mAddNameEditText = (EditText) createPlayerDialogView.findViewById(R.id.addNameEditText);
                Button mAddPlayerButton = (Button) createPlayerDialogView.findViewById(R.id.addPlayerButton);
                ArrayList<String> playerHeightOptions = new ArrayList<>();

                for(int i=60;i<92;i++){
                    int feet = i/12;
                    int inches = i%12;
                    String height = feet + "' "+inches+ "\"";
                    playerHeightOptions.add(height);
                }

                ArrayAdapter<String> heightAdapter = new ArrayAdapter<String>(TeamDetailActivity.this,android.R.layout.simple_spinner_item,playerHeightOptions);
                mPlayerHeightSpinner.setAdapter(heightAdapter);

                //attaches builder to a new Dialog
                createPlayerDialogBuilder.setView(createPlayerDialogView);
                final AlertDialog dialog = createPlayerDialogBuilder.create();

                mAddPlayerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mAddAgeEditText.getText().toString().isEmpty() || mAddNameEditText.getText().toString().isEmpty()){
                            Toast.makeText(TeamDetailActivity.this, "Please Provide All Information", Toast.LENGTH_SHORT).show();
                        }else{
                            String name = mAddNameEditText.getText().toString();
                            mAddNameEditText.setText("");
                            int age = Integer.parseInt(mAddAgeEditText.getText().toString());
                            mAddAgeEditText.setText("");
                            String height = mPlayerHeightSpinner.getSelectedItem().toString();

                            Player newPlayer = new Player(name,height,age);

                            //Data Structure lesson change this area
                            DatabaseReference playerReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS);

                            DatabaseReference pushRef = playerReference.push();
                            String pushId = pushRef.getKey();
                            newPlayer.setPushId(pushId);

                            //adds a players pushId to an arraylist of players from the Team model
                            currentTeam.addPlayer(newPlayer.getPushId());
                            //assigns a team id to the player object
                            newPlayer.setTeamId(currentTeam.getPushId());

                            //adds player to firebase
                            pushRef.setValue(newPlayer);

                            //over writes same team in firebase this time with an arraylist of players
                            DatabaseReference teamPlayerReference = FirebaseDatabase.getInstance()
                                    .getReference(Constants.FIREBASE_CHILD_TEAMS)
                                    .child(currentTeam.getPushId());
                            teamPlayerReference.setValue(currentTeam);


                            //add new player to the array associated and reset the adapter
                            filterPlayer.add(newPlayer);
                            mRecyclerView.setAdapter(playerAdapter);

                            dialog.dismiss();

                        }
                    }
                });
                dialog.show();
                return true;
            }
        });


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

