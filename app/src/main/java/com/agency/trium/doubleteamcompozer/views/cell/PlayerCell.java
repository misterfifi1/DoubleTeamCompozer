package com.agency.trium.doubleteamcompozer.views.cell;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.agency.trium.doubleteamcompozer.R;
import com.agency.trium.doubleteamcompozer.activities.PlayerActivity;
import com.agency.trium.doubleteamcompozer.modele.Player;


/**
 * Created by kevin on 21/06/14.
 */
public class PlayerCell extends ViewCell<Player> implements View.OnLongClickListener {

    EditText pseudo;
    RatingBar ratingBar;

    @Override
    public void animer() {

    }

    @Override
    public void construire() {
    }

    @Override
    public void charger() {
        pseudo = (EditText) findViewById(R.id.pseudo);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
    }

    @Override
    public void remplir() {
    }

    @Override
    public void ajouterListeners() {
        pseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                getObject().setPseudo(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        getView().setOnLongClickListener(this);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                getObject().setNote((int)v);
            }
        });
    }

    @Override
    public boolean onLongClick(View view) {
        ((PlayerActivity)getContext()).removePlayer(getPosition());
        return false;
    }
}
