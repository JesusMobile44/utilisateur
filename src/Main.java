import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main extends Application {
    private ArrayList<Utilisateur> listeUser = new ArrayList<>();
    private HashMap hashMap;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        hashMap = new HashMap<String,Utilisateur>();

        load(listeUser);
        File csvFile = new File("bingCSV.csv");
        if (!csvFile.exists())
            throw new FileNotFoundException("Le fichier n'existe pas");

        primaryStage.setWidth(700);
        primaryStage.setHeight(800);
        primaryStage.setTitle("Laboratoire 5");
        primaryStage.setResizable(false);

        //==========================================
        Label erreurConnex = new Label("");
        erreurConnex.setTranslateX(300);
        erreurConnex.setTranslateY(600);

        TextField nomUtil = new TextField();
        nomUtil.setPromptText("Veuillez entrez votre nom d'utilisateur");
        nomUtil.setTranslateX(300);
        nomUtil.setTranslateY(300);
        PasswordField password = new PasswordField();
        password.setPromptText("Veuillez entrez votre mot de passe");
        password.setTranslateX(300);
        password.setTranslateY(360);


        Label labelNomUtil = new Label("Nom d'utilisateur");
        labelNomUtil.setTranslateX(300);
        labelNomUtil.setTranslateY(280);
        Label labelPassword = new Label("Mot de passe");
        labelPassword.setTranslateX(300);
        labelPassword.setTranslateY(340);

        Button connecterButton = new Button();
        connecterButton.setText("Se connecter");
        connecterButton.setTranslateX(300);
        connecterButton.setTranslateY(410);
        Button inscrireButton = new Button();
        inscrireButton.setText("S'inscrire");
        inscrireButton.setTranslateX(400);
        inscrireButton.setTranslateY(410);
        //======================================================

        ProgressIndicator barreChargement = new ProgressIndicator();
        barreChargement.setTranslateX(330);
        barreChargement.setTranslateY(330);
        Label labelCharge = new Label("Chargement du contenu");
        labelCharge.setTranslateX(290);
        labelCharge.setTranslateY(400);

        Group chargeGroup = new Group(barreChargement,labelCharge);

        Scene chargement = new Scene(chargeGroup);

        connecterButton.setOnAction((event) -> {
            primaryStage.setScene(chargement);
                }
        );
        //=======================================================
        Label erreurInscri = new Label("");
        erreurInscri.setTranslateX(300);
        erreurInscri.setTranslateY(600);

        TextField prenom = new TextField();
        prenom.setPromptText("Prénom");
        prenom.setTranslateX(300);
        prenom.setTranslateY(40);
        TextField nom = new TextField();
        nom.setPromptText("Nom");
        nom.setTranslateX(300);
        nom.setTranslateY(90);
        TextField nomUtilNew = new TextField();
        nomUtilNew.setPromptText("Nom d'utilisateur");
        nomUtilNew.setTranslateX(300);
        nomUtilNew.setTranslateY(140);
        PasswordField passwordInscri = new PasswordField();
        passwordInscri.setPromptText("Mot de passe");
        passwordInscri.setTranslateX(300);
        passwordInscri.setTranslateY(190);
        PasswordField passwordConfirm = new PasswordField();
        passwordConfirm.setPromptText("Mot de passe");
        passwordConfirm.setTranslateX(300);
        passwordConfirm.setTranslateY(240);
        RadioButton radioGenreHomme = new RadioButton();
        radioGenreHomme.setText("Homme");
        radioGenreHomme.setTranslateX(300);
        radioGenreHomme.setTranslateY(290);
        RadioButton radioGenreFemme = new RadioButton();
        radioGenreFemme.setText("Femme");
        radioGenreFemme.setTranslateX(370);
        radioGenreFemme.setTranslateY(290);
        RadioButton radioGenreAutre = new RadioButton();
        radioGenreAutre.setText("Autre");
        radioGenreAutre.setTranslateX(440);
        radioGenreAutre.setTranslateY(290);

        ToggleGroup toggleGroup = new ToggleGroup();
        radioGenreHomme.setToggleGroup(toggleGroup);
        radioGenreFemme.setToggleGroup(toggleGroup);
        radioGenreAutre.setToggleGroup(toggleGroup);

        Spinner spinner = new Spinner(18,150,18);
        spinner.setTranslateX(300);
        spinner.setTranslateY(340);
        CheckBox checkbox = new CheckBox("J'accepte les conditions d'utilisation");
        checkbox.setTranslateX(300);
        checkbox.setTranslateY(390);

        Button inscrireNew = new Button();
        inscrireNew.setText("S'inscrire");
        inscrireNew.setTranslateX(300);
        inscrireNew.setTranslateY(450);
        Button effacer = new Button();
        effacer.setText("Effacer");
        effacer.setTranslateX(370);
        effacer.setTranslateY(450);
        Button retour = new Button();
        retour.setText("Retour");
        retour.setTranslateX(420);
        retour.setTranslateY(450);

        Label labelPrenom = new Label("Prénom");
        labelPrenom.setTranslateX(300);
        labelPrenom.setTranslateY(20);

        Label labelNom = new Label("Nom");
        labelNom.setTranslateX(300);
        labelNom.setTranslateY(70);

        Label labelNomUtilNew = new Label("Nom D'utilisateur");
        labelNomUtilNew.setTranslateX(300);
        labelNomUtilNew.setTranslateY(120);

        Label labelPasswordInscri = new Label("Mot de passe");
        labelPasswordInscri.setTranslateX(300);
        labelPasswordInscri.setTranslateY(170);

        Label labelPasswordConfirm = new Label("Confirmer le Mot de passe");
        labelPasswordConfirm.setTranslateX(300);
        labelPasswordConfirm.setTranslateY(220);

        Label labelGenre = new Label("Genre");
        labelGenre.setTranslateX(300);
        labelGenre.setTranslateY(270);

        Label labelSpinner = new Label("Age");
        labelSpinner.setTranslateX(300);
        labelSpinner.setTranslateY(320);

        Group textFieldInscrit = new Group(prenom,nom,nomUtilNew,passwordInscri,passwordConfirm,radioGenreHomme,radioGenreFemme,radioGenreAutre,spinner,checkbox,inscrireNew,effacer,retour);
        Group labelInscrit = new Group(labelNom,labelPrenom,labelNomUtilNew,labelPasswordInscri,labelPasswordConfirm,labelGenre,labelSpinner,erreurInscri);
        Group rootInscrit = new Group(textFieldInscrit,labelInscrit);

        Scene inscription =new Scene(rootInscrit);

        //==========================================================================
        Group textField = new Group(nomUtil,password,labelNomUtil,labelPassword,connecterButton,inscrireButton,erreurConnex);

        Group root = new Group(textField);
        Scene connexion = new Scene(root);

        inscrireNew.setOnAction((event) ->{
            erreurInscri.setText("");
            if (prenom.getText().isEmpty() ||
                    nom.getText().isEmpty() ||
                    nomUtilNew.getText().isEmpty()||
                    passwordInscri.getText().isEmpty()||
                    passwordConfirm.getText().isEmpty()||
                    (!radioGenreAutre.isSelected()&&!radioGenreFemme.isSelected()&&!radioGenreHomme.isSelected())){
                erreurInscri.setText("Veillez remplir tous les champs");
            }
           // else if ()
            else if (!passwordInscri.getText().equals(passwordConfirm.getText())){
                erreurInscri.setText("Les mots de passes ne sont pas les même");
            }
            else if (!checkbox.isSelected()){
                erreurInscri.setText("Vous n'avez pas acceptez les conditions d'utilisations");
            }
            if( erreurInscri.getText().equals("")){
                Utilisateur temp = new Utilisateur();
                temp.setPrenom(prenom.getText());
                temp.setNom(nom.getText());
                temp.setNomUtil(nomUtilNew.getText());
                temp.setPassword(hash(passwordInscri.getText()));
                if (radioGenreAutre.isSelected()){temp.setGenre("A");}
                else if (radioGenreFemme.isSelected()){temp.setGenre("F");}
                else if (radioGenreHomme.isSelected()){temp.setGenre("H");}
                temp.setAge(spinner.getValue().toString());

                hashMap.put(nomUtilNew.getText(),temp);

                String write = temp.getPrenom()+","+temp.getNom()+","+temp.getNomUtil()+","+temp.getPassword()+","+temp.getGenre()+","+temp.getAge()+"\n";
                try{
                    if (csvFile.exists())
                        Files.write(Paths.get("bingCSV.csv"), write.getBytes(), StandardOpenOption.APPEND);
                    else
                        Files.write(Paths.get("bingCSV.csv"), write.getBytes(), StandardOpenOption.CREATE);
                }catch(Exception e){

                }
                retour.fire();
            }

        } );

        inscrireButton.setOnAction((event) -> {
            primaryStage.setScene(inscription);
        });
        retour.setOnAction(event ->{
            effacer.fire();
            primaryStage.setScene(connexion);
        } );
        effacer.setOnAction(event -> {
            prenom.clear();
            nom.clear();
            nomUtilNew.clear();
            passwordInscri.clear();
            passwordConfirm.clear();
            radioGenreHomme.setSelected(false);
            radioGenreFemme.setSelected(false);
            radioGenreAutre.setSelected(false);
            checkbox.setSelected(false);
            spinner.getValueFactory().setValue(18);
            erreurInscri.setText("");
        });

        primaryStage.setScene(connexion);
        primaryStage.show();
    }
    public static String hash(String chaine){

        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    chaine.getBytes(StandardCharsets.UTF_8));

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < encodedhash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
                return hexString.toString();
            }
        }catch (Exception e){
            System.out.println("Hashing error");
        }
        return null;
    }
    public static boolean verfication(String key){
        boolean pareil = false;
        for (int i=0;i<)
    }
    public static void load(ArrayList<Utilisateur> listeUser){
        try{
            List<String> toutesLigne = Files.readAllLines(Paths.get("bingCSV.csv"));
        }
        catch(Exception exception){

        }
    }
}
