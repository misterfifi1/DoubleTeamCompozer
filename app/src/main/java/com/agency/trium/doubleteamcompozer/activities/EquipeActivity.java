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
import com.agency.trium.doubleteamcompozer.views.cell.TeamCell;

import java.util.ArrayList;


public class EquipeActivity extends Activity implements View.OnClickListener {

    private ArrayList<Team> teams;
    private ObjectAdapter<Team> teamAdapter;
    private ListView list;
    TextView teamCount;
    int mTeamCount;

    public static final String LIST_TEAM = "LISTTEAM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.je_verrais_apres);
        load();
        perform();
        addListerners();
    }

    private void perform() {

        if (teams.size() == 0) {
            teams.add(new Team());
            teams.add(new Team());
        }
        teamAdapter.notifyDataSetChanged();
        setCount();
    }


    private void load() {
        list = (ListView) findViewById(R.id.listView);
        if (getIntent() == null || getIntent().getExtras() == null || getIntent().getExtras().getSerializable(LIST_TEAM) == null) {
            teams = new ArrayList<Team>();
        } else {
            teams = (ArrayList<Team>) getIntent().getExtras().getSerializable(LIST_TEAM);
        }
        teamAdapter = new ObjectAdapter<Team>(this, teams, R.layout.cell_team, TeamCell.class);
        list.setAdapter(teamAdapter);
    }

    private void addListerners() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.team, menu);
        View notif = menu.findItem(R.id.action_add_team).getActionView();
        teamCount = (TextView) notif.findViewById(R.id.team_count);
        teamCount.setText(String.valueOf(mTeamCount));
        notif.setOnClickListener(this);

        return true;
    }

    /**
     * Permet de mettre à jour le badge du nombre de joueurs
     */
    public void setCount() {
        if (teams != null)
            mTeamCount = teams.size();
        else
            mTeamCount = 0;
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

    private void nextStep() {
        checkAllNameTeam();
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(LIST_TEAM, teams);
        startActivity(intent);
        finish();
    }


    private void checkAllNameTeam() {
        int teamWithoutName = 0;
        for (Team team : teams) {
            if (team.getName() == null) {
                team.setName(getString(R.string.team) + " " + (teamWithoutName + 1));
                teamWithoutName++;
            }
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_add_team:
                teams.add(new Team());
                teamAdapter.notifyDataSetChanged();
                setCount();
                break;
        }
    }

    public void removeTeam(int position) {
        if (mTeamCount != 2) {
            teams.remove(position);
            teamAdapter.notifyDataSetChanged();
            teamAdapter.notifyDataSetInvalidated();
            setCount();
        } else {
            Toast.makeText(this, getString(R.string.nombre_joueur_minimum), Toast.LENGTH_SHORT).show();
        }
    }
}
