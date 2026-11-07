package com.examen.finaldesweb;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/** Single source of truth; screens identify tasks by stable ID. */
public final class TaskRepository {
    private static TaskRepository instance;
    private final List<Task> tasks = new ArrayList<>();
    private TaskRepository(Context context) { }
    public static synchronized TaskRepository get(Context context) {
        if (instance == null) instance = new TaskRepository(context.getApplicationContext());
        return instance;
    }
    public List<Task> all() { return new ArrayList<>(tasks); }
    public void setCompleted(String id, boolean completed) {
        Task task = find(id);
        if (task != null) task.completed = completed;
    }
    public Task add(String description) {
        String clean = description.trim();
        if (clean.isEmpty() || clean.length() > 500) throw new IllegalArgumentException("Escribe entre 1 y 500 caracteres");
        Task task = new Task(java.util.UUID.randomUUID().toString(), clean, System.currentTimeMillis(), false);
        tasks.add(0, task);
        return task;
    }
    public Task find(String id) {
        for (Task task : tasks) if (task.id.equals(id)) return task;
        return null;
    }
}
