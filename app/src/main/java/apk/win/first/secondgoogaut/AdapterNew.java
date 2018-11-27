package apk.win.first.secondgoogaut;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterNew extends RecyclerView.Adapter<AdapterNew.ViewHolder> {

    public ArrayList<Model> model;

    public AdapterNew(List<Model> model)
    {
        this.model = (ArrayList<Model>) model;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView description, id;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            description = view.findViewById(R.id.textView2_description);
            id = view.findViewById(R.id.textView_id);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.id.setText(String.valueOf(model.get(position).getId()));
        holder.description.setText(model.get(position).getDescription());
        Glide.with(holder.itemView.getContext()).load(model.get(position).getIcon()).into(holder.imageView);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        if (model == null)
            return 0;
        return model.size();
    }
}
