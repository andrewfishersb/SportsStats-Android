package fisher.andrew.sportstats.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import fisher.andrew.sportstats.model.Player;

/**
 * Created by Guest on 12/20/16.
 */
public class FirebasePlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebasePlayerViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPlayer(Player player){

    }

    @Override
    public void onClick(View view) {

    }
}
