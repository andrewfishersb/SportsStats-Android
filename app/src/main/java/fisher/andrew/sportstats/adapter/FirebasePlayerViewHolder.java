package fisher.andrew.sportstats.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;


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
        TextView playerName = (TextView) mView.findViewById(R.id.recyclerPlayerName);
        TextView playerAge = (TextView) mView.findViewById(R.id.recyclerPlayerAge);
        TextView playerHeight = (TextView) mView.findViewById(R.id.recyclerPlayerHeight);

        playerName.setText(player.getName());
        playerAge.setText("Age: " + player.getAge());
        playerHeight.setText("Height: " + player.getHeight());
        //eventually add a pic?

    }

    @Override
    public void onClick(View view) {
        //eventually link to player detail activity or frag?
    }
}
