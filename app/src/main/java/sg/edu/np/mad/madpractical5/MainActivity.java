package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public User getselectedUser(String name, ArrayList<User> UserList) {
      for (User user : UserList){
          if (user.getName().equals(name)) {
              Log.d("User", user.getName() + "returning");
              return user;

          }
          Log.d("User", user.getName());
      }
      return null;
    }
    private DatabaseHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHandler = new DatabaseHandler(this, null,null,1);

        Intent receivedIntent = getIntent();
        String name = receivedIntent.getStringExtra("Name");
        Log.d("Alert", name + "From list");

        ArrayList<User> userList = ListActivity.userList;
        User selectedUser = getselectedUser(name, userList);



        //Set and apply text views

        int id = selectedUser.getID();
        String desc = selectedUser.getDescription();
        boolean followed = selectedUser.getFollowed();

        TextView tvName = findViewById(R.id.title);
        TextView tvDescription = findViewById(R.id.desc);
        TextView btnFollow = findViewById(R.id.button);
        TextView btnMessage = findViewById(R.id.button2);
        TextView tvTitle = findViewById(R.id.headLine);

        tvName.setText(name);

        tvTitle.setText(String.valueOf(id));
        tvDescription.setText(desc);

        if (followed) {
            btnFollow.setText("Unfollow");
        }
        else {
            btnFollow.setText("Follow");
        }
        btnMessage.setText("Message");

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnFollow.getText() == "Follow") {
                    btnFollow.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                    dbHandler.updateUser(id, true);

                }
                else {
                    btnFollow.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                    dbHandler.updateUser(id, false);
                }
            }
        });
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent usersView = new Intent(MainActivity.this, ListActivity.class);
                startActivity(usersView);
            }
        });
    }
}

