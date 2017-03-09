package fisher.andrew.sportstats.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.ui.PlayerProfileActivity;



public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    ArrayList<Player> mPlayers = new ArrayList<>();
    private Context mContext;

    //firebase user and uid
    private FirebaseUser user;
    private String uid;
    private DatabaseReference mPlayerReference;

//INSTEAD SHOULD THIS TAKE IN AN ARRAY OF PLAYERIDs
    public PlayerAdapter(Context context, ArrayList<Player> players){
        mContext = context;

        //then read in the ids and pull out the player objects (like they did on the previous page
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
        //image
        ImageView bindPlayerImage = holder.playerImage;

        //end image
        bindPlayerName.setText(player.getName());
        bindPlayerAge.setText("Age: " + player.getAge());
        bindPlayerHeight.setText("Height: " + player.getHeight());

        //use reference to pull picture out
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        mPlayerReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS).child(uid).child(player.getPushId());
        //

        //bind image
        Log.d("Image",player.getImageURL()+"");
        if(player.getImageURL()!=null){
            try {
                Bitmap imageBitmap = decodeFromFirebaseBase64(player.getImageURL());
                bindPlayerImage.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //end bind image


        //SET IMAGE HERE IF NULL OR SOME DEFAULT VALUE THEN DONT SET IT OR SET IT TO A SPECIFIC CANT FIND IMAGE
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

    //only if successfully add an image
    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }
    //end image


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cv; //maybe dont need?
        public TextView playerName;
        public TextView  playerAge;
        public TextView playerHeight;

        //image
        public ImageView playerImage;
//end image

        private Context mContext;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            //maybe dont need
            cv = (CardView)itemView.findViewById(R.id.cv);

            playerName = (TextView) itemView.findViewById(R.id.recyclerPlayerName);
            playerAge = (TextView) itemView.findViewById(R.id.recyclerPlayerAge);
            playerHeight = (TextView) itemView.findViewById(R.id.recyclerPlayerHeight);


            //image
            playerImage = (ImageView) itemView.findViewById(R.id.playerRecyclerPicture);
            //image

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




