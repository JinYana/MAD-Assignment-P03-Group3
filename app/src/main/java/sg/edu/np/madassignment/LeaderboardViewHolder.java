package sg.edu.np.madassignment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView level;
    TextView position;


    ImageView profileppic;
    public LeaderboardViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.leadername);
        level =itemView.findViewById(R.id.leadlevel);
        profileppic = itemView.findViewById(R.id.leadpic);
        position =itemView.findViewById(R.id.leaderposition);
    }
}
