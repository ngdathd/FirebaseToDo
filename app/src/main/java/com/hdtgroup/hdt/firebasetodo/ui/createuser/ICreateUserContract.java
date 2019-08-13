package com.hdtgroup.hdt.firebasetodo.ui.createuser;

import com.hdtgroup.hdt.firebasetodo.entities.User;

public interface ICreateUserContract {
    interface IPresenter {
        void createUser(User user);

        void updateUser(User user);
    }

    interface IView {
        void showSuccess();

        void showFailure();
    }
}
