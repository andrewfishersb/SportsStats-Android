package fisher.andrew.sportstats.adapter;

//maybe keep to display all?
//IS THIS NEVER USED?????
public class FirebasePlayerViewHolder{//} extends RecyclerView.ViewHolder implements View.OnClickListener {
//    View mView;
//    Context mContext;
//
//    public FirebasePlayerViewHolder(View itemView){
//        super(itemView);
//        mView = itemView;
//        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);
//
//    }
//
//    public void bindPlayer(Player player){
//            TextView playerName = (TextView) mView.findViewById(R.id.recyclerPlayerName);
//            TextView playerAge = (TextView) mView.findViewById(R.id.recyclerPlayerAge);
//            TextView playerHeight = (TextView) mView.findViewById(R.id.recyclerPlayerHeight);
//
//            playerName.setText(player.getName());
//            playerAge.setText("Age: " + player.getAge());
//            playerHeight.setText("Height: " + player.getHeight());
//
//
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
//        //make an array list
//        final ArrayList<Player> players = new ArrayList<>();
//        // get a reference
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PLAYERS);
//            //attach a listener
//            ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //loop a snapshot to fill arraylist
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    players.add(snapshot.getValue(Player.class));
//                }
//
//                int playerIndex = getLayoutPosition();
//
//                Player clickedPlayer = players.get(playerIndex);
//
//                Intent intent = new Intent(mContext, PlayerProfileActivity.class);
//                intent.putExtra("player", Parcels.wrap(clickedPlayer));
//
//                mContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//    }
}
