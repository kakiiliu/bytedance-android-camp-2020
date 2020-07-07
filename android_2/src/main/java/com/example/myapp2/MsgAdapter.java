package com.example.myapp2;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> mMsgList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView msgImage;
        TextView msgName;
        TextView msgTime;
        View contentView;

        public ViewHolder (View view)
        {
            super(view);
            contentView = view;
            msgImage = (ImageView) view.findViewById(R.id.msg_image);
            msgName = (TextView) view.findViewById(R.id.msgname);
            msgTime = (TextView) view.findViewById(R.id.msgtime);
        }


    }

    public MsgAdapter (List <Msg> msgList) {
        mMsgList = msgList;
    }



    @NonNull
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MsgAdapter.ViewHolder holder, final int position) {
        Msg msg = mMsgList.get(position);
        holder.msgImage.setImageResource(msg.getImageId());
        holder.msgName.setText(msg.getName());
        holder.msgTime.setText(msg.getTime());
        holder.contentView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"You've clicked item " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), JumpActivity.class);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }


}
