package sg.edu.np.madassignment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView level;


    ImageView profileppic;
    public LeaderboardViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.friendName);
        level =itemView.findViewById(R.id.friendLevel);
        profileppic = itemView.findViewById(R.id.friendPP);
    }
}
