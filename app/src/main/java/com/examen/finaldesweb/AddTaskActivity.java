package com.examen.finaldesweb;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.*;

public final class AddTaskActivity extends Activity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        LinearLayout root = Ui.screen(this, "Nueva tarea", "¿Qué necesitas hacer hoy?");
        root.addView(Ui.text(this, "Descripción", 16));
        EditText description = new EditText(this);
        description.setId(android.R.id.edit);
        description.setHint("Ej.: Revisar el inventario");
        description.setMinLines(3);
        description.setGravity(android.view.Gravity.TOP);
        description.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE | android.text.InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        description.setFilters(new InputFilter[]{new InputFilter.LengthFilter(500)});
        root.addView(description, new LinearLayout.LayoutParams(-1, -2));
        root.addView(Ui.text(this, "Máximo 500 caracteres. Se guardará como pendiente.", 13));
        Ui.button(this, root, "Guardar tarea", v -> {
            try {
                TaskRepository.get(this).add(description.getText().toString());
                Toast.makeText(this, "Tarea creada", Toast.LENGTH_SHORT).show();
                finish();
            } catch (IllegalArgumentException error) { description.setError(error.getMessage()); }
        });
        Ui.button(this, root, "Cancelar y volver", v -> finish());
    }
}
