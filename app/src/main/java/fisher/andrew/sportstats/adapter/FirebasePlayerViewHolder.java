package fisher.andrew.sportstats.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;

public class FirebasePlayerViewHolder extends RecyclerView.ViewHolder{//maybe click listeners
    View mView;
    Context mContext;

    public FirebasePlayerViewHolder(View playerView){
        super(playerView);
        mView = playerView;
        mContext = playerView.getContext();
    }

    public void bindPlayer(Player player){
        TextView nameTextView = (TextView) mView.findViewById(R.id.playerName);
        TextView fieldGoalsTextView= (TextView) mView.findViewById(R.id.playerFG);
        TextView threePointsTextView = (TextView) mView.findViewById(R.id.player3Pts);
        TextView freeThrowsTextView = (TextView) mView.findViewById(R.id.playerFT);
        TextView reboundsTextView = (TextView) mView.findViewById(R.id.playerReb);
        TextView assistsTextView = (TextView) mView.findViewById(R.id.playerAst);
        TextView stealsTextView = (TextView) mView.findViewById(R.id.playerStl);
        TextView blocksTextView = (TextView) mView.findViewById(R.id.playerBLK);
        TextView totalPointsTextView = (TextView) mView.findViewById(R.id.playerPoints);

        nameTextView.setText(player.getName());
        fieldGoalsTextView.setText(player.getFieldGoals());
        threePointsTextView.setText(player.getThreePointers());
        freeThrowsTextView.setText(player.getFreeThrows());
        reboundsTextView.setText(player.getRebounds());
        assistsTextView.setText(player.getAssists());
        stealsTextView.setText(player.getSteals());
        blocksTextView.setText(player.getBlocks());
        totalPointsTextView.setText(player.getTotalPoints());




    }


}
