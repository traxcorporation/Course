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
 * Created by ytraore on 10/12/2014.
 */
public class RubriqueBoucherie extends Activity implements View.OnClickListener {

    // Déclaration d’objets de type CheckBox comme variables de classe
    private CheckBox chkCoteDeChine, chkViandeDeChevre, chkViandeBoeuf, chkViandeHachee,chkViandeMouton,chkViandePorc;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boucherie);
        //  Associer les CheckBox définies dans le fichier boucherie.xml avec les variables de classe
        String listeBoucherie = lireListeBoucherie();

        chkCoteDeChine = (CheckBox) findViewById(R.id.cotedechine);
        chkViandeDeChevre = (CheckBox) findViewById(R.id.viandechevre);
        chkViandeBoeuf= (CheckBox) findViewById(R.id.viandeboeuf);
        chkViandeHachee = (CheckBox) findViewById(R.id.viandehachee);
        chkViandeMouton = (CheckBox) findViewById(R.id.viandemouton);
        chkViandePorc=(CheckBox) findViewById(R.id.viandeporc);

        majCheckBox(listeBoucherie);
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
            // Si mot[i] vaut "cotedechine", cocher la case correspondante
            if (mot[i].equals("cotedechine")){
                chkCoteDeChine.setChecked(true);
            }
            // Si mot[i] vaut "chkViandeDeChevre", cocher la case correspondante
            else if (mot[i].equals("viandedechevre")){
                chkViandeDeChevre.setChecked(true);
            }
            // Si mot[i] vaut "viandeboeuf", cocher la case correspondante
            else if (mot[i].equals("viandeboeuf")){
                chkViandeBoeuf.setChecked(true);
            }
            // Si mot[i] vaut "chkviandehachee", cocher la case correspondante
            else if (mot[i].equals("viandehachee")){
                chkViandeHachee.setChecked(true);
            }
            // Si mot[i] vaut "citron, cocher la case correspondante
            else if (mot[i].equals("viandemouton")){
                chkViandeMouton.setChecked(true);
            }
            // Si mot[i] vaut "viandeporc", cocher la case correspondante
            else if (mot[i].equals("viandeporc")){
                chkViandePorc.setChecked(true);
            }
            i++;
        }

    }
    // Lorsqu’on clique sur le bouton Enregistrer
    public void onClick(View v) {
        Toast toaster;
        String msg="";
        // Traiter l’état des CheckBox
        if (chkCoteDeChine.isChecked())
            msg+=" Cote De Chine+";
        if (chkViandeDeChevre.isChecked())
            msg+=" Viande De Chèvre+";
        if (chkViandeBoeuf.isChecked())
            msg+=" Viande De Boeuf+";
        if (chkViandeMouton.isChecked())
            msg+=" Viande de Mouton+";
        if (chkViandePorc.isChecked())
            msg+=" Viande Porc+";
        if (chkViandeHachee.isChecked())
            msg+=" Viande Hachée+";
        // Afficher un message éphémère s’il n’est pas vide
        if (!msg.equals("")){
            String msgToast = msg.replace("+", " ");
            toaster = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
            toaster.show();
        }
        // Mémoriser les choix sélectionnés
        ecrireListeBoucherie(msg);
        // Fermer l’activité en cours
        fermerBoucherie();
    }

    public void ecrireListeBoucherie(String tmp) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("Boucherie.txt", Context.MODE_PRIVATE);
            Log.i("-----------  Fichier : ", getFilesDir().toString());
            fos.write(tmp.getBytes());
            fos.close();
        }
        catch (IOException ex){
            Log.i("-----------  Fichier : ", "Erreur d'écriture ...");
        }

    }

    public String lireListeBoucherie() {
        FileInputStream fis;
        String  data="";
        try {
            fis= openFileInput("Boucherie.json");
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
    public void fermerBoucherie(){
        this.finish();
    }

}
