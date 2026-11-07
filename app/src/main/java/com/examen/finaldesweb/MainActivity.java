package com.examen.finaldesweb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public final class MainActivity extends Activity {
    private TaskAdapter adapter;
    private TextView summary;
    private TaskRepository repository;
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        repository = TaskRepository.get(this);
        LinearLayout root = Ui.screen(this, "Mis pendientes", "Organiza tu día, una tarea a la vez.");
        summary = Ui.text(this, "", 14); root.addView(summary);
        ListView list = new ListView(this);
        list.setId(android.R.id.list);
        adapter = new TaskAdapter(this, repository.all()); list.setAdapter(adapter);
        list.setOnItemClickListener((parent, view, position, id) -> startActivity(
            new android.content.Intent(this, DetailActivity.class).putExtra(DetailActivity.EXTRA_TASK_ID, adapter.getItem(position).id)));
        TextView empty = Ui.text(this, "Aún no hay tareas. Agrega tu primer pendiente.", 18);
        root.addView(empty); list.setEmptyView(empty);
        root.addView(list, new LinearLayout.LayoutParams(-1, 0, 1));
        Ui.button(this, root, "+ Nueva tarea", v -> startActivity(new android.content.Intent(this, AddTaskActivity.class)));
    }
    @Override protected void onResume() { super.onResume(); refresh(); }
    private void refresh() {
        adapter.submit(repository.all());
        summary.setText(repository.all().size() + " tareas en tu jornada");
    }
}
