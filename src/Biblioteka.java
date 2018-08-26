
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
import java.util.Collection;
import java.util.Formatter;
import java.util.Iterator;

public class Biblioteka {

	ArrayList<Racun> listaRacuna = new ArrayList<>();
	ArrayList<Knjiga> listaKnjiga = new ArrayList<>();
	ArrayList<DetaljiORacunimaIKnjigama> listaDetalji = new ArrayList<>();
	DetaljiORacunimaIKnjigama detalji = new DetaljiORacunimaIKnjigama();

	public void kreirajRacun(int brojRacuna, String imeKorisnika) {
		Racun racun = new Racun(brojRacuna, imeKorisnika);
		
		//(glupost)
		// DetaljiORacunimaIKnjigama detalji = new DetaljiORacunimaIKnjigama(brojRacuna,
		// imeKorisnika);

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
			// listaDetalji.add(detalji);
			System.out.println("Uspijesno ste kreirali racun.");

		}

		try {

			File fajl1 = new File("Racuni.txt");
			PrintWriter pw1 = new PrintWriter(fajl1);

			for (Racun e : listaRacuna) {

				pw1.println(e.getBrojRacuna() + " " + e.getIme());
				System.out.println();
			}
			pw1.close();

		} catch (Exception ex) {
			System.out.println("nema fajla:");
		}

	}

	public void kreirajKnjigu(int brojKnjige, String imeKnjige) {

		Knjiga knjiga = new Knjiga(brojKnjige, imeKnjige);
		
		//(glupost)
		// DetaljiORacunimaIKnjigama detalji = new DetaljiORacunimaIKnjigama(brojKnjige,
		// imeKnjige);

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
			// listaDetalji.add(detalji);
			System.out.println("Uspjesno ste kreirali knjigu.");

		}

		try {

			File fajl2 = new File("Knjige.txt");
			PrintWriter pw2 = new PrintWriter(fajl2);

			for (Knjiga e : listaKnjiga) {

				pw2.println(e.getBrojKnjige() + " " + e.getImeKnjige());

			}
			pw2.close();
		} catch (Exception ex) {
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
					if (r.getBrojRacuna() == brojRacuna && r.getBrojKnjige() < 3) {
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
					e.setBrojKnjige(e.getBrojKnjige() + 1);
					index = listaRacuna.indexOf(e);
				}

			}

			for (DetaljiORacunimaIKnjigama e : listaDetalji) {

				if (e.getBrojRacuna() == brojRacuna && e.getBrojKnjiga() == brojKnjige) {

					e.setBrojRacuna(brojRacuna);
					e.setBrojKnjiga(brojKnjige);
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

	/*	(glupost)
	 * 
	 * try {

			File fajl1 = new File("Detalji_O_Racunima.txt");
			PrintWriter pw = new PrintWriter(fajl1);

			for (Racun e : listaRacuna) {

				pw.println(e.getBrojRacuna() + " " + e.getIme() + " (" + e.getBrojKnjiga() + " " + e.getImeKnjige()
						+ ") ");
				System.out.println();
			}
			pw.close();

		} catch (Exception ex) {
			System.out.println("nema fajla:");
		}
*/
	}

	public void DetaljiORacunima(int brojRacuna, String ime, int brojKnjige, String imeKnjige) throws FileNotFoundException {

		java.util.Date datumPosudjivanja2 = new java.util.Date();
		int index2 = 0;
		
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
				e.setStatus(true);
				e.setDatumPosudnjivanja(datumPosudjivanja2);
			}

		}
		for (Racun e : listaRacuna) {
			if (e.getBrojRacuna() == brojRacuna) {
				e.setBrojKnjige(e.getBrojKnjige() + 1);
				index2 = listaRacuna.indexOf(e);
				e.setIme(ime);
				e.setBrojKnjige(brojKnjige);
				e.setImeKnjige(imeKnjige);
			}

		}
		
		}
		
		try {

			File fajl1 = new File("Detalji_O_Racunima.txt");
			PrintWriter pw = new PrintWriter(fajl1);

			for (Racun e : listaRacuna) {

				pw.println(e.getBrojRacuna() + " " + e.getIme() + " (" + e.getBrojKnjige() + " " + e.getImeKnjige()
						+ ") ");
				System.out.println();
			}
			pw.close();

		} catch (Exception ex) {
			System.out.println("nema fajla:");
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

	
	// (jos jedna glupost pokusaj necega al nek bude i ovo tu bit ce izbrisano)
	public void Detalji() throws FileNotFoundException {

		DetaljiORacunimaIKnjigama detalji = new DetaljiORacunimaIKnjigama();

		try {

			File fajl3 = new File("Detalji.txt");
			PrintWriter pw11 = new PrintWriter(fajl3);

			for (Racun e : listaRacuna) {

				pw11.println(e.getBrojRacuna() + " " + e.getIme() + " " + e.getBrojKnjige() + " " + e.getImeKnjige());

			}

			pw11.close();

		} catch (Exception ex) {
			System.out.println("nema fajla:");
		}

		/* (neka glupost al nek bude tu za sada) nevolim brista stvari dok program nije gotov kasnije ih se rijesim
		 * File fajl = new File("Knjige.txt"); try {
		 * 
		 * 
		 * PrintWriter upisUFajl = new PrintWriter(fajl);
		 * 
		 * upisUFajl.print (brojKnjige + " "); upisUFajl.print (imeKnjige);
		 * upisUFajl.close();
		 * 
		 * } catch (IOException ex) {
		 * 
		 * System.out.println("greska: "); }
		 */
	}

}
