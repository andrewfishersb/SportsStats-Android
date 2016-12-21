package fisher.andrew.sportstats.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder>{
    private ArrayList<Player> mPlayers;
    private Context mContext;

    public PlayerAdapter(Context context, ArrayList<Player> players){
        mContext = context;
        mPlayers = players;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View playerView = inflater.inflate(R.layout.single_player_recyclerview,parent,false);
        ViewHolder viewHolder = new ViewHolder(playerView);

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

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //not sure what to do here, they just initialized ids from the layout
        public TextView playerName;
        public TextView  playerAge;
        public TextView playerHeight;

        public ViewHolder(View itemView) {
            super(itemView);
             playerName = (TextView) itemView.findViewById(R.id.recyclerPlayerName);
             playerAge = (TextView) itemView.findViewById(R.id.recyclerPlayerAge);
             playerHeight = (TextView) itemView.findViewById(R.id.recyclerPlayerHeight);
        }
    }
}
