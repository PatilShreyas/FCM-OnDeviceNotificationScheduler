package com.spdroid.schedulefcm.example.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.spdroid.schedulefcm.example.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSubscribe = findViewById(R.id.buttonSubscribe);
        buttonSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(MainActivity.this.getClass().getName(), "Subscribing to discount-offers topic");
                FirebaseMessaging.getInstance().subscribeToTopic("discount-offers")
                        .addOnCompleteListener((new OnCompleteListener<Void>() {
                            public final void onComplete(@NonNull Task task) {
                                MainActivity.this.showToast("Subscribed! You will get all discount offers notifications");
                                if (!task.isSuccessful()) {
                                    MainActivity.this.showToast("Failed! Try again.");
                                }
                            }
                        }));
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
