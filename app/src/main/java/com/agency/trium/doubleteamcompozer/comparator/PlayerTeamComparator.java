package com.agency.trium.doubleteamcompozer.comparator;

import com.agency.trium.doubleteamcompozer.modele.Player;

import java.util.Comparator;

/**
 * Created by dev-w8-gfi on 07/08/2014.
 */
public class PlayerTeamComparator implements Comparator<Player>{

    @Override
    public int compare(Player p1, Player p2)

    {
        return p1.getEquipe() > p2.getEquipe() ? +1 : p1.getEquipe() < p2.getEquipe() ? -1 : 0;
    }
}
