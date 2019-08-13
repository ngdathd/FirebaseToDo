package com.hdtgroup.hdt.firebasetodo.ui.read;

import com.hdtgroup.hdt.firebasetodo.entities.Task;

import java.util.List;

public interface IReadContract {
    interface IPresenter {
        void readTasks();

        void deleteTask(String key);

        void sendNotyFireBase();
    }

    interface IView {
        void showSuccess(List<Task> tasks);

        void showFailure();
    }
}
