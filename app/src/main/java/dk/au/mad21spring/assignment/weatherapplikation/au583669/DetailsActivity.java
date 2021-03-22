package dk.au.mad21spring.assignment.weatherapplikation.au583669;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    public static final int EDIT_REQUEST = 201;

    //Init widgets
    ImageView imageFlagDetails;
    TextView detailsCity, detailsTemperature, detailsHumidity, detailsWeatherStatus, detailsUserRating, detailsUserNotes;
    Button backBtn, editBtn;

    //init object "City" used to pass data around
    private int flag, index;

    private City cityObj;
    private CityViewModel vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        vm = new ViewModelProvider(this).get(CityViewModel.class);
        cityObj = (City) getIntent().getSerializableExtra(Constant.CITY);
        if (vm.getCity().getValue().cityName !=""){
            cityObj = (City) vm.getCity().getValue();
        }
        index = getIntent().getIntExtra(Constant.INDEX, 0);
        flag = getIntent().getIntExtra(Constant.FLAG, 0);

        vm.getCity().observe(this, new Observer<City>() {
            @Override
            public void onChanged(City city) {
                cityObj = city;
                detailsCity.setText(cityObj.cityName);
                detailsTemperature.setText("Temp: "+ cityObj.temperature + "C");
                detailsHumidity.setText("Humidity: " + cityObj.humidity + "%");
                detailsWeatherStatus.setText("Weather: " + cityObj.weatherCondition);
                detailsUserRating.setText("User Rating: " + cityObj.rating.toString());
                detailsUserNotes.setText("User Notes: " + cityObj.userNotes);


            }
        });

        imageFlagDetails = findViewById(R.id.imageCountryFlagDetails);
        imageFlagDetails.setImageResource(flag);
        detailsCity = findViewById(R.id.CityNameDetails);
        detailsTemperature = findViewById(R.id.TemperatureDetails);
        detailsHumidity = findViewById(R.id.HumidityDetails);
        detailsWeatherStatus = findViewById(R.id.WeatherStatusDetails);

        detailsUserRating = findViewById(R.id.UserRatingDetails);
        detailsUserNotes = findViewById(R.id.UserNotesDetails);

        backBtn = findViewById(R.id.backDetails);
        editBtn = findViewById(R.id.editDetails);

        vm.updateCities(cityObj);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackClick();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditClick();
            }
        });
    }

    private void onEditClick() {
        Intent editIntent = new Intent(this, EditActivity.class);

        editIntent.putExtra(Constant.CITY_DATA_EDIT, cityObj);
        editIntent.putExtra(Constant.FLAG, flag);
        editIntent.putExtra(Constant.INDEX, index);
        startActivityForResult(editIntent, EDIT_REQUEST);
    }

    private void onBackClick() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_REQUEST){
            if (resultCode == RESULT_OK) {
                City city = (City) data.getSerializableExtra(Constant.CITY);

                index = data.getIntExtra(Constant.INDEX, 0);
                vm.updateCities(city);

                Intent i = new Intent();
                i.putExtra(Constant.CITY, city);
                i.putExtra(Constant.INDEX, index);
                setResult(RESULT_OK, i);
                finish();

            }else {
                onBackClick();
            }
        }
    }
}