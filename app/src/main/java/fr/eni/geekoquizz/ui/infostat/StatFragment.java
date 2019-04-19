package fr.eni.geekoquizz.ui.infostat;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fr.eni.geekoquizz.R;
import fr.eni.geekoquizz.adapter.ListStatAdapter;
import fr.eni.geekoquizz.bo.Quizz;

import static android.support.constraint.Constraints.TAG;

public class StatFragment extends Fragment {

    private InfostatViewModel mViewModel;

    private static Quizz MonQuizz = new Quizz();

    //private OnFragmentInteractionListener mListener;

    public static StatFragment newInstance(Quizz MQuizz) {
        MonQuizz = MQuizz;
        return new StatFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.stat_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InfostatViewModel.class);
        // TODO: Use the ViewModel
    }

    private TextView tvNbQuestion;
    private RecyclerView RvListStat;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvNbQuestion = (TextView) view.findViewById(R.id.tvNbQuestionStat);
        int NbTotQuestions = 0;
        if(MonQuizz.getQuestions() != null){
            NbTotQuestions = MonQuizz.getQuestions().size();
        }
        tvNbQuestion.setText(String.valueOf(NbTotQuestions));

        RvListStat = (RecyclerView) view.findViewById(R.id.recyclerView);
        RvListStat.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerQuizz = new LinearLayoutManager(view.getContext());
        DividerItemDecoration divider = new DividerItemDecoration(RvListStat.getContext(),DividerItemDecoration.VERTICAL);
        RvListStat.addItemDecoration(divider);
        RvListStat.setLayoutManager(layoutManagerQuizz);

        RecyclerView.Adapter LisyAdapterQuizz = new ListStatAdapter(MonQuizz.getStatistiques(),NbTotQuestions);
        RvListStat.setAdapter(LisyAdapterQuizz);
    }
}