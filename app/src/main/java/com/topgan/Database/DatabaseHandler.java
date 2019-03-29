package com.topgan.Database;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DatabaseHandler {
    private static final String TAG = "DatabaseHandler";

    private static DatabaseHandler dbInstance = null;
    private FirebaseFirestore db;
    private FirebaseStorage storage;

    // other instance variables can be here

    private DatabaseHandler() {
        if (db == null) {
            db = FirebaseFirestore.getInstance();
        }
        if (storage == null) {
             storage = FirebaseStorage.getInstance();
        }
    };

    public static DatabaseHandler getInstance() {
        if (dbInstance == null) {
            dbInstance = new DatabaseHandler();
        }
        return(dbInstance);
    }

    public FirebaseFirestore getDb() {
        return db;
    }
    public FirebaseStorage getStorage() { return storage; }
    public StorageReference getStorageRef() { return storage.getReference(); }

    private void loadImage(ImageView imageView, String url) {
        StorageReference imagesRef = getStorageRef().child(url);
    }

    public <T> void getDocData(DocumentReference ref, final Class<T> resObj, final Callback callback) {
        FirebaseFirestore db = DatabaseHandler.getInstance().getDb();
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        Log.d("ROIGR1", document.getData().toString());
                        T t = document.toObject(resObj);
                        if (t != null) {
                            callback.onSuccess(t);
                        }

                        Log.d(TAG, "DocumentSnapshot data: " + t.toString());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public void getTest() {

        DocumentReference docRef = db.collection("test").document("IvJoOh8FEGGLvKu6OScw");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }


}
