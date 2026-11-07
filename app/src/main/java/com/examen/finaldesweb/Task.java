package com.examen.finaldesweb;

public final class Task {
    public final String id;
    public final String description;
    public final long createdAt;
    public boolean completed;

    public Task(String id, String description, long createdAt, boolean completed) {
        this.id = id;
        this.description = description;
        this.createdAt = createdAt;
        this.completed = completed;
    }
}
