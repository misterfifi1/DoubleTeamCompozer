package com.agency.trium.doubleteamcompozer.utils;

import android.util.Log;

import com.agency.trium.doubleteamcompozer.comparator.PlayerMaxMinComparator;
import com.agency.trium.doubleteamcompozer.comparator.PlayerTeamComparator;
import com.agency.trium.doubleteamcompozer.modele.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by dev-w8-gfi on 07/08/2014.
 */
public class AlgoUtils {

    public static ArrayList<Player> partagerEnEquipe(ArrayList<Player> players, int nbEquipe) {
        ArrayList<Player> playersSort = sortMaxMin(players);
        ArrayList<Player> teams = new ArrayList<Player>();

        ArrayList<Integer> positions;
        // Log.e("initial playersSort", playersSort.toString());

        int count;

        Player tmp;
        Player ref;
        int team;
        int position;
        ArrayList<Integer> teamPossible = new ArrayList<Integer>();
        int[] notes = new int[nbEquipe];

        for (int i = 0; i < notes.length; i++)
            notes[i] = 0;

        count = 0;
        positions = new ArrayList<Integer>();

        for (int i = 0; i < nbEquipe; i++)
            positions.add(i);

        while (count < playersSort.size() && count < nbEquipe) {
            tmp = playersSort.get(count);
            position = RandomUtils.randomNumber(0, positions.size());
            team = positions.remove(position);
            tmp.setEquipe(team);
            notes[team] += tmp.getNote();
            teams.add(tmp);
            count++;
        }

        for (int i = 0; i < count; i++)
            playersSort.remove(0);


        boolean good;
        ArrayList<Player> playerTemp;
        int cmpt = 0;

        //Log.e("first step playersSort", playersSort.toString());

        while (!playersSort.isEmpty()) {
            //Log.e("playersSort", playersSort.toString());
            //Log.e("team", teams.toString());
            good = true;
            count = 1;
            playerTemp = new ArrayList<Player>();
            ref = playersSort.get(0);
            playerTemp.add(ref);

            while (count < playersSort.size() && good) {
                tmp = playersSort.get(count);

                if (tmp.getNote() == ref.getNote()) {
                    playerTemp.add(tmp);
                    count++;
                } else
                    good = false;
            }


            while (!playerTemp.isEmpty()) {
                if (teamPossible.isEmpty()) {
                    for (int i = 0; i < nbEquipe; i++)
                        teamPossible.add(i);
                }

                position = RandomUtils.randomNumber(0, playerTemp.size());
                tmp = playerTemp.remove(position);
                team = getMin(notes, teamPossible);

                for (int i = 0; i < teamPossible.size(); i++) {
                    if (teamPossible.get(i) == team) {
                        teamPossible.remove(i);
                        break;
                    }
                }

                notes[team] += tmp.getNote();
                tmp.setEquipe(team);
                teams.add(tmp);

            }


            for (int i = 0; i < count; i++)
                playersSort.remove(0);

            //Log.e("Après Remove playersSort", playersSort.toString());

        }

        return sortEquipe(teams);

    }

    public static ArrayList<Player> sortMaxMin(ArrayList<Player> players) {
        Collections.sort(players, new PlayerMaxMinComparator());
        return players;
    }

    public static ArrayList<Player> sortEquipe(ArrayList<Player> players) {
        Collections.sort(players, new PlayerTeamComparator());
        return players;
    }

    public static int getMin(int[] notes, ArrayList<Integer> teamPossible) {
        int min = 10000;

        ArrayList<Integer> minArray = new ArrayList<Integer>();


        for (int i = 0; i < notes.length; i++) {

            if (notes[i] < min && teamPossible.contains(i)) {
                minArray.clear();
                minArray.add(i);
                min = notes[i];
            } else {
                if (notes[i] == min && teamPossible.contains(i))
                    minArray.add(i);
            }
        }
        Log.e("Min array", minArray.toString());
        Log.e("teamPossible", teamPossible.toString());
        return minArray.get(RandomUtils.randomNumber(0, minArray.size()));
    }
}
