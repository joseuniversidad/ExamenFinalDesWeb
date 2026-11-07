package com.examen.finaldesweb;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

/** Single source of truth; screens identify tasks by stable ID. */
public final class TaskRepository {
    private static TaskRepository instance;
    private final List<Task> tasks = new ArrayList<>();
    private final SharedPreferences preferences;
    private TaskRepository(Context context) {
        preferences = context.getSharedPreferences("com.examen.finaldesweb.TASKS", Context.MODE_PRIVATE);
        try {
            JSONArray data = new JSONArray(preferences.getString("tasks", "[]"));
            for (int i = 0; i < data.length(); i++) {
                JSONObject item = data.getJSONObject(i);
                tasks.add(new Task(item.getString("id"), item.getString("description"), item.getLong("createdAt"), item.getBoolean("completed")));
            }
        } catch (JSONException error) {
            throw new IllegalStateException("No se pudieron leer las tareas guardadas", error);
        }
    }
    private void save() {
        JSONArray data = new JSONArray();
        try {
            for (Task task : tasks) {
                JSONObject item = new JSONObject();
                item.put("id", task.id); item.put("description", task.description);
                item.put("createdAt", task.createdAt); item.put("completed", task.completed);
                data.put(item);
            }
        } catch (JSONException error) { throw new IllegalStateException(error); }
        preferences.edit().putString("tasks", data.toString()).apply();
    }
    public static synchronized TaskRepository get(Context context) {
        if (instance == null) instance = new TaskRepository(context.getApplicationContext());
        return instance;
    }
    public List<Task> all() { return new ArrayList<>(tasks); }
    public void delete(String id) { tasks.removeIf(task -> task.id.equals(id)); save(); }
    public void setCompleted(String id, boolean completed) {
        Task task = find(id);
        if (task != null) { task.completed = completed; save(); }
    }
    public Task add(String description) {
        String clean = description.trim();
        if (clean.isEmpty() || clean.length() > 500) throw new IllegalArgumentException("Escribe entre 1 y 500 caracteres");
        Task task = new Task(java.util.UUID.randomUUID().toString(), clean, System.currentTimeMillis(), false);
        tasks.add(0, task);
        save();
        return task;
    }
    public Task find(String id) {
        for (Task task : tasks) if (task.id.equals(id)) return task;
        return null;
    }
}
