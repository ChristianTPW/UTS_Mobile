package umn.ac.id;

import android.content.Context;
import android.content.Intent;
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
    private Context mContext;

    LaguAdapter(Context context, LinkedList<String> daftarLagu){
        mInflater = LayoutInflater.from(context);
        mDaftarLagu = daftarLagu;
        mContext = context;
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

        holder.laguItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, PlayerActivity.class);
                intent.putExtra("position", position);
                mContext.startActivity(intent);


            }
        });
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