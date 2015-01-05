package com.example.ytraore.listedecourse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * Created by ytraore on 10/12/2014.
 */
public class RubriquePoissonerie extends Activity implements View.OnClickListener {

    // Déclaration d’objets de type CheckBox comme variables de classe
    private CheckBox chkCabillaud1, chkColin, chkSaumon, chkPoissonPane,chkSurimi,chkCrevettes;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poissonnerie);
        //  Associer les CheckBox définies dans le fichier fruits.xml avec les variables de classe
        String listePoissonnerie = lireListePoissonnerie();

        chkCabillaud1 = (CheckBox) findViewById(R.id.cabillaud1);
        chkColin = (CheckBox) findViewById(R.id.colin);
        chkSaumon= (CheckBox) findViewById(R.id.saumon);
        chkPoissonPane = (CheckBox) findViewById(R.id.poissonpane);
        chkSurimi = (CheckBox) findViewById(R.id.surimi);
        chkCrevettes=(CheckBox) findViewById(R.id.crevettes);

        majCheckBox(listePoissonnerie);
        btnSave = (Button) findViewById(R.id.Save);
        // Mettre en place d’un écouteur d’événements sur le bouton Enregistrer
        btnSave.setOnClickListener(this);
    }
    public void majCheckBox(String tmp){
        // Créer un objet st qui détecte des champs de mots et des séparateurs "+"
        StringTokenizer st = new StringTokenizer(tmp,"+");
        int i=0;
        String mot[] = new String[st.countTokens()];
        // Tant qu’il y a des champs séparés par des "+"
        while (st.hasMoreTokens()) {
            // Enregistrer le champs courant dans le tableau mot à l’indice i
            mot[i] = st.nextToken();
            // Si mot[i] vaut "cabillaud", cocher la case correspondante
            if (mot[i].equals("cabillaud1")){
                chkCabillaud1.setChecked(true);
            }
            // Si mot[i] vaut "colin", cocher la case correspondante
            else if (mot[i].equals("colin")){
                chkColin.setChecked(true);
            }
            // Si mot[i] vaut "saumo", cocher la case correspondante
            else if (mot[i].equals("saumo")){
                chkSaumon.setChecked(true);
            }
            // Si mot[i] vaut "poissonpane", cocher la case correspondante
            else if (mot[i].equals("poissonpane")){
                chkPoissonPane.setChecked(true);
            }
            // Si mot[i] vaut "surimi", cocher la case correspondante
            else if (mot[i].equals("surimi")){
                chkSurimi.setChecked(true);
            }
            // Si mot[i] vaut "crevettes", cocher la case correspondante
            else if (mot[i].equals("crevettes")){
                chkCrevettes.setChecked(true);
            }
            i++;
        }

    }
    // Lorsqu’on clique sur le bouton Enregistrer
    public void onClick(View v) {
        Toast toaster;
        String msg="";
        // Traiter l’état des CheckBox
        if (chkCabillaud1.isChecked())
            msg+=" Cabillaud+";
        if (chkColin.isChecked())
            msg+=" Colin+";
        if (chkSaumon.isChecked())
            msg+=" Saumon+";
        if (chkPoissonPane.isChecked())
            msg+=" Poisson Pane+";
        if (chkSurimi.isChecked())
            msg+=" Surimi+";
            if (chkCrevettes.isChecked())
            msg+=" Crevettes+";
        // Afficher un message éphémère s’il n’est pas vide
        if (!msg.equals("")){
            String msgToast = msg.replace("+", " ");
            toaster = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toaster.show();
        }
        // Mémoriser les fruits sélectionnés
        ecrireListePoissonnerie(msg);
        // Fermer l’activité en cours
        fermerPoisonnerie();
    }

    public void ecrireListePoissonnerie(String tmp) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("Poissonnerie.json", Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'écriture ...");
        }

    }

    public String lireListePoissonnerie() {
        FileInputStream fis;
        String  data="";
        try {
            fis= openFileInput("Poissonnerie.txt");
            char[] charLus = new char[255];
            InputStreamReader isr = new InputStreamReader(fis);
            isr.read(charLus);
            data = new String(charLus);
            fis.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur de lecture ...");
        }
        return data;
    }
    public void fermerPoisonnerie(){
        this.finish();
    }

}
