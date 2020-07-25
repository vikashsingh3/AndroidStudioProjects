package com.example.educational_app.game_fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.educational_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        imageView = view.findViewById(R.id.imageView);

        if (savedInstanceState != null) {
            String encodedString = savedInstanceState.getString("bitmap");
//            running = savedInstanceState.getBoolean("running");
            setImageView(StringToBitMap(encodedString));
        }
        return view;
    }

    private Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void setImageView(Bitmap image_bitmap) {
        imageView.setImageBitmap(image_bitmap);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("bitmap", String.valueOf(((BitmapDrawable) imageView.getDrawable()).getBitmap()));
    }
}
