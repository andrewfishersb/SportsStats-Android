package fisher.andrew.sportstats.ui;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.adapter.FirebaseTeamViewHolder;
import fisher.andrew.sportstats.model.Team;

public class ViewTeamsActivity extends AppCompatActivity{
    private DatabaseReference mTeamReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.allTeamsRecyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        ButterKnife.bind(this);
        mTeamReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TEAMS);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Team, FirebaseTeamViewHolder>(Team.class,R.layout.team_instance,FirebaseTeamViewHolder.class,mTeamReference) {
            @Override
            protected void populateViewHolder(FirebaseTeamViewHolder viewHolder, Team model, int position) {
                viewHolder.bindTeam(model);
            }

        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }






    //dialog box code
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_team, menu);
        MenuItem item = menu.findItem(R.id.action_addTeamMenu);

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(ViewTeamsActivity.this, "menu item clicked", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ViewTeamsActivity.this);
                View mDialogView = getLayoutInflater().inflate(R.layout.create_a_team_dialog, null);
                final EditText mName = (EditText) mDialogView.findViewById(R.id.createTeamNameEditText);
                Button mCreateButton = (Button) mDialogView.findViewById(R.id.createTeamButton);

                //was below
                mBuilder.setView(mDialogView);
                final AlertDialog dialog = mBuilder.create();

                mCreateButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        if(mName.getText().toString().isEmpty()){
                            Toast.makeText(ViewTeamsActivity.this, "No Name Entered", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ViewTeamsActivity.this,"Submit", Toast.LENGTH_SHORT).show();
                            String teamName = mName.getText().toString();
                            Team newTeam = new Team(teamName);

                            DatabaseReference teamRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_TEAMS);
                            DatabaseReference pushRef = teamRef.push();
                            String pushId = pushRef.getKey();
                            newTeam.setPushId(pushId);
                            pushRef.setValue(newTeam);
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


    //maybe a method that will encompass most of the dialog box so i can open up a dialog if no teams exist..or at least a different dialog with similar properties



}
