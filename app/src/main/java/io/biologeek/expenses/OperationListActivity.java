package io.biologeek.expenses;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import io.biologeek.expenses.adapters.OperationAdapter;
import io.biologeek.expenses.api.AbstractAPI;
import io.biologeek.expenses.api.OperationAPI;
import io.biologeek.expenses.api.beans.Operation;
import io.biologeek.expenses.api.beans.PaginatedOperationsList;
import io.biologeek.expenses.beans.UserInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OperationListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    OperationAPI operationAPI;
    private Operation[] operationList;
    private SwipeRefreshLayout swipeLayout;
    private ListView mListView;

    public OperationListActivity(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_list);
        mListView = findViewById(R.id.list);
        ((TextView) findViewById(R.id.activityTitle)).setText(R.string.title_activity_operation_list);
        this.operationAPI = new AbstractAPI(true).buildHttpClient(getApplicationContext()).create(OperationAPI.class);

        ImageView previousActivity = findViewById(R.id.previousActivityImage);
        previousActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDashboard = new Intent(OperationListActivity.this, DashboardActivity.class);
                startActivity(goToDashboard);
            }
        });

        //swipeLayout.setOnRefreshListener(this);
        

        Call<PaginatedOperationsList> opCall = operationAPI.getPaginatedOperationList(1, 0, 50, "id", true);
        opCall.enqueue(callBack);
        operationList = new Operation[]{new Operation()};
    }

    Callback<PaginatedOperationsList> callBack = new Callback<PaginatedOperationsList>() {
            @Override
            public void onResponse(Call<PaginatedOperationsList> call, Response<PaginatedOperationsList> response) {
                if (response.isSuccessful() && response.body() != null){
                    Object[] objArray = response.body().getOperations().toArray();
                    operationList = Arrays.copyOf(objArray, objArray.length, Operation[].class);
                    mListView.setAdapter(new OperationAdapter(OperationListActivity.this, (Operation[]) operationList));
                } else if (response.code() == 401){
                    Intent goBackToLogin = new Intent(OperationListActivity.this, LoginActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(LoginActivity.SHOULD_RELOAD, false);
                    goBackToLogin.putExtras(bundle);
                    startActivity(goBackToLogin);
                }
            }

            @Override
            public void onFailure(Call<PaginatedOperationsList> call, Throwable t) {
                Toast error = Toast.makeText(OperationListActivity.this, "Error", Toast.LENGTH_LONG);
                error.show();
            }
        };

    @Override
    public void onRefresh() {
        
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
