package com.prashant.RoomDemo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.prashant.RoomDemo.models.NearbyPlace;

import java.util.List;

/**
 * Created by Prashant on 12/03/18.
 */

public class PlacesListAdapter extends RecyclerView.Adapter<PlacesListAdapter.PlaceViewHolder> {

    List<NearbyPlace> placeList;

    public PlacesListAdapter(List<NearbyPlace> placeList) {
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {

        public PlaceViewHolder(View itemView) {
            super(itemView);
        }
    }
}
