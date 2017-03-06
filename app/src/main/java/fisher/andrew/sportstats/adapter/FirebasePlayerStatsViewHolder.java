package fisher.andrew.sportstats.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;


// HAVE AN ARRAY OF 2 INDEX'S, CLICK ONE PUTS IN A NUMBER
//CLICK TWO ADDS ANOTHER NUMBER AND THEN SWAPS
//
//


public class FirebasePlayerStatsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    View mView;
    Context mContext;
    String teamId;
    DatabaseReference ref;
    DatabaseReference playerToSelectRef;

//    int [] switchArray = new int[2];
    int subOutPlayer;


    public FirebasePlayerStatsViewHolder(View playerView){
        super(playerView);
        mView = playerView;
        mContext = playerView.getContext();
        subOutPlayer=-1;
    }


    public void bindPlayer(Player player, String teamId, int position){

        //This block here will hide certain players after 5
        ViewGroup.LayoutParams test = mView.getLayoutParams();
        if(position>4){
            mView.setVisibility(View.INVISIBLE);
            test.height=0;
            test.width=0;
            mView.setLayoutParams(test);
        }





            //sent the teamId to the bind in order to check for team id when clicked
            this.teamId=teamId;

            TextView nameTextView = (TextView) mView.findViewById(R.id.playerName);
            TextView totalPointsTextView = (TextView) mView.findViewById(R.id.playerPoints);
            TextView  twoPointsTextView = (TextView) mView.findViewById(R.id.player2Pts);
            TextView threePointsTextView= (TextView) mView.findViewById(R.id.player3Pts);
            TextView freeThrowsTextView = (TextView) mView.findViewById(R.id.playerFT);
            TextView reboundsTextView = (TextView) mView.findViewById(R.id.playerReb);
            TextView assistsTextView = (TextView) mView.findViewById(R.id.playerAst);
            TextView stealsTextView = (TextView) mView.findViewById(R.id.playerStl);
            TextView blocksTextView = (TextView) mView.findViewById(R.id.playerBLK);

            //might not need
            ImageView subsitution = (ImageView) mView.findViewById(R.id.subsituteButton);


            nameTextView.setText(player.getName());
            twoPointsTextView.setText("2Pts.\n"+player.getTwoPointers()+"");
            threePointsTextView.setText("3pts.\n"+ player.getThreePointers()+"");
            freeThrowsTextView.setText("FT\n" + player.getFreeThrows()+"");
            reboundsTextView.setText("REB\n" + player.getRebounds()+"");
            assistsTextView.setText("AST\n"+player.getAssists()+"");
            stealsTextView.setText("STL\n" + player.getSteals()+"");
            blocksTextView.setText("BLK\n"+player.getBlocks()+"");
            totalPointsTextView.setText("TOT\n"+ player.getTotalPoints()+"");



        //Could i be weird and loop

//Click to add
            twoPointsTextView.setOnClickListener(this);
            threePointsTextView.setOnClickListener(this);
            freeThrowsTextView.setOnClickListener(this);
            reboundsTextView.setOnClickListener(this);
            assistsTextView.setOnClickListener(this);
            stealsTextView.setOnClickListener(this);
            blocksTextView.setOnClickListener(this);


            twoPointsTextView.setOnLongClickListener(this);
            threePointsTextView.setOnLongClickListener(this);
            freeThrowsTextView.setOnLongClickListener(this);
            reboundsTextView.setOnLongClickListener(this);
            assistsTextView.setOnLongClickListener(this);
            stealsTextView.setOnLongClickListener(this);
            blocksTextView.setOnLongClickListener(this);

        //might not need
        subsitution.setOnClickListener(this);


    }


    //THIS WAS THE CODE I HAD WHEN I WOULD CLICK ON USER TO ADD STAT
    //THIS MAY BE WHERE WRONG INFO IS BEING PASSED
            //EITHER QUERY OR SOMETHING TO DO WITH THE players.get(playerIndex)
    //possibly click on the item but will then send the id, position and the intent
    @Override
    public void onClick(final View view){
        final ArrayList<Player> players = new ArrayList<>();
        final int viewId = view.getId();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();

        ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS).child(uid);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //checks the player belongs to the current team
                    if(snapshot.getValue(Player.class).getTeamId().equals(teamId)){
                        players.add(snapshot.getValue(Player.class));
                    }


                }
                int playerIndex = getLayoutPosition();

                if(playerIndex >= 0 ){
                    Player selectedPlayer = players.get(playerIndex);

                    //Gets the reference of the player. So I can easily append to depending on the switch statement

                    playerToSelectRef = FirebaseDatabase.getInstance()
                            .getReference(Constants.FIREBASE_CHILD_PLAYERS).child(uid)
                            .child(selectedPlayer.getPushId());

                    //check what view was clicked and then update accordingly
                    switch(viewId){
                        case R.id.player2Pts:
                            selectedPlayer.setTwoPoints(1);
                            playerToSelectRef.child(Constants.FIREBASE_CHILD_TWO_POINTERS).setValue(selectedPlayer.getTwoPointers());
                            updateTotalScore(selectedPlayer);
                            break;
                        case R.id.player3Pts:
                            selectedPlayer.setThreePointer(1);
                            playerToSelectRef.child(Constants.FIREBASE_CHILD_THREE_POINTERS).setValue(selectedPlayer.getThreePointers());
                            updateTotalScore(selectedPlayer);
                            break;
                        case R.id.playerFT:
                            selectedPlayer.setFreeThrow(1);
                            playerToSelectRef.child(Constants.FIREBASE_CHILD_FREE_THROWS).setValue(selectedPlayer.getFreeThrows());
                            updateTotalScore(selectedPlayer);
                            break;
                        case R.id.playerReb:
                            selectedPlayer.setRebound(1);
                            playerToSelectRef.child(Constants.FIREBASE_CHILD_REBOUNDS).setValue(selectedPlayer.getRebounds());
                            break;
                        case R.id.playerAst:
                            selectedPlayer.setAssist(1);
                            playerToSelectRef.child(Constants.FIREBASE_CHILD_ASSISTS).setValue(selectedPlayer.getAssists());
                            break;
                        case R.id.playerStl:
                            selectedPlayer.setSteals(1);
                            playerToSelectRef.child(Constants.FIREBASE_CHILD_STEALS).setValue(selectedPlayer.getSteals());
                            break;
                        case R.id.playerBLK:
                            selectedPlayer.setBlock(1);
                            playerToSelectRef.child(Constants.FIREBASE_CHILD_BLOCKS).setValue(selectedPlayer.getBlocks());
                            break;
                        case R.id.subsituteButton:

                            if(subOutPlayer==-1){
                                subOutPlayer = playerIndex;
                                Toast.makeText(mContext, "initial click", Toast.LENGTH_SHORT).show();
                            }else{
                                Collections.swap(players,subOutPlayer,playerIndex);
                                Toast.makeText(mContext, players.get(1).getName(), Toast.LENGTH_SHORT).show();
                                subOutPlayer=-1;
                            }
                            break;
                    }
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


    @Override
    public boolean onLongClick(View view) {
        final ArrayList<Player> players = new ArrayList<>();
        final int viewId = view.getId();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();

         ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS).child(uid);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //checks the player belongs to the current team
                    if(snapshot.getValue(Player.class).getTeamId().equals(teamId)){
                        players.add(snapshot.getValue(Player.class));
                    }


                }
                int playerIndex = getLayoutPosition();
                Player selectedPlayer = players.get(playerIndex);

                //Gets the reference of the player. So I can easily append to depending on the switch statement

                playerToSelectRef = FirebaseDatabase.getInstance()
                        .getReference(Constants.FIREBASE_CHILD_PLAYERS).child(uid)
                        .child(selectedPlayer.getPushId());

                //check what view was clicked and then update accordingly
                switch(viewId){
                    case R.id.player2Pts:
                        selectedPlayer.setTwoPoints(-1);
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_TWO_POINTERS).setValue(selectedPlayer.getTwoPointers());
                        updateTotalScore(selectedPlayer);
                        break;
                    case R.id.player3Pts:
                        selectedPlayer.setThreePointer(-1);
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_THREE_POINTERS).setValue(selectedPlayer.getThreePointers());
                        updateTotalScore(selectedPlayer);
                        break;
                    case R.id.playerFT:
                        selectedPlayer.setFreeThrow(-1);
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_FREE_THROWS).setValue(selectedPlayer.getFreeThrows());
                        updateTotalScore(selectedPlayer);
                        break;
                    case R.id.playerReb:
                        selectedPlayer.setRebound(-1);
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_REBOUNDS).setValue(selectedPlayer.getRebounds());
                        break;
                    case R.id.playerAst:
                        selectedPlayer.setAssist(-1);
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_ASSISTS).setValue(selectedPlayer.getAssists());
                        break;
                    case R.id.playerStl:
                        selectedPlayer.setSteals(-1);
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_STEALS).setValue(selectedPlayer.getSteals());
                        break;
                    case R.id.playerBLK:
                        selectedPlayer.setBlock(-1);
                        playerToSelectRef.child(Constants.FIREBASE_CHILD_BLOCKS).setValue(selectedPlayer.getBlocks());
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });        return true;
    }
}
