
import java.util.InputMismatchException;
import java.util.Scanner;

public class BibliotekaTest {

	public static void main(String[] args) throws Exception {

		Scanner unos = new Scanner(System.in);

		Biblioteka b = new Biblioteka();

		int opcija = 1;
		int brojRacuna, brojKnjige;

		while (opcija != 0) {

			System.out.println();
			System.out.println("Izaberite jednu od opcija: ");
			System.out.println(
					"1. Kreiraj racun\n2. Kreiraj knjigu\n3. Podigni knjigu\n4. Vrati knjigu\n5. Ispis racuna\n6. Ispis knjia\n0. Izlaz");

			try {

				opcija = unos.nextInt();
				int opcija1 = unosOpcije(opcija);

				if (opcija1 == 1) {

					System.out.println("Unesite broj racuna: ");
					brojRacuna = unos.nextInt();

					unos.nextLine();

					System.out.println("Unesite ime korisnika");
					String ime = unos.nextLine();

					b.kreirajRacun(brojRacuna, ime);
				}
				if (opcija1 == 2) {

					System.out.println("Unesite broj knjige: ");
					brojKnjige = unos.nextInt();

					unos.nextLine();

					System.out.println("Unesite ime knjige: ");
					String imeKnjige = unos.nextLine();

					b.kreirajKnjigu(brojKnjige, imeKnjige);

				}

				if (opcija1 == 3) {

					System.out.println("Unesite broj racuna: ");
					brojRacuna = unos.nextInt();

					unos.nextLine();

					System.out.println("Unesite broj knjige");
					brojKnjige = unos.nextInt();

					b.podigniKnjigu(brojRacuna, brojKnjige);

				}

				if (opcija1 == 4) {

					boolean uspjesno = false;
					
					while (uspjesno)
					try {
					System.out.println("Unesite broj racuna: ");
					brojRacuna = unos.nextInt();

					unos.nextLine();

					System.out.println("Unesite broj knjige");
					brojKnjige = unos.nextInt();

					b.vratiKnjigu(brojRacuna, brojKnjige);
					
					uspjesno = true;
					
					} catch (InputMismatchException ex) {
						
						System.out.println("Pogresan unos: ");
						uspjesno = false;
						
						unos.nextLine();
					}
				}

				if (opcija1 == 5) {
					System.out.println("Detalji o postojecim racunima: ");
					b.ispisDetaljaORacunima();

				}

				if (opcija1 == 6) {
					System.out.println("Detalji o postojeæim knjigama: ");
					b.ispisDetaljaOKnjigama();

				}
				
				if (opcija1 == 0) {
					
					System.exit(0);
				}
				
			} catch (InputMismatchException ex) {

				System.out.println("Pogresan unos: ");

				unos.nextLine();

			}catch (IllegalArgumentException ex) {
				
				System.out.println("pogresan unos: ");
				
				unos.hasNextLine();
			}
		}
		unos.close();
	}

	public static int unosOpcije(int opcija) {

		if (opcija < 0 || opcija > 6) {

			throw new IllegalArgumentException("Pogresan unos: ");
		}
		

		return opcija;

	}

}
