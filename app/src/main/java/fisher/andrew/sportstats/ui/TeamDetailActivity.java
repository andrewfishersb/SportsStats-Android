package fisher.andrew.sportstats.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.model.Team;


//eventually create menu to add new players
public class TeamDetailActivity extends AppCompatActivity {
    @Bind(R.id.teamNameDetailTextView) TextView mTeamName;
    Team currentTeam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        ButterKnife.bind(this);
        //unwrap player
        currentTeam = Parcels.unwrap(getIntent().getParcelableExtra("team"));
        mTeamName.setText(currentTeam.getName());


    }

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
