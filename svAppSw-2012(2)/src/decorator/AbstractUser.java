/**
 * Subject: Rappresenta l'interfaccia comune a RealSubject(User) e a Proxy(ProxyUser)
 * 
 */
package decorator;

import java.sql.SQLException;

import composite.Itinerary;
import composite.StayTemplate;

/**
 * @author Alessandro
 *
 */
public abstract class AbstractUser extends AbstractUserComponent {

	public abstract String getUsername();
	public abstract String getPwd();
	public abstract String getRuolo();
	public abstract void setUsername(String username);
	public abstract void setPwd(String pwd);
	public abstract void setRuolo(String ruolo);
	
	public boolean login(String username, String password) throws IllegalArgumentException, SQLException {
		return false;
	}
	
	public User getUser() {
		return null;
	}
	
}

