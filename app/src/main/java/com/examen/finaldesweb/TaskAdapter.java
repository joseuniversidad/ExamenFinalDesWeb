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
        LinearLayout row;
        if (recycled instanceof LinearLayout) row = (LinearLayout) recycled;
        else {
            row = new LinearLayout(activity); row.setGravity(Gravity.CENTER_VERTICAL);
            TextView label = Ui.text(activity, "", 18);
            row.addView(label, new LinearLayout.LayoutParams(0, -2, 1));
            CheckBox check = new CheckBox(activity); check.setFocusable(false); row.addView(check);
        }
        TextView label = (TextView) row.getChildAt(0);
        CheckBox check = (CheckBox) row.getChildAt(1);
        Task task = getItem(position);
        label.setText(task.description + "\n" + (task.completed ? "Completada" : "Pendiente"));
        label.setMaxLines(4); label.setEllipsize(android.text.TextUtils.TruncateAt.END);
        row.setPadding(Ui.dp(activity, 12), Ui.dp(activity, 20), Ui.dp(activity, 12), Ui.dp(activity, 20));
        label.setPaintFlags(task.completed ? label.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG : label.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        label.setAlpha(task.completed ? 0.55f : 1f);
        check.setOnCheckedChangeListener(null); check.setChecked(task.completed);
        check.setContentDescription("Completar: " + task.description);
        check.setOnCheckedChangeListener((button, checked) -> {
            TaskRepository.get(activity).setCompleted(task.id, checked);
            notifyDataSetChanged();
        });
        return row;
    }
}
