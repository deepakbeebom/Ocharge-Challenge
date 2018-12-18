package com.example.deepak.ochargetask.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.deepak.ochargetask.R;
import com.example.deepak.ochargetask.entity.UserEntity;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<UserEntity> mUsers;

    public UserAdapter() {
    }

    public void setUsers(List<UserEntity> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (mUsers != null) {
            UserEntity current = mUsers.get(position);
            viewHolder.mTitle.setText(current.getTitle());
            viewHolder.mBody.setText(current.getBody());
            viewHolder.userId.setText(current.getId()+"");
        }
    }

    @Override
    public int getItemCount() {
        if (mUsers != null)
            return mUsers.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle, mBody, userId;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);
            mBody = itemView.findViewById(R.id.body);
            userId = itemView.findViewById(R.id.userId);

        }
    }
}
