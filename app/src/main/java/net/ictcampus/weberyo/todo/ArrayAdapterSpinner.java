package net.ictcampus.weberyo.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ArrayAdapterSpinner extends ArrayAdapter<String> {
    String icons[], categories[];
    LayoutInflater mInfalter;

    //constructor, sets the gotten data to the instancevariables
    public ArrayAdapterSpinner(Context context, String[] icons, String[] categories){
        super(context, R.layout.spinner_row,  R.id.category_icon, categories);
        this.setDropDownViewResource(R.layout.spinner_row);
        this.icons = icons;
        this.categories = categories;
        mInfalter = LayoutInflater.from(context);
    }

    //class for set the categorie Spinner with the right Icon
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView==null){
            convertView = mInfalter.inflate(R.layout.spinner_row, parent, false);
            holder = new ViewHolder();
            holder.tv_icons = (FontAwesome) convertView.findViewById(R.id.icon);
            holder.tv_categories = (TextView)convertView.findViewById(R.id.category_icon);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tv_icons.setText(icons[position]);
        holder.tv_categories.setText(categories[position]);
        return convertView;
    }

    static class ViewHolder{
        FontAwesome tv_icons;
        TextView tv_categories;
    }
}
