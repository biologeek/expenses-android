package io.biologeek.expenses.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import io.biologeek.expenses.R;
import io.biologeek.expenses.api.beans.Operation;

/**
 * Created by xavier on 07/06/18.
 */


public class OperationAdapter extends android.widget.ArrayAdapter<Operation> {
    private final Operation[] objects;
    private final Context context;

    public static final Integer DEFAULT_CATEGORY_PICTURE = 0;
    SimpleDateFormat DATE_PARSER = new SimpleDateFormat("dd/MM/yyyy");

    public OperationAdapter(@NonNull Context context, @NonNull Operation[] objects) {
        super(context, -1, objects);
        this.context = context;
        this.objects = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.content_operation_list, parent, false);
        TextView amount = view.findViewById(R.id.amount);
        ImageView image = view.findViewById(R.id.picture);
        TextView date = view.findViewById(R.id.date);
        TextView category = view.findViewById(R.id.category);
        TextView subCategory = view.findViewById(R.id.subcategory);

        Operation currentOperation = objects[position];
        if (currentOperation.getCategory() == null || currentOperation.getCategory().getPictureId() == 0)
            image.setImageResource(DEFAULT_CATEGORY_PICTURE);
        else
            image.setImageDrawable(context.getResources().getDrawable(currentOperation.getCategory().getPictureId(), context.getTheme()));

        amount.setText(currentOperation.getAmount() == null ? "0" : currentOperation.getAmount().toString());
        date.setText(currentOperation.getEffectiveDate() == null ? "" : DATE_PARSER.format(currentOperation.getEffectiveDate()));
        if (currentOperation.getNomenclature() != null) {
            if (currentOperation.getNomenclature().size() > 1)
                subCategory.setText(currentOperation.getNomenclature().get(1).getName());
            category.setText(currentOperation.getNomenclature().get(0).getName());
        }
        return view;
    }
}
