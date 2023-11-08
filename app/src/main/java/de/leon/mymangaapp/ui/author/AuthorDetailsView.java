package de.leon.mymangaapp.ui.author;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import de.leon.mymangaapp.R;

public class AuthorDetailsView extends LinearLayout {

    EditText authorNameText;
    EditText authorSeriesText;

    public AuthorDetailsView(Context context) {
        super(context);
        init(context);
    }

    public AuthorDetailsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_author_details, this);
        setOrientation(LinearLayout.VERTICAL);
        initElements();
    }

    private void initElements() {
        authorNameText = findViewById(R.id.authorNameText);
        authorNameText.setKeyListener(null);
        authorNameText.setText("");

        authorSeriesText = findViewById(R.id.authorSeriesText);
        authorSeriesText.setKeyListener(null);
        authorSeriesText.setText("0");
    }

    public void setAuthorName(String name) {
        authorNameText.setText(name);
    }

    public void setAuthorSeries(Integer series) {
        authorNameText.setText(series);
    }
}
