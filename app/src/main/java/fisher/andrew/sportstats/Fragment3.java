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
public class Fragment3 extends Fragment {


    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static Fragment3 newInstance(int page, String title) {
        Fragment3 fragmentThree = new Fragment3();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentThree.setArguments(args);
        return fragmentThree;
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
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.frag3TextView);
        tvLabel.setText(page + " -- " + title);
        return view;
    }

}
