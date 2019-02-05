package in.skr.shivamkumar.livechat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AdapterChat extends RecyclerView.Adapter<ViewHolderChat> {

    LayoutInflater layoutInflater;
    ArrayList<ChatData> itemsList;

    public AdapterChat(Context context, ArrayList<ChatData> chatData) {
         layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.itemsList = chatData;
    }

    @NonNull
    @Override
    public ViewHolderChat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View output = layoutInflater.inflate(R.layout.chat_viewholder,parent,false);
        return new ViewHolderChat(output);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderChat holder, int position) {
        ChatData chatData = itemsList.get(position);
        holder.message1.setText(chatData.message);

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
