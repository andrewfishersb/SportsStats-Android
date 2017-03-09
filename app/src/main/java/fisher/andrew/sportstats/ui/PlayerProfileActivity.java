package fisher.andrew.sportstats.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;

import fisher.andrew.sportstats.Constants;
import fisher.andrew.sportstats.R;
import fisher.andrew.sportstats.adapter.PlayerStatPagerAdapter;
import fisher.andrew.sportstats.model.Player;
import fisher.andrew.sportstats.model.Team;

//need to send back to previous page but cant do it through the manifest???
public class PlayerProfileActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;


    private Player currentPlayer;

//camera
    private static final int REQUEST_IMAGE_CAPTURE = 111;
    private ImageView mProfilePicture;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);


        //retrieves player object
        currentPlayer = Parcels.unwrap(getIntent().getParcelableExtra("player"));

        //creates the view pager and assists in retrieving the fragments
        ViewPager myPager;
        myPager = (ViewPager) findViewById(R.id.playerViewPager);
        adapterViewPager = new PlayerStatPagerAdapter(getSupportFragmentManager(),currentPlayer);
        myPager.setAdapter(adapterViewPager);



        TextView mProfileName = (TextView) findViewById(R.id.profileName);
        TextView mProfileHeight = (TextView) findViewById(R.id.profileHeight);
        TextView mProfileAge = (TextView) findViewById(R.id.profileAge);
        final TextView mProfileTeam = (TextView) findViewById(R.id.profileTeam);

        //image
        mProfilePicture = (ImageView) findViewById(R.id.profilePicture);


//


        mProfileName.setText(currentPlayer.getName());
        mProfileHeight.setText("Height " +currentPlayer.getHeight()+"");
        mProfileAge.setText("Age: " + Integer.toString(currentPlayer.getAge())+"");

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference teamRef = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_TEAMS).child(uid)
                .child(currentPlayer.getTeamId());

        teamRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mProfileTeam.setText(dataSnapshot.getValue(Team.class).getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_photo, menu);
        MenuItem item = menu.findItem(R.id.action_photo);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(PlayerProfileActivity.this, "Take my PEECTURE", Toast.LENGTH_SHORT).show();
                //in their notes it is in the method below but i think herre it will work
                onLaunchCamera();
                return true;
            }

        });
return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mProfilePicture.setImageBitmap(imageBitmap);
            encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }
    public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_PLAYERS)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(currentPlayer.getPushId())
                .child("imageUrl");
        ref.setValue(imageEncoded);

        currentPlayer.setImageURL(imageEncoded);
        Log.d("Image String",currentPlayer.getImageURL());
    }
}
