package com.abnstudio.tasklist;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListAdapter;

public class MainActivity extends AppCompatActivity {
    private ListAdapter todoListAdapter;
    private TodoListSQLHelper todoListSQLHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateTodoList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_task :
                AlertDialog.Builder toDoTaskBuilder = new AlertDialog.Builder(this);
                toDoTaskBuilder.setTitle("Add Todo Task Item");
                toDoTaskBuilder.setMessage("Describe Task ...");
                final EditText todoET = new EditText(this);
                toDoTaskBuilder.setView(todoET);
                toDoTaskBuilder.setPositiveButton("Add Task", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String toDoTaskInput = todoET.getText().toString();
                        todoListSQLHelper = new TodoListSQLHelper(MainActivity.this);
                        SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.clear();

                        values.put(TodoListSQLHelper.COL1_TASK,toDoTaskInput);
                        sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME,null, values, SQLiteDatabase.CONFLICT_IGNORE);
                        
                        updateTodoList();
                    }
                });
                toDoTaskBuilder.setNegativeButton("Cancel",null);
                toDoTaskBuilder.create().show();
                return true;
                default:
                    return false;
        }
    }

    private void updateTodoList() {
    }
}
