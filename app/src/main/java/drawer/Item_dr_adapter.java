package drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ungdungdocbao.R;

import java.util.ArrayList;

public class Item_dr_adapter extends RecyclerView.Adapter<Item_dr_adapter.ItemHolder> {
    private ArrayList<Item_drawer> arr_drawer;
    private OnClickItemListner onClickItemListner;
    private Context context;
    public void setOnClickItemListener(OnClickItemListner onClickItemListener) {
        this.onClickItemListner = onClickItemListener;
    }
    public Item_dr_adapter(Context context) {
        this.context = context;
        initData();
    }
    private void initData() {
        arr_drawer = new ArrayList<>();
        arr_drawer.add(new Item_drawer("Trang chủ", "https://www.24h.com.vn/upload/rss/trangchu.rss"));
        arr_drawer.add(new Item_drawer("Bóng đá", "https://www.24h.com.vn/upload/rss/bongda.rss"));
        arr_drawer.add(new Item_drawer("An ninh", "https://www.24h.com.vn/upload/rss/anninhhinhsu.rss"));
        arr_drawer.add(new Item_drawer("Thời trang", "https://www.24h.com.vn/upload/rss/thoitrang.rss"));
        arr_drawer.add(new Item_drawer("Tài chính - BĐS", "https://www.24h.com.vn/upload/rss/taichinhbatdongsan.rss"));
        arr_drawer.add(new Item_drawer("Ẩm thực", "https://www.24h.com.vn/upload/rss/amthuc.rss"));
        arr_drawer.add(new Item_drawer("Làm đẹp", "https://www.24h.com.vn/upload/rss/lamdep.rss"));
        arr_drawer.add(new Item_drawer("Phim", "https://www.24h.com.vn/upload/rss/phim.rss"));
        arr_drawer.add(new Item_drawer("Giáo dục - du học", "https://www.24h.com.vn/upload/rss/giaoducduhoc.rss"));
        arr_drawer.add(new Item_drawer("Bạn trẻ - cuộc sống", "https://www.24h.com.vn/upload/rss/bantrecuocsong.rss"));
        arr_drawer.add(new Item_drawer("Du lịch", "https://www.24h.com.vn/upload/rss/dulich.rss"));
        arr_drawer.add(new Item_drawer("Công nghệ thông tin", "https://www.24h.com.vn/upload/rss/congnghethongtin.rss"));

    }
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_drawer, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.txt_drawer.setText(arr_drawer.get(position).getTen());
    }

    @Override
    public int getItemCount() {
        return arr_drawer.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_drawer;

        private TextView txt_drawer;

        public ItemHolder(View itemView) {
            super(itemView);
            txt_drawer = itemView.findViewById(R.id.txt_name_drawer);
            ll_drawer = itemView.findViewById(R.id.ll_drawer);
            ll_drawer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemListner.OnClick(arr_drawer.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnClickItemListner {
        void OnClick(Item_drawer item_drawer);
    }
}
