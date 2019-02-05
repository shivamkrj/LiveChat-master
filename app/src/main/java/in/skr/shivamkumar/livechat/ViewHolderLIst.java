package in.skr.shivamkumar.livechat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolderLIst extends RecyclerView.ViewHolder {

    TextView textView;
    View itemView;

    public ViewHolderLIst(View itemView) {
        super(itemView);
        this.itemView = itemView;
        textView = itemView.findViewById(R.id.content_list);
    }
}
