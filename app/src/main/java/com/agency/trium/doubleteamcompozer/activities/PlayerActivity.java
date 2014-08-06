package com.agency.trium.doubleteamcompozer.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agency.trium.doubleteamcompozer.R;
import com.agency.trium.doubleteamcompozer.adapters.ObjectAdapter;
import com.agency.trium.doubleteamcompozer.modele.Player;
import com.agency.trium.doubleteamcompozer.modele.Team;
import com.agency.trium.doubleteamcompozer.views.cell.PlayerCell;

import java.util.ArrayList;


public class PlayerActivity extends Activity implements View.OnClickListener {

    private ArrayList<Player> players;
    private ArrayList<Team> teams;
    private ObjectAdapter<Player> playerAdapter;
    private ListView list;
    TextView playerCount;
    int mplayerCount;
    int numberTeam = 2;

    public static final String LISTPLAYER = "LISTPLAYER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.je_verrais_apres);
        load();
        perform();
        addListerners();
    }

    private void perform() {

        teams = (ArrayList<Team>)getIntent().getExtras().getSerializable(EquipeActivity.LIST_TEAM);
        numberTeam = teams.size();

        for(int i= 0; i < numberTeam; i++)
            players.add(new Player());

        playerAdapter.notifyDataSetChanged();
        setCount();

    }


    private void load() {
        list = (ListView) findViewById(R.id.listView);
        players = new ArrayList<Player>();
        playerAdapter = new ObjectAdapter<Player>(this, players, R.layout.cell_player, PlayerCell.class);
        list.setAdapter(playerAdapter);
    }

    private void addListerners() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.player, menu);
        View notif = menu.findItem(R.id.action_add_player).getActionView();
        playerCount = (TextView) notif.findViewById(R.id.player_count);
        playerCount.setText(String.valueOf(mplayerCount));
        notif.setOnClickListener(this);

        return true;
    }

    /**
     * Permet de mettre à jour le badge du nombre de joueurs
     */
    public void setCount() {
        if (players != null)
            mplayerCount = players.size();
        else
            mplayerCount = 0;
        invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_valid:
                nextStep();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_add_player:
                players.add(new Player());
                playerAdapter.notifyDataSetChanged();
                setCount();
                break;
        }
    }


    public void removePlayer(int position) {
        if (mplayerCount != numberTeam) {
            players.remove(position);
            playerAdapter.notifyDataSetChanged();
            playerAdapter.notifyDataSetInvalidated();
            setCount();
        } else {
            Toast.makeText(this, numberTeam+ " "+getString(R.string.nombre_joueur_minimum), Toast.LENGTH_SHORT).show();
        }
    }

    private void nextStep() {
        checkAllNamePlayer();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EquipeActivity.LIST_TEAM,teams);
        intent.putExtra(LISTPLAYER,players);
        startActivity(intent);
    }

    private void checkAllNamePlayer() {
        int playerWithoutName = 0;
        for (Player player : players) {
            if (player.getPseudo() == null) {
                player.setPseudo(getString(R.string.pseudo) + " " + (playerWithoutName+1));
                player.setEquipe(0);
                playerWithoutName++;
            }
        }
    }
}
