package com.examen.finaldesweb;

import android.app.Activity;
import android.graphics.Paint;
import android.view.*;
import android.widget.*;
import java.util.List;

/** ListView adapter, with recycled rows and explicit state binding. */
public final class TaskAdapter extends BaseAdapter {
    private final Activity activity;
    private List<Task> tasks;
    public TaskAdapter(Activity activity, List<Task> tasks) { this.activity = activity; this.tasks = tasks; }
    public void submit(List<Task> value) { tasks = value; notifyDataSetChanged(); }
    @Override public int getCount() { return tasks.size(); }
    @Override public Task getItem(int position) { return tasks.get(position); }
    @Override public long getItemId(int position) { return position; }
    @Override public View getView(int position, View recycled, ViewGroup parent) {
        TextView row = recycled instanceof TextView ? (TextView) recycled : Ui.text(activity, "", 18);
        Task task = getItem(position);
        row.setText(task.description + "\n" + (task.completed ? "Completada" : "Pendiente"));
        row.setPadding(Ui.dp(activity, 12), Ui.dp(activity, 20), Ui.dp(activity, 12), Ui.dp(activity, 20));
        row.setPaintFlags(row.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        return row;
    }
}
