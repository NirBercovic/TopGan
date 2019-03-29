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

public class ChildDetailsContactFragment extends Fragment {
    private static final String TAG = "ChildDetailsPersonal";
    private ChildDetailsViewModel mViewModel;

    private TextView tv_cd_parent1;
    private TextView tv_cd_parentPhone1;
    private TextView tv_cd_parent2;
    private TextView tv_cd_parentPhone2;
    private TextView tv_cd_contact1;
    private TextView tv_cd_contactPhone1;
    private TextView tv_cd_relation1;
    private TextView tv_cd_contact2;
    private TextView tv_cd_contactPhone2;
    private TextView tv_cd_relation2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_details_contact, container, false);
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
            
            tv_cd_parent1 = view.findViewById(R.id.tv_cd_parent1);
            tv_cd_parentPhone1 = view.findViewById(R.id.tv_cd_parentPhone1);
            tv_cd_parent2 = view.findViewById(R.id.tv_cd_parent2);
            tv_cd_parentPhone2 = view.findViewById(R.id.tv_cd_parentPhone2);
            tv_cd_contact1 = view.findViewById(R.id.tv_cd_contact1);
            tv_cd_contactPhone1 = view.findViewById(R.id.tv_cd_contactPhone1);
            tv_cd_relation1 = view.findViewById(R.id.tv_cd_relation1);
            tv_cd_contact2 = view.findViewById(R.id.tv_cd_contact2);
            tv_cd_contactPhone2 = view.findViewById(R.id.tv_cd_contactPhone2);
            tv_cd_relation2 = view.findViewById(R.id.tv_cd_relation2);


        }
    }

    private String wrapWithBracets(String s){
        return "(" + s + ")";
    }

    private void subscribeLiveData() {

        if (mViewModel != null) {
            mViewModel.getChildDetails().observe(this, new Observer<Child>() {
                @Override
                public void onChanged(@Nullable Child child) {
                    tv_cd_parent1.setText(child.getParent1());
                    tv_cd_parentPhone1.setText(child.getParentPhone1());
                    tv_cd_parent2.setText(child.getParent2());
                    tv_cd_parentPhone2.setText(child.getParentPhone2());
                    tv_cd_contact1.setText(child.getContact1());
                    tv_cd_contactPhone1.setText(child.getContactPhone1());
                    tv_cd_relation1.setText(wrapWithBracets(child.getRelation1()));
                    tv_cd_contact2.setText(child.getContact2());
                    tv_cd_contactPhone2.setText(child.getContactPhone2());
                    tv_cd_relation2.setText(wrapWithBracets(child.getRelation2()));
                }
            });
        }
    }
}
