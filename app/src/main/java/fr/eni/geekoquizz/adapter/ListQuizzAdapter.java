package fr.eni.geekoquizz.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
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
import fr.eni.geekoquizz.activity.MainActivity;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.activity.InfostatActivity;
import fr.eni.geekoquizz.activity.QuizzActivity;
import fr.eni.geekoquizz.bo.Theme;

public class ListQuizzAdapter extends RecyclerView.Adapter<ListQuizzAdapter.ViewHolder>
{
    //Permet de stocker les données à afficher.
    private List<Quizz> mDataset;

    private static Integer[] TabId = {R.id.ivTheme1,R.id.ivTheme2,R.id.ivTheme3,R.id.ivTheme4,R.id.ivTheme5,R.id.ivTheme6};

    private static List<ImageButton> ListImageButton = new ArrayList<>();

    private static List<List<ImageButton>> ListImageButtonQuizz = new ArrayList<>();

    private static List<List<Theme>> ListTheme = new ArrayList<>();

    // Fournit une référence aux vues pour chaque élément de données
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        // Chaque élément contient seulement une TextView
        public ImageView ivPhoto1,ivPhoto2,ivPhoto3;

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

            for(int a = 0; a < TabId.length;a++){
                ListImageButton.add((ImageButton) v.findViewById(TabId[a]));
            }

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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final int idQuizz =  mDataset.get(i).getId();
        viewHolder.ivPhoto1.setImageResource(R.drawable.quizz1_01);
        viewHolder.ivPhoto2.setImageResource(R.drawable.quizz1_02);
        viewHolder.ivPhoto3.setImageResource(R.drawable.quizz1_03);
        viewHolder.tvTitre.setText(mDataset.get(i).getNom());
        viewHolder.tvNbQuestion.setText(String.valueOf(59/*mDataset.get(i).getQuestions().size()*/));
        viewHolder.rbDifficult.setRating(mDataset.get(i).getDifficulte());
        viewHolder.tvType.setText(mDataset.get(i).getType().getNom());
        viewHolder.tvAuteur.setText(mDataset.get(i).getUtilisateur().getNom());


        if(mDataset.get(i).getThemes().size() != 0){
            List<Theme> MaListeThe = new ArrayList<>();
            List<ImageButton> MaListeImg = new ArrayList<>();
            for(int b = 0; b < mDataset.get(i).getThemes().size();b++){
                for(int a = 0; a < ListImageButton.size();a++) {
                    if(ListTheme.size() < 6){
                        if(ListImageButton.get(a).getTag().toString().equals(mDataset.get(i).getThemes().get(b).getTheme().getIcon())){
                            ListImageButton.get(a).setVisibility(View.VISIBLE);
                            MaListeThe.add(mDataset.get(i).getThemes().get(b).getTheme());
                            MaListeImg.add(ListImageButton.get(a));
                        }
                    }else{
                        break;
                    }
                }
            }
            ListImageButtonQuizz.add(MaListeImg);
            ListTheme.add(MaListeThe);
        }

        String MaDate;
        if(new DateFormat().format("yyyy-MM-dd", new Date()).equals(new DateFormat().format("yyyy-MM-dd", mDataset.get(i).getDateCrea()))){
            MaDate = (String)new DateFormat().format("HH:mm", mDataset.get(i).getDateCrea());
        }else{
            MaDate = (String)new DateFormat().format("dd/MM/yy", mDataset.get(i).getDateCrea());
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

        for(int a = 0; a < ListImageButtonQuizz.get(i).size();a++){
            final int id = i,NumThe = a;
            ListImageButtonQuizz.get(i).get(a).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetDescriptTheme(v,ListImageButtonQuizz.get(id).get(NumThe).getDrawable(),ListTheme.get(id).get(NumThe));
                }
            });
        }
    }

    public void GetDescriptTheme(View view, Drawable Draw, Theme MonTheme){

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle(MonTheme.getNom());
        builder.setIcon(Draw);
        builder.setMessage(MonTheme.getDescription());
        builder.setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void getBtn(int id,View view,int idQuizz){
        switch (id){
            case 1:
                Intent intent = new Intent(view.getContext(), QuizzActivity.class);
                view.getContext().startActivity(intent);
                break;
            case 2:
                Intent intent1 = new Intent(view.getContext(), InfostatActivity.class);
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
