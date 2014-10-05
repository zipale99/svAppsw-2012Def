/**
 * 
 */
package resources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import composite.*;
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
	public static ActivitySearchResults searchActivity() {
        ActivitySearchResults results = new ActivitySearchResults();
    	Connection connessione = DBconnection.getConnection();    	
    	//se la connessione è andata a buon fine   	
        try {
            Statement st = connessione.createStatement();

            String sql = "SELECT * FROM attivita";

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	Activity act  = new Activity(rs.getInt("idattivita"),rs.getString("tipo"),rs.getString("citta"),
            					rs.getString("descrizione"),rs.getInt("durata"),rs.getDouble("prezzo"));
            	results.add(act);
            }            
            st.close();
            connessione.close();                        
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return results;
    }
	
	public static void createHMS(StayTemplate st) {
		Connection connessione = DBconnection.getConnection();    	
	    try {
	    	Statement stStayTemplate = connessione.createStatement();
	    	Statement stLeaf = connessione.createStatement();
	        stStayTemplate.executeUpdate("INSERT INTO staytemplate (nomest, startloc, endloc, durata, transport, prezzo) VALUES ('"+st.getNome()+"','"+st.getStartLoc()+"','"+st.getEndLoc()+"',"+0+","+false+","+0+")");
	           
	        ResultSet rsStayTemplate = stStayTemplate.executeQuery("SELECT * from staytemplate");
	        int idStayTemplate = 0;
	        while (rsStayTemplate.next())
	        	idStayTemplate = rsStayTemplate.getInt("idStayTempl");
	        
	        stLeaf.executeUpdate("INSERT INTO staytemplate_leaf (idstaytemplate, startloc, endloc, typeleaf) VALUES ("+idStayTemplate+",'"+st.getStartLoc()+"','"+st.getEndLoc()+"','"+st.getTypeLeaf()+"')");
	           
	        stStayTemplate.close();
	        connessione.close();                        
	       	}
	       	catch (SQLException ex) {
	       		ex.printStackTrace();
	       	}
    }
    
    
	/*
	 * Restituisce una lista di ititnerari associati al nome utente passato come parametro
	 */
	public static ItinerarySearchResults myItinerary(User user) {    	
    	Connection connessione = DBconnection.getConnection();
    	ItinerarySearchResults results = new ItinerarySearchResults();   	
        try {
            Statement stIt = connessione.createStatement(); 
            Statement stActivity = connessione.createStatement();
            Statement stStay = connessione.createStatement();
            
            ResultSet rsIt = stIt.executeQuery("SELECT * FROM itinerario where creatoruser ='"+user.getUsername()+"'");
            while(rsIt.next()) {   
                int idItinerario = Integer.parseInt(rsIt.getString("idItinerario"));
            	Itinerary it = new Itinerary(rsIt.getString("creatoruser"),
            			rsIt.getString("startloc"),rsIt.getString("endloc"),rsIt.getInt("durata"),
            			rsIt.getString("itname"),rsIt.getString("itdesc"),rsIt.getString("categoria"),rsIt.getString("stato"),
            			rsIt.getDouble("prezzo"));
            	it.setId(idItinerario);
            	ResultSet rsStay = stStay.executeQuery("SELECT * from STAY where idItinerario ="+idItinerario);
            	while (rsStay.next()) {
            		StayTemplate stay = new StayTemplateComposite();
            		int idStayTemplate = rsStay.getInt("idStayTemplate");
            		int idStay = rsStay.getInt("idStay");
            		System.out.println("idStay: "+idStay);
            		stay.setId(rsStay.getInt("idStay"));
            		stay.setIdStay(idStayTemplate);
            		stay.setTimeOffset(rsStay.getInt("timeOffset"));
            		stay.setPrice(rsStay.getInt("prezzo"));
            		stay.setActivityList(searchActivityStayTemplate(idStayTemplate).getElencoAttivita());//TO-DO:recupero attivita personalizzate!!
            		ResultSet rsActivity = stActivity.executeQuery("SELECT * FROM attivita_stay, attivita where idstay="+idStay+
            																"and attivita_stay.idattivita = attivita.idattivita");
            		List<Activity> activityPers = new ArrayList<Activity>();
            		while (rsActivity.next()) {
            			Activity activity = new Activity();
            			activity.setIdActivity(rsActivity.getInt("id"));
              			activity.setType(rsActivity.getString("tipo"));
            			activity.setLocation(rsActivity.getString("citta"));
            			activity.setDesc(rsActivity.getString("descrizione"));
            			activity.setDurata(rsActivity.getInt("durata"));
            			activity.setPrice(rsActivity.getDouble("prezzo"));
            			//eventualmente aggiungere il timeoffset nel db e settarlo
            			activityPers.add(activity);
            		}
            		/*
            		 * Dal momento che quando salviamo una tappa, ricopiamo cmq tutte le attività nella tabella attività personalizzate
            		 * non è necessario andare a recuperare pure quelle standard. Per tanto l'istruzione seguente non è più necessaria.
            		 * stay.getActivityList().addAll(activityPers);
            		 * ma è sufficiente:
            		 */
            			stay.setActivityList(activityPers);
            		for (StayTemplate leaf :  searchLeafStayTemplate(idStayTemplate).getElencoStayTemplate()) {          			
                		leaf.setOptionList(getOptionLeafPers(idStay, leaf.getId()).getElencoOptions());
                		stay.add(leaf);
            		}
            		it.addStay(stay);
            	}
            	results.add(it);
            }
            
            stIt.close();
            stStay.close();
            connessione.close();
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return results;
    }
    

    
    /*
     * Restituisce una lista di itinerari sulla base dei parametri di ricerca.
     */
	public static ItinerarySearchResults searchItinerary(String sl,String el,int d,String nome,String cat) {
		Connection connessione = DBconnection.getConnection();
    	ItinerarySearchResults results = new ItinerarySearchResults();   	
        try {
            Statement stIt = connessione.createStatement(); 
            Statement stActivity = connessione.createStatement();
            Statement stStay = connessione.createStatement();
            ResultSet rsIt = null;
                            
            if( (!sl.equals("") || !el.equals("")) && (d!=0 || nome != "" || cat != "") ) {
            	if (d!=0) rsIt = stIt.executeQuery("SELECT * FROM itinerario where (startloc='"+sl+"' OR endloc='"+el+"') AND (durata="+d+")");
               	if (nome!="") rsIt = stIt.executeQuery("SELECT * FROM itinerario where (startloc='"+sl+"' OR endloc='"+el+"') AND (itname='"+nome+"')");
            	if(cat!="") rsIt = stIt.executeQuery("SELECT * FROM itinerario where (startloc='"+sl+"' OR endloc='"+el+"') AND (categoria='"+cat+"')");
            }
            
            else if( ( sl!="" || el!="") && (d == 0 || nome == "" || cat == ""))
            	rsIt = stIt.executeQuery("SELECT * FROM itinerario where (startloc='"+sl+"' OR endloc='"+el+"')");
            
            else if( (sl == "" && el=="") && (d!=0||nome != "" || cat != "") ) {
            	if (d!=0) rsIt = stIt.executeQuery("SELECT * FROM itinerario where durata="+d);
            	if (nome != "") rsIt = stIt.executeQuery("SELECT * FROM itinerario where itname='"+nome+"'");
            	if(cat != "") rsIt = stIt.executeQuery("SELECT * FROM itinerario where categoria='"+cat+"'");
            }
            
            else rsIt = stIt.executeQuery("select * from itinerario");
            
            
            //ResultSet rsIt = stIt.executeQuery("SELECT * FROM itinerario where ((startloc='"+sl+"') OR (endloc='"+el+"')) AND (((durata="+d+") OR (categoria='"+cat+"')) OR (itname='"+nome+"'))");
            while(rsIt.next()) {   
                int idItinerario = Integer.parseInt(rsIt.getString("idItinerario"));
            	Itinerary it = new Itinerary(rsIt.getString("creatoruser"),
            			rsIt.getString("startloc"),rsIt.getString("endloc"),rsIt.getInt("durata"),
            			rsIt.getString("itname"),rsIt.getString("itdesc"),rsIt.getString("categoria"),rsIt.getString("stato"),
            			rsIt.getDouble("prezzo"));
            	it.setId(idItinerario);
            	ResultSet rsStay = stStay.executeQuery("SELECT * from STAY where idItinerario ="+idItinerario);
            	while (rsStay.next()) {
            		StayTemplate stay = new StayTemplateComposite();
            		int idStayTemplate = rsStay.getInt("idStayTemplate");
            		int idStay = rsStay.getInt("idStay");
            		System.out.println("idStay: "+idStay);
            		stay.setId(rsStay.getInt("idStay"));
            		stay.setIdStay(idStayTemplate);
            		stay.setTimeOffset(rsStay.getInt("timeOffset"));
            		stay.setPrice(rsStay.getInt("prezzo"));
            		stay.setActivityList(searchActivityStayTemplate(idStayTemplate).getElencoAttivita());//TO-DO:recupero attivita personalizzate!!
            		ResultSet rsActivity = stActivity.executeQuery("SELECT * FROM attivita_stay, attivita where idstay="+idStay+
            																"and attivita_stay.idattivita = attivita.idattivita");
            		List<Activity> activityPers = new ArrayList<Activity>();
            		while (rsActivity.next()) {
            			Activity activity = new Activity();
            			activity.setIdActivity(rsActivity.getInt("id"));
            			activity.setType(rsActivity.getString("tipo"));
            			activity.setLocation(rsActivity.getString("citta"));
            			activity.setDesc(rsActivity.getString("descrizione"));
            			activity.setDurata(rsActivity.getInt("durata"));
            			activity.setPrice(rsActivity.getDouble("prezzo"));
            			//eventualmente aggiungere il timeoffset nel db e settarlo
            			activityPers.add(activity);
            		}
            		/*
            		 * Dal momento che quando salviamo una tappa, ricopiamo cmq tutte le attività nella tabella attività personalizzate
            		 * non è necessario andare a recuperare pure quelle standard. Per tanto l'istruzione seguente non è più necessaria.
            		 * stay.getActivityList().addAll(activityPers);
            		 * ma è sufficiente:
            		 */
            			stay.setActivityList(activityPers);
            		for (StayTemplate leaf :  searchLeafStayTemplate(idStayTemplate).getElencoStayTemplate()) {          			
                		leaf.setOptionList(getOptionLeafPers(idStay, leaf.getId()).getElencoOptions());
                		stay.add(leaf);
            		}
            		it.addStay(stay);
            	}
            	results.add(it);
            }
            
            stIt.close();
            stStay.close();
            connessione.close();
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return results;
    }
  
  	 
    
    
    
    /**
     * @param elenco per gli stayTemplate presenti nel DB
     */
    public static StaySearchResults searchStayTemplated() {        
    	Connection connessione = DBconnection.getConnection();    	    	
    	StaySearchResults esb = new StaySearchResults();
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
     * @param recupero gli stayTemplate presenti nel DB
     */
    public static StaySearchResults searchStayTemplate() {        
    	Connection connessione = DBconnection.getConnection();    	    	
    	StaySearchResults esb = new StaySearchResults();
    	//se la connessione è andata a buon fine   	
        try {
            Statement st = connessione.createStatement();                                
            String sql = "SELECT * FROM staytemplate where transport = false";            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	StayTemplateComposite stc = new StayTemplateComposite();
            	int id = rs.getInt("idstaytempl");
            	stc.setId(id);
            	stc.setStartLoc(rs.getString("startLoc"));
            	stc.setEndLoc(rs.getString("endLoc"));
            	stc.setDurata(rs.getInt("durata"));
            	stc.setNome(rs.getString("nomest"));
            	stc.setPrice(rs.getDouble("prezzo"));
            	stc.setActivityList(searchActivityStayTemplate(id).getElencoAttivita());
            	for (StayTemplate stay :  searchLeafStayTemplate(id).getElencoStayTemplate()) {
            		stay.setOptionList(getOptionLeaf(stay.getId()).getElencoOptions());
            		stc.add(stay);
            	}
            	
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
     * metodo utile a recuperare dal DB gli stayTemplate di tipo transfer con StartLoc=start ed endLoc=end
     * @param start
     * @param end
     * @return arrayList contenente tutti gli stayTemplate di tipo transfer
     */
    public static StaySearchResults transferList(String start, String end) {
    	StaySearchResults results = new StaySearchResults();
    	Connection connessione = DBconnection.getConnection();    	
    	//recupera dal db(tabella stayTemplate) le tuple corrispondenti a tranfer=true e start=startLoc e end=endLoc
    	try {
            Statement st = connessione.createStatement();
            String sql = "SELECT * FROM staytemplate where startloc='"+start+"' and endloc='"+end+"' and transport="+true;
            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	StayTemplateComposite stc = new StayTemplateComposite();
            	int id = rs.getInt("idstaytempl");
            	stc.setId(id);
            	stc.setStartLoc(rs.getString("startLoc"));
            	stc.setEndLoc(rs.getString("endLoc"));
            	stc.setDurata(rs.getInt("durata"));
            	stc.setNome(rs.getString("nomest"));
            	stc.setPrice(rs.getDouble("prezzo"));
            	stc.setActivityList(searchActivityStayTemplate(id).getElencoAttivita());
            	for (StayTemplate stay :  searchLeafStayTemplate(id).getElencoStayTemplate()) {
            		stay.setOptionList(getOptionLeaf(stay.getId()).getElencoOptions());
            		stc.add(stay);
            	}
            	
            	results.add(stc);
            }            
            st.close();
            connessione.close();
    	}
    	catch (SQLException ex) {
        	ex.printStackTrace();
        }
    	return results;
    }

    
    
    /**
     * metodo utile a trovare l'elenco delle attività corrispondenti ad un certo stayTemplate
     * @param id
     * @return beanActivity
     */
    public static ActivitySearchResults searchActivityStayTemplate(int id) {
    	ActivitySearchResults results = new ActivitySearchResults();        
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
            results.add(ac);
          }                
          st.close();
          connessione.close();
        }
        catch (SQLException ex) {
          ex.printStackTrace();
        }    	
    	return results;
    }
	
    /**
     * metodo che dato un idStayTemplate, recupera i leaf ad esso associati
     * @param idStayTemplate
     * @return
     */
    
    public static StaySearchResults searchLeafStayTemplate(int idStayTemplate) {
    	StaySearchResults results = new StaySearchResults();
    	Connection connessione = DBconnection.getConnection();
    	
    	try {
            Statement st = connessione.createStatement();                       
            
            String sql = "SELECT * from staytemplate_leaf where idstaytemplate="+idStayTemplate;            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	StayTemplateLeaf stl = new StayTemplateLeaf(rs.getString("startloc"),
            	rs.getString("endloc"), rs.getInt("durata"), null,
            	rs.getString("typeleaf"),0,-1,null);
            	stl.setId(rs.getInt("idstleaf"));
              results.addLeaf(stl);
            }                
            st.close();
            connessione.close();
          }
          catch (SQLException ex) {
            ex.printStackTrace();
          }    	
    	return results; 
    }
    
    
    /*
     * recupera option associate ad un certo leaf(ovvero un determinato stayTemplateLeaf)
     * usando il parametro idStLeaf della tabella opzioni_standard
     */
    public static OptionSearchResults getOptionLeaf(int idLeaf) {
    	
    	Connection connessione = DBconnection.getConnection();    	
    	OptionSearchResults results = new OptionSearchResults();    	
    	//se la connessione è andata a buon fine   	
        try {
            Statement st = connessione.createStatement();
            //TO-DO
            String sql = "SELECT * FROM OPZIONI_STANDARD, OPTION_LIST"
            			+ " WHERE OPZIONI_STANDARD.idStLeaf = "+ idLeaf + "AND OPZIONI_STANDARD.idOptionList = OPTION_LIST.id";

            ResultSet rs = st.executeQuery(sql);
           
            while (rs.next()) {
            	Option opt = new Option();
            	OptionValue optValueStandard = new OptionValue();
            	optValueStandard.setId(rs.getInt("ID"));
            	opt.setId(rs.getInt("idoption"));
            	opt.setName(rs.getString("optionname"));
            	opt.setDesc(rs.getString("description"));
            	
            	
            	optValueStandard.setValue(rs.getString("value"));
            	optValueStandard.setPrice(rs.getInt("price"));
            	
            	
            	opt.setPossibleValue(getOptionValue(opt.getId()));
            	opt.setValue(optValueStandard);
            	
            	results.add(opt);
            }
            
            st.close();
            connessione.close();
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return results;
    }
    
    
    /*
     * Dato l'id di un opzione recupera tutti i valori ad essa associtati
     */
    public static List<OptionValue> getOptionValue(int idOption) {
    	
    	Connection connessione = DBconnection.getConnection();    	
    	List<OptionValue> results = new ArrayList<OptionValue>();    	
    	//se la connessione è andata a buon fine   	
        try {
            Statement st = connessione.createStatement();
            //TO-DO
            String sql = "SELECT * FROM OPTION_LIST"
            			+ " WHERE idOption = "+ idOption;

            ResultSet rs = st.executeQuery(sql);
           
            while (rs.next()) {
            	OptionValue optValue = new OptionValue();
            	optValue.setId(rs.getInt("id"));
            	optValue.setValue(rs.getString("value"));
            	optValue.setPrice(rs.getInt("price"));     
            	results.add(optValue);
            }
            
            st.close();
            connessione.close();
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return results;
    }
    
public static OptionSearchResults getOptionLeafPers(int idStay, int idLeaf) {
    	
    	Connection connessione = DBconnection.getConnection();    	
    	OptionSearchResults results = new OptionSearchResults();    	
    	//se la connessione è andata a buon fine   	
        try {
            Statement st = connessione.createStatement();
            //TO-DO
            String sql = "SELECT * FROM OPZIONI_PERS, OPZIONI_STANDARD, OPTION_LIST"
            			+ " WHERE OPZIONI_PERS.idstay = "+idStay+" AND OPZIONI_STANDARD.idstleaf="+idLeaf+""
            					+ " AND OPZIONI_PERS.idoptionlist=OPTION_LIST.id AND OPZIONI_PERS.idoptstandard = OPZIONI_STANDARD.idoption";

            ResultSet rs = st.executeQuery(sql);
           
            while (rs.next()) {
            	Option opt = new Option();
            	OptionValue optValuePers = new OptionValue();
            	
            	opt.setId(rs.getInt("idoption"));
            	opt.setName(rs.getString("optionname"));
            	opt.setDesc(rs.getString("description"));
            	
            	optValuePers.setValue(rs.getString("value"));
            	optValuePers.setPrice(rs.getInt("price"));
            	optValuePers.setId(rs.getInt("ID"));
            	
            	opt.setPossibleValue(getOptionValue(opt.getId()));
            	opt.setValue(optValuePers);
            	
            	results.add(opt);
            }
            
            st.close();
            connessione.close();
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return results;
    }
    
    /*
     * SaveItinerary()
     * 
     * Inserisce nella tapella Itinerary le informazioni reperite dall'oggetto Itinerary di user
     * Scorre l'arrayList associata a Itinerary e inserisce le informazioni per ciascun StayTemplate nella tabella Stay
     * Importante: Associare anche l'id dello stayTemplate associato(utile per il recupero dei leaf)
     * Inserire le attivita dell'arrayList activity associato a ciascun StayTemplate nella tabell attività personalizzate
     * Settare i valori delle opzioni.
     * Con questo metodo si conclude lo scenario principale di successo.
     */
    public static void saveItinerary(Itinerary it) {
    	Connection connessione = DBconnection.getConnection();
    	try {
            Statement stIt = connessione.createStatement();
            Statement stLeaf = connessione.createStatement();
            Statement stStay = connessione.createStatement();
            Statement stActivity = connessione.createStatement();
            Statement stOpzioni_pers = connessione.createStatement();
            ResultSet rsIt;
            
            stIt.executeUpdate( "INSERT INTO itinerario (creatoruser, startloc, endloc, durata, itname, itdesc, categoria, stato, prezzo) "
            		+ "VALUES ('"+it.getUser()+"','"+it.getStartLoc()+"','"+ it.getEndLoc()+"',"+ it.getDurata()+",'"+ it.getNome()+"','" 
            		+it.getDesc()+"','"+ it.getCategoria()+"','"+ it.getStato()+"',"+ it.getPrice()+")" );
            rsIt = stIt.executeQuery("select iditinerario from itinerario");
         	int idItinerario = 0;
            while (rsIt.next())
            	idItinerario = rsIt.getInt("iditinerario");
            System.out.println("idItinerario: " + idItinerario);
            int i = 0;
            int size = it.getSize();
            int j = 0;
            while(size > 0) {
            	System.out.println("staytemplid:"+it.getStayTemplate(i).getId());
            	stStay.executeUpdate("INSERT INTO stay (iditinerario, idstaytemplate, timeoffset, prezzo) VALUES "
            			+ "("+idItinerario+","+it.getStayTemplate(i).getId()+","+ it.getStayTemplate(i).getTimeOffset()
            			+","+ it.getStayTemplate(i).getPrice()+")");
            	ResultSet rsStay = stStay.executeQuery("Select * from stay");
            	int idStay = 0;
                while (rsStay.next())
                	idStay = rsStay.getInt("idStay");
            	for (int z = 0; z < it.getStayTemplate(i).getActivityList().size(); z++)
            		stActivity.executeUpdate("INSERT INTO attivita_stay (idstay, idattivita, timeoffset) VALUES "
            				+ "("+ idStay +","+ it.getStayTemplate(i).getActivityList().get(z).getIdActivity()+","+ it.getStayTemplate(i).getActivityList().get(z).getTimeOffset() + ")");
            	int sizeLeaf = it.getStayTemplate(i).getSize();
            	while(sizeLeaf > 0) {
            		System.out.println("j: "+j);
            		for (int x = 0; x < it.getStayTemplate(i).getStayTemplate(j).getOptionListSize(); x++) {
            			stOpzioni_pers.executeUpdate("INSERT INTO opzioni_pers (idoptstandard, idstay, idoptionlist) VALUES "
            					+ "("+it.getStayTemplate(i).getStayTemplate(j).getOption(x).getId()+","
            					+ idStay +","+ it.getStayTemplate(i).getStayTemplate(j).getOption(x).getValue().getId() +")");
            		}
            		sizeLeaf--;
                	j++;
            	}
            	j=0;
            	size--;
            	i++;
            }
            stIt.close();
            stLeaf.close();
            stStay.close();
            stOpzioni_pers.close();
            connessione.close();
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }  	
    }
    
    
    public static void deleteItinerary(Itinerary it) {
    	Connection connessione = DBconnection.getConnection();    	  	
        try {
            Statement st = connessione.createStatement();
            String deleteOptPers = "delete from opzioni_pers where idstay=";
            String deleteAttPers = "delete from attivita_stay where idstay=";
            String deleteStay = "delete from stay where iditinerario="+it.getId();
            String deleteIt = "delete from itinerario where iditinerario="+it.getId();
            int i = 0;
            int size = it.getSize();
            while (size > 0) {
            	st.executeUpdate(deleteOptPers+it.getStayTemplate(i).getId());
            	st.executeUpdate(deleteAttPers+it.getStayTemplate(i).getId());
            	i++;
            	size--;
            }
            st.executeUpdate(deleteStay);
            st.executeUpdate(deleteIt);
                      
            st.close();
            connessione.close();
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
    }
    
    public static void modificaItinerary(Itinerary it) {
    	Connection connessione = DBconnection.getConnection();    	  	
        try {
        	Statement st = connessione.createStatement();
        	
            String deleteOptPers = "delete from opzioni_pers where idstay=";
            String deleteAttPers = "delete from attivita_stay where idstay=";
            String deleteStay = "delete from stay where iditinerario="+it.getId();
            int y = 0;
            int sizeIt = it.getSize();
            while (sizeIt > 0) {
            	st.executeUpdate(deleteOptPers+it.getStayTemplate(y).getId());
            	st.executeUpdate(deleteAttPers+it.getStayTemplate(y).getId());
            	y++;
            	sizeIt--;
            }
            st.executeUpdate(deleteStay);
            
            Statement stIt = connessione.createStatement();
            Statement stLeaf = connessione.createStatement();
            Statement stStay = connessione.createStatement();
            Statement stActivity = connessione.createStatement();
            Statement stOpzioni_pers = connessione.createStatement();
    
           	int idItinerario = it.getId();
            System.out.println("idItinerario: " + idItinerario);
            int i = 0;
            int size = it.getSize();
            int j = 0;
            while(size > 0) {
            	System.out.println("staytemplid:"+it.getStayTemplate(i).getId());
            	System.out.println("stayid:"+it.getStayTemplate(i).getIdStay());
            	stStay.executeUpdate("INSERT INTO stay (iditinerario, idstaytemplate, timeoffset, prezzo) VALUES "
            			+ "("+idItinerario+","+it.getStayTemplate(i).getIdStay()+","+ it.getStayTemplate(i).getTimeOffset()
            			+","+ it.getStayTemplate(i).getPrice()+")");
            	ResultSet rsStay = stStay.executeQuery("Select * from stay");
            	int idStay = 0;
                while (rsStay.next())
                	idStay = rsStay.getInt("idStay");
                System.out.println("idStayActivity: "+idStay);
            	for (int z = 0; z < it.getStayTemplate(i).getActivityList().size(); z++) {
            		 System.out.println("idActivity: "+ it.getStayTemplate(i).getActivityList().get(z).getIdActivity());
            		stActivity.executeUpdate("INSERT INTO attivita_stay (idstay, idattivita, timeoffset) VALUES "
            				+ "("+ idStay +","+ it.getStayTemplate(i).getActivityList().get(z).getIdActivity()+","+ it.getStayTemplate(i).getActivityList().get(z).getTimeOffset() + ")");
            	}
            	int sizeLeaf = it.getStayTemplate(i).getSize();
            	while(sizeLeaf > 0) {
            		System.out.println("j: "+j);
            		for (int x = 0; x < it.getStayTemplate(i).getStayTemplate(j).getOptionListSize(); x++) {
            			System.out.println("idoptstandard: "+it.getStayTemplate(i).getStayTemplate(j).getOption(x).getId());
            			System.out.println("idstay: "+idStay);
            			System.out.println("idoptionlist: "+it.getStayTemplate(i).getStayTemplate(j).getOption(x).getValue().getId());
            			stOpzioni_pers.executeUpdate("INSERT INTO opzioni_pers (idoptstandard, idstay, idoptionlist) VALUES "
            					+ "("+it.getStayTemplate(i).getStayTemplate(j).getOption(x).getId()+","
            					+ idStay +","+ it.getStayTemplate(i).getStayTemplate(j).getOption(x).getValue().getId() +")");
            		}
            		sizeLeaf--;
                	j++;
            	}
            	j=0;
            	size--;
            	i++;
            }
            stIt.close();
            stLeaf.close();
            stStay.close();
            stOpzioni_pers.close();
            st.close();
            connessione.close();
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        }
    }
    	
    
    
    
	
}
