package fisher.andrew.sportstats.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import fisher.andrew.sportstats.model.Player;

/**
 * Created by andrewfisher on 3/6/17.
 */

//DEPENDS ON IF WE DO THE SWIPE
public class LeaderPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Player> players;


    public LeaderPagerAdapter(FragmentManager fm, ArrayList<Player>players) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
