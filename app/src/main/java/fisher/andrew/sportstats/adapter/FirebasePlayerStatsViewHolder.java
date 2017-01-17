package fisher.andrew.sportstats.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;

public class FirebasePlayerStatsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;


    public FirebasePlayerStatsViewHolder(View playerView){
        super(playerView);
        mView = playerView;
        mContext = playerView.getContext();
    }


    public void bindPlayer(Player player){
        TextView nameTextView = (TextView) mView.findViewById(R.id.playerName);
        TextView totalPointsTextView = (TextView) mView.findViewById(R.id.playerPoints);
        TextView  twoPointsTextView = (TextView) mView.findViewById(R.id.player2Pts);
        TextView threePointsTextView= (TextView) mView.findViewById(R.id.player3Pts);
        TextView freeThrowsTextView = (TextView) mView.findViewById(R.id.playerFT);
        TextView reboundsTextView = (TextView) mView.findViewById(R.id.playerReb);
        TextView assistsTextView = (TextView) mView.findViewById(R.id.playerAst);
        TextView stealsTextView = (TextView) mView.findViewById(R.id.playerStl);
        TextView blocksTextView = (TextView) mView.findViewById(R.id.playerBLK);


        nameTextView.setText(player.getName());
        twoPointsTextView.setText("2Pts.\n"+player.getTwoPointers()+"");
        threePointsTextView.setText("3pts.\n"+ player.getThreePointers()+"");
        freeThrowsTextView.setText("FT\n" + player.getFreeThrows()+"");
        reboundsTextView.setText("REB\n" + player.getRebounds()+"");
        assistsTextView.setText("AST\n"+player.getAssists()+"");
        stealsTextView.setText("STL\n" + player.getSteals()+"");
        blocksTextView.setText("BLK\n"+player.getBlocks()+"");
        totalPointsTextView.setText("TOT\n"+ player.getTotalPoints()+"");


        twoPointsTextView.setOnClickListener(this);
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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    players.add(snapshot.getValue(Player.class));
                }
                int playerIndex = getLayoutPosition();
                Player selectedPlayer = players.get(playerIndex);

                //Gets the reference of the player. So I can easily append to depending on the switch statement

                DatabaseReference playerToSelectRef = FirebaseDatabase.getInstance()
                        .getReference(Constants.FIREBASE_CHILD_PLAYERS).child(uid)
                        .child(selectedPlayer.getPushId());

                //check what view was clicked and then update accordingly
                switch(viewId){
                    case R.id.player2Pts:
                        selectedPlayer.addTwoPoints();
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_TWO_POINTERS).setValue(selectedPlayer.getTwoPointers());
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
                    case R.id.playerReb:
                        selectedPlayer.addRebound();
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_REBOUNDS).setValue(selectedPlayer.getRebounds());
                        break;
                    case R.id.playerAst:
                        selectedPlayer.addAssist();
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_ASSISTS).setValue(selectedPlayer.getAssists());
                        break;
                    case R.id.playerStl:
                        selectedPlayer.addSteal();
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_STEALS).setValue(selectedPlayer.getSteals());
                        break;
                    case R.id.playerBLK:
                        selectedPlayer.addBlock();
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_BLOCKS).setValue(selectedPlayer.getBlocks());
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    //will update a player score when called, this way I dont have to keep calling these lines of code in three places in the switch. Before I was calling this at the end of the switch statement but that would write to firebase even if i only updated assists
    private void updateTotalScore(Player player){
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference addTotalPointsRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS).child(uid).child(player.getPushId()).child(Constants.FIREBASE_CHILD_TOTAL_POINTS);
        addTotalPointsRef.setValue(player.getTotalPoints());
    }


}
