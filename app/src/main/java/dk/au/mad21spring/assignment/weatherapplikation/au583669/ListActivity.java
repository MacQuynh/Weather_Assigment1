package dk.au.mad21spring.assignment.weatherapplikation.au583669;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity implements CityAdapter.ICityClickListener{

    public static final int DETAILS_REQUEST = 100;

    //Widgets
    private RecyclerView rcvView;
    private CityAdapter adapter;

    private Button exitBtn;

    private CityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //ViewModel
        vm = new ViewModelProvider(this).get(CityViewModel.class);
        vm.loadCity().observe(this, new Observer<ArrayList<City>>() {
            @Override
            public void onChanged(ArrayList<City> cities) {
                adapter.updateListOfCities(cities);
            }
        });

        adapter = new CityAdapter(this);
        rcvView = findViewById(R.id.rcvCityList);
        rcvView.setLayoutManager(new LinearLayoutManager(this));
        rcvView.setAdapter(adapter);


        createExitBtn();
    }

    private void createExitBtn() {
        exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(v -> {
            finish();
            System.exit(0);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DETAILS_REQUEST) {
            if (resultCode == RESULT_OK) {
                City tempCity = (City) data.getSerializableExtra(Constant.CITY); //Caster CityObj
                int index = data.getIntExtra(Constant.INDEX, 0);
                vm.setCityDataByIndex(index, tempCity);
            } else
            { Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();}
        }
    }

    @Override
    public void onCityClicked(int index, int Imgflag) {

        Intent i = new Intent(this, DetailsActivity.class);
        City cityAtTheMoment = vm.getCityByIndex(index);
        i.putExtra(Constant.INDEX, index);
        i.putExtra(Constant.CITY, cityAtTheMoment);
        i.putExtra(Constant.FLAG, Imgflag);
        startActivityForResult(i, DETAILS_REQUEST);


    }
}