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
public class RubriqueBoulangerie extends Activity implements View.OnClickListener {

    // Déclaration d’objets de type CheckBox comme variables de classe
    private CheckBox chkBaguette, chkPainDemie, chkPainComplet, chkPains,chkBrioche,chkBiscottes;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boulangerie);
        //  Associer les CheckBox définies dans le fichier fruits.xml avec les variables de classe
        String listeBoulangerie = lireListeBoulangerie();

        chkBaguette = (CheckBox) findViewById(R.id.baguette);
        chkPainDemie = (CheckBox) findViewById(R.id.paindemie);
        chkPainComplet= (CheckBox) findViewById(R.id.paincomplet);
        chkPains = (CheckBox) findViewById(R.id.pains);
        chkBrioche = (CheckBox) findViewById(R.id.brioche);
        chkBiscottes=(CheckBox) findViewById(R.id.biscottes);

        majCheckBox(listeBoulangerie);
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
            // Si mot[i] vaut "baguette", cocher la case correspondante
            if (mot[i].equals("baguette")){
                chkBaguette.setChecked(true);
            }
            // Si mot[i] vaut "paindemie", cocher la case correspondante
            else if (mot[i].equals("paindemie")){
                chkPainDemie.setChecked(true);
            }
            // Si mot[i] vaut "paincomplet", cocher la case correspondante
            else if (mot[i].equals("paincomplet")){
                chkPainComplet.setChecked(true);
            }
            // Si mot[i] vaut "pains", cocher la case correspondante
            else if (mot[i].equals("pains")){
                chkPains.setChecked(true);
            }
            // Si mot[i] vaut "brioche", cocher la case correspondante
            else if (mot[i].equals("brioche")){
                chkBrioche.setChecked(true);
            }
            // Si mot[i] vaut "biscottes", cocher la case correspondante
            else if (mot[i].equals("biscottes")){
                chkBiscottes.setChecked(true);
            }
            i++;
        }

    }
    // Lorsqu’on clique sur le bouton Enregistrer
    public void onClick(View v) {
        Toast toaster;
        String msg="";
        // Traiter l’état des CheckBox
        if (chkBaguette.isChecked())
            msg+=" Baguette+";
        if (chkPainDemie.isChecked())
            msg+=" Pain Demie+";
        if (chkPainComplet.isChecked())
            msg+=" Pain Complet+";
        if (chkPains.isChecked())
            msg+=" Pain+";
        if (chkBrioche.isChecked())
            msg+=" Brioche+";
        if (chkBiscottes.isChecked())
            msg+=" Biscottes+";
        // Afficher un message éphémère s’il n’est pas vide
        if (!msg.equals("")){
            String msgToast = msg.replace("+", " ");
            toaster = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toaster.show();
        }
        // Mémoriser les fruits sélectionnés
        ecrireListeBoulangerie(msg);
        // Fermer l’activité en cours
        fermerBoulangerie();
    }

    public void ecrireListeBoulangerie(String tmp) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("Boulangerie.json", Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'écriture ...");
        }

    }

    public String lireListeBoulangerie() {
        FileInputStream fis;
        String  data="";
        try {
            fis= openFileInput("Boulangerie.txt");
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
    public void fermerBoulangerie(){
        this.finish();
    }

}
