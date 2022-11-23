package ddd.magdy.fashione_commerace.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.model.ResponseUserItem;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder> {

    private List<ResponseUserItem> userItemList;
    List<String> myList = Arrays.asList("23 min", "1 hour", "yesterday", " just now", "40 min");


    public MessageListAdapter(List<ResponseUserItem> userItemList) {
        this.userItemList = userItemList;
    }

    public void setData(List<ResponseUserItem> userItemList) {
        this.userItemList = userItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListViewHolder holder, int position) {

        holder.bind(userItemList.get(position));
        if (onMessagesListItemClick != null) {
            holder.itemView.setOnClickListener(v -> {
                onMessagesListItemClick.onMessageItemClick(position, userItemList.get(position));
            });
        }
    }

    @Override
    public int getItemCount() {
        return userItemList.size();
    }

    public OnMessagesListItemClick onMessagesListItemClick;

    public interface OnMessagesListItemClick {
        void onMessageItemClick(int position, ResponseUserItem userItem);
    }

    class MessageListViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemName, itemPhone, itemTime;

        public MessageListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_chat_image);
            itemName = itemView.findViewById(R.id.item_chat_name);
            itemPhone = itemView.findViewById(R.id.item_chat_message);
            itemTime = itemView.findViewById(R.id.item_chat_time);
        }

        public void bind(ResponseUserItem responseUserItem) {
            itemName.setText(responseUserItem.getUsername());
            itemImage.setImageResource(R.drawable.image_review2);
            itemPhone.setText(responseUserItem.getPhone());

            Random random = new Random();

            int randomItem = random.nextInt(myList.size());
            String randomElement = myList.get(randomItem);
            itemTime.setText(randomElement);

        }
    }
}











