package fisher.andrew.sportstats.ui;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.adapter.FirebasePlayerViewHolder;
import fisher.andrew.sportstats.model.Player;

public class TrackStatActivity extends AppCompatActivity {
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private DatabaseReference mPlayerReference;

    @Bind(R.id.playerStatRecyclerView) RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_track_stat);

        ButterKnife.bind(this);


        mPlayerReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS);

        setUpFirebaseAdapter();



    }

    private void setUpFirebaseAdapter() {

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Player, FirebasePlayerViewHolder>(Player.class, R.layout.single_player_stat, FirebasePlayerViewHolder.class,mPlayerReference){
            //this was moved in the last lesson so I do not know if it will be moved later?
            @Override
            protected void populateViewHolder(FirebasePlayerViewHolder viewHolder, Player model, int position){
                viewHolder.bindPlayer(model);

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
