package dk.au.mad21spring.assignment.weatherapplikation.au583669;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Inspired by the recycleView video from L3 - Demo 2: RecyclerView in action

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.RcvViewHolder> //implements RecyclerviewAdapter
{
    //Interface to handling when and city item is clicked
    public interface ICityClickListener {
        void onCityClicked(int index, int Imgflag);
    }

    private ICityClickListener listener;

    private ArrayList<City> listOfCities;

    public CityAdapter(ICityClickListener listener) {
        this.listener = listener;
    }

    public void updateListOfCities(ArrayList<City> lists) {
        listOfCities = lists;
        notifyDataSetChanged();
    }

    private int getImg(String country) {
        int imgID = 0;
        switch (country) {
            case "DK":
                imgID = R.drawable.dk;
                break;
            case "AE":
                imgID = R.drawable.ae;
                break;
            case "AU":
                imgID = R.drawable.au;
                break;
            case "FI":
                imgID = R.drawable.fi;
                break;
            case "FJ":
                imgID = R.drawable.fj;
                break;
            case "FO":
                imgID = R.drawable.fo;
                break;
            case "JP":
                imgID = R.drawable.jp;
                break;
            case "NA":
                imgID = R.drawable.na;
                break;
            case "RU":
                imgID = R.drawable.ru;
                break;
            case "SG":
                imgID = R.drawable.sg;
                break;
            case "US":
                imgID = R.drawable.us;
                break;
        }
        return imgID;
    }

    @NonNull
    @Override
    public RcvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.city_list, parent, false);
        //Inflater stored in RcvViewHolder to return the view - the city list in adapter
        return new RcvViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.RcvViewHolder holder, int position) {

        holder.txtCity.setText(listOfCities.get(position).cityName);
        holder.txtWCondition.setText(listOfCities.get(position).weatherCondition);
        holder.txtTemp.setText(listOfCities.get(position).temperature);
        holder.txtRating.setText(listOfCities.get(position).rating.toString());
        String country = listOfCities.get(position).countries;
        holder.imgFlag.setImageResource(getImg(country));
    }


    @Override
    public int getItemCount() {
        return listOfCities.size();
    }

    public class RcvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Init activity
        ImageView imgFlag;
        TextView txtCity, txtTemp, txtWCondition, txtRating;

        ICityClickListener listener;

        public RcvViewHolder(@NonNull View itemView, ICityClickListener cityClickListener) {
            super(itemView);

            txtCity = itemView.findViewById(R.id.txtCityName);
            txtTemp = itemView.findViewById(R.id.txtTemp);
            txtWCondition = itemView.findViewById(R.id.txtCondition);
            txtRating = itemView.findViewById(R.id.txtRate);
            imgFlag = itemView.findViewById(R.id.imgCity);

            listener = cityClickListener;


            //set click listener for the whole list item
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            String country = listOfCities.get(getAdapterPosition()).countries;
            listener.onCityClicked(getAdapterPosition(), getImg(country));
        }
    }
}