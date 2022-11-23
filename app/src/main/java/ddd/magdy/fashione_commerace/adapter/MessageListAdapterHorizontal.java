package ddd.magdy.fashione_commerace.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.model.ResponseUserItem;

public class MessageListAdapterHorizontal extends RecyclerView.Adapter<MessageListAdapterHorizontal.MessageListHorizontalViewHolder> {

    private List<ResponseUserItem> userItemList;

    public MessageListAdapterHorizontal(List<ResponseUserItem> userItemList) {
        this.userItemList = userItemList;
    }

    public void setData(List<ResponseUserItem> userItemList) {
        this.userItemList = userItemList;
        notifyDataSetChanged();
    }

    public OnMessageListItemClick onMessageListItemClick;

    public interface OnMessageListItemClick {
        void onItemClick(int position, ResponseUserItem userItem);
    }


    @NonNull
    @Override
    public MessageListHorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageListHorizontalViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_person_message_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListHorizontalViewHolder holder, int position) {
        holder.bind(userItemList.get(position));
        if (onMessageListItemClick != null) {
            holder.itemView.setOnClickListener(v -> {
                onMessageListItemClick.onItemClick(position, userItemList.get(position));
            });
        }
    }

    @Override
    public int getItemCount() {
        return userItemList.size();
    }

    class MessageListHorizontalViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        public MessageListHorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_person_name);
            image = itemView.findViewById(R.id.item_person_image);

        }

        public void bind(ResponseUserItem userItem) {
            name.setText(userItem.getUsername());
            image.setImageResource(R.drawable.image_review4);
        }
    }
}
