package com.examen.finaldesweb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import java.text.DateFormat;
import java.util.Date;

public final class DetailActivity extends Activity {
    public static final String EXTRA_TASK_ID = "com.examen.finaldesweb.TASK_ID";
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        Task task = TaskRepository.get(this).find(getIntent().getStringExtra(EXTRA_TASK_ID));
        if (task == null) {
            Toast.makeText(this, "La tarea ya no está disponible", Toast.LENGTH_SHORT).show();
            finish(); return;
        }
        LinearLayout root = Ui.screen(this, "Detalle de tarea", "Toda la información de tu pendiente.");
        ScrollView scroll = new ScrollView(this);
        LinearLayout content = new LinearLayout(this); content.setOrientation(LinearLayout.VERTICAL);
        scroll.addView(content); root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));
        content.addView(Ui.text(this, "DESCRIPCIÓN", 12));
        content.addView(Ui.text(this, task.description, 23));
        content.addView(Ui.text(this, "ESTADO", 12));
        content.addView(Ui.text(this, task.completed ? "Completada" : "Pendiente", 18));
        CheckBox completed = new CheckBox(this); completed.setText("Tarea completada");
        completed.setChecked(task.completed); content.addView(completed);
        completed.setOnCheckedChangeListener((button, checked) -> {
            TaskRepository.get(this).setCompleted(task.id, checked);
            ((TextView) content.getChildAt(3)).setText(checked ? "Completada" : "Pendiente");
        });
        content.addView(Ui.text(this, "FECHA DE CREACIÓN", 12));
        content.addView(Ui.text(this, DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date(task.createdAt)), 18));
        Ui.button(this, root, "Volver a la lista", v -> finish());
    }
}
