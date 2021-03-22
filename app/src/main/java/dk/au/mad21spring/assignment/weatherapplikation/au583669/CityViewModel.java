package dk.au.mad21spring.assignment.weatherapplikation.au583669;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
// inspired by this: https://stackoverflow.com/questions/54555630/how-to-get-position-in-arraylist-using-viewmodel?fbclid=IwAR1fr73mLm8w8dfsbnN9CRXx5v_60gzaNtRQOWDikUc0WxLUL5_2VUol8bM
public class CityViewModel extends ViewModel {
    ArrayList<City> cities = new ArrayList<City>();

    private MutableLiveData<ArrayList<City>> listOfCity;
    private MutableLiveData<City> city;

    public LiveData<ArrayList<City>> loadCity() {
        if (listOfCity == null) {
            listOfCity = new MutableLiveData<ArrayList<City>>();

            cities.add(new City("Aarhus", "DK","-2", "100","Snow. Light breeze.", (float) 0, "Testing"));
            cities.add(new City("Helsinki", "FI","-17", "97","Gentle Breeze..", (float)0, ""));
            cities.add(new City("Melbourne", "AU","34", "27","Light rain. Gentle Breeze.", (float) 0, ""));
            cities.add(new City("Windhoek", "NA","28", "25","Clear sky. Light breeze.", (float) 0, ""));
            cities.add(new City("Singapore", "SG","27","66","Overcast clouds. Moderate breeze.", (float) 0, ""));
            cities.add(new City("Novosibirsk","RU","-17","96","Snow. Light breeze.", (float) 0, ""));
            cities.add(new City("Dubai","AE","23","54","Scattered clouds. Gentle Breeze.", (float) 0, ""));
            cities.add(new City("Thorshavn","FO","8","83","Light rain. Fresh Breeze.", (float) 0, ""));
            cities.add(new City("San Francisco","US","16","39","Clear sky. Light breeze.", (float) 0, ""));
            cities.add(new City("Suva","FJ","26","85","Moderate rain. Moderate breeze.", (float) 0, ""));

            listOfCity.setValue(cities);
        }
        return listOfCity;
    }

    public LiveData<City> getCity() {
        if (city == null) {
            city = new MutableLiveData<City>();
            city.setValue(new City("","","","","", (float) 0.0, null));
        }
        return city;
    }
    public City getCityByIndex(int index) {return cities.get(index);}

    public void updateCities(City addCity) {city.setValue(addCity);}

    public void setCityDataByIndex(int index, City tempCity) {
        cities.set(index, tempCity);
        listOfCity.setValue(cities);
    }
}
