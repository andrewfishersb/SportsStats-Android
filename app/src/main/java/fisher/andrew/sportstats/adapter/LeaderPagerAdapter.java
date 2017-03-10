package fisher.andrew.sportstats.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.model.Team;
import fisher.andrew.sportstats.ui.LeaderboardFragment;

/**
 * Created by andrewfisher on 3/6/17.
 */

//DEPENDS ON IF WE DO THE SWIPE
public class LeaderPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Player> mPlayers;
    private ArrayList<Team> mTeams;

    private static int NUM_ITEMS = 16;


    public LeaderPagerAdapter(FragmentManager fm, ArrayList<Player>players, ArrayList<Team> teams) {
        super(fm);
        mPlayers=players;
        mTeams= teams;

    }

    public Fragment getItem(int position) {


        //Will determine the type of stats the user is shown
        switch (position) {
            case 0:
                return LeaderboardFragment.newInstance(0, mPlayers,mTeams);
            case 1:
                return LeaderboardFragment.newInstance(1, mPlayers,mTeams);
            case 2:
                return LeaderboardFragment.newInstance(2, mPlayers,mTeams);
            case 3:
                return LeaderboardFragment.newInstance(3, mPlayers,mTeams);
            case 4:
                return LeaderboardFragment.newInstance(4, mPlayers,mTeams);
            case 5:
                return LeaderboardFragment.newInstance(5, mPlayers,mTeams);
            case 6:
                return LeaderboardFragment.newInstance(6, mPlayers,mTeams);
            case 7:
                return LeaderboardFragment.newInstance(7, mPlayers,mTeams);
            case 8:
                return LeaderboardFragment.newInstance(8, mPlayers,mTeams);
            case 9:
                return LeaderboardFragment.newInstance(9, mPlayers,mTeams);
            case 10:
                return LeaderboardFragment.newInstance(10, mPlayers,mTeams);
            case 11:
                return LeaderboardFragment.newInstance(11, mPlayers,mTeams);
            case 12:
                return LeaderboardFragment.newInstance(12, mPlayers,mTeams);
            case 13:
                return LeaderboardFragment.newInstance(13, mPlayers,mTeams);
            case 14:
                return LeaderboardFragment.newInstance(14, mPlayers,mTeams);
            case 15:
                return LeaderboardFragment.newInstance(15, mPlayers,mTeams);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 8;
    }
}

//Average 2Pts 0
//Total 2Pts 1
//Average 3pts 2
//Total 3pts 3
//ave ft 4
//total ft 5
//ave ppg 6
//total points 7
//ave assists 8
//total assists 10
//ave rebounds 11
//tot rebounds 12
//ave stl 13
//total stl 14
//ave block 15
//total block 16