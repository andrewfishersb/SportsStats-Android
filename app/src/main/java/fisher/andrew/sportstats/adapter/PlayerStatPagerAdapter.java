package fisher.andrew.sportstats.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fisher.andrew.sportstats.SinglePlayerStatFragment;
import fisher.andrew.sportstats.model.Player;


/**
 * Created by andrewfisher on 3/7/17.
 */

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
            case 0: //Maybe eventually get rid of the Page #1 title bar thing
                return SinglePlayerStatFragment.newInstance(0, mPlayer);
            case 1: // Fragment # 0 - This will show FirstFragment different title
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


//extends PagerAdapter {

//    @Override
//    public int getCount() {
//        return 3;
//    }
//
//
//    public Object instantiateItem (ViewGroup collection, int position)
//    {
//        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        int resId = 0;
//
//        switch (position){
//            case 0:
//                resId = showAverages();
//                break;
//            case 1:
//                resId=showOverall();
//                break;
//            case 2:
//                resId = showSomethingElse();
//                break;
//        }
//
//        View view = inflater.inflate(resId,null);
////        ((ViewPager) collection).addView(view,0);//says redundant
//        collection.addView(view,0);
//        return view;
//    }
//
//
//    public int showAverages(){
//        int resId;
//        resId= R.layout.test;
//        return resId;
//    }
//    public int showOverall(){
//        int resId;
//        resId= R.layout.test2;
//        return resId;
//    }
//    public int showSomethingElse(){
//        int resId;
//        resId= R.layout.test3;
//        return resId;
//    }
//
//
//
//
//
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return false;
//    }
//
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
//    }


}
