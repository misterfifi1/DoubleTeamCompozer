package com.agency.trium.doubleteamcompozer.views.cell;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.agency.trium.doubleteamcompozer.R;
import com.agency.trium.doubleteamcompozer.activities.EquipeActivity;
import com.agency.trium.doubleteamcompozer.modele.Player;
import com.agency.trium.doubleteamcompozer.modele.Team;
import com.agency.trium.doubleteamcompozer.utils.RandomUtils;

import org.w3c.dom.Text;


/**
 * Created by kevin on 21/06/14.
 */
public class TeamCell extends ViewCell<Team> implements View.OnLongClickListener {

    EditText nameTeam;
    TextView number;

    @Override
    public void animer() {

    }

    @Override
    public void construire() {
    }

    @Override
    public void charger() {
        nameTeam = (EditText) findViewById(R.id.nameTeam);
        number = (TextView) findViewById(R.id.number_team);
    }

    @Override
    public void remplir() {
        if (getObject() != null) {
            this.number.setText(getPosition() + "");
            this.number.setBackgroundColor(getContext().getResources().getColor(getObject().getColorId()));
        }
    }

    @Override
    public void ajouterListeners() {
        nameTeam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                getObject().setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        getView().setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        ((EquipeActivity) getContext()).removeTeam(getPosition());
        return false;
    }
}
