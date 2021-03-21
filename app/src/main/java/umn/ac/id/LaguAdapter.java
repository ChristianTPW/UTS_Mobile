package umn.ac.id;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class LaguAdapter extends RecyclerView.Adapter<LaguAdapter.LaguViewHolder> {

    private final LinkedList<String> mDaftarLagu;
    private LayoutInflater mInflater;

    LaguAdapter(Context context, LinkedList<String> daftarLagu){
        mInflater = LayoutInflater.from(context);
        mDaftarLagu = daftarLagu;
    }

    @NonNull
    @Override
    public LaguAdapter.LaguViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.daftarlagu_item, parent, false);
        return new LaguViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LaguAdapter.LaguViewHolder holder, int position) {
        String mCurrent = mDaftarLagu.get(position);
        holder.laguItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mDaftarLagu.size();
    }

    class LaguViewHolder extends RecyclerView.ViewHolder {
        public final TextView laguItemView;
        final LaguAdapter mAdapter;

        public LaguViewHolder(@NonNull View itemView, LaguAdapter adapter){
            super(itemView);
            laguItemView = itemView.findViewById(R.id.lagu);
            this.mAdapter = adapter;
        }
    }


}