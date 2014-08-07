package com.agency.trium.doubleteamcompozer.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agency.trium.doubleteamcompozer.R;
import com.agency.trium.doubleteamcompozer.adapters.ObjectAdapter;
import com.agency.trium.doubleteamcompozer.adapters.StickyAdapter;
import com.agency.trium.doubleteamcompozer.modele.Player;
import com.agency.trium.doubleteamcompozer.modele.Team;
import com.agency.trium.doubleteamcompozer.utils.AlgoUtils;
import com.agency.trium.doubleteamcompozer.views.cell.PlayerCell;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


public class MainActivity extends Activity implements View.OnClickListener {

    private ArrayList<Player> players;
    private ArrayList<Team> teams;
    private StickyAdapter stickyAdapter;
    private StickyListHeadersListView stickyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();
        perform();
        addListerners();
    }

    private void perform() {
    }


    private void load() {
        teams = (ArrayList<Team>) getIntent().getExtras().getSerializable(EquipeActivity.LIST_TEAM);
        players = (ArrayList<Player>) getIntent().getExtras().getSerializable(PlayerActivity.LISTPLAYER);

        stickyAdapter = new StickyAdapter(this, players, teams);
        stickyAdapter.notifyDataSetChanged();

        stickyList = (StickyListHeadersListView) findViewById(R.id.list);
        stickyList.setAdapter(stickyAdapter);

    }

    private void addListerners() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_action_refresh:
                reloadTeam();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reloadTeam() {
        ArrayList<Player> tmp = AlgoUtils.partagerEnEquipe(players, teams.size());
        players.clear();

        Log.e("tmp",tmp.toString());
        players.addAll(tmp);
        stickyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this,PlayerActivity.class);
        intent.putExtra(EquipeActivity.LIST_TEAM, teams);
        intent.putExtra(PlayerActivity.LISTPLAYER, players);
        startActivity(intent);
        this.overridePendingTransition(0, 0);
    }
}
