package com.topgan.ChildDetailsScreen.ui.childdetails.tabs;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.topgan.ChildDetailsScreen.ui.childdetails.ChildDetailsTabAdapter;
import com.topgan.ChildDetailsScreen.ui.childdetails.ChildDetailsViewModel;
import com.topgan.CommonData.Child;
import com.topgan.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ChildDetailsPersonalFragment extends Fragment {
    private static final String TAG = "ChildDetailsPersonal";
    private ChildDetailsViewModel mViewModel;

    private TextView tv_cd_first_name;
    private TextView tv_cd_last_name;
    private TextView tv_cd_birth_date;
    private TextView tv_cd_gender;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_details_personal, container, false);
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


            tv_cd_first_name = view.findViewById(R.id.tv_cd_first_name);
            tv_cd_last_name = view.findViewById(R.id.tv_cd_last_name);
            tv_cd_birth_date = view.findViewById(R.id.tv_cd_birth_date);
            tv_cd_gender = view.findViewById(R.id.tv_cd_gender);


        }
    }

    private void subscribeLiveData() {
        if (mViewModel != null) {
            mViewModel.getChildDetails().observe(this, new Observer<Child>() {
                @Override
                public void onChanged(@Nullable Child child) {
                    tv_cd_first_name.setText(child.getFirstName());
                    tv_cd_last_name.setText(child.getLastName());
                    String date = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(child.getBirthDate());
                    tv_cd_birth_date.setText(date);
                    tv_cd_gender.setText(child.getGender());
                }
            });
        }
    }
}
