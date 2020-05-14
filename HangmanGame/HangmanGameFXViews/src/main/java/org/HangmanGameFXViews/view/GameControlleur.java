package org.HangmanGameFXViews.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import org.HangmanGameFXViews.Main;
import org.hangman.helper.PropertiesLoader;
import org.hangman.helper.Scoremanager;
import org.hangman.model.Round;
import org.hangman.model.Score;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;

public class GameControlleur {

	private ObservableList<Round> rounds = FXCollections.observableArrayList(
			new Callback<Round, Observable[]>() {
				@Override
				public Observable[] call(Round param) {
					return new Observable[]{
							param.getGuessWord(),
							param.getErrorsCount()
					};
				}
			}
			);
	private Map<Character,Button> buttonGroup = new HashMap<>();


	@FXML
	private ImageView image;
	@FXML
	private Label foundWords;
	@FXML
	private Label score;
	@FXML
	private Label guessedWord;

	@FXML
	private FlowPane line1;
	@FXML
	private FlowPane line2;
	@FXML
	private FlowPane line3;
	@FXML
	private FlowPane line4;

	@FXML
	private Button A;
	@FXML
	private Button B;
	@FXML
	private Button C;
	@FXML
	private Button D;
	@FXML
	private Button E;
	@FXML
	private Button F;
	@FXML
	private Button G;
	@FXML
	private Button H;
	@FXML
	private Button I;
	@FXML
	private Button J;
	@FXML
	private Button K;
	@FXML
	private Button L;
	@FXML
	private Button M;
	@FXML
	private Button N;
	@FXML
	private Button O;
	@FXML
	private Button P;
	@FXML
	private Button Q;
	@FXML
	private Button R;
	@FXML
	private Button S;
	@FXML
	private Button T;
	@FXML
	private Button U;
	@FXML
	private Button V;
	@FXML
	private Button W;
	@FXML
	private Button X;
	@FXML
	private Button Y;
	@FXML
	private Button Z;


	private List<String> dico = new ArrayList<>();

	private Main mainApp;

	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	public int getNbRounds() {
		return rounds.size();
	}

	@FXML
	public void initialize() {

		initButtons();
		rounds.addListener(new RoundChange());
		newGame();
	}

	private void initButtons() {
		addMargin(line1);
		addMargin(line2);
		addMargin(line3);
		addMargin(line4);
		buttonGroup.put('A', A);
		buttonGroup.put('B', B);
		buttonGroup.put('C', C);
		buttonGroup.put('D', D);
		buttonGroup.put('E', E);
		buttonGroup.put('F', F);
		buttonGroup.put('G', G);
		buttonGroup.put('H', H);
		buttonGroup.put('I', I);
		buttonGroup.put('J', J);
		buttonGroup.put('K', K);
		buttonGroup.put('L', L);
		buttonGroup.put('M', M);
		buttonGroup.put('N', N);
		buttonGroup.put('O', O);
		buttonGroup.put('P', P);
		buttonGroup.put('Q', Q);
		buttonGroup.put('R', R);
		buttonGroup.put('S', S);
		buttonGroup.put('T', T);
		buttonGroup.put('U', U);
		buttonGroup.put('V', V);
		buttonGroup.put('W', W);
		buttonGroup.put('X', X);
		buttonGroup.put('Y', Y);
		buttonGroup.put('Z', Z);
		EventHandler<ActionEvent> handler = new ButtonHandler();

		for(Map.Entry<Character, Button> e : buttonGroup.entrySet()) {
			e.getValue().setOnAction(handler);
		}
	}

	private void addMargin(FlowPane element) {
		for(Node child : element.getChildren()) {
			FlowPane.setMargin(child, new Insets(5));
		}
	}


	public void endGame() {

		List<Score> scores = Scoremanager.getTenTopScore();	
		if(scores.get(scores.size()-1).getScore() < this.getScore()) {
			String pseudo = getPseudo();
			if(!pseudo.isEmpty()) {
				Scoremanager.addScore(this.getScore(),this.getNbRounds()-1,pseudo);
			}
			newGame();
			mainApp.switchToScore();
		}
		else {
			newGame();
			mainApp.switchToHome();
		}


	}

	private String getPseudo() {
		TextInputDialog dialog = new TextInputDialog("walter");
		dialog.setTitle("Choix du pseudo");
		dialog.setHeaderText("Votre score fait partie du top 10, fécliciation. Rentrer votre nom pour sauvegarder votre score :");
		dialog.setContentText("Please enter your name:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			return result.get();
		}else {
			return "";
		}


	}

	public void refresh() {
		if(!rounds.isEmpty()) {
			Properties prop = PropertiesLoader.getInstance().getProperties();
			foundWords.setText(String.format(prop.getProperty("Game.nbFoundWords"),this.rounds.size()-1));
			score.setText(String.format(prop.getProperty("Game.score"),getScore()));
			guessedWord.setText(getCurrentRound().getGuessWord().get());
			//Update buttons
			if(getCurrentRound().getUsedChar().get().isEmpty()) {
				for(Button b : buttonGroup.values()) {
					b.setDisable(false);
				}
			}
			else {
				for(char c : getCurrentRound().getUsedChar().get()) {
					buttonGroup.get(Character.toUpperCase(c)).setDisable(true);
				}
			}
			updatePicture();
		}
	}

	private void updatePicture() {
		String pictureName = "Game.picture." + this.getCurrentRound().getErrorsCount().get();
		image.setImage(new Image(PropertiesLoader.getInstance().getProperties().getProperty(pictureName)));

	}

	public void newGame() {
		dico = PropertiesLoader.getDico();
		rounds.clear();
		newRound();

	}

	public void submitChar(char c){ 
		this.getCurrentRound().submitChar(c);
	}



	public void newRound() {
		int wordNumber = new Random().nextInt(dico.size());
		rounds.add(new Round(dico.get(wordNumber)));
	}
	public int getScore() {
		return rounds.stream().mapToInt(Round::getRoundScore).reduce(0, (a,b)->a+b)-rounds.get(rounds.size()-1).getRoundScore();
	}

	private Round getCurrentRound() {
		return rounds.get(rounds.size()-1);
	}
	class ButtonHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			Button button = (Button) event.getSource();
			submitChar(button.getText().charAt(0));
		}
	}

	class RoundChange implements ListChangeListener<Round>{

		@Override
		public void onChanged(Change<? extends Round> c) {
			while (c.next()) {
				if (c.wasAdded()) {
					refresh();
				}
				else if(c.wasUpdated()) {
					refresh();
					if(getCurrentRound().isOver()) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information");
						alert.setHeaderText(null);
						alert.setContentText("Le mot a trouvé était "+getCurrentRound().getWord());

						alert.showAndWait();
						if(getCurrentRound().getErrorsCount().get() > 6) {
							endGame();
						}
						else {
							newRound();
						}
					}

				}
			}

		}

	}

}
