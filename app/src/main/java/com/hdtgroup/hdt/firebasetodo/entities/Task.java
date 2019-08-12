package com.hdtgroup.hdt.firebasetodo.entities;

public class Task {
    private String keyTask;
    private String nameTask;

    public Task(String keyTask, String nameTask) {
        this.keyTask = keyTask;
        this.nameTask = nameTask;
    }

    public String getKeyTask() {
        return keyTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }
}
