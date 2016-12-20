package fisher.andrew.sportstats.adapter;


import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.ui.TrackStatActivity;

public class FirebasePlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    //should these be public or private
    //all boxes that i can click on
    private TextView fieldGoalsTextView;
    private TextView threePointsTextView;
    private TextView freeThrowsTextView;
    private TextView reboundsTextView;
    private TextView assistsTextView;
    private TextView stealsTextView;
    private TextView blocksTextView;

    View mView;
    Context mContext;



    public FirebasePlayerViewHolder(View playerView){
        super(playerView);
        mView = playerView;
        mContext = playerView.getContext();
    }


    public void bindPlayer(Player player){
        TextView nameTextView = (TextView) mView.findViewById(R.id.playerName);
        TextView totalPointsTextView = (TextView) mView.findViewById(R.id.playerPoints);
        fieldGoalsTextView= (TextView) mView.findViewById(R.id.playerFG);
        threePointsTextView= (TextView) mView.findViewById(R.id.player3Pts);
        freeThrowsTextView = (TextView) mView.findViewById(R.id.playerFT);
        reboundsTextView = (TextView) mView.findViewById(R.id.playerReb);
        assistsTextView = (TextView) mView.findViewById(R.id.playerAst);
        stealsTextView = (TextView) mView.findViewById(R.id.playerStl);
        blocksTextView = (TextView) mView.findViewById(R.id.playerBLK);


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
        threePointsTextView.setOnClickListener(this);
        freeThrowsTextView.setOnClickListener(this);
        reboundsTextView.setOnClickListener(this);
        assistsTextView.setOnClickListener(this);
        stealsTextView.setOnClickListener(this);
        blocksTextView.setOnClickListener(this);


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

                //test later if i need the Textviews being stated up to, i may not since i can just call the id

                //Gets the reference of the player. So I can easily append to depending on the switch statement
                DatabaseReference playerToSelectRef = FirebaseDatabase.getInstance()
                        .getReference(Constants.FIREBASE_CHILD_PLAYER)
                        .child(selectedPlayer.getPushId());

                switch(viewId){
                    case R.id.playerFG:
                        selectedPlayer.addFieldGoal();
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_FIELD_GOALS).setValue(selectedPlayer.getFieldGoals());
                        updateTotalScore(selectedPlayer);
                        break;

                    case R.id.player3Pts:
                        selectedPlayer.addThreePointer();
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_THREE_POINTERS).setValue(selectedPlayer.getThreePointers());
                        updateTotalScore(selectedPlayer);
                        break;
                    case R.id.playerFT:
                        selectedPlayer.addFreeThrow();
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_FREE_THROWS).setValue(selectedPlayer.getFreeThrows());
                        updateTotalScore(selectedPlayer);
                        break;
                }





//R.id.playerPoints
//R.id.playerFG
//R.id.player3Pts
//R.id.playerFT
//R.id.playerReb
//R.id.playerAst
//R.id.playerStl
//R.id.playerBLK












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

//                selectedPlayer.addFieldGoal();

                //maybe do this without the last child and use the switch to determine the last child
//                DatabaseReference addStatRef = FirebaseDatabase.getInstance()
//                        .getReference(Constants.FIREBASE_CHILD_PLAYER)
//                        .child(selectedPlayer.getPushId())
//                        .child(Constants.FIREBASE_CHILD_FIELD_GOALS);
//                addStatRef.setValue(selectedPlayer.getFieldGoals());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    //will update a player score when called, this way I dont have to keep calling these lines of code in three places in the switch. Before I was calling this at the end of the switch statement but that would write to firebase even if i only updated assists
    private void updateTotalScore(Player player){
        DatabaseReference addTotalPointsRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYER).child(player.getPushId()).child(Constants.FIREBASE_CHILD_TOTAL_POINTS);
        addTotalPointsRef.setValue(player.getTotalPoints());
    }

}
