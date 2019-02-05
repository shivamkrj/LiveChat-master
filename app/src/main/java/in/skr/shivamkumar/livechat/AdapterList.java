package in.skr.shivamkumar.livechat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AdapterList extends RecyclerView.Adapter<ViewHolderLIst> {

    ArrayList<String> itemsList;
    ClickInterface clickInterface;
    LayoutInflater layoutInflater;

    public AdapterList(Context context,ArrayList<String> itemsList, ClickInterface clickInterface) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.itemsList = itemsList;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public ViewHolderLIst onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View output = layoutInflater.inflate(R.layout.layout_list_view_holder,parent,false);
        return new ViewHolderLIst(output);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLIst holder, final int position) {
        String s = itemsList.get(position);
        holder.textView.setText(s);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.onViewClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
