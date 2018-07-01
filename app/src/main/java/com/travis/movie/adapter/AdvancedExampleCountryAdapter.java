package com.travis.movie.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.travis.movie.activity.MovieDetail;
import com.travis.movie.activity.MovieMain;
import com.travis.movie.extra.AdvancedExampleCountryPickerBox;
import com.travis.movie.model.AdvancedExampleCountryPOJO;
import com.travis.movie.R;
import com.travis.movie.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThisIsNSH on 6/28/2018.
 */
public class AdvancedExampleCountryAdapter extends ArrayAdapter<AdvancedExampleCountryPOJO> {

    private List<AdvancedExampleCountryPOJO> items;
    private Context context;

    public AdvancedExampleCountryAdapter(@NonNull Context context, int resource, @NonNull List<AdvancedExampleCountryPOJO> objects) {
        super(context, resource, objects);
        this.items = objects;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AdvancedExampleCountryAdapter.ViewHolder holder;

        if (null == convertView) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert vi != null;
            convertView = vi.inflate(R.layout.advanced_example_country_list_item, parent, false);
            holder = new AdvancedExampleCountryAdapter.ViewHolder();
            holder.text = convertView.findViewById(R.id.custom_cell_text);
            convertView.setTag(holder);
        } else {
            holder = (AdvancedExampleCountryAdapter.ViewHolder) convertView.getTag();
        }
        if (null != holder) {
            holder.text.setText(items.get(position).getTitle());
            SharedPreferences sharedpreferences = context.getSharedPreferences("year", Context.MODE_PRIVATE);
            SharedPreferences.Editor pref = sharedpreferences.edit();
            pref.putString("yearSelected",items.get(position).getTitle());
            pref.commit();
        }
        return convertView;
    }

    private class ViewHolder {
        TextView text;
    }
}
