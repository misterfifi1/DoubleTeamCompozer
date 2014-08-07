package com.agency.trium.doubleteamcompozer.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.agency.trium.doubleteamcompozer.R;
import com.agency.trium.doubleteamcompozer.modele.Player;
import com.agency.trium.doubleteamcompozer.modele.Team;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by dev-w8-gfi on 06/08/2014.
 */
public class StickyAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    ArrayList<Player> players;
    ArrayList<Team> teams;
    private LayoutInflater inflater;

    public StickyAdapter(Context context, ArrayList<Player> players, ArrayList<Team> teams) {
        inflater = LayoutInflater.from(context);
        this.players = players;
        this.teams = teams;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_player, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.note = (RatingBar) convertView.findViewById(R.id.ratingBar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(players.get(position).getPseudo());
        holder.note.setRating(players.get(position).getNote());
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        int team = players.get(position).getEquipe();
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }


        //set header text as first char in name
        String headerText = teams.get(team).getName();
        Log.e("HeaderText",headerText);
        holder.text.setText(headerText);

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return teams.get(players.get(position).getEquipe()).getName().hashCode();
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
        RatingBar note;
    }
}
