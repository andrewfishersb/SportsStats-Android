package fisher.andrew.sportstats.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fisher.andrew.sportstats.R;

public class CreateTeamActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_team, menu);
        MenuItem item = menu.findItem(R.id.action_addTeamMenu);

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                YourActivity.this.someFunctionInYourActivity();
                Toast.makeText(CreateTeamActivity.this, "menu item clicked", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CreateTeamActivity.this);
                View mDialogView = getLayoutInflater().inflate(R.layout.create_a_team_dialog, null);
                final EditText mName = (EditText) mDialogView.findViewById(R.id.createTeamNameEditText);
                Button mCreateButton = (Button) mDialogView.findViewById(R.id.createTeamButton);

                mCreateButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        if(mName.getText().toString().isEmpty()){
                            Toast.makeText(CreateTeamActivity.this, "No Name Entered", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //important
                mBuilder.setView(mDialogView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_addTeamMenu:

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }


    }


}
