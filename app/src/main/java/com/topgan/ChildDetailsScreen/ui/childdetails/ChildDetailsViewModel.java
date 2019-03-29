package com.topgan.ChildDetailsScreen.ui.childdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.topgan.CommonData.Child;
import com.topgan.Database.Callback;
import com.topgan.Database.DatabaseHandler;

public class ChildDetailsViewModel extends ViewModel {
    private final String TAG = this.getClass().getName();
    private MutableLiveData<Child> ldChild;
    public LiveData<Child> getChildDetails() {
        if (ldChild == null) {
            ldChild = new MutableLiveData<Child>();
         }
        return ldChild;
    }

    public void loadChildDetails(String childId) {
        getChildDetails(childId);
    }

    public void getChildDetails(String childId) {
        FirebaseFirestore db = DatabaseHandler.getInstance().getDb();

        DatabaseHandler.getInstance().getDocData(db.collection("Children").document(childId), Child.class, new Callback<Child>() {
            @Override
            public void onSuccess(Child child) {
                ldChild.postValue(child);
            }
        });


//        DocumentReference docRef = db.collection("Children").document(childId);
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document != null && document.exists()) {
//                        Child child = document.toObject(Child.class);
//                        ldChild.postValue(child);
//                        Log.d(TAG, "DocumentSnapshot data: " + child.toString());
//                    } else {
//                        Log.d(TAG, "No such document");
//                    }
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });
    }
}
