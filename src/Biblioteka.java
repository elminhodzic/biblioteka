
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Formatter;

public class Biblioteka {
	
	ArrayList<Racun> listaRacuna = new ArrayList<>();
	ArrayList<Knjiga> listaKnjiga = new ArrayList<>();
	
	

	public void kreirajRacun(int brojRacuna, String imeKorisnika) {
		Racun racun = new Racun(brojRacuna, imeKorisnika);
		boolean validanRacun = true;
		for (Racun e : listaRacuna) {
			if (e.getBrojRacuna() == brojRacuna) {
				validanRacun = false;
				System.out.println("Racun sa brojem " + brojRacuna + " vec postoji.");
			}
		}
		if (brojRacuna < 0) {
			validanRacun = false;
			System.out.println("Nije moguce kreirati racun sa negativnim brojem.");
		}
		if (validanRacun) {
			listaRacuna.add(racun);
			System.out.println("Uspijesno ste kreirali racun.");
			
		}
		
		try{
			
			
			File fajl = new File ("Racuni.txt");
			FileWriter fw = new FileWriter (fajl);
			Writer ispis = new BufferedWriter (fw);
			
			for (Racun e : listaRacuna) {
				
				ispis.write(e.racuni().toString());
				
				
			}
			ispis.close();
			}catch(Exception ex) {
				System.out.println("nema fajla:");
			}
		
		
		
	}

	public void kreirajKnjigu(int brojKnjige, String imeKnjige)  {

		Knjiga knjiga = new Knjiga(brojKnjige, imeKnjige);

		boolean validnaKnjiga = true;
		

		for (Knjiga e : listaKnjiga) {

			if (e.getBrojKnjige() == brojKnjige) {

				validnaKnjiga = false;
				System.out.println("Knjiga sa brojem " + brojKnjige + " vec postoji.");
			}
		}

		if (brojKnjige < 0) {
			validnaKnjiga = false;
			System.out.println("Nije moguce kreirati knjigu sa negativnim brojem.");
		}

		if (validnaKnjiga) {
			listaKnjiga.add(knjiga);
			System.out.println("Uspjesno ste kreirali knjigu.");
			
			
		}
		
try{
			
			
			File fajl = new File ("Knjige.txt");
			FileWriter fw = new FileWriter (fajl);
			Writer ispis = new BufferedWriter (fw);
			
			for (Racun e : listaRacuna) {
				
				ispis.write(e.racuni().toString());
				
				
			}
			ispis.close();
			}catch(Exception ex) {
				System.out.println("nema fajla:");
			}
		
	}

	public void podigniKnjigu(int brojRacuna, int brojKnjige) {
		boolean validnoPodizanje = false;
		boolean validanRacun = false;
		int index = 0;
		for (Knjiga e : listaKnjiga) {
			if (e.getBrojKnjige() == brojKnjige) {
				validnoPodizanje = true;
				if (e.isStatus()) {
					validnoPodizanje = false;
					System.out.println("Knjiga je vec izdata.");
				}
				for (Racun r : listaRacuna) {
					if (r.getBrojRacuna() == brojRacuna && r.getBrojKnjiga() < 3) {
						validanRacun = true;
					}
				}
			}
		}
		if (validanRacun && validnoPodizanje) {
			java.util.Date datumPosudjivanja = new java.util.Date();
			for (Knjiga e : listaKnjiga) {
				if (e.getBrojKnjige() == brojKnjige) {
					e.setStatus(true);
					e.setDatumPosudnjivanja(datumPosudjivanja);
				}
			}
			for (Racun e : listaRacuna) {
				if (e.getBrojRacuna() == brojRacuna) {
					e.setBrojKnjiga(e.getBrojKnjiga() + 1);
					index = listaRacuna.indexOf(e);
				}
			}
			System.out.println("Knjiga uspijesno izdata korisniku " + listaRacuna.get(index).getIme());
		} else {
			System.out.println("Knjiga se ne može izdati.");
		}
	}

	public void vratiKnjigu(int brojRacuna, int brojKnjige) throws Exception {
		boolean validnoVracanje = false;
		boolean validanRacun = false;

		for (Knjiga e : listaKnjiga) {
			if (e.getBrojKnjige() == brojKnjige) {
				validnoVracanje = true;
				if (!e.isStatus()) {
					validnoVracanje = false;
					System.out.println("Knjiga nije izdata korisniku.");
				}
				for (Racun r : listaRacuna) {
					if (r.getBrojRacuna() == brojRacuna) {
						validanRacun = true;
					}
				}
			}
		}
		if (validanRacun && validnoVracanje) {
			for (Knjiga e : listaKnjiga) {
				if (e.getBrojKnjige() == brojKnjige) {
					e.setStatus(false);
				}
			}
			for (Racun e : listaRacuna) {
				if (e.getBrojRacuna() == brojRacuna) {
					e.setBrojKnjiga(e.getBrojKnjiga() - 1);
				}
			}
			System.out.println("Knjiga uspijesno vracena");
		} else {
			System.out.println("Pogresan unos.");
		}
	}

	public void ispisDetaljaORacunima() {
		for (Racun e : listaRacuna) {
			System.out.println();
			System.out.println("Broj racuna: " + e.getBrojRacuna());
			System.out.println("Ime: " + e.getIme());
			System.out.println("Broj knjiga: " + e.getBrojKnjiga());

		}
	}

	public void ispisDetaljaOKnjigama() throws Exception {

		
		for (Knjiga e : listaKnjiga) {
			System.out.println();
			System.out.println("Broj knjige: " + e.getBrojKnjige());
			System.out.println("Ime knjige: " + e.getImeKnjige());
			if (e.isStatus()) {
				System.out.println("Knjiga izdata " + e.getDatumPosudnjivanja());
			} else {
				System.out.println("Knjiga nije izdata.");
			}
		}

	}

	public void upusUFajl(int brojKnjige, String imeKnjige) {
		// TODO Auto-generated method stub
		File fajl = new File("Knjige.txt");
		try {
			
		
		PrintWriter upisUFajl = new PrintWriter(fajl);
		
		upisUFajl.print (brojKnjige + " ");
		upisUFajl.print (imeKnjige);
		upisUFajl.close();
		
		}
		catch (IOException ex) {
			
			System.out.println("greska: ");
		}
	}

	
}
