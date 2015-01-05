package com.example.ytraore.listedecourse;

import android.app.Activity;
import android.content.Context;
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
 * Created by ytraore on 13/12/2014.
 */

public class RubriqueLegumes extends Activity implements View.OnClickListener{

    // Déclaration d’objets de type CheckBox comme variables de classe
    private CheckBox chkCarotte, chkConcombre, chkPiment, chkPoivron,chkPommeDeTerre,chkSalade;
    private Button btnSave;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legumes);
        //  Associer les CheckBox définies dans le fichier fruits.xml avec les variables de classe
        String listeLegumes = lireListeLegumes();

        chkCarotte = (CheckBox) findViewById(R.id.carotte);
        chkConcombre = (CheckBox) findViewById(R.id.concombre);
        chkPiment= (CheckBox) findViewById(R.id.piment);
        chkPoivron = (CheckBox) findViewById(R.id.poivron);
        chkPommeDeTerre = (CheckBox) findViewById(R.id.pommedeterre);
        chkSalade=(CheckBox) findViewById(R.id.salade);

        majCheckBox(listeLegumes);
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
            // Si mot[i] vaut "carotte", cocher la case correspondante
            if (mot[i].equals("carotte")){
                chkCarotte.setChecked(true);
            }
            // Si mot[i] vaut "Concombre", cocher la case correspondante
            else if (mot[i].equals("concombre")){
                chkConcombre.setChecked(true);
            }
            // Si mot[i] vaut "piment", cocher la case correspondante
            else if (mot[i].equals("piment")){
                chkPiment.setChecked(true);
            }
            // Si mot[i] vaut "poivron", cocher la case correspondante
            else if (mot[i].equals("poivron")){
                chkPoivron.setChecked(true);
            }
            // Si mot[i] vaut "pommedeterre", cocher la case correspondante
            else if (mot[i].equals("pommedeterre")){
                chkPommeDeTerre.setChecked(true);
            }
            // Si mot[i] vaut "salade", cocher la case correspondante
            else if (mot[i].equals("salade")){
                chkSalade.setChecked(true);
            }
            i++;
        }

    }
    // Lorsqu’on clique sur le bouton Enregistrer
    public void onClick(View v) {
        Toast toaster;
        String msg="";
        // Traiter l’état des CheckBox
        if (chkCarotte.isChecked())
            msg+="carotte+";
        if (chkConcombre.isChecked())
            msg+=" Concombre+";
        if (chkPiment.isChecked())
            msg+=" Piment+";
        if (chkPoivron.isChecked())
            msg+=" Poivron+";
        if (chkPommeDeTerre.isChecked())
            msg+=" Pomme De Terre+";
        if (chkSalade.isChecked())
            msg+=" Salade+";
        // Afficher un message éphémère s’il n’est pas vide
        if (!msg.equals("")){
            String msgToast = msg.replace("+", "  ");
            toaster = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toaster.show();
        }
        // Mémoriser les fruits sélectionnés
        ecrireListeLegumes(msg);
        // Fermer l’activité en cours
        fermerLesLegumes();
    }

    public void ecrireListeLegumes(String tmp) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("Legumes.json", Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'écriture ...");
        }

    }

    public String lireListeLegumes() {
        FileInputStream fis;
        String  data="";
        try {
            fis= openFileInput("Laitiers.txt");
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
    public void fermerLesLegumes(){
        this.finish();
    }



}

