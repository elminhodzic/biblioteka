import java.util.Date;

public class Racun extends Knjiga {
	
	
	private int brojRacuna;
	private String ime;
	private int brojKnjige;
	private String imeKnjige;
	private Date datumPosudnjivanja;

	Racun() {

	}

	public Racun(int brojRacuna, String ime) {
		this.brojRacuna = brojRacuna;
		this.ime = ime;
	}

	public Racun(int brojRacuna, String ime, int brojKnjige, String imeKnjige) {
		this(brojKnjige, ime);
		this.brojKnjige = brojKnjige;
	}

	public int getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(int brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public int getBrojKnjige() {
		return brojKnjige;
	}

	public void setBrojKnjige(int brojKnjiga) {
		this.brojKnjige = brojKnjiga;
	}
	
	public String racuni () {
		
		return brojRacuna + " " + ime + "\n";
	}

	public String getImeKnjige() {
		return imeKnjige;
	}

	public void setImeKnjige(String imeKnjige) {
		this.imeKnjige = imeKnjige;
	}

	public Date getDatumPosudnjivanja() {
		return datumPosudnjivanja;
	}

	public void setDatumPosudnjivanja(Date datumPosudnjivanja) {
		this.datumPosudnjivanja = datumPosudnjivanja;
	}

}
