package com.topgan.ChildDetailsScreen.ui.childdetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.topgan.ChildDetailsScreen.ChildDetailsActivity;
import com.topgan.ChildDetailsScreen.ui.childdetails.tabs.ChildDetailsContactFragment;
import com.topgan.ChildDetailsScreen.ui.childdetails.tabs.ChildDetailsNotesFragment;
import com.topgan.ChildDetailsScreen.ui.childdetails.tabs.ChildDetailsPersonalFragment;
import com.topgan.CommonData.Child;
import com.topgan.Constants;
import com.topgan.Database.DatabaseHandler;
import com.topgan.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ChildDetailsFragment extends Fragment {

    private static final String TAG = "ChildDetailsFragment";
    private ChildDetailsViewModel mViewModel;

    private ImageView iv_cd_image;
    private TextView tv_cd_fullname;


    private String childId;
    private Child child;
    private ChildDetailsTabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static ChildDetailsFragment newInstance(String childId) {
        ChildDetailsFragment fragment = new ChildDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.CHILD_ID, childId );
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        childId = getArguments().getString(Constants.CHILD_ID);
        Log.d(TAG, childId);

        return inflater.inflate(R.layout.child_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        mViewModel = ViewModelProviders.of(getActivity()).get(ChildDetailsViewModel.class);
        subscribeLiveData();
        mViewModel.loadChildDetails(childId);

    }

    private void initUI(View view) {
        if (view != null) {

            viewPager = (ViewPager) view.findViewById(R.id.viewPager);
            tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

            iv_cd_image = view.findViewById(R.id.iv_cd_image);
            tv_cd_fullname = view.findViewById(R.id.tv_cd_fullname);

            adapter = new ChildDetailsTabAdapter(getFragmentManager());
            adapter.addFragment(new ChildDetailsPersonalFragment(), "פרטים אישיים");
            adapter.addFragment(new ChildDetailsContactFragment(), "אנשי קשר");
            adapter.addFragment(new ChildDetailsNotesFragment(), "הערות");
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void subscribeLiveData() {
        if (mViewModel != null) {
            mViewModel.getChildDetails().observe(this, new Observer<Child>() {
                @Override
                public void onChanged(@Nullable Child c) {
                    child = c;
                    Log.d(TAG, child.getFirstName() + " " + child.getImage());
                    Glide.with(getContext())
                            .load(child.getImage())
                            .into(iv_cd_image);
                    String fullname = child.getFirstName() + " " + child.getLastName();
                    tv_cd_fullname.setText(fullname);
//                    tv_cd_first_name.setText(child.getFirstName());
//                    tv_cd_last_name.setText(child.getLastName());
//                    String date = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(child.getBirthDate());
//                    tv_cd_birth_date.setText(date);
//                    tv_cd_gender.setText(child.getGender());
                }
            });
        }
    }
}
