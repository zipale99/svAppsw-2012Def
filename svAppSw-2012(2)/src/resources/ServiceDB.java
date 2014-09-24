/**
 * 
 */
package resources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import composite.Itinerary;

import decorator.User;
import DB.DBconnection;

/**
 * @author utente
 *
 */
public class ServiceDB {
	
	
	
	/*
	 * TO-DO: Metodo login, se l'autenticazione va a buon fine, deve restituire
	 * il ruolo dell'utente. In caso di autenticazione fallita, restituirà la stringa vuota.
	 */
	public static String login(String username, String password) throws SQLException{
		Connection conn = DBconnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM UTENTI where username='"+username+
        		"' and password='"+password+"'");
        if(rs.next()){
            return rs.getString("ruolo");
        }
        rs.close();
        st.close();
        conn.close();
        return "";
	 }
	
	/**
     * recupera tutte le attività dell'agenzia e le mette in un arrayList che sarà il Bean
     * searchActivityResults
     */	
	public static ElencoAttivitaBean searchActivity() {
        ElencoAttivitaBean elb = new ElencoAttivitaBean();
    	Connection connessione = DBconnection.getConnection();    	
    	//se la connessione è andata a buon fine   	
        try {
            Statement st = connessione.createStatement();

            String sql = "SELECT * FROM attivita";

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	Activity act  = new Activity(rs.getInt("idattivita"),rs.getString("tipo"),rs.getString("citta"),
            					rs.getString("descrizione"),rs.getInt("durata"),rs.getDouble("prezzo"));
            	elb.add(act);
            }            
            st.close();
            connessione.close();                        
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return elb;
    }
    

	/**
	 * @param user utente per il quale cercare gli itinerari
	 * @return ArrayList che rappresenta un elenco di itinerari dell'utente
	 */    
    public static ArrayList<Itinerary> riempiItDaDB(User user) {
        
    	Connection connessione = DBconnection.getConnection();
    	
    	ArrayList <Itinerary> listaIt = new ArrayList<Itinerary>();
    	
    	//se la connessione è andata a buon fine   	
        try {
            Statement st = connessione.createStatement();

            String sql = "SELECT * FROM itinerario where creatoruser='"+user.getUsername()+"'";

            ResultSet rs = st.executeQuery(sql);
           
            while (rs.next()) {
            	Itinerary it = new Itinerary(rs.getString("creatoruser"),
            	rs.getString("startLoc"),rs.getString("endloc"),rs.getInt("durata"),
            	rs.getString("itname"),rs.getString("itdesc"),rs.getString("categoria"),rs.getString("stato"),
            	rs.getDouble("prezzo"));
            	it.setId(rs.getInt("iditinerario"));
            	listaIt.add(it);
            }
            
            st.close();
            connessione.close();
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return listaIt;
    }
    
    
    /**
     * ricerca gli itinerari presenti nel DB aventi iparametri specificati(in and i primi 2 parametri)
     * @param sl
     * @param el
     * @param d
     * @param nome
     * @param cat
     * @return
     */    
    public static ElencoItineraryBean searchItinerary(String sl,String el,int d,String nome,String cat) {
        
    	Connection connessione = DBconnection.getConnection();
    	
    	ElencoItineraryBean eib = new ElencoItineraryBean();
    	
    	//se la connessione è andata a buon fine   	
        try {
            Statement st = connessione.createStatement();

            String sql = "SELECT * FROM itinerario where (startloc='"+sl+"' and endloc='"+el+"') or durata="+d+
            		" or itname='"+nome+"' or categoria='"+cat+"'";

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	Itinerary it = new Itinerary(rs.getString("creatoruser"),
            			rs.getString("startloc"),rs.getString("endloc"),rs.getInt("durata"),
            			rs.getString("itname"),rs.getString("itdesc"),rs.getString("categoria"),rs.getString("stato"),
            			rs.getDouble("prezzo"));
            	it.setId(rs.getInt("iditinerario"));
            	eib.add(it);
            }
            
            st.close();
            connessione.close();
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return eib;
    }
    
    
    /**
     * @param elenco per gli stayTemplate presenti nel DB
     */
    public static ElencoStayTemplateBean searchStayTemplate() {        
    	Connection connessione = DBconnection.getConnection();    	    	
    	ElencoStayTemplateBean esb = new ElencoStayTemplateBean();
    	//se la connessione è andata a buon fine   	
        try {
            Statement st = connessione.createStatement();                                
            String sql = "SELECT * FROM staytemplate";            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	StayTemplateComposite stc = new StayTemplateComposite(rs.getString("startLoc"),
            			rs.getString("endloc"), rs.getInt("durata"), rs.getString("nomest"),
            			rs.getDouble("prezzo"),null);
            	esb.add(stc);
            }            
            st.close();
            connessione.close();
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return esb;
    }
    
    /**
     * metodo utile a trovare l'elenco delle attività corrispondenti ad un certo stayTemplate
     * @param id
     * @return beanActivity
     */
    public static ElencoAttivitaBean searchActivityStayTemplate(int id) {
    	ElencoAttivitaBean elb = new ElencoAttivitaBean();        
        Connection connessione = DBconnection.getConnection();
        	//se la connessione è andata a buon fine   	
        try {
          Statement st = connessione.createStatement();                       
            
          String sql = "SELECT attivita.idattivita,attivita.tipo,attivita.citta,attivita.descrizione,attivita.durata,"
          		+ "attivita.prezzo FROM attivita,ATTIVITA_ST where attivita_st.idstaytemplate="
        		  +id+" and ATTIVITA_ST.IDACTIVITY=ATTIVITA.IDATTIVITA";            
          ResultSet rs = st.executeQuery(sql);
          while (rs.next()) {
          	Activity ac = new Activity(rs.getInt("idattivita"),
          	rs.getString("tipo"), rs.getString("citta"), rs.getString("descrizione"),
          	rs.getInt("durata"), rs.getDouble("prezzo"),-1,false);
            elb.add(ac);
          }                
          st.close();
          connessione.close();
        }
        catch (SQLException ex) {
          ex.printStackTrace();
        }    	
    	return elb;
    }
	
    /**
     * metodo che dato un idStayTemplate, recupera i leaf ad esso associati
     * @param idStayTemplate
     * @return
     */
    
    public static ElencoStayTemplateBean searchLeafStayTemplate(int idStayTemplate) {
    	ElencoStayTemplateBean elb = new ElencoStayTemplateBean();
    	Connection connessione = DBconnection.getConnection();
    	
    	try {
            Statement st = connessione.createStatement();                       
            
            String sql = "SELECT * from staytemplate_leaf where idstaytemplate="+idStayTemplate;            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	StayTemplateLeaf stl = new StayTemplateLeaf(rs.getString("startloc"),
            	rs.getString("endloc"), rs.getInt("durata"), null,
            	rs.getString("typeleaf"), rs.getDouble("price"),-1,null);
              elb.addLeaf(stl);
            }                
            st.close();
            connessione.close();
          }
          catch (SQLException ex) {
            ex.printStackTrace();
          }    	
    	return elb; 
    }
    
    
    /**
     * recupera option associate ad un certo leaf(ovvero un determinato stayTemplateLeaf)
     * usando il parametro idStLeaf della tabella opzioni_standard
     */
    public void searchOptionLeaf(StayTemplateLeaf stl) {
    	
    	Connection connessione = DBconnection.getConnection();    	
    	ArrayList <Option> listaOpt = new ArrayList<Option>();    	
    	//se la connessione è andata a buon fine   	
        try {
            Statement st = connessione.createStatement();
            //TO-DO
            String sql = "SELECT * FROM option,staytemplate_leaf,opzioni_standard"
            		+ " where ";

            ResultSet rs = st.executeQuery(sql);
           
            while (rs.next()) {
            	Option opt = new Itinerary(rs.getString("creatoruser"),
            	rs.getString("startLoc"),rs.getString("endloc"),rs.getInt("durata"),
            	rs.getString("itname"),rs.getString("itdesc"),rs.getString("categoria"),rs.getString("stato"),
            	rs.getDouble("prezzo"));
            	it.setId(rs.getInt("iditinerario"));
            	lisaOpt.add(it);
            }
            
            st.close();
            connessione.close();
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return listaIt;
    }
	
	
}
