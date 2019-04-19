package fr.eni.geekoquizz.adapter;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.bo.Statistique;

public class ListStatAdapter extends RecyclerView.Adapter<ListStatAdapter.ViewHolder>
{
    //Permet de stocker les données à afficher.
    private List<Statistique> mDataset;

    private int NbTotQuestion = 0;

    // Fournit une référence aux vues pour chaque élément de données
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        // Chaque élément contient seulement une TextView
        public TextView tvPseudo,tvDate,tvGood,tvPercent,tvNbQuestion;

        public ConstraintLayout cvStatQuizz;


        public ViewHolder(View v)
        {
            super(v);
            //MainActivity
            tvPercent = v.findViewById(R.id.tvPercent);
            tvPseudo = v.findViewById(R.id.tvPseudo);
            tvDate = v.findViewById(R.id.tvDate);
            tvNbQuestion = v.findViewById(R.id.tvNbQuestionStat);
            tvGood = v.findViewById(R.id.tvGood);
            cvStatQuizz = v.findViewById(R.id.cvStatQuizz);
        }

        @Override
        public void onClick(View view) {

        }
    }

    // Constructeur qui attend les données à afficher en paramètre
    public ListStatAdapter(List<Statistique> myDataset,int NbQuestions)
    {
        NbTotQuestion = NbQuestions;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public ListStatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detailsstatquizz,viewGroup, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListStatAdapter.ViewHolder viewHolder, int i) {
        //MainActivity
        int NbGoodQuestion = mDataset.get(i).getNbCorrect();
        if(NbTotQuestion >= NbGoodQuestion){
            int Percent = (NbGoodQuestion*100/NbTotQuestion);
            viewHolder.tvPercent.setText(String.valueOf(Percent));
        }else{
            viewHolder.tvPercent.setText(String.valueOf(0));
        }
        String MaDate;
        if(new android.text.format.DateFormat().format("yyyy-MM-dd", new Date()).equals(new android.text.format.DateFormat().format("yyyy-MM-dd", mDataset.get(i).getDate()))){
            MaDate = (String)new android.text.format.DateFormat().format("HH:mm", mDataset.get(i).getDate());
        }else{
            MaDate = (String)new android.text.format.DateFormat().format("dd/MM/yy", mDataset.get(i).getDate());
        }
        viewHolder.tvDate.setText(MaDate);
        viewHolder.tvPseudo.setText(mDataset.get(i).getJoueur().getNom());
        viewHolder.tvGood.setText(String.valueOf(mDataset.get(i).getNbCorrect()));
    }

    @Override
    public int getItemCount() {
        if(mDataset != null){
            return mDataset.size();
        }else{
            return 0;
        }
    }
}
