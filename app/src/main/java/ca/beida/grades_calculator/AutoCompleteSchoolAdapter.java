package ca.beida.grades_calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class AutoCompleteSchoolAdapter extends ArrayAdapter<SchoolItem> {
    private List<SchoolItem> schoolListFull;

    public AutoCompleteSchoolAdapter(@NonNull Context context, @NonNull List<SchoolItem> schoolList) {
        super(context, 0, Objects.requireNonNull(schoolList));
        schoolListFull = new ArrayList<>(schoolList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return schoolFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.school_autocomplete_row, parent, false
            );
        }
        TextView textViewName = convertView.findViewById(R.id.text_view_name);
        ImageView imageView = convertView.findViewById(R.id.image_view_school);

        SchoolItem schoolItem = getItem(position);
        if (schoolItem != null) {
            textViewName.setText(schoolItem.getSchoolName());
            imageView.setImageResource(schoolItem.getSchoolImage());
        }

        return convertView;
    }

    private Filter schoolFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<SchoolItem> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(schoolListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (SchoolItem item: schoolListFull) {
                    if (item.getSchoolName().toLowerCase().contains(filterPattern)) {
                       suggestions.add(item);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List)results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((SchoolItem) resultValue).getSchoolName();
        }
    };
}
