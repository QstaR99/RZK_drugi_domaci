package rzk;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ogla;

/**
 * Session Bean implementation class Statistika
 */
@Singleton
@LocalBean
@Startup
public class Statistika implements StatistikaLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext
	EntityManager em;
	
	private HashMap<Integer, Integer> pregledi;
	private int javljanja;
	@SuppressWarnings("unused")
	private Timer timer;
	@Resource
	private TimerService timerService;
	
    public Statistika() {
//         TODO Auto-generated constructor stub
    	this.pregledi=new HashMap<Integer, Integer>();
    	this.javljanja=0;
    	
    }
    

	@PostConstruct
	public void startTimer() {
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setPersistent(false);
		timer = timerService.createIntervalTimer(0, 15 * 60 * 1000, timerConfig);
	}

    
    public void updatePregledi(Ogla o) {
    	if(pregledi.containsKey(o.getIdOglas())) {
    		pregledi.put(o.getIdOglas(), pregledi.get(o.getIdOglas())+1);
    	}else {
    		pregledi.put(o.getIdOglas(), 1);
    	}
    }
    
    public void updateJavljanja() {
    	javljanja++;
    }
    
    @Schedule(persistent = false)
    public void cleanJavljanja() {
    	System.out.println("Broj javljanja u ovom danu je bio: "+javljanja);
    	javljanja = 0;
    }
    
    
    @Timeout
    public void updateDB() {
    	for(Entry<Integer, Integer> e:pregledi.entrySet()) {
    		Ogla o = em.find(Ogla.class, e.getKey());
    		o.setBrojPregleda(o.getBrojPregleda()+e.getValue());
    		em.merge(o);
    	}
    	System.out.println("Baza je apdejtovana!");
    }

}
