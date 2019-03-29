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
import com.bumptech.glide.Glide;
import com.topgan.ChildDetailsScreen.ChildDetailsActivity;
import com.topgan.CommonData.MessageItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MessageBaseAdapter extends RecyclerView.Adapter<MessageBaseAdapter.ViewHolder> implements ItemClickListener {

    private LayoutInflater m_inflater;
    private ArrayList<MessageItem> m_dataSource;
    private MainScreenActivity m_context;
    private String m_selected = "none";

    public MessageBaseAdapter(MainScreenActivity context, ArrayList<MessageItem> items)
    {
        m_dataSource            = items;
        m_inflater              = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        m_context               = context;
    }

    public void chooseAllItems()
    {
        m_selected = "all";
        notifyDataSetChanged();
    }

    public void chooseNoneItems()
    {
        m_selected = "none";
        notifyDataSetChanged();
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

    }

    @Override
    public void onLongClick(View view, int position) { }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public final ImageView m_image;
        public final ImageView m_selectIcon;
        public final TextView m_tv_name;
        public final TextView m_tv_lastMessage;
        public final TextView m_tv_date;
        private ItemClickListener itemClickListener;
        public boolean m_itemSelected;
        public String m_id;

        public ViewHolder(View view) {
            super(view);
            m_tv_name           = view.findViewById(R.id.tv_nameHeadline);
            m_tv_lastMessage    = view.findViewById(R.id.tv_lastMessageText);
            m_tv_date           = view.findViewById(R.id.tv_timeText);
            m_image             = view.findViewById(R.id.iv_smallIcon);
            m_selectIcon        = view.findViewById(R.id.iv_selectIcon);
            m_itemSelected      = false;

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void onBindViewHolder(final MessageItem message) {
            m_tv_name.setText(message.getPrivateName() + " " + message.getLastName());
            m_tv_lastMessage.setText(message.getLastMessage());
            String date = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(message.getTime());
            m_tv_date.setText(date);
            m_id = message.getId();
            Glide.with(m_context)
                    .load(message.getPhoto())
                    .into(m_image);

            m_selectIcon.setImageResource(R.drawable.select_icon);
            m_selectIcon.setVisibility(View.INVISIBLE);

            if (m_selected == "none")
            {
                m_itemSelected      = false;
                m_context.m_selectedIds.remove(m_id);
                m_selectIcon.setVisibility(View.INVISIBLE);

            }
            else
            {
                m_itemSelected      = true;
                m_context.m_selectedIds.add(m_id);
                m_selectIcon.setVisibility(View.VISIBLE);
            }

            if (m_context.m_selectedIds.isEmpty())
            {
                m_context.setVisable(false);
            }
            else
            {
                m_context.setVisable(true);
            }

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

            if (m_context.m_selectedIds.isEmpty())
            {
                m_context.setVisable(false);
            }
            else
            {
                m_context.setVisable(true);
            }
            itemClickListener.onLongClick(v,getAdapterPosition());
            return true;
        }

    }

}
