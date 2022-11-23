package ddd.magdy.fashione_commerace.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.model.Message;
import ddd.magdy.fashione_commerace.utils.Constant;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {

    int SENT_MESSAGE_TYPE = 11;
    int RECEIVE_MESSAGE_TYPE = 12;

    public List<Message> messageList;

    public MessagesAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void setData(List<Message> messageList){
        this.messageList=messageList;
    }

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENT_MESSAGE_TYPE) {
            return new SendMessageViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_send, parent, false));
        } else {
            return new ReceiveMessageViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messgae_revived, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);
        if (message.getSenderId().equals(Constant.user.getId())) {
            return SENT_MESSAGE_TYPE;
        } else {
            return RECEIVE_MESSAGE_TYPE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
        holder.bind(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    abstract class MessagesViewHolder extends RecyclerView.ViewHolder {


        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        abstract void bind(Message message);

    }

    class SendMessageViewHolder extends MessagesViewHolder {
        TextView messageTxt, messageTime;

        public SendMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTxt = itemView.findViewById(R.id.item_message_text_send);
            messageTime = itemView.findViewById(R.id.item_message_time_send);
        }

        @Override
        void bind(Message message) {
            messageTxt.setText(message.getMessageTxt());
            messageTime.setText(message.formatTime());
        }
    }

    class ReceiveMessageViewHolder extends MessagesViewHolder {
        TextView messageTxt, messageTime;

        public ReceiveMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTxt = itemView.findViewById(R.id.item_message_text);
            messageTime = itemView.findViewById(R.id.item_message_time);
        }

        @Override
        void bind(Message message) {
            messageTxt.setText(message.getMessageTxt());
            messageTime.setText(message.formatTime());
        }
    }

}
