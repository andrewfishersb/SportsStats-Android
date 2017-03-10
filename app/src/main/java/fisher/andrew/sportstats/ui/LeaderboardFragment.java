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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.model.Team;

public class LeaderboardFragment extends Fragment {
    private int page;
    private ArrayList<Player> mPlayers;
    private ArrayList<Team> mTeams;

    private ArrayAdapter listAdapter;
    private Context mContext;
    private String teamName;


    public static LeaderboardFragment newInstance(int page, ArrayList<Player> players, ArrayList<Team> teams) {
        // Required empty public constructor
        LeaderboardFragment fragmentLeaderboard = new LeaderboardFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        fragmentLeaderboard.mPlayers = players;
        fragmentLeaderboard.mTeams = teams;

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
        //Average
        case 0:

            Collections.sort(mPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p2.getOverallTwoPointers() - p1.getOverallTwoPointers();
                }
            });
            header.setText("Overall Two Pointers Leaderboard");

            for (Player player : mPlayers) {
                outcome.add(rank+ ". "+ player.getName() + "\t\t -- " + getTeamName(player,mTeams) + "\t\t -- " + player.getOverallTwoPointers());
                rank++;
            }
            break;
        case 1:

            Collections.sort(mPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p2.getOverallThreePointers() - p1.getOverallThreePointers();
                }
            });
            header.setText("Overall Three Pointers Leaderboard");

            for (Player player : mPlayers) {
                outcome.add(rank+ ". "+ player.getName() + "\t\t -- " + getTeamName(player,mTeams) + "\t\t -- " + player.getOverallThreePointers());
                rank++;
            }
            break;
        case 2:

            Collections.sort(mPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p2.getOverallFreeThrows() - p1.getOverallFreeThrows();
                }
            });
            header.setText("Overall Free Throw Leaderboard");

            for (Player player : mPlayers) {
                outcome.add(rank+ ". "+ player.getName() + "\t\t -- " + getTeamName(player,mTeams) + "\t\t -- " + player.getOverallFreeThrows());
                rank++;
            }
            break;
        case 3:

            Collections.sort(mPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p2.getOverallPoints() - p1.getOverallPoints();
                }
            });
            header.setText("Overall Points Leaderboard");

            for (Player player : mPlayers) {
                outcome.add(rank+ ". "+ player.getName() + "\t\t -- " + getTeamName(player,mTeams) + "\t\t -- " + player.getOverallPoints());
                rank++;
            }
            break;
        case 4:

            Collections.sort(mPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p2.getOverallAssists() - p1.getOverallAssists();
                }
            });
            header.setText("Overall Assists Leaderboard");

            for (Player player : mPlayers) {
                outcome.add(rank+ ". "+ player.getName() + "\t\t -- " + getTeamName(player,mTeams) + "\t\t -- " + player.getOverallAssists());
                rank++;
            }
            break;
        case 5:

            Collections.sort(mPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p2.getOverallRebounds() - p1.getOverallRebounds();
                }
            });
            header.setText("Overall Rebounds Leaderboard");

            for (Player player : mPlayers) {
                outcome.add(rank+ ". "+ player.getName() + "\t\t -- " + getTeamName(player,mTeams) + "\t\t -- " + player.getOverallRebounds());
                rank++;
            }
            break;
        case 6:

            Collections.sort(mPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p2.getOverallBlocks() - p1.getOverallBlocks();
                }
            });
            header.setText("Overall Blocks Leaderboard");

            for (Player player : mPlayers) {
                outcome.add(rank+ ". "+ player.getName() + "\t\t -- " + getTeamName(player,mTeams) + "\t\t -- " + player.getOverallBlocks());
                rank++;
            }
            break;
        case 7:

            Collections.sort(mPlayers, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p2.getOverallSteals() - p1.getOverallSteals();
                }
            });
            header.setText("Overall Steals Leaderboard");

            for (Player player : mPlayers) {
                outcome.add(rank+ ". "+ player.getName() + "\t\t -- " + getTeamName(player,mTeams) + "\t\t -- " + player.getOverallSteals());
                rank++;
            }
            break;

    }


        listAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, outcome);
        leaderBoard.setAdapter(listAdapter);

        return view;
    }


    public String getTeamName(Player player, ArrayList<Team> teams){
        for(int i = 0;i<teams.size();i++){
            if(player.getTeamId().equals(teams.get(i).getPushId())){
                return teams.get(i).getName();
            }
        }
        return "Free Agent";
    }

    public double round(int topNumber, int bottomNumber){
        return (double) Math.round(topNumber/(float) bottomNumber * 10) /10;
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



//Average
//case 0:
//
//        Collections.sort(mPlayers, new Comparator<Player>() {
//@Override
//public int compare(Player p1, Player p2) {
//        return   round(p2.getOverallTwoPointers(),p2.getGamesPlayed()) - (round(p1.getOverallTwoPointers(),p2.getGamesPlayed()));
//        }
//        });
//        header.setText("Average Two Pointer Leaderboard");
//
//        for (Player player : mPlayers) {
//        double average2Points = round(player.getOverallTwoPointers(),player.getGamesPlayed());
//        outcome.add(rank+ ". "+ player.getName() + "\t\t -- " + getTeamName(player,mTeams) + "\t\t -- " + average2Points);
//        rank++;
//        }
//        break;