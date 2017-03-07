package fisher.andrew.sportstats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SinglePlayerStatFragment extends Fragment {
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static SinglePlayerStatFragment newInstance(int page, String title) {
        SinglePlayerStatFragment fragmentFirst = new SinglePlayerStatFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
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
//        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        View view = inflater.inflate(R.layout.single_player_stat, container, false);


//All the TextViews
        TextView tvLabel = (TextView) view.findViewById(R.id.playerName);



        //will determine if average or overall
        if(page==0){

            tvLabel.setText("Andrew");

        }else if(page==1){
            TextView tvLabel = (TextView) view.findViewById(R.id.playerName);
            tvLabel.setText(page + " -- Fragment One View 2");
        }

        return view;
    }

}

//