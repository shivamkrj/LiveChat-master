package in.skr.shivamkumar.livechat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewHolderChat extends RecyclerView.ViewHolder {
    LinearLayout linearLayout1,linearLayout2;

    TextView message1;
    TextView time;
    View itemView;
    public ViewHolderChat(View itemView) {
        super(itemView);
        this.itemView = itemView;
        message1=itemView.findViewById(R.id.message);
        time = itemView.findViewById(R.id.time);
        //message = itemView.findViewById(R);

    }
}
