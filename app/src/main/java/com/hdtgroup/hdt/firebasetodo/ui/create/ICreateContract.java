package com.hdtgroup.hdt.firebasetodo.ui.create;

public interface ICreateContract {
    interface IPresenter {
        void createTask(String nameTask);

        void updateTask(String keyTask, String nameTask);
    }

    interface IView {
        void showSuccess();

        void showFailure();
    }
}
