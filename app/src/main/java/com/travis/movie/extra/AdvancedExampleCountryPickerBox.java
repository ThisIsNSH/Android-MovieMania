package com.travis.movie.extra;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.directselect.DSAbstractPickerBox;
import com.travis.movie.model.AdvancedExampleCountryPOJO;
import com.travis.movie.R;

/**
 * Created by ThisIsNSH on 6/28/2018.
 */
public class AdvancedExampleCountryPickerBox extends DSAbstractPickerBox<AdvancedExampleCountryPOJO> {
    public static TextView text;
    private View cellRoot;

    public AdvancedExampleCountryPickerBox(@NonNull Context context) {
        this(context, null);
    }

    public AdvancedExampleCountryPickerBox(@NonNull Context context,
                                           @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdvancedExampleCountryPickerBox(@NonNull Context context,
                                           @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert mInflater != null;
        mInflater.inflate(R.layout.advanced_example_country_picker_box, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.text = findViewById(R.id.custom_cell_text);
        this.cellRoot = findViewById(R.id.custom_cell_root);
    }

    @Override
    public void onSelect(AdvancedExampleCountryPOJO selectedItem, int selectedIndex) {
        this.text.setText(selectedItem.getTitle());
    }

    @Override
    public View getCellRoot() {
        return this.cellRoot;
    }
}
