package com.hdtgroup.hdt.firebasetodo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.hdtgroup.hdt.firebasetodo.R;
import com.hdtgroup.hdt.firebasetodo.entities.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {
    private List<User> mUsers;
    private OnClickButtonUser onClickButtonUser;

    public UserAdapter(List<User> mUsers, OnClickButtonUser onClickButtonUser) {
        this.mUsers = mUsers;
        this.onClickButtonUser = onClickButtonUser;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_user, parent, false);
        return new UserVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserVH userVH = (UserVH) holder;
        userVH.bindView(position);
    }

    @Override
    public int getItemCount() {
        if (mUsers != null) {
            return mUsers.size();
        } else {
            return 0;
        }
    }

    public interface OnClickButtonUser {
        void clickBtnUpdate(User user);

        void clickBtnDelete(String key);
    }

    private class UserVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvUsername;
        private TextView tvPhone;
        private TextView tvAddress;
        private AppCompatButton btnUpdate;
        private AppCompatButton btnDelete;

        public UserVH(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_userName);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            tvAddress = itemView.findViewById(R.id.tv_address);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            btnDelete = itemView.findViewById(R.id.btn_delete);

            btnUpdate.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        private void bindView(int position) {
            tvUsername.setText(mUsers.get(position).getName());
            tvPhone.setText(mUsers.get(position).getPhone());
            tvAddress.setText(mUsers.get(position).getAddress());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_update: {
                    onClickButtonUser.clickBtnUpdate(mUsers.get(getAdapterPosition()));
                    break;
                }
                case R.id.btn_delete: {
                    onClickButtonUser.clickBtnDelete(mUsers.get(getAdapterPosition()).getId());
                    break;
                }
                default:
                    break;
            }
        }
    }
}
