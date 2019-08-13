package com.hdtgroup.hdt.firebasetodo.ui.createuser;

import com.google.firebase.database.DatabaseReference;
import com.hdtgroup.hdt.firebasetodo.entities.User;
import com.hdtgroup.hdt.firebasetodo.network.firebase.FireBaseInstance;
import com.hdtgroup.hdt.firebasetodo.utils.Constants;

public class CreateUserPresenter implements ICreateUserContract.IPresenter, Constants {
    private ICreateUserContract.IView mIView;
    private DatabaseReference mDatabase;

    public CreateUserPresenter(ICreateUserContract.IView iView) {
        this.mIView = iView;
        mDatabase = FireBaseInstance.getDatabaseReference();
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void createUser(User user) {
        String userId = mDatabase.child(USERS).push().getKey();
        if (userId != null) {
            user.setId(userId);
            FireBaseInstance.getDatabaseReference().child(USERS).child(userId).setValue(user);
        }
    }
}
