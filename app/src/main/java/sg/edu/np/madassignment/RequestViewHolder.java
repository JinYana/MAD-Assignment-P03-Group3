package sg.edu.np.madassignment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RequestViewHolder extends RecyclerView.ViewHolder{
    TextView name;
    TextView level;
    ImageButton accept;
    ImageButton ignore;
    ImageView profileppic;
    public RequestViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.reqName);
        level =itemView.findViewById(R.id.friendLevel);
        profileppic = itemView.findViewById(R.id.friendPP);
        accept =itemView.findViewById(R.id.accept);
        ignore = itemView.findViewById(R.id.ignore);
    }
}
