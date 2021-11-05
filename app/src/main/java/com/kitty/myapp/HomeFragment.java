package com.kitty.myapp;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private RecyclerView recyclerView;
    ordersAdapter orders; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
        signInAnonymously();
        // Firebase Realtime Database
        final Global globalVariable = (Global) getActivity().getApplicationContext();
        mbase = FirebaseDatabase.getInstance().getReference("temp1");
        if (globalVariable.getUser() == 2)
            mbase = FirebaseDatabase.getInstance().getReference("temp2");

        recyclerView = view.findViewById(R.id.recyclerview);
        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        FirebaseRecyclerOptions<orders> options
                = new FirebaseRecyclerOptions.Builder<orders>()
                .setQuery(mbase, orders.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        orders = new ordersAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(orders);
        return view;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        orders.startListening();
        mAuth.addAuthStateListener(mAuthListener);
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override
    public void onStop()
    {
        super.onStop();
        orders.stopListening();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInAnonymously:onComplete:" + task.isSuccessful());
            }
        });
    }
}
