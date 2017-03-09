package fisher.andrew.sportstats.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fisher.andrew.sportstats.SinglePlayerStatFragment;
import fisher.andrew.sportstats.model.Player;


public class PlayerStatPagerAdapter extends FragmentPagerAdapter {
    Player mPlayer;
    private static int NUM_ITEMS = 2;

    public PlayerStatPagerAdapter(FragmentManager fragmentManager, Player player) {
        super(fragmentManager);
        mPlayer=player;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: //Will show players average stats
                return SinglePlayerStatFragment.newInstance(0, mPlayer);
            case 1: // Will show Players Overall Stats
                return SinglePlayerStatFragment.newInstance(1, mPlayer);
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }



}
