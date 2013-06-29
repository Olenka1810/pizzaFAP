/*
 * Prototyp modelu użytkownika
 * Ta klasa zawiera implementację czynności jakie użyszkodnik może wykonać
 * poprzez uporczywe klikanie będąc zalogowanym jako "uzytkownik", przy czym
 * akcje dotyczące bazy są wykonywane przez UserRole.
 */

package states;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
import java.sql.ResultSet;

import database.UserRole;
import objects.Pizzeria;
import objects.Oferta;

public class User {
	User() {
		try {
			//pozyskujemy uprawnienia
			role = new UserRole();
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(Login.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	public Vector<Pizzeria> Pizzeria_GetAll() {
		Vector<Pizzeria> result = null;
		try {
			ResultSet rs = role.Pizzeria_GetAll();
			result = Pizzeria.converter.convert(rs);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return result;
	}
	
	public Vector<Oferta> Oferta_GetAll() {
		Vector<Oferta> result = null;
		try {
			ResultSet rs = role.Oferta_GetAll();
			result = Oferta.converter.convert(rs);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return result;
	}
	
	public Vector<Pizzeria> Pizzeria_GetSome(String nazwa, String ulica, String telefon, 
			float ocenaMin, float ocenaMax, int iloscMin, int iloscMax) {
		Vector<Pizzeria> result = null;
		try {
			ResultSet rs = role.Pizzeria_GetSome(nazwa, ulica, telefon, ocenaMin,
				ocenaMax, iloscMin, iloscMax);
			result = Pizzeria.converter.convert(rs);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return result;
	}
	
	private UserRole role;
}