package fisher.andrew.sportstats.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.model.Team;

public class LeaderboardFragment extends Fragment {
    private int page;
    private ArrayList<Player> mPlayers;
    private ArrayAdapter listAdapter;
    private Context mContext;
    private String teamName;


    public static LeaderboardFragment newInstance(int page, ArrayList<Player> players) {
        // Required empty public constructor
        LeaderboardFragment fragmentLeaderboard = new LeaderboardFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        fragmentLeaderboard.mPlayers=players;
        fragmentLeaderboard.setArguments(args);
        return fragmentLeaderboard;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page", 0);
        mContext=getContext();
        Log.d("Got to","The Leader Fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.leaderboard, container, false);

        ListView leaderBoard = (ListView) view.findViewById(R.id.leaderBoardListView);
        TextView header = (TextView) view.findViewById(R.id.leaderBoardTitle);


//switch statement
        //sort by the specific value
        //fill a new array with the contents I want for the listview (name\t team(maybe)\stat
        //array adapter
        ArrayList<String>outcome = new ArrayList<>();
        int rank=1;
    switch(page) {
        case 0:
            Collections.sort(mPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p2.getOverallTwoPointers() - p1.getOverallTwoPointers();
                }
            });
            header.setText("Overall Two Pointers");

            for (Player player : mPlayers) {
                outcome.add(rank+ ". "+ player.getName() + "\t\t--" + getPlayersTeam(player) + "\t\t--" + player.getOverallTwoPointers());
                rank++;
            }

            break;
    }


        listAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, outcome);
        leaderBoard.setAdapter(listAdapter);
//eventually will be here
//        listAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, outcome);
//        leaderBoard.setAdapter(listAdapter);

        return view;
    }


    public String getPlayersTeam(Player player){

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference teamRef = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_TEAMS).child(uid)
                .child(player.getTeamId());

        teamRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                teamName = dataSnapshot.getValue(Team.class).getName();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return teamName;
    }


}
//Average 2Pts 1
//Total 2Pts 2
//Average 3pts 3
//Total 3pts 4
//ave ft 5
//total ft 6
//ave ppg 7
//total points 8
//ave assists 9
//total assists 10
//ave rebounds 11
//tot rebounds 12
//ave stl 13
//total stl 14
//ave block 15
//total block 16