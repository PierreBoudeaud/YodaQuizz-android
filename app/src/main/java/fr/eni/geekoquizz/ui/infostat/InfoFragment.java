package fr.eni.geekoquizz.ui.infostat;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.adapter.ListQuizzAdapter;
import fr.eni.geekoquizz.bo.Quizz;
import fr.eni.geekoquizz.bo.Statistique;

public class InfoFragment extends Fragment {

    private static Quizz MonQuizz = new Quizz();
    private InfostatViewModel mViewModel;

    public static InfoFragment newInstance(Quizz MQuizz) {
        MonQuizz = MQuizz;
        return new InfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.info_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InfostatViewModel.class);
    }

    //Ecran Info_Fragment
        private TextView tvNbQuestion,tvType,tvDescription,tvAuteur,tvDate,textView12;
        private RatingBar rbDifficulte;
        private Integer[] TabId = {R.id.ivTheme1,R.id.ivTheme2,R.id.ivTheme3,R.id.ivTheme4,R.id.ivTheme5,R.id.ivTheme6};
        private List<ImageView> ListImageView = new ArrayList<>();
        private DownloadImageTask ivQuest1,ivQuest2,ivQuest3;
        private ConstraintLayout cLayoutImage;

    private void SetData(){
        if(MonQuizz.getThemes().size() != 0){
            for(int i = 0; i < ListImageView.size();i++){
                ListImageView.get(i).setVisibility(View.VISIBLE);
                //ListImageView.get(i).setImageResource(Integer.valueOf(MonQuizz.getThemes().get(i).getTheme().getIcon()));
            }
        }
        int MoyCorrect = 0,MoyPercent = 0;
        if(MonQuizz.getStatistiques() != null && MonQuizz.getStatistiques().size() > 0){
            for (Statistique Stat:MonQuizz.getStatistiques()
                 ) {
                MoyCorrect += Stat.getNbCorrect();
            }
            MoyCorrect = MoyCorrect / MonQuizz.getStatistiques().size();
            if(MonQuizz.getQuestions() != null && MoyCorrect < MonQuizz.getQuestions().size()){
                MoyPercent = MoyCorrect*100/MonQuizz.getQuestions().size();
            }
        }

        int NbQuestion = 0;
        if(MonQuizz.getQuestions() != null){
            NbQuestion = MonQuizz.getQuestions().size();
            if(NbQuestion >= 3){ // on prend les 3 premieres images
                cLayoutImage.setVisibility(View.VISIBLE);
                List<String> ListImages = MonQuizz.getSomeImagesOfQuestions();
                ivQuest1.execute("http://yoda.pboudeaud.fr/api/showFile/"+ListImages.get(0));
                ivQuest2.execute("http://yoda.pboudeaud.fr/api/showFile/"+ListImages.get(1));
                ivQuest3.execute("http://yoda.pboudeaud.fr/api/showFile/"+ListImages.get(2));
            }
        }

        tvNbQuestion.setText(       String.valueOf(NbQuestion));
        tvType.setText(             MonQuizz.getType().getNom());
        tvAuteur.setText(           MonQuizz.getUtilisateur().getNom());
        String MaDate;
        if(new android.text.format.DateFormat().format("yyyy-MM-dd", new Date()).equals(new android.text.format.DateFormat().format("yyyy-MM-dd", MonQuizz.getDateModif()))){
            MaDate = (String)new android.text.format.DateFormat().format("HH:mm", MonQuizz.getDateModif());
        }else{
            MaDate = (String)new android.text.format.DateFormat().format("dd/MM/yy", MonQuizz.getDateModif());
        }
        tvDate.setText(             MaDate);
        String Text = String.valueOf(MoyPercent)+" "+getString(R.string.tvInfoPart1)+" "+String.valueOf(MoyCorrect)+" "+getString(R.string.tvInfoPart2);
        textView12.setText(         Text);
        tvDescription.setText(      MonQuizz.getDescription());
        rbDifficulte.setRating(     MonQuizz.getDifficulte());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDescription   = view.findViewById(R.id.tvDescription);
        tvNbQuestion    = view.findViewById(R.id.tvNbQuestionStat);
        tvType          = view.findViewById(R.id.tvType);
        tvAuteur        = view.findViewById(R.id.tvAuteur);
        tvDate          = view.findViewById(R.id.tvDate);
        textView12      = view.findViewById(R.id.textView12);
        rbDifficulte    = view.findViewById(R.id.rbDifficulte);
        ivQuest1        = new InfoFragment.DownloadImageTask((ImageView) view.findViewById(R.id.ivQuest1));
        ivQuest2        = new InfoFragment.DownloadImageTask((ImageView) view.findViewById(R.id.ivQuest2));
        ivQuest3        = new InfoFragment.DownloadImageTask((ImageView) view.findViewById(R.id.ivQuest3));
        cLayoutImage    = view.findViewById(R.id.constraintLayoutImage);

        if(MonQuizz.getThemes().size() != 0){
            for(int i = 0; i < MonQuizz.getThemes().size();i++){
                if(i <= 5){
                    ListImageView.add((ImageView) view.findViewById(TabId[i]));
                }else{
                    break;
                }
            }
        }

        SetData();
    }

    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
