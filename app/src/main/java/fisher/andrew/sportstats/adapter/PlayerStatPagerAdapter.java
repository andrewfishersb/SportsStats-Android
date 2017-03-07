package fisher.andrew.sportstats.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fisher.andrew.sportstats.R;

/**
 * Created by andrewfisher on 3/7/17.
 */

public class PlayerStatPagerAdapter extends PagerAdapter {

    @Override
    public int getCount() {
        return 3;
    }


    public Object instantiateItem (ViewGroup collection, int position)
    {
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int resId = 0;

        switch (position){
            case 0:
                resId = showAverages();
                break;
            case 1:
                resId=showOverall();
                break;
            case 2:
                resId = showSomethingElse();
                break;
        }

        View view = inflater.inflate(resId,null);
//        ((ViewPager) collection).addView(view,0);//says redundant
        collection.addView(view,0);
        return view;
    }


    public int showAverages(){
        int resId;
        resId= R.layout.single_player_stat;
        return resId;
    }
    public int showOverall(){
        int resId;
        resId= R.layout.single_player_recyclerview;
        return resId;
    }
    public int showSomethingElse(){
        int resId;
        resId= R.layout.create_a_team_dialog;
        return resId;
    }






    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
