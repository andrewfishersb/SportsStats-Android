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
            header.setText("Average Statistics");
            //eventually programatically will have values here
            twoPoints.setText("2.6");
            threePoints.setText("2.6");
            freeThrows.setText("2.6");
            totalPoints.setText("2.6");
            assists.setText("2.6");
            rebounds.setText("2.6");
            steals.setText("2.6");
            blocks.setText("2.6");
        }else if(page==1){
            header.setText("Career Statistics");
            twoPoints.setText("15");
            threePoints.setText("215");
            freeThrows.setText("15");
            totalPoints.setText("6");
            assists.setText("26");
            rebounds.setText("15");
            steals.setText("15");
            blocks.setText("156");
        }

        return view;
    }

}

//twoPoints.setText("2Pts.\n"+player.getTwoPointers()+"");
//        threePoints.setText("3pts.\n"+ player.getThreePointers()+"");
//        freeThrows.setText("FT\n" + player.getFreeThrows()+"");
//        rebounds.setText("REB\n" + player.getRebounds()+"");
//        assists.setText("AST\n"+player.getAssists()+"");
//        steals.setText("STL\n" + player.getSteals()+"");
//        blocks.setText("BLK\n"+player.getBlocks()+"");
//        totalPoints.setText("TOT\n"+ player.getTotalPoints()+"");
