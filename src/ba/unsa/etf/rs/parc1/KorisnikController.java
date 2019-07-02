package ba.unsa.etf.rs.parc1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class KorisnikController {
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldUsername;
    public ListView<Korisnik> listKorisnici;
    public PasswordField fldPassword;
    public PasswordField fldPasswordRepeat;

    private KorisniciModel model;

    public KorisnikController(KorisniciModel model) {
        this.model = model;
    }

    @FXML
    public void initialize() {
        listKorisnici.setItems(model.getKorisnici());
        listKorisnici.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            model.setTrenutniKorisnik(newKorisnik);
            listKorisnici.refresh();
         });

        listKorisnici.getSelectionModel().selectFirst();
        fldIme.textProperty().bindBidirectional( model.getTrenutniKorisnik().imeProperty() );
        fldPrezime.textProperty().bindBidirectional( model.getTrenutniKorisnik().prezimeProperty() );
        fldEmail.textProperty().bindBidirectional( model.getTrenutniKorisnik().emailProperty() );
        fldUsername.textProperty().bindBidirectional( model.getTrenutniKorisnik().usernameProperty() );
        fldPassword.textProperty().bindBidirectional( model.getTrenutniKorisnik().passwordProperty() );


        fldPassword.textProperty().addListener((observableValue, s, t1) -> {
                if(!t1.isEmpty() && fldPassword.getText().trim().equals(fldPasswordRepeat.getText().trim())){
                    fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                    fldPasswordRepeat.getStyleClass().removeAll("poljeNijeIspravno");
                    fldPassword.getStyleClass().addAll("poljeIspravno");
                    fldPasswordRepeat.getStyleClass().addAll("poljeIspravno");
            } else {
                fldPassword.getStyleClass().removeAll("poljeIspravno");
                fldPasswordRepeat.getStyleClass().removeAll("poljeIspravno");
                fldPassword.getStyleClass().addAll("poljeNijeIspravno");
                fldPasswordRepeat.getStyleClass().addAll("poljeNijeIspravno");
            }
        });
        fldPasswordRepeat.textProperty().addListener((observableValue, s, t1) -> {
                if(!t1.isEmpty() && fldPassword.getText().trim().equals(fldPasswordRepeat.getText().trim())){
                    fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                    fldPasswordRepeat.getStyleClass().removeAll("poljeNijeIspravno");
                    fldPassword.getStyleClass().addAll("poljeIspravno");
                    fldPasswordRepeat.getStyleClass().addAll("poljeIspravno");
            } else {
                fldPassword.getStyleClass().removeAll("poljeIspravno");
                fldPasswordRepeat.getStyleClass().removeAll("poljeIspravno");
                fldPassword.getStyleClass().addAll("poljeNijeIspravno");
                fldPasswordRepeat.getStyleClass().addAll("poljeNijeIspravno");
            }
        });
        model.trenutniKorisnikProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            if (oldKorisnik != null) {
                fldIme.textProperty().unbindBidirectional(oldKorisnik.imeProperty() );
                fldPrezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty() );
                fldEmail.textProperty().unbindBidirectional(oldKorisnik.emailProperty() );
                fldUsername.textProperty().unbindBidirectional(oldKorisnik.usernameProperty() );
                fldPassword.textProperty().unbindBidirectional(oldKorisnik.passwordProperty() );
            }
            if (newKorisnik == null) {
                fldIme.setText("");
                fldPrezime.setText("");
                fldEmail.setText("");
                fldUsername.setText("");
                fldPassword.setText("");
                fldPasswordRepeat.setText("");
            }
            else {
                fldIme.textProperty().bindBidirectional( newKorisnik.imeProperty() );
                fldPrezime.textProperty().bindBidirectional( newKorisnik.prezimeProperty() );
                fldEmail.textProperty().bindBidirectional( newKorisnik.emailProperty() );
                fldUsername.textProperty().bindBidirectional( newKorisnik.usernameProperty() );
                fldPassword.textProperty().bindBidirectional( newKorisnik.passwordProperty() );
            }
        });

        fldIme.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldIme.getStyleClass().removeAll("poljeNijeIspravno");
                fldIme.getStyleClass().add("poljeIspravno");
            } else {
                fldIme.getStyleClass().removeAll("poljeIspravno");
                fldIme.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPrezime.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldPrezime.getStyleClass().removeAll("poljeNijeIspravno");
                fldPrezime.getStyleClass().add("poljeIspravno");
            } else {
                fldPrezime.getStyleClass().removeAll("poljeIspravno");
                fldPrezime.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldEmail.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldEmail.getStyleClass().removeAll("poljeNijeIspravno");
                fldEmail.getStyleClass().add("poljeIspravno");
            } else {
                fldEmail.getStyleClass().removeAll("poljeIspravno");
                fldEmail.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldUsername.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldUsername.getStyleClass().removeAll("poljeNijeIspravno");
                fldUsername.getStyleClass().add("poljeIspravno");
            } else {
                fldUsername.getStyleClass().removeAll("poljeIspravno");
                fldUsername.getStyleClass().add("poljeNijeIspravno");
            }
        });
    }

    public void dodajAction(ActionEvent actionEvent) {
        model.getKorisnici().add(new Korisnik("", "", "", "", ""));
        listKorisnici.getSelectionModel().selectLast();
    }

    public void obrisiAction(ActionEvent actionEvent) {
        model.getKorisnici().remove(listKorisnici.getSelectionModel().getSelectedItem());
        listKorisnici.refresh();
    }

    public void krajAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}
