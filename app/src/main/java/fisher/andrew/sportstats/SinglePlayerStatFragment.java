package fisher.andrew.sportstats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fisher.andrew.sportstats.model.Player;


/**
 * A simple {@link Fragment} subclass.
 */
public class SinglePlayerStatFragment extends Fragment {

    private String title;
    private int page;
    private Player mPlayer;

    // newInstance constructor for creating fragment with arguments

    public static SinglePlayerStatFragment newInstance(int page, String title,Player player) {
        SinglePlayerStatFragment fragmentPlayerStats = new SinglePlayerStatFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentPlayerStats.mPlayer=player;
        fragmentPlayerStats.setArguments(args);
        return fragmentPlayerStats;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        mPlayer =  getArguments().getParcelable(PLAYER_KEY);
        Log.d("DID IT WORK???",mPlayer.getName());

//        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
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
        //MAYBE REPLACE 2pts WITH GAMES OR FIGURE SOMETHING ELSE OUT TO ADD IN GAMES MAYBE TO THE HEADER????
        if(page==0){
            header.setText("Average Statistics -- Games Played: "+mPlayer.getGamesPlayed()+"");
            //eventually programatically will have values here
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
            twoPoints.setText("2Pts.\n"+mPlayer.getTwoPointers()+"");
            threePoints.setText("3pts.\n"+ mPlayer.getThreePointers()+"");
            freeThrows.setText("FT\n" + mPlayer.getFreeThrows()+"");
            totalPoints.setText("TOT\n"+ mPlayer.getTotalPoints()+"");
            assists.setText("AST\n"+mPlayer.getAssists()+"");
            rebounds.setText("REB\n" + mPlayer.getRebounds()+"");
            steals.setText("STL\n" + mPlayer.getSteals()+"");
            blocks.setText("BLK\n"+mPlayer.getBlocks()+"");
        }

        return view;
    }
    //May MOVE TO THE FRAGMENT IF THATS WHERE THE ROUNDING IS NEEDED
    public double round(int topNumber, int bottomNumber){
//        return  Math.round((topNumber/bottomNumber * 100)*10)/10.0;
        return (double) Math.round(topNumber/(float) bottomNumber * 10) /10;
    }
}
