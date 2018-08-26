import java.util.Date;

public class DetaljiORacunimaIKnjigama {

	private int brojRacuna;
	private String imeKorisnika;
	private int brojKnjiga;
	private String imeKnjige;
	private boolean status;
	private Date datumPosudnjivanja;

	
	
	
	DetaljiORacunimaIKnjigama() {

	}
	
	
	public DetaljiORacunimaIKnjigama(int brojRacuna, String imeKorisnika, int brojKnjige, String imeKnjige, boolean status) {

	}
	
	public DetaljiORacunimaIKnjigama(int brojRacuna, String imeKorisnika) {
		this.brojRacuna = brojRacuna;
		this.imeKorisnika = imeKorisnika;
	}
	
	public DetaljiORacunimaIKnjigama(int brojKnjiga, String imeKnjige, boolean status) {
		
	}


	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getDatumPosudnjivanja() {
		return datumPosudnjivanja;
	}

	public void setDatumPosudnjivanja(Date datumPosudnjivanja) {
		this.datumPosudnjivanja = datumPosudnjivanja;
	}

	public int getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(int brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public String getImeKorisnika() {
		return imeKorisnika;
	}

	public void setImeKorisnika(String ime) {
		this.imeKorisnika = ime;
	}

	public int getBrojKnjiga() {
		return brojKnjiga;
	}

	public void setBrojKnjiga(int brojKnjiga) {
		this.brojKnjiga = brojKnjiga;
	}

	public String getImeKnjige() {
		return imeKnjige;
	}

	public void setImeKnjige(String imeKnjige) {
		this.imeKnjige = imeKnjige;
	}


	@Override
	public String toString() {
		return "DetaljiORacunimaIKnjigama [brojRacuna=" + brojRacuna + ", imeKorisnika=" + imeKorisnika
				+ ", brojKnjiga=" + brojKnjiga + ", imeKnjige=" + imeKnjige + ", status=" + status
				+ ", datumPosudnjivanja=" + datumPosudnjivanja + "]";
	}

}
