package net.ictcampus.weberyo.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.ictcampus.weberyo.todo.FontAwesome;
import net.ictcampus.weberyo.todo.R;

import java.util.ArrayList;

public class ArrayAdapterDayviewrow extends ArrayAdapter<String> {
    String icons[], titles[];
    int[] priorities;
    boolean[] privacy;

    LayoutInflater inflater;

    //constructor, set the gotten datas to the intance variables
    public ArrayAdapterDayviewrow(Context context, String[] icons, String[] titles, boolean[] privacy, int[] priorities ){
        super(context, R.layout.dayview_row);
        this.icons = icons;
        this.titles = titles;
        this.privacy = privacy;
        this.priorities = priorities;
    }

    //sets the layout of a todo in the dayview
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if(v == null){
            inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.dayview_row, null);
        }

            FontAwesome cat = (FontAwesome) v.findViewById(R.id.dayview_category);
            View prio = v.findViewById(R.id.dayview_priority);
            TextView title = v.findViewById(R.id.dayview_title);
            if(!privacy[position]) {
                if (cat != null) {
                    cat.setText(getStringIdentifier(getContext(), icons[position]));
                }
                if (prio != null) {
                    switch (priorities[position]) {
                        case 1:
                            prio.setBackgroundColor(getContext().getResources().getColor(R.color.priority1));
                            break;
                        case 2:
                            prio.setBackgroundColor(getContext().getResources().getColor(R.color.priority2));
                            break;
                        case 3:
                            prio.setBackgroundColor(getContext().getResources().getColor(R.color.priority3));
                            break;
                        case 4:
                            prio.setBackgroundColor(getContext().getResources().getColor(R.color.priority4));
                            break;
                        case 5:
                            prio.setBackgroundColor(getContext().getResources().getColor(R.color.priority5));
                            break;

                    }
                }
                if (title != null){
                    title.setText(titles[position]);
                }
            }
            else {
                if(cat!=null){
                    cat.setText(getStringIdentifier(getContext(), "todo_private"));
                }

                if(prio != null){
                    prio.setBackgroundColor(getContext().getResources().getColor(R.color.classic_dark));
                }

                if(title != null){
                    title.setText("This Todo is private");
                }
            }
        return v;
    }

    public static int getStringIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }

}
