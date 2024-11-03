package fpoly.kienpdph44811.duanmau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.kienpdph44811.duanmau.Models.Top;
import fpoly.kienpdph44811.duanmau.R;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder>{
    ArrayList<Top> list;

    public TopAdapter(ArrayList<Top> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top , null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Top top = list.get(position);
        holder.tvTopSL.setText("Số Lần Mượn :"+String.valueOf(top.getSoLuong()));
        holder.tvTopName.setText("Tên Sách :"+top.getTenSach());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTopName , tvTopSL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTopName = itemView.findViewById(R.id.tvTopName);
            tvTopSL = itemView.findViewById(R.id.tvTopSl);
        }
    }
}
