package rzk;

import java.util.List;

import javax.ejb.Remote;

import model.Ogla;

@Remote
public interface UserBeanRemote {
	public String login(String username, String password);
	public void ispisi();
	public List<Ogla> pretrazi(String text);
	public void dodavanjeOglasa(String text);
	public Ogla javljanjeNaOglas(String text, int idOglas);
}
