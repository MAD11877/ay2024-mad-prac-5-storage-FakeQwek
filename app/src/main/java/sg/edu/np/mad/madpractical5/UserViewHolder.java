package sg.edu.np.mad.madpractical5;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder{
    ImageView smallImage;
    TextView name;
    TextView description;

    ImageView largeImage;

    public LinearLayout linearLayout;
    public UserViewHolder(View itemView){
        super(itemView);
        smallImage = itemView.findViewById(R.id.smallImageView);
        name = itemView.findViewById(R.id.tvName);
        description = itemView.findViewById(R.id.tvDescription);
        linearLayout = itemView.findViewById(R.id.main);
        largeImage = itemView.findViewById(R.id.largeImageView);


        };
}
