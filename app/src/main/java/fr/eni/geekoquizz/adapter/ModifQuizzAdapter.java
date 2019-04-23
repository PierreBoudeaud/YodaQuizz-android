package fr.eni.geekoquizz.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
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

import java.util.List;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.bo.Quizz;

public class ModifQuizzAdapter extends RecyclerView.Adapter<ModifQuizzAdapter.ViewHolder>
{
    //Permet de stocker les données à afficher.
    private List<Quizz> mDataset;

    // Fournit une référence aux vues pour chaque élément de données
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        // Chaque élément contient seulement une TextView
        public ImageView ivPhoto1,ivPhoto2,ivPhoto3;

        public TextView tvTitre, tvNbQuestion;

        public RatingBar rbDifficult;

        public Button btnModif;

        public ImageButton btnDelete;

        public ViewHolder(View v)
        {
            super(v);
            ivPhoto1 = v.findViewById(R.id.ivPhoto1);
            ivPhoto2 = v.findViewById(R.id.ivPhoto2);
            ivPhoto3 = v.findViewById(R.id.ivPhoto3);
            tvTitre = v.findViewById(R.id.tvTitreQuizz);
            tvNbQuestion = v.findViewById(R.id.tvNbQuestionStat);
            rbDifficult = v.findViewById(R.id.ratingBar);
            btnModif = v.findViewById(R.id.btnActionQuizz);
            btnDelete = v.findViewById(R.id.btnRemoveQuizz);
        }
    }

    // Constructeur qui attend les données à afficher en paramètre
    public ModifQuizzAdapter(List<Quizz> myDataset)
    {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public ModifQuizzAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quizz_modif,viewGroup, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ModifQuizzAdapter.ViewHolder viewHolder, int i) {
        viewHolder.ivPhoto1.setImageResource(R.drawable.quizz1_01);
        viewHolder.ivPhoto2.setImageResource(R.drawable.quizz1_02);
        viewHolder.ivPhoto3.setImageResource(R.drawable.quizz1_03);
        viewHolder.tvTitre.setText(mDataset.get(i).getNom());
        viewHolder.tvNbQuestion.setText(String.valueOf(59/*mDataset.get(i).getQuestions().size()*/));
        viewHolder.rbDifficult.setRating(Float.valueOf(mDataset.get(i).getDifficulte()));
        viewHolder.btnModif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Flemme ^^", Toast.LENGTH_LONG).show();
            }
        });
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext())
                        .setCancelable(true)
                        .setTitle("Supression du Quizz ?")
                        .setMessage("Étes-vous sûr de suprrimer ce quizz ?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(v.getContext(),"Tu y a crus hein ;)",Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(v.getContext(),"J'avais la flemme de toute façon",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
