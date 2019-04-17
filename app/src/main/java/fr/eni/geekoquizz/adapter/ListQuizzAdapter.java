package fr.eni.geekoquizz.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.activity.QuizzActivity;
import fr.eni.geekoquizz.bo.Quizz;

public class ListQuizzAdapter extends RecyclerView.Adapter<ListQuizzAdapter.ViewHolder>
{
    //Permet de stocker les données à afficher.
    private String[][] mDataset;

    // Fournit une référence aux vues pour chaque élément de données
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        // Chaque élément contient seulement une TextView
        public ImageView ivPhoto1,ivPhoto2,ivPhoto3,ivTheme1,ivTheme2,ivTheme3,ivTheme4,ivTheme5,ivTheme6;

        public TextView tvTitre, tvNbQuestion, tvType, tvAuteur, tvDate, tvDecription;

        public RatingBar rbDifficult;

        public Button btnPlay, btnStat;

        public ImageButton btnCollapse;

        public ConstraintLayout cvDetailQuizz;

        public ViewHolder(View v)
        {
            super(v);
            ivPhoto1 = v.findViewById(R.id.ivPhoto1);
            ivPhoto2 = v.findViewById(R.id.ivPhoto2);
            ivPhoto3 = v.findViewById(R.id.ivPhoto3);
            ivTheme1 = v.findViewById(R.id.ivTheme);
            ivTheme2 = v.findViewById(R.id.ivTheme1);
            ivTheme3 = v.findViewById(R.id.ivTheme2);
            ivTheme4 = v.findViewById(R.id.ivTheme3);
            ivTheme5 = v.findViewById(R.id.ivTheme4);
            ivTheme6 = v.findViewById(R.id.ivTheme5);
            tvTitre = v.findViewById(R.id.tvTitreQuizz);
            tvAuteur = v.findViewById(R.id.tvAuteur);
            tvDate = v.findViewById(R.id.tvDate);
            tvDecription = v.findViewById(R.id.tvDescription);
            tvNbQuestion = v.findViewById(R.id.tvNbQuestion);
            tvType = v.findViewById(R.id.tvType);
            rbDifficult = v.findViewById(R.id.ratingBar);
            btnPlay = v.findViewById(R.id.btnPlayQuizz);
            btnStat = v.findViewById(R.id.btnStatQuizz);
            btnCollapse = v.findViewById(R.id.btnColapse);
            cvDetailQuizz = v.findViewById(R.id.cvDetail);
        }
    }

    // Constructeur qui attend les données à afficher en paramètre
    public ListQuizzAdapter(String[][] myDataset)
    {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public ListQuizzAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quizz_simple,viewGroup, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListQuizzAdapter.ViewHolder viewHolder, int i) {
        viewHolder.ivPhoto1.setImageResource(R.drawable.quizz1_01);
        viewHolder.ivPhoto2.setImageResource(R.drawable.quizz1_02);
        viewHolder.ivPhoto3.setImageResource(R.drawable.quizz1_03);
        viewHolder.tvTitre.setText(mDataset[i][3]);
        viewHolder.tvNbQuestion.setText(mDataset[i][4]);
        viewHolder.rbDifficult.setRating(Float.valueOf(mDataset[i][5]));
        viewHolder.tvType.setText(mDataset[i][6]);
        viewHolder.ivTheme1.setImageResource(R.drawable.jeux_video);
        viewHolder.ivTheme2.setImageResource(R.drawable.serie_tele);
        viewHolder.ivTheme3.setImageResource(R.drawable.film);
        viewHolder.ivTheme4.setImageResource(R.drawable.anime);
        viewHolder.ivTheme5.setImageResource(R.drawable.pop_culture);
        viewHolder.ivTheme6.setImageResource(R.drawable.livre);
        viewHolder.tvAuteur.setText(mDataset[i][12]);
        viewHolder.tvDate.setText(mDataset[i][13]);
        viewHolder.tvDecription.setText(mDataset[i][14]);
        viewHolder.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuizzActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        viewHolder.btnStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Nan , il est pas encore temps", Toast.LENGTH_LONG).show();
            }
        });

        viewHolder.btnCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.cvDetailQuizz.getVisibility() == View.GONE)
                {
                    viewHolder.cvDetailQuizz.setVisibility(View.VISIBLE);
                    viewHolder.btnCollapse.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                }
                else
                {
                    viewHolder.cvDetailQuizz.setVisibility(View.GONE);
                    viewHolder.btnCollapse.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
