package com.topgan;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.topgan.ChildDetailsScreen.ChildDetailsActivity;
import com.topgan.CommonData.MessageItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class MessageBaseAdapter extends RecyclerView.Adapter<MessageBaseAdapter.ViewHolder> implements ItemClickListener {

    private LayoutInflater m_inflater;
    private ArrayList<MessageItem> m_dataSource;
    private MainScreenActivity m_context;


    public MessageBaseAdapter(MainScreenActivity context, ArrayList<MessageItem> items)
    {
        m_dataSource            = items;
        m_inflater              = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        m_context               = context;
    }

    public void setItems(ArrayList<MessageItem> items) {
        m_dataSource.clear();
        m_dataSource.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return m_dataSource.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = m_inflater.inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBindViewHolder(m_dataSource.get(position));
        holder.setItemClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        Toast.makeText(m_context, "onClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(View view, int position) { }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public final ImageView m_image;
        public final ImageView m_selectIcon;
        public final TextView m_tv_name;
        public final TextView m_tv_lastMessage;
        public final TextView m_tv_time;
        private ItemClickListener itemClickListener;
        public boolean m_itemSelected;
        public String m_id;

        public ViewHolder(View view) {
            super(view);
            m_tv_name           = view.findViewById(R.id.tv_nameHeadline);
            m_tv_lastMessage    = view.findViewById(R.id.tv_lastMessageText);
            m_tv_time           = view.findViewById(R.id.tv_timeText);
            m_image             = view.findViewById(R.id.iv_smallIcon);
            m_selectIcon        = view.findViewById(R.id.iv_selectIcon);
            m_itemSelected      = false;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void onBindViewHolder(final MessageItem message) {
            m_tv_name.setText(message.getPrivateName() + " " + message.getLastName());
            m_tv_lastMessage.setText(message.getLastMessage());
            m_tv_time.setText("2222");
            m_id = message.getId();

            m_selectIcon.setImageResource(R.drawable.select_icon);
            m_selectIcon.setVisibility(View.INVISIBLE);

            m_image.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent childDetailsIntent = new Intent(m_context, ChildDetailsActivity.class);
                    childDetailsIntent.putExtra(Constants.CHILD_ID, message.getId());
                    m_context.startActivity(childDetailsIntent);
                }
            });

        }

        public void setItemClickListener(ItemClickListener onClickCallback) {
            itemClickListener = onClickCallback;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            if (m_itemSelected) {
                m_selectIcon.setVisibility(View.INVISIBLE);
                m_context.m_selectedIds.remove(m_id);
            }
            else{
                m_selectIcon.setVisibility(View.VISIBLE);
                m_context.m_selectedIds.add(m_id);
            }

            m_itemSelected = !m_itemSelected;
            itemClickListener.onLongClick(v,getAdapterPosition());
            return true;
        }

    }

}
