package fisher.andrew.sportstats.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.createPlayerTextViewLinkFromMainActivity) TextView mCreatePlayerLink;
    @Bind(R.id.createTeamTextViewLinkFromMainActivity) TextView mCreateTeamLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCreatePlayerLink.setOnClickListener(this);
        mCreateTeamLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        if(view == mCreatePlayerLink){
            Intent intent = new Intent(MainActivity.this,CreatePlayerActivity.class);
            startActivity(intent);
        }
        if(view ==mCreateTeamLink){
            Intent intent = new Intent(MainActivity.this,CreateTeamActivity.class);
            startActivity(intent);
        }
    }
}
