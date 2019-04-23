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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.infostat_Activity;
import fr.eni.geekoquizz.activity.QuizzActivity;

public class ListQuizzAdapter extends RecyclerView.Adapter<ListQuizzAdapter.ViewHolder>
{
    //Permet de stocker les données à afficher.
    private List<Quizz> mDataset = new ArrayList<Quizz>();

    // Fournit une référence aux vues pour chaque élément de données
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        // Chaque élément contient seulement une TextView
        public ImageView ivPhoto1,ivPhoto2,ivPhoto3,ivTheme1,ivTheme2,ivTheme3,ivTheme4,ivTheme5,ivTheme6;

        public TextView tvTitre, tvNbQuestion, tvType, tvAuteur, tvDate, tvDecription;

        public RatingBar rbDifficult;

        public Button btnPlay,btnInfoMain,btnStat;

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
            tvNbQuestion = v.findViewById(R.id.tvNbQuestionStat);
            tvType = v.findViewById(R.id.tvType);
            rbDifficult = v.findViewById(R.id.ratingBar);
            btnPlay = v.findViewById(R.id.btnPlayQuizz);
            btnStat = v.findViewById(R.id.btnStatQuizz);
            btnCollapse = v.findViewById(R.id.btnColapse);
            cvDetailQuizz = v.findViewById(R.id.cvDetail);
        }
        @Override
        public void onClick(View view) {

        }
    }

    // Constructeur qui attend les données à afficher en paramètre
    public ListQuizzAdapter(List<Quizz> ListQuizz)
    {
        mDataset = ListQuizz;
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
        final int idQuizz =  mDataset.get(i).getId();
        viewHolder.ivPhoto1.setImageResource(R.drawable.quizz1_01);
        viewHolder.ivPhoto2.setImageResource(R.drawable.quizz1_02);
        viewHolder.ivPhoto3.setImageResource(R.drawable.quizz1_03);
        viewHolder.tvTitre.setText(mDataset.get(i).getNom());
        viewHolder.tvNbQuestion.setText(String.valueOf(59/*mDataset.get(i).getQuestions().size()*/));
        viewHolder.rbDifficult.setRating(mDataset.get(i).getDifficulte());
        viewHolder.tvType.setText(mDataset.get(i).getType().getNom());
        viewHolder.ivTheme1.setImageResource(Integer.valueOf(mDataset.get(i).getThemes().get(0).getIcon()));
        viewHolder.ivTheme2.setImageResource(Integer.valueOf(mDataset.get(i).getThemes().get(1).getIcon()));
        viewHolder.ivTheme3.setImageResource(Integer.valueOf(mDataset.get(i).getThemes().get(2).getIcon()));
        viewHolder.ivTheme4.setImageResource(Integer.valueOf(mDataset.get(i).getThemes().get(3).getIcon()));
        viewHolder.ivTheme5.setImageResource(Integer.valueOf(mDataset.get(i).getThemes().get(4).getIcon()));
        viewHolder.ivTheme6.setImageResource(Integer.valueOf(mDataset.get(i).getThemes().get(5).getIcon()));
        viewHolder.tvAuteur.setText(mDataset.get(i).getCreateur().getNom());
        String MaDate;
        if(new android.text.format.DateFormat().format("yyyy-MM-dd", new Date()).equals(new android.text.format.DateFormat().format("yyyy-MM-dd", mDataset.get(i).getDateCrea()))){
            MaDate = (String)new android.text.format.DateFormat().format("HH:mm", mDataset.get(i).getDateCrea());
        }else{
            MaDate = (String)new android.text.format.DateFormat().format("dd/MM/yy", mDataset.get(i).getDateCrea());
        }
        viewHolder.tvDate.setText(MaDate);
        viewHolder.tvDecription.setText(mDataset.get(i).getDescription());

        viewHolder.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBtn(1,v,idQuizz);

            }
        });

        viewHolder.btnStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                getBtn(2,v,idQuizz);
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

    public void getBtn(int id,View view,int idQuizz){
        switch (id){
            case 1:
                Intent intent = new Intent(view.getContext(), QuizzActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 2:
                Intent intent1 = new Intent(view.getContext(), infostat_Activity.class);
                intent1.putExtra("IdQuizz",idQuizz);
                view.getContext().startActivity(intent1);
                break;
        }
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
