package sg.edu.np.madassignment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    ImageButton cat1;
    ImageButton cat2;
    TextView cat1txt;
    TextView cat2txt;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        cat1 = itemView.findViewById(R.id.cat1);
        cat2 =itemView.findViewById(R.id.cat2);
        cat1txt = itemView.findViewById(R.id.cat1txt);
        cat2txt = itemView.findViewById(R.id.cat2txt);
    }
}
