package com.example.manga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.R;
import com.example.manga.elements.child.Comments;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

public class ListCommentHotAdapter extends RecyclerView.Adapter<ListCommentHotAdapter.Item> {

    private Context context;
    private List<Comments> list;

    public void setList(List<Comments> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ListCommentHotAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_comment_show,parent,false);

        return new Item(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position) {
        Comments comments = list.get(position);
        if(comments == null){
            return;
        }
        if (comments.getId_user() != null) {
            // Nếu có, an toàn khi truy cập getUsername và getAvatar
            holder.name.setText(comments.getId_user().getUsername());

            try {
                Picasso.get().load(comments.getId_user().getAvatar()).into(holder.avatar);
            } catch (Exception e) {
                // Xử lý nếu không thể tải ảnh, ví dụ: đặt ảnh mặc định
                holder.avatar.setImageResource(R.drawable.default_avatar); // Giả sử R.drawable.default_avatar là ảnh mặc định của bạn
            }
        } else {
            // Nếu không, đặt tên mặc định và/hoặc ảnh đại diện mặc định
            holder.name.setText("Ẩn danh"); // Hoặc bất kỳ chuỗi mặc định nào phù hợp
            holder.avatar.setImageResource(R.drawable.default_avatar); // Đặt ảnh mặc định
        }
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class Item extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name,content,date;
        public Item(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.item_avatar_user);
            name = itemView.findViewById(R.id.item_name_user);
            content = itemView.findViewById(R.id.item_conent_comment);
            date = itemView.findViewById(R.id.item_date_comment);
        }
    }
}
