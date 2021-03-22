package dk.au.mad21spring.assignment.weatherapplikation.au583669;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    private TextView txtCity, txtRatingValue, txtUserNotes;
    private ImageView flagImage;

    private String cityName, userNoteValue;
    private int flag, index;
    private City cityToBeEdit;
    private float ratingValue = 0;

    private SeekBar seekBarRating;
    private Button btnCancel;
    private Button btnOK;

    CityViewModel vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        txtCity = findViewById(R.id.EditCityName);
        txtUserNotes = findViewById(R.id.editNotes);
        txtRatingValue = findViewById(R.id.txtRatingValue);

        seekBarRating = findViewById(R.id.slidebar);

        flagImage = findViewById(R.id.flagEdit);

        vm = new ViewModelProvider(this).get(CityViewModel.class);

        cityToBeEdit = (City) getIntent().getSerializableExtra(Constant.CITY_DATA_EDIT);
        if (vm.getCity().getValue().cityName != "") {
            cityToBeEdit = (City) vm.getCity().getValue();
        }
        index = getIntent().getIntExtra(Constant.INDEX, 0);
        flag = getIntent().getIntExtra(Constant.FLAG, 0);

        System.out.println(flag);

        cityName = cityToBeEdit.cityName;
        ratingValue = cityToBeEdit.rating;
        userNoteValue = cityToBeEdit.userNotes;

        flagImage.setImageResource(flag);
        System.out.println(flagImage);

        txtCity.setText(cityName);


        //Buttons:
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnCancel();
            }
        });
        btnOK = findViewById(R.id.btnOk);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnOK();
            }
        });

        seekBarRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ratingValue = progress * 0.5f;
                UpdateRatingScore(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        UpdateRatingScore(cityToBeEdit.rating.intValue() * 2);

    }

    private void OnOK() {
        userNoteValue = txtUserNotes.getText().toString();
        if (userNoteValue.equals("")) {
            userNoteValue = "There's no notes!";
        }

        City cityWithUpdate = cityToBeEdit;
        cityWithUpdate.userNotes = userNoteValue;
        cityWithUpdate.rating = ratingValue;

        Intent intentOk = new Intent();
        intentOk.putExtra(Constant.CITY, cityWithUpdate);
        intentOk.putExtra(Constant.INDEX, index);

        setResult(RESULT_OK, intentOk);
        finish();
    }

    private void OnCancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void UpdateRatingScore(int progress) {
        txtUserNotes.setText(userNoteValue);
        txtRatingValue.setText("My Rating: " + ratingValue);
        seekBarRating.setProgress(progress);
    }

}