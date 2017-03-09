package fisher.andrew.sportstats.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.ui.LeaderboardFragment;

/**
 * Created by andrewfisher on 3/6/17.
 */

//DEPENDS ON IF WE DO THE SWIPE
public class LeaderPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Player> mPlayers;
    private static int NUM_ITEMS = 16;


    public LeaderPagerAdapter(FragmentManager fm, ArrayList<Player>players) {
        super(fm);
        mPlayers=players;

    }

    public Fragment getItem(int position) {
        Log.d("Got to","The Leader Page Adapter Get Item");

        switch (position) {
            case 0: //Will show players average stats
                return LeaderboardFragment.newInstance(0, mPlayers);
//            case 1: // Will show Players Overall Stats
//                return SinglePlayerStatFragment.newInstance(1, mPlayers);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 1;
    }
}

