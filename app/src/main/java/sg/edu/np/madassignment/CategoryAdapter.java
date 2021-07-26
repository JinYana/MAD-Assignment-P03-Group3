package sg.edu.np.madassignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    ArrayList<Category> data;
    Context context;


    public CategoryAdapter(ArrayList<Category> input) {
        data = input;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;

        item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.categoryrecycler,
                parent,
                false
        );

        context = parent.getContext();


        return new CategoryViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category c = data.get(position);

        holder.cat1txt.setText(c.getCat1());
        holder.cat2txt.setText(c.getCat2());



        holder.cat1.setImageResource(c.cat1img);
        holder.cat2.setImageResource(c.cat2img);

        holder.cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor gameeditor = 	v.getContext().getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putInt("QuestionsAnswered", 0);
                gameeditor.putInt("Score", 0);
                gameeditor.apply();

                SharedPreferences.Editor powerupeditor = 	v.getContext().getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                powerupeditor.putInt("powerup1count", 1);
                powerupeditor.putInt("powerup2count", 1);
                powerupeditor.apply();

                Intent intent = new Intent(v.getContext(), TriviaActivity.class);
                intent.putExtra("gameid", c.cat1no);

                v.getContext().startActivity(intent);
            }
        });

        holder.cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor gameeditor = 	v.getContext().getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putInt("QuestionsAnswered", 0);
                gameeditor.putInt("Score", 0);
                gameeditor.apply();

                SharedPreferences.Editor powerupeditor = 	v.getContext().getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                powerupeditor.putInt("powerup1count", 1);
                powerupeditor.putInt("powerup2count", 1);
                powerupeditor.apply();

                Intent intent = new Intent(v.getContext(), TriviaActivity.class);
                intent.putExtra("gameid", c.cat2no);

                v.getContext().startActivity(intent);
            }
        });






    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
