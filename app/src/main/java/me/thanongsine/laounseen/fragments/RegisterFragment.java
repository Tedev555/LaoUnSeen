package me.thanongsine.laounseen.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Objects;
import me.thanongsine.laounseen.Activities.MainActivity;
import me.thanongsine.laounseen.R;
import me.thanongsine.laounseen.utility.MyAlert;

public class RegisterFragment extends Fragment {
    private Toolbar toolbar;
    private Uri uri;
    private ImageView imageView;
    private boolean aBoolean = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        initToolbar(view);

        imageView = view.findViewById(R.id.imvPhoto);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Please Choose App"), 1);
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        getActivity();
        if (resultCode == Activity.RESULT_OK) {

            uri = data.getData();
            aBoolean = false;

            try {
                Bitmap bitmap = BitmapFactory.decodeStream(Objects.requireNonNull(getActivity()).getContentResolver().openInputStream(uri));
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 400, 300, true);
                imageView.setImageBitmap(bitmap1);

            }catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(getActivity(), "Please Choose Photo", Toast.LENGTH_SHORT).show();
        }
    }

    private void initToolbar(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Register");
        ((MainActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_main, new MainFragment())
                        .commit();
            }
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_register, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.upload_item) {
            uploadProcess();
        }

        return true;
    }

    private void uploadProcess() {
        EditText nameEditText = getView().findViewById(R.id.edt_name);
        EditText emailEditText = getView().findViewById(R.id.edt_email);
        EditText passwordEditText = getView().findViewById(R.id.edt_password);

        //Get Value From EditText
        String nameString = nameEditText.getText().toString().trim();
        String emailString = emailEditText.getText().toString().trim();
        String passwordString = passwordEditText.getText().toString().trim();

        MyAlert myAlert = new MyAlert(getActivity());
        //Check Choose photo
        if (aBoolean) {
            //None choose photo
            myAlert.normalDialog("Non Choose Photo",
                    "Please Choose Photo");
        } else if (nameString.isEmpty() || passwordString.isEmpty() || passwordString.isEmpty()) {
            myAlert.normalDialog("Have Space",
                    "Please Fill All Every Blank");
        } else {
            //No Space
        }
    }

}
