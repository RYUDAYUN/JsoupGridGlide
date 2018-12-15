package dayun.jsoupgrid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<String> imageArray;


    public GridAdapter(Activity act,ArrayList<String> imageArray) {
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imageArray = imageArray;


    }
    @Override
    public int getCount() {
        return imageArray.size();
    }

    @Override
    public Object getItem(int position) {
        return imageArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item, parent, false);
        } else {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewResult);
            imageView.setAdjustViewBounds(true);

            Glide.with(convertView).load(imageArray.get(position)).into(imageView);

        }
        return convertView;
    }
}