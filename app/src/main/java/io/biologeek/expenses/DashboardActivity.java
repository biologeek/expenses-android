package io.biologeek.expenses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setupButtonListeners();
    }

    private void setupButtonListeners() {

        // Add expense
        findViewById(R.id.addImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddOperation = new Intent(DashboardActivity.this, null);
            }
        });

        // List last operations
        findViewById(R.id.listImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToListActivity = new Intent(DashboardActivity.this, OperationListActivity.class);
                startActivity(goToListActivity);
            }
        });
    }
}
