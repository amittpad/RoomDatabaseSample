package com.india.ets.roomdatabasesample;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.india.ets.roomdatabasesample.adapter.TasksAdapter;
import com.india.ets.roomdatabasesample.roomdatabase.DatabaseClient;
import com.india.ets.roomdatabasesample.roomdatabase.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FloatingActionButton addTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview_tasks);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addTask = findViewById(R.id.floating_button_add);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        getTasks();
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Task>>{

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            /**
             * <p>Runs on the UI thread after {@link #doInBackground}. The
             * specified result is the value returned by {@link #doInBackground}.</p>
             *
             * <p>This method won't be invoked if the task was cancelled.</p>
             *
             * @param tasks The result of the operation computed by {@link #doInBackground}.
             * @see #onPreExecute
             * @see #doInBackground
             * @see #onCancelled(Object)
             */
            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                TasksAdapter adapter = new TasksAdapter(MainActivity.this, tasks);
                mRecyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();

    }
}
