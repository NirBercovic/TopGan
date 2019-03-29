package com.topgan.ChildDetailsScreen.ui.childdetails.tabs;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.topgan.ChildDetailsScreen.ui.childdetails.ChildDetailsViewModel;
import com.topgan.CommonData.Child;
import com.topgan.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ChildDetailsNotesFragment extends Fragment {
    private static final String TAG = "ChildDetailsNotes";
    private ChildDetailsViewModel mViewModel;

    private TextView tv_cd_sensitive;
    private TextView tv_cd_comments;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_details_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        mViewModel = ViewModelProviders.of(getActivity()).get(ChildDetailsViewModel.class);
        subscribeLiveData();

    }

    private void initUI(View view) {
        if (view != null) {

            tv_cd_sensitive = view.findViewById(R.id.tv_cd_sensitive);
            tv_cd_comments = view.findViewById(R.id.tv_cd_comments);

        }
    }

    private void subscribeLiveData() {
        if (mViewModel != null) {
            mViewModel.getChildDetails().observe(this, new Observer<Child>() {
                @Override
                public void onChanged(@Nullable Child child) {
                    tv_cd_sensitive.setText(child.getSensitive());
                    tv_cd_comments.setText(child.getComments());
                }
            });
        }
    }
}
