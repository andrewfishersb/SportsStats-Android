package fisher.andrew.sportstats.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    @Bind(R.id.createPlayerTextViewLinkFromMainActivity) TextView mCreatePlayerLink;
    @Bind(R.id.createTeamTextViewLinkFromMainActivity) TextView mCreateTeamLink;
    @Bind(R.id.leaderBoardTextView) TextView mLeaderBoardTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        mLeaderBoardTextView.setOnClickListener(this);
        mCreateTeamLink.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){

        if(view == mLeaderBoardTextView){
            Intent intent = new Intent(MainActivity.this,LeaderboardActivity.class);
            startActivity(intent);
        }


        //may eventually go in overflow menu
//        if(view == mCreatePlayerLink){
//            Intent intent = new Intent(MainActivity.this,CreatePlayerActivity.class);
//            startActivity(intent);
//        }
        //may eventually go in overflow menu
        if(view ==mCreateTeamLink){
            Intent intent = new Intent(MainActivity.this,ViewTeamsActivity.class);
            startActivity(intent);
        }
        //may just be in view team eventually
//        if(view ==mStartGame){
//            //wont really start a game but for now this will display all players and their stats section
//            Intent intent = new Intent(MainActivity.this,TrackStatActivity.class);
//            startActivity(intent);
//        }
    }



    //Set up the overflow menu and logging out process
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
//have a start game option
//maybe fragments for team during game and stats section, i really dont know