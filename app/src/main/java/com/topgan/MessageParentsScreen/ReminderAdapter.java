package com.topgan.MessageParentsScreen;

import android.content.Context;
import android.net.sip.SipSession;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.topgan.ItemClickListener;
import com.topgan.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.MyViewHolder> {
    private List<Reminder> reminderList;
    private Context context;
    public ItemClickListener listener;

    private List<Reminder> reminderSelected;


    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        // each data item is just a string in this case
        public TextView tvTitle;
        public ImageView ivPhotoId, ivChecked;


        public MyViewHolder(View v) {
            super(v);

            tvTitle = itemView.findViewById(R.id.tvReminderTitle);
            //tvRecurrence = itemView.findViewById(R.id.tvRecurrence);
            ivChecked = itemView.findViewById(R.id.ivChecked);
            ivPhotoId = itemView.findViewById(R.id.ivPhotoId);



        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReminderAdapter(List<Reminder> myDataset, Context context, ItemClickListener listener) {
        reminderList = myDataset;
        this.context = context;
        this.listener = listener;
        reminderSelected = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReminderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reminder_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Reminder item = reminderList.get(position);
        if (item != null) {

            holder.tvTitle.setText(item.reminderTitle);
            //holder.tvRecurrence.setText(item.reminderRecurrence);
            holder.ivPhotoId.setImageResource(item.photoId);

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if (holder.ivChecked.getVisibility() == View.INVISIBLE) {
                        holder.ivChecked.setVisibility(View.VISIBLE);
                        reminderSelected.add(item);

                    } else {
                        holder.ivChecked.setVisibility(View.INVISIBLE);
                        reminderSelected.remove(item);
                    }
                    listener.onLongClick(v, position);

                    return false;
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v,position);
                }
            });
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return reminderList != null ? reminderList.size() : 0;
    }

    public List<Reminder> getReminderSelected() {
        return reminderSelected;
    }


}
