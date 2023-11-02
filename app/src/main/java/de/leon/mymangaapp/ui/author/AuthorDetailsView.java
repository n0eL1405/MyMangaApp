package de.leon.mymangaapp.ui.author;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;

import de.leon.mymangaapp.R;

public class AuthorDetailsView extends LinearLayout {

    EditText authorNameTextInput;

    public AuthorDetailsView(Context context, AttributeSet attributes) {
        super(context, attributes);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_author_details, this);
        setOrientation(LinearLayout.VERTICAL);
        initElements();
    }

    private void initElements() {
        authorNameTextInput = findViewById(R.id.authorNameTextInput);
        authorNameTextInput.setText("HalloHallo");
        authorNameTextInput.setKeyListener(null);
    }
}
