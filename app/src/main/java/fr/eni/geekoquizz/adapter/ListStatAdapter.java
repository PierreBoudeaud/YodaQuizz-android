package fr.eni.geekoquizz.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Date;
import java.util.List;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.bo.Statistique;

public class ListStatAdapter extends RecyclerView.Adapter<ListStatAdapter.ViewHolder>
{
    private List<Statistique> mDataset;
    private int NbTotQuestion;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView tvPseudo,tvDate,tvGood,tvPercent;
        private ViewHolder(View v)
        {
            super(v);
            tvPercent = v.findViewById(R.id.tvPercent);
            tvPseudo = v.findViewById(R.id.tvPseudo);
            tvDate = v.findViewById(R.id.tvDate);
            tvGood = v.findViewById(R.id.tvGood);
        }

        @Override
        public void onClick(View view) {
        }
    }

    public ListStatAdapter(List<Statistique> myDataset,int NbQuestions)
    {
        NbTotQuestion = NbQuestions;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public ListStatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detailsstatquizz,viewGroup, false);
        return new ViewHolder(v);
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
