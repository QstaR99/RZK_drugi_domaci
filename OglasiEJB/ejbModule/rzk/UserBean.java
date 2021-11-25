package rzk;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Ogla;
import model.OglasKorisnik;
import model.OglasPrijava;
import rzk.interceptors.JavljanjeInterceptor;
import rzk.interceptors.NadInterceptor;
import rzk.interceptors.PregledInterceptor;

/**
 * Session Bean implementation class UserBean
 */
@Stateful
@LocalBean
@Interceptors(NadInterceptor.class)
public class UserBean implements UserBeanRemote {

    /**
     * Default constructor. 
     */
    public UserBean() {
        // TODO Auto-generated constructor stub
    }
    OglasKorisnik user;
    int userId;
    @PersistenceContext
    EntityManager em;
    

	@SuppressWarnings("unchecked")
	@Override
	public String login(String username, String password) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT u FROM OglasKorisnik u WHERE u.username LIKE :username AND u.password LIKE :password",OglasKorisnik.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		List<OglasKorisnik> lista = q.getResultList();
		try {
			user = lista.get(0);
			userId = user.getIdKorisnik();
			System.out.println(this.getClass().getCanonicalName()+": Logged in user with id="+userId);
			return user.getUsername();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}


	@Override
	public void ispisi() {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT u FROM OglasKorisnik u",OglasKorisnik.class);
		List<OglasKorisnik> lista = q.getResultList();
		for(OglasKorisnik k:lista) {
			System.out.println(k.getUsername());
			System.out.println(k.getPassword());
		}
	}
	
	@Override
	@Interceptors(PregledInterceptor.class)
	public List<Ogla> pretrazi(String text) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT o FROM Ogla o WHERE o.text LIKE :search",Ogla.class);
		q.setParameter("search","%"+ text+"%");
		List<Ogla> oglasi = q.getResultList();
		return oglasi;
	}
	
	@Override 
	public void dodavanjeOglasa(String text) {
		Ogla o = new Ogla();
		o.setText(text);
		o.setOglasKorisnik(user);
		System.out.println(o.getIdOglas());
		em.persist(o);
	}


	@Override
	@Interceptors(JavljanjeInterceptor.class)
	public Ogla javljanjeNaOglas(String text, int idOglas) {
		// TODO Auto-generated method stub
		OglasPrijava op = new OglasPrijava();
		Ogla o = em.find(Ogla.class, idOglas);
		op.setOgla(o);
		op.setOglasKorisnik(user);
		op.setText(text);
		em.persist(op);
		return o;
	}
	
	@PostConstruct
	private void create() {
		System.out.println(this.getClass().getSimpleName()+": created");
	}
	@PreDestroy
	private void destroy() {
		System.out.println(this.getClass().getSimpleName()+": destroyed");
	}
}
