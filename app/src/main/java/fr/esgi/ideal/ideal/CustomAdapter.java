package fr.esgi.ideal.ideal;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<objetEnVente> implements View.OnClickListener{

    private ArrayList<objetEnVente> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView txtNom;
        TextView txtDesc;
        TextView txtPrix;
        TextView txtLike;
        ImageView image;
    }

    public CustomAdapter(ArrayList<objetEnVente> data, Context context) {
        super(context, R.layout.row_item, data);
        Log.i("err","1 "+data);
        Log.i("err","1.1 "+context);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {
        Log.i("err","2");
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        objetEnVente objetEnVente=(objetEnVente)object;

        switch (v.getId())
        {
            case R.id.row_item_image:
                Snackbar.make(v, "Release date " +objetEnVente.getImage(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("err","3");
        // Get the data item for this position
        objetEnVente objetEnVente = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtNom = (TextView) convertView.findViewById(R.id.row_name);
            viewHolder.txtDesc = (TextView) convertView.findViewById(R.id.row_description);
            viewHolder.txtPrix = (TextView) convertView.findViewById(R.id.row_prix);
            viewHolder.txtLike = (TextView) convertView.findViewById(R.id.row_likes);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.row_item_image);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtNom.setText(objetEnVente.getName());
        viewHolder.txtDesc.setText(objetEnVente.getDescription());
        viewHolder.txtPrix.setText(objetEnVente.getPrix());
        viewHolder.txtLike.setText(objetEnVente.getLike());
        viewHolder.image.setOnClickListener(this);
        viewHolder.image.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}