package fisher.andrew.sportstats.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;

import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.ui.PlayerProfileActivity;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    ArrayList<Player> mPlayers = new ArrayList<>();
    private Context mContext;

    public PlayerAdapter(Context context, ArrayList<Player> players){
        mContext = context;
        mPlayers = players;
    }



    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        Context context = parent.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View playerView = inflater.inflate(R.layout.single_player_recyclerview,viewGroup,false); //see if this works?
//        View playerView = LayoutInflater.from(parent.getContext().inflate(R.layout.single_player_recyclerview, parent,false);
        View playerView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_player_recyclerview, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(playerView,viewGroup.getContext());

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlayerAdapter.ViewHolder holder, int position) {
        Player player = mPlayers.get(position);



        TextView bindPlayerName = holder.playerName;
        TextView bindPlayerAge = holder.playerAge;
        TextView bindPlayerHeight = holder.playerHeight;


        bindPlayerName.setText(player.getName());
        bindPlayerAge.setText("Age: " + player.getAge());
        bindPlayerHeight.setText("Height: " + player.getHeight());

    }

    //needed?
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cv; //maybe dont need?
        public TextView playerName;
        public TextView  playerAge;
        public TextView playerHeight;
        private Context mContext;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            //maybe dont need
            cv = (CardView)itemView.findViewById(R.id.cv);

            playerName = (TextView) itemView.findViewById(R.id.recyclerPlayerName);
            playerAge = (TextView) itemView.findViewById(R.id.recyclerPlayerAge);
            playerHeight = (TextView) itemView.findViewById(R.id.recyclerPlayerHeight);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int index = getLayoutPosition();
            Player sendPlayer = mPlayers.get(index);
            Intent intent = new Intent(mContext, PlayerProfileActivity.class);
                intent.putExtra("player", Parcels.wrap(sendPlayer));
                mContext.startActivity(intent);

        }
    }
}




