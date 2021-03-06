package window.search;

import java.awt.Button;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Vector;

import javax.jws.WebParam.Mode;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objects.Ocena;
import objects.Oferta;

import states.StateManager;
import states.User;
import states.can.CanSearchPizza;
import window.search.PizzaSearchForm.PizzaSearchActionListener;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReviewForm extends JFrame{
	private User model;
	private SearchWindow parent;
	private JTextArea recenzja;
	private int podmiot;
	private JSlider gwiazdki;
	
	public ReviewForm(String s, SearchWindow parent, User model, int podmiot){
		super(s);
		this.parent = parent;
		this.recenzja = new JTextArea();
		recenzja.setLineWrap(true);
		recenzja.setWrapStyleWord(true);
		this.gwiazdki = new JSlider(0,5,0);
		this.podmiot = podmiot;
		this.model = model;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(200,200);
		createForm();
	}
	
	private void createForm(){
		this.setSize(300,320);
		
		Container mainContainer  = this.getContentPane();
		getContentPane().setLayout(new MigLayout("", "[298px]", "[27px][27px][27px][27px,grow][27px]"));
		
		//Slider z oceną
		Label nameLabel = new Label("Ocena:");
		mainContainer.add(nameLabel, "cell 0 0,grow");
		gwiazdki.setMinorTickSpacing(1);
		gwiazdki.setMajorTickSpacing(1);
		gwiazdki.setPaintLabels(true);
		gwiazdki.setPaintTicks(true);
		mainContainer.add(gwiazdki, "cell 0 1,grow");
		
		//Pobieranie nazwy pizzeri
		Label pizzeriaLabel = new Label("Recenzja:");
		mainContainer.add(pizzeriaLabel, "cell 0 2,grow");
		mainContainer.add(recenzja, "cell 0 3,grow");

        //Jeśli użytkownik już wystawił ocenę, widzi ją i może ją zmienić (nie może dodać drugiej)
        Ocena oc = model.Ocena_GetOne(podmiot, StateManager.user_id);
        if (oc != null)
        {
            gwiazdki.setValue(oc.gwiazdki);
            recenzja.setText(oc.recenzja);
        }

		//Przycisk oceń
		Button submit = new Button("Oceń");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.Ocena_InsertOrUpdate(podmiot, StateManager.user_id, recenzja.getText(), gwiazdki.getValue());
				dispose();
				parent.refresh();
                parent.cleanPizzaView();
				parent.cleanPizzeriaView();
			}
		});
		//submit.addActionListener(new PizzaSearchActionListener(parent));
		mainContainer.add(submit, "cell 0 4,grow");
	}
}
