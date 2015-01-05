package com.example.ytraore.listedecourse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {
    public static final int EGL_SWAP_BEHAVIOR= 0x00003093;

//Défiition des constantes pour distinguer les rubriques

    private static final int FRUIT = 0;
    private static final int LAITIERS = 1;
    private static final int LEGUMES = 2;
    private static final int BOUCHERIE = 3;
    private static final int POISSONNERIE = 4;
    private static final int BOULANGERIE = 5;
    private static final int CHARCUTERIE = 6;
    private static final int VOIR_PANIER =7;
    private static final int BIERES = 8;
    //private static final int DEMARRAGE = 9;
    //les boutons sont stockés dans un tableau
    private Button[] listeBoutons = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Afficher les composants graphiques définis dans le fichiers activity_main.xml
        setContentView(R.layout.activity_main);

        //Récupération d'un service Web depuis notre application
      //  new DLTask().execute("hhtp://fabrigli.fr/cours/example.json");

        //Stocker les boutons dans le tableau en fonction de l'id
//défini dans le fichier accueil.xml

        listeBoutons[FRUIT] = ((Button) this.findViewById(R.id.fruit));
        listeBoutons[LAITIERS] = ((Button) this.findViewById(R.id.laitiers));
        listeBoutons[LEGUMES] = ((Button) this.findViewById(R.id.legume));
        listeBoutons[BOUCHERIE] = ((Button) this.findViewById(R.id.boucherie));
        listeBoutons[POISSONNERIE] = ((Button) this.findViewById(R.id.poissonerie));
        listeBoutons[BOULANGERIE] = ((Button) this.findViewById(R.id.boulangerie));
        listeBoutons[CHARCUTERIE] = ((Button) this.findViewById(R.id.charcuterie));
        listeBoutons[VOIR_PANIER] = ((Button) this.findViewById(R.id.panier));
        listeBoutons[BIERES] = ((Button) this.findViewById(R.id.bieres));

//Chaque bouton écoute l'évènement onClick
        for (int i = 0; i < listeBoutons.length; i++) {
            listeBoutons[i].setOnClickListener(this);
        }
       // new DLTask().execute();
    }


//Action à réaliser lorsqu'un évènement onClick est entendu
    public void onClick(View v) {
        String msg="";

//Tester l'identifiant de l'objet ayant capturer
// l'évènement onClick et agir en conséquences
         Activity activite = null;
        switch (v.getId()){

            case R.id.fruit :
                msg="Choisir des fruits";
                activite= new RubriqueFruits();
                break;
            case R.id.laitiers:
                msg="Choisir des produits laitiers";
                activite= new RubriqueLaitiers();
                break;
            case R.id.legume :
                msg="Choisir des légumes";
                activite= new RubriqueLegumes();
                break;
            case R.id.boucherie:
                msg="Sélectionner des articles chez votre boucher";
                 activite= new RubriqueBoucherie();
                break;
            case R.id.poissonerie:
                msg="Sélectionner des articles chez votre poissonnier";
                activite= new RubriquePoissonerie();
                break;
            case R.id.boulangerie:
                msg="Sélectionner des articles chez votre boulanger";
                activite= new RubriqueBoulangerie();
                break;
            case R.id.charcuterie:
                msg="Sélectionner des articles chez votre charcutier";
                activite=  new RubriqueCharcuterie();
                break;
            case R.id.panier:
                msg="Ajouter dans le panier";

                break;
            case R.id.bieres:
                msg="bieres";
                activite= new Bieres();
                break;

        }

        if (activite != null) {
            Intent i= new Intent(getApplicationContext(), activite.getClass());
            startActivity(i);

        }
//Afficher le message correspondant au bouton sélectionné
        Toast msgT= Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        msgT.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
