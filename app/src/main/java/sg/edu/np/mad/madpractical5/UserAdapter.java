package sg.edu.np.mad.madpractical5;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    private ArrayList<User> list_objects;
    private UserListener listener;
    public UserAdapter(ArrayList<User> list_objects, UserListener listener) {
        this.list_objects = list_objects;
        this.listener = listener;
    }
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(UserViewHolder holder, int position) {
        User list_item = list_objects.get(position);
        holder.name.setText(list_item.getName());
        holder.description.setText(list_item.getDescription());

        if (list_item.getID() % 10 == 7) {

           Log.d("Prompt1","Ends with seven");
        }
        else {
            holder.largeImage.setVisibility(View.GONE);
            Log.d("Prompt","Does not end with 7");
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClicked(list_item);
            }
        });
    }
    public int getItemCount() {return list_objects.size();}
}
