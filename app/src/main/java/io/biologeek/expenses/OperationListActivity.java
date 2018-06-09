package io.biologeek.expenses;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import io.biologeek.expenses.api.OperationAPI;
import io.biologeek.expenses.api.beans.Operation;

public class OperationListActivity extends ListActivity {

    OperationAPI operationAPI;
    private List<Operation> operationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_list);
        this.operationList = operationAPI.getPaginatedOperationList(1, null, null, null, false);
    }

}
