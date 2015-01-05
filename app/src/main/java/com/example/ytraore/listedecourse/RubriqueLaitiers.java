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

    public class RubriqueLaitiers extends Activity implements View.OnClickListener{

        // Déclaration d’objets de type CheckBox comme variables de classe
        private CheckBox chkLait, chkLaitChocolat, chkLaitFraise, chkLaitVanilleSoja,chkYaourh,chkYaourh1;
        private Button btnSave;


        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.laitiers);
            //  Associer les CheckBox définies dans le fichier fruits.xml avec les variables de classe
            String listeLaitiers = lireListeLaitiers();

            chkLait = (CheckBox) findViewById(R.id.lait);
            chkLaitChocolat = (CheckBox) findViewById(R.id.laitchocolat);
            chkLaitFraise= (CheckBox) findViewById(R.id.laitfraise);
            chkLaitVanilleSoja = (CheckBox) findViewById(R.id.laitvanillesoja);
            chkYaourh = (CheckBox) findViewById(R.id.yaourh);
            chkYaourh1=(CheckBox) findViewById(R.id.yaourh1);

            majCheckBox(listeLaitiers);
            btnSave = (Button) findViewById(R.id.Save);
            // Mettre en place d’un écouteur d’événements sur le bouton Enregistrer
            btnSave.setOnClickListener(this);
        }
        //
        public void majCheckBox(String tmp){
            // Créer un objet st qui détecte des champs de mots et des séparateurs "+"
            StringTokenizer st = new StringTokenizer(tmp,"+");
            int i=0;
            String mot[] = new String[st.countTokens()];
            // Tant qu’il y a des champs séparés par des "+"
            while (st.hasMoreTokens()) {
                // Enregistrer le champs courant dans le tableau mot à l’indice i
                mot[i] = st.nextToken();
                // Si mot[i] vaut "lait", cocher la case correspondante
                if (mot[i].equals("lait")){
                    chkLait.setChecked(true);
                }
                // Si mot[i] vaut "laitchocolat", cocher la case correspondante
                else if (mot[i].equals("laitchocolat")){
                    chkLaitChocolat.setChecked(true);
                }
                // Si mot[i] vaut "laitfraise", cocher la case correspondante
                else if (mot[i].equals("laitfraise")){
                    chkLaitFraise.setChecked(true);
                }
                // Si mot[i] vaut "laitvanillesoja", cocher la case correspondante
                else if (mot[i].equals("laitvanillesoja")){
                    chkLaitVanilleSoja.setChecked(true);
                }
                // Si mot[i] vaut "yaourh", cocher la case correspondante
                else if (mot[i].equals("yaourh")){
                    chkYaourh.setChecked(true);
                }
                // Si mot[i] vaut "yaourh1", cocher la case correspondante
                else if (mot[i].equals("yaourh1")){
                    chkYaourh1.setChecked(true);
                }
                i++;
            }

        }
        // Lorsqu’on clique sur le bouton Enregistrer
        public void onClick(View v) {
            Toast toaster;
            String msg="";
            // Traiter l’état des CheckBox
            if (chkLait.isChecked())
                msg+=" lait+";
            if (chkLaitChocolat.isChecked())
                msg+=" lait chocolat+";
            if (chkLaitFraise.isChecked())
                msg+=" lait fraise+";
            if (chkLaitVanilleSoja.isChecked())
                msg+=" lait vanille soja+";
            if (chkYaourh.isChecked())
                msg+=" yaourht+";
            if (chkYaourh1.isChecked())
                msg+=" yaourht pure vache+";
            // Afficher un message éphémère s’il n’est pas vide
            if (!msg.equals("")){
                String msgToast = msg.replace("+", " ");
                toaster = Toast.makeText(this, msgToast, Toast.LENGTH_LONG);
                toaster.show();
            }
            // Mémoriser les fruits sélectionnés
            ecrireListeLaitiers(msg);
            // Fermer l’activité en cours
            fermerLesLaitiers();
        }

        public void ecrireListeLaitiers(String tmp) {
            FileOutputStream fos;
            try {
                fos = openFileOutput("Laitiers.json", Context.MODE_PRIVATE);
                Log.i("-----------  Fichier : ", getFilesDir().toString());
                fos.write(tmp.getBytes());
                fos.close();
            }
            catch (IOException ex){
                Log.i("-----------  Fichier : ", "Erreur d'écriture ...");
            }

        }

        public String lireListeLaitiers() {
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
        public void fermerLesLaitiers(){
            this.finish();
        }
    }
