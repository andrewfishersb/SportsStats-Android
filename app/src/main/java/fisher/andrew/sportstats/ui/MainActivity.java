package fisher.andrew.sportstats.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    @Bind(R.id.createPlayerTextViewLinkFromMainActivity) TextView mCreatePlayerLink;
    @Bind(R.id.createTeamTextViewLinkFromMainActivity) TextView mCreateTeamLink;
    @Bind(R.id.startGameLinkTextViewMainActivity) TextView mStartGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mCreatePlayerLink.setOnClickListener(this);
        mCreateTeamLink.setOnClickListener(this);
        mStartGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
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
        if(view ==mStartGame){
            //wont really start a game but for now this will display all players and their stats section
            Intent intent = new Intent(MainActivity.this,TrackStatActivity.class);
            startActivity(intent);
        }
    }
}
//have a start game option
//maybe fragments for team during game and stats section, i really dont know