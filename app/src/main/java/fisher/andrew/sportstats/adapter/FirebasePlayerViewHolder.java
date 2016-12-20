package fisher.andrew.sportstats.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.ui.TrackStatActivity;

public class FirebasePlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

   public TextView fieldGoalsTextView;
    View mView;
    Context mContext;



    public FirebasePlayerViewHolder(View playerView){
        super(playerView);
        mView = playerView;
        mContext = playerView.getContext();
    }

    public void bindPlayer(Player player){

        TextView nameTextView = (TextView) mView.findViewById(R.id.playerName);
         fieldGoalsTextView= (TextView) mView.findViewById(R.id.playerFG);
        TextView threePointsTextView= (TextView) mView.findViewById(R.id.player3Pts);
        TextView freeThrowsTextView = (TextView) mView.findViewById(R.id.playerFT);
        TextView reboundsTextView = (TextView) mView.findViewById(R.id.playerReb);
        TextView assistsTextView = (TextView) mView.findViewById(R.id.playerAst);
        TextView stealsTextView = (TextView) mView.findViewById(R.id.playerStl);
        TextView blocksTextView = (TextView) mView.findViewById(R.id.playerBLK);
        TextView totalPointsTextView = (TextView) mView.findViewById(R.id.playerPoints);

        nameTextView.setText(player.getName());
        fieldGoalsTextView.setText(player.getFieldGoals()+"");
        threePointsTextView.setText(player.getThreePointers()+"");
        freeThrowsTextView.setText(player.getFreeThrows()+"");
        reboundsTextView.setText(player.getRebounds()+"");
        assistsTextView.setText(player.getAssists()+"");
        stealsTextView.setText(player.getSteals()+"");
        blocksTextView.setText(player.getBlocks()+"");
        totalPointsTextView.setText(player.getTotalPoints()+"");



        fieldGoalsTextView.setOnClickListener(this);
    }

    //possibly click on the item but will then send the id, position and the intent
    @Override
    public void onClick(final View view){
        final ArrayList<Player> players = new ArrayList<>();
        final int viewId = view.getId();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYER);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    players.add(snapshot.getValue(Player.class));
                }
                int playerIndex = getLayoutPosition();
                Player selectedPlayer = players.get(playerIndex);



                //WAS USED TO PASS INFORMATION TO ACTIVITY
                // Intent intent = new Intent(mContext, TrackStatActivity.class);
//                intent.putExtra("view_id",viewId); //
//
//////                intent.putExtra("index", playerIndex + "");
//                intent.putExtra("player",Parcels.wrap(selectedPlayer));
////
//
//                //this is to differentiate between the two intents on the next page
//                intent.putExtra("intent_sent_from","FirebasePlayerViewHolder");

                //maybe still need an intent?
//                mContext.startActivity(intent);

                selectedPlayer.addFieldGoal();

                //accesses the fieldGoal of this specific player
                DatabaseReference addStatRef = FirebaseDatabase.getInstance()
                        .getReference(Constants.FIREBASE_CHILD_PLAYER)
                        .child(selectedPlayer.getPushId())
                        .child(Constants.FIREBASE_CHILD_FIELD_GOALS);
                addStatRef.setValue(selectedPlayer.getFieldGoals());
                addStatRef.setValue(selectedPlayer.getTotalPoints());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
