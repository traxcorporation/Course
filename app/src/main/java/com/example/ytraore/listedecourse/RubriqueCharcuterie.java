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
public class RubriqueCharcuterie extends Activity implements View.OnClickListener {

    // Déclaration d’objets de type CheckBox comme variables de classe
    private CheckBox chkChipolata, chkJustinBridoux, chkSaucissonCochonou, chkSaucisseDeToulouse,chkMerguez,chkJambon;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charcuterie);
        //  Associer les CheckBox définies dans le fichier fruits.xml avec les variables de classe
        String listeCharcuterie = lireListeCharcuterie();

        chkChipolata = (CheckBox) findViewById(R.id.chipolata);
        chkJustinBridoux = (CheckBox) findViewById(R.id.justinbridoux);
        chkSaucisseDeToulouse= (CheckBox) findViewById(R.id.saucissedetoulouse);
        chkSaucissonCochonou = (CheckBox) findViewById(R.id.saucissoncochonou);
        chkMerguez=(CheckBox) findViewById(R.id.merguez);
        chkJambon= (CheckBox) findViewById(R.id.jambon);

        majCheckBox(listeCharcuterie);
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
            // Si mot[i] vaut "Chipolata", cocher la case correspondante
            if (mot[i].equals("chipolata")){
                chkChipolata.setChecked(true);
            }
            // Si mot[i] vaut "JustinBridoux", cocher la case correspondante
            else if (mot[i].equals("justinbridoux")){
                chkJustinBridoux.setChecked(true);
            }
            // Si mot[i] vaut "saucissoncochonou", cocher la case correspondante
            else if (mot[i].equals("saucissoncochonou")){
                chkSaucissonCochonou.setChecked(true);
            }
            // Si mot[i] vaut "SaucisseDeToulouse", cocher la case correspondante
            else if (mot[i].equals("saucissedetoulouse")){
                chkSaucisseDeToulouse.setChecked(true);
            }
            // Si mot[i] vaut "Merguez", cocher la case correspondante
            else if (mot[i].equals("merguez")){
                chkMerguez.setChecked(true);
            }
            // Si mot[i] vaut "Jambon", cocher la case correspondante
            else if (mot[i].equals("jambon")){
                chkJambon.setChecked(true);
            }
            i++;
        }

    }
    // Lorsqu’on clique sur le bouton Enregistrer
    public void onClick(View v) {
        Toast toaster;
        String msg="";
        // Traiter l’état des CheckBox
        if (chkChipolata.isChecked())
            msg+=" Chipolata+";
        if (chkJustinBridoux.isChecked())
            msg+=" Justin Bridoux+";
        if (chkSaucisseDeToulouse.isChecked())
            msg+=" Saucisse De Toulouse+";
        if (chkSaucissonCochonou.isChecked())
            msg+=" Saucisson Cochonou+";
        if (chkMerguez.isChecked())
            msg+=" Merguez+";
        if (chkJambon.isChecked())
            msg+=" Jambon+";
        // Afficher un message éphémère s’il n’est pas vide
        if (!msg.equals("")){
            String msgToast = msg.replace("+", " ");
            toaster = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toaster.show();
        }
        // Mémoriser les fruits sélectionnés
        ecrireListeCharcuterie(msg);
        // Fermer l’activité en cours
        fermerCharcuterie();
    }

    public void ecrireListeCharcuterie(String tmp) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("Charcuterie.json", Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'écriture ...");
        }

    }

    public String lireListeCharcuterie() {
        FileInputStream fis;
        String  data="";
        try {
            fis= openFileInput("Charcuterie.txt");
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
    public void fermerCharcuterie(){
        this.finish();
    }

}
