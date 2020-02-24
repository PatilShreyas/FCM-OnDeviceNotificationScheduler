package com.spdroid.schedulefcm.example.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.spdroid.schedulefcm.example.R;

import org.jetbrains.annotations.NotNull;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (getSupportActionBar()!=null)
            getSupportActionBar().setTitle("Schedule FCM Java");
    }


    private void showToast(String message) {
        Toast.makeText((Context) this, (CharSequence) message, Toast.LENGTH_SHORT).show();
    }

    public void click_subscribejavaversion(View view) {
        Log.d(Main2Activity.this.getClass().getName(), "Subscribing to discount-offers topic");
        FirebaseMessaging.getInstance().subscribeToTopic("discount-offers")
                .addOnCompleteListener((new OnCompleteListener<Void>() {
                    public final void onComplete(@NotNull Task task) {
//                Intrinsics.checkParameterIsNotNull(task, "task");
                        Main2Activity.this.showToast("Subscribed! You will get all discount offers notifications");
                        if (!task.isSuccessful()) {
                            Main2Activity.this.showToast("Failed! Try again.");
                        }

                    }
                }));


    }

}
