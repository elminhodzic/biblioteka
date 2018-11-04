import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Biblioteka {

	ArrayList<Racun> listaRacuna = new ArrayList<>();
	ArrayList<Knjiga> listaKnjiga = new ArrayList<>();
	ArrayList<DetaljiORacunimaIKnjigama> listaDetalji = new ArrayList<>();
	DetaljiORacunimaIKnjigama detalji = new DetaljiORacunimaIKnjigama();

	boolean ispravnost = false;

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

		try {

			File fajlRacuni = new File("Racuni.txt");
			PrintWriter printanjeRacuni = new PrintWriter(fajlRacuni);

			for (Racun e : listaRacuna) {

				printanjeRacuni.println(e.getBrojRacuna() + " " + e.getIme());
				System.out.println();
			}
			printanjeRacuni.close();

		} catch (Exception ex) {
			System.out.println("nema fajla:");
		}

	}

	public void kreirajKnjigu(int brojKnjige, String imeKnjige) {
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

		try {

			File fajlKnjiga = new File("Knjige.txt");
			PrintWriter printanjeKnjiga = new PrintWriter(fajlKnjiga);

			for (Knjiga e : listaKnjiga) {

				printanjeKnjiga.println(e.getBrojKnjige() + " " + e.getImeKnjige());

			}
			printanjeKnjiga.close();

		} catch (Exception ex) {
			System.out.println("nema fajla:");
		}

	}

	public void podigniKnjigu(int brojRacuna, int brojKnjige) throws FileNotFoundException {
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
					if (r.getBrojRacuna() == brojRacuna && r.getBrojKnjige() < 3) {
						validanRacun = true;
					}
				}
			}
		}

		if (validanRacun && validnoPodizanje) {
			Date datumPosudjivanja = new Date();

			File opsti = new File("opsti.txt");
			PrintWriter printanjeOpsti = new PrintWriter(opsti);

			
			
			for (Racun e : listaRacuna) {

				if (e.getBrojRacuna() == brojRacuna) {

					e.setBrojKnjige(e.getBrojKnjige() + 1);
					index = listaRacuna.indexOf(e);
					
					//printanjeOpsti.print(e.getBrojRacuna() + " " + e.getIme() + " ");
					printanjeOpsti.write(e.getBrojRacuna() + " " + e.getIme() + " ");
				}

			}

			
			for (Knjiga e : listaKnjiga) {

				if (e.getBrojKnjige() == brojKnjige) {

					e.setStatus(true);
					e.setDatumPosudnjivanja(datumPosudjivanja);
					
					printanjeOpsti.write(e.getBrojKnjige() + " " + e.getImeKnjige() + " " + e.getDatumPosudnjivanja());
					System.out.println();
					
					
				}

			}
			System.out.println();
			printanjeOpsti.close();

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
					e.setBrojKnjige(e.getBrojKnjige() - 1);
				}
			}

			System.out.println("Knjiga uspijesno vracena");
		} else {
			System.out.println("Pogresan unos.");
		}
	}

	public void ispisDetaljaORacunima() throws FileNotFoundException {

		for (Racun e : listaRacuna) {
			System.out.println();
			System.out.println("Broj racuna: " + e.getBrojRacuna());
			System.out.println("Ime: " + e.getIme());
			System.out.println("Broj knjiga: " + e.getBrojKnjige());

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

	public void nekiDetalji() throws FileNotFoundException {

		File fajlDetalji = new File("detaljcici.txt");
		PrintWriter p = new PrintWriter(fajlDetalji);

		p.println("broj racuna " + "     ime musterije   " + "     broj Knjige   " + "      ime Knjige    "
				+ "      datum podiznja");

		if (ispravnost) {

			for (Racun e : listaRacuna) {

				p.print(e.getBrojRacuna() + " " + e.getIme() + " ");
			}

			for (Knjiga e : listaKnjiga) {

				p.print(e.getBrojKnjige() + " " + e.getImeKnjige() + " ");

				if (e.isStatus()) {
					p.print(e.getDatumPosudnjivanja());

				} else {

					System.out.println("Knjiga nije izdata.");
				}
			}
			System.out.println();

		}
		p.close();
	}

}
