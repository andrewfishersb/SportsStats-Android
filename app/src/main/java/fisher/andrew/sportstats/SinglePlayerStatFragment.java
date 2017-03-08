package fisher.andrew.sportstats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fisher.andrew.sportstats.model.Player;

public class SinglePlayerStatFragment extends Fragment {

    private int page;
    private Player mPlayer;

    // newInstance constructor for creating fragment with arguments

    public static SinglePlayerStatFragment newInstance(int page, Player player) {
        SinglePlayerStatFragment fragmentPlayerStats = new SinglePlayerStatFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        fragmentPlayerStats.mPlayer=player;
        fragmentPlayerStats.setArguments(args);
        return fragmentPlayerStats;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page", 0);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.single_player_stat, container, false);


        //All the TextViews
        TextView header = (TextView) view.findViewById(R.id.playerName);
        TextView twoPoints = (TextView) view.findViewById(R.id.player2Pts);
        TextView threePoints = (TextView) view.findViewById(R.id.player3Pts);
        TextView freeThrows = (TextView) view.findViewById(R.id.playerFT);
        TextView totalPoints = (TextView) view.findViewById(R.id.playerPoints);
        TextView assists = (TextView) view.findViewById(R.id.playerAst);
        TextView rebounds = (TextView) view.findViewById(R.id.playerReb);
        TextView steals = (TextView) view.findViewById(R.id.playerStl);
        TextView blocks = (TextView) view.findViewById(R.id.playerBLK);


        //will determine if average or overall
        //MAYBE USE THE STRING.XML FILLERS
        if(page==0){
            header.setText("Average Statistics -- Games Played: "+mPlayer.getGamesPlayed()+"");
            twoPoints.setText("2Pts.\n"+round(mPlayer.getTwoPointers(),mPlayer.getGamesPlayed())+"");
            threePoints.setText("3pt PG.\n"+ round(mPlayer.getOverallThreePointers(),mPlayer.getGamesPlayed())+"");
            freeThrows.setText("FTPG\n" + round(mPlayer.getOverallFreeThrows(),mPlayer.getGamesPlayed())+"");
            totalPoints.setText("PPG\n"+ round(mPlayer.getOverallPoints(),mPlayer.getGamesPlayed())+"");
            assists.setText("APG\n"+round(mPlayer.getOverallAssists(),mPlayer.getGamesPlayed())+"");
            rebounds.setText("RPG\n" + round(mPlayer.getOverallRebounds(),mPlayer.getGamesPlayed())+"");
            steals.setText("STLPG\n" + round(mPlayer.getOverallSteals(),mPlayer.getGamesPlayed())+"");
            blocks.setText("BLKPG\n"+round(mPlayer.getOverallBlocks(),mPlayer.getGamesPlayed())+"");
        }else if(page==1){
            header.setText("Career Statistics -- Games Played: "+mPlayer.getGamesPlayed()+"");
            twoPoints.setText("2Pts.\n"+mPlayer.getOverallTwoPointers()+"");
            threePoints.setText("3pts.\n"+ mPlayer.getOverallThreePointers()+"");
            freeThrows.setText("FT\n" + mPlayer.getOverallFreeThrows()+"");
            totalPoints.setText("TOT\n"+ mPlayer.getOverallPoints()+"");
            assists.setText("AST\n"+mPlayer.getOverallAssists()+"");
            rebounds.setText("REB\n" + mPlayer.getOverallRebounds()+"");
            steals.setText("STL\n" + mPlayer.getOverallSteals()+"");
            blocks.setText("BLK\n"+mPlayer.getOverallBlocks()+"");
        }

        return view;
    }
    public double round(int topNumber, int bottomNumber){
        return (double) Math.round(topNumber/(float) bottomNumber * 10) /10;
    }
}
