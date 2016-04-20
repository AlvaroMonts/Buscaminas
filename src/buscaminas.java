import java.util.Scanner;

/**
 * @author Alvaro Buscaminas casero y algo cutre
 * 
 */
public class buscaminas {
	Scanner sc = new Scanner(System.in);
	String dificultad;
	int numMinas, alto, ancho, bomba;
	int mapa[][] = null;

	public buscaminas() {
		System.out.println("Bienvenido al Buscaminas!!");
		System.out.println("Elija la dificultad");
		dificultad = sc.next();
		if (dificultad.equals("Principiante")) {
			numMinas = 10;
			alto = 9;
			ancho = 9;
		} else if (dificultad.equals("Intermedio")) {
			numMinas = 40;
			alto = 16;
			ancho = 16;
		} else if (dificultad.equals("Avanzado")) {
			numMinas = 99;
			alto = 16;
			ancho = 30;
		} else if (dificultad.equals("Personalizado")) {
			System.out.println("¿Cuantas minas desea poner?");
			numMinas = sc.nextInt();
			System.out.println("¿Cual seria el alto del mapa?");
			alto = sc.nextInt();
			System.out.println("¿Cual seria el ancho del mapa?");
			ancho = sc.nextInt();
		}
		bomba = alto * ancho;
		System.out.println("Hay " + numMinas
				+ " minas, y el mapa tiene un tamaño de " + alto + "x" + ancho
				+ " mosaicos");
		mapa = new int[alto][ancho];
	}

	public void distribuirBombas() {
		for (int a = 0; a < alto; a++) {
			for (int b = 0; b < ancho; b++) {
				mapa[a][b] = 0;
			}
		}
		int aleaAlto;
		int aleaAncho;
		for (int a = 0; a < numMinas; a++) {
			aleaAlto = (int) (Math.random() * alto);
			aleaAncho = (int) (Math.random() * ancho);
			if (mapa[aleaAlto][aleaAncho] < bomba) {
				mapa[aleaAlto][aleaAncho] = bomba;
				// contadores de alrededor
				if (aleaAncho + 1 < ancho && aleaAlto + 1 < alto)
					mapa[aleaAlto + 1][aleaAncho + 1]++;
				if (aleaAlto + 1 < alto)
					mapa[aleaAlto + 1][aleaAncho]++;
				if (aleaAlto + 1 < alto && aleaAncho - 1 >= 0)
					mapa[aleaAlto + 1][aleaAncho - 1]++;
				if (aleaAncho + 1 < ancho)
					mapa[aleaAlto][aleaAncho + 1]++;
				if (aleaAncho - 1 >= 0)
					mapa[aleaAlto][aleaAncho - 1]++;
				if (aleaAlto - 1 >= 0 && aleaAncho + 1 < ancho)
					mapa[aleaAlto - 1][aleaAncho + 1]++;
				if (aleaAlto - 1 >= 0)
					mapa[aleaAlto - 1][aleaAncho]++;
				if (aleaAlto - 1 >= 0 && aleaAncho - 1 >= 0)
					mapa[aleaAlto - 1][aleaAncho - 1]++;
			}
		}
		// quitar despues (muestra mapa 'codificado')
		for (int a = 0; a < alto; a++) {
			for (int b = 0; b < ancho; b++) {
				System.out.print(mapa[a][b] + " ");
			}
			System.out.println();
		}
	}

	public void Jugar() {
		boolean bomb;
		int contador = 0;
		int mosaicosNoMinas = (alto * ancho) - numMinas;
		String mapaRes[][] = new String[alto][ancho];
		for (int a = 0; a < alto; a++) {
			for (int b = 0; b < ancho; b++) {
				mapaRes[a][b] = "_";
			}
		}
		System.out.println("Mapa:");
		for (int a = 0; a < alto; a++) {
			System.out.print("|");
			for (int b = 0; b < ancho; b++) {
				System.out.print(mapaRes[a][b] + "|");
			}
			System.out.println();
		}
		do {
			int nAlto, nAncho, nBomba;
			System.out.println("Introduzca Coordenadas:");
			System.out
					.println("**Tenga en cuenta que empieza en (0,0) en la esquina superior izquierda y que se pone ANTES el alto que el ancho");
			System.out.println("Introduzca un alto");
			nAlto = Integer.parseInt(sc.next());
			System.out.println("Introduzca un ancho");
			nAncho = Integer.parseInt(sc.next());
			System.out.println();
			nBomba = mapa[nAlto][nAncho];
			mapaRes[nAlto][nAncho] = mapa[nAlto][nAncho] + "";
			if (nBomba >= bomba) {
				bomb = true;
				mapaRes[nAlto][nAncho] = "M";
				for (int a = 0; a < alto; a++) {
					System.out.print("|");
					for (int b = 0; b < ancho; b++) {
						System.out.print(mapaRes[a][b] + "|");
					}
					System.out.println();
				}
				System.out.println("¡BOOM! Has perdido");
				break;
			} else {
				bomb = false;
				contador++;
				int numAlto = nAlto;
				int numAncho = nAncho;
				// nAncho + 1 < ancho && nAlto + 1 < alto
				// nAlto - 1 >= 0 && nAncho - 1 >= 0
				// ahora despliega todo lo de alrededor a un 0
				if (mapa[numAlto][numAncho] == 0) {
					if (nAncho + 1 < ancho && nAlto + 1 < alto) {
						if (mapa[nAlto + 1][nAncho + 1] == 0) {
							mapaRes[nAlto + 1][nAncho + 1] = "0";
							contador++;
						} else {
							mapaRes[nAlto + 1][nAncho + 1] = mapa[nAlto + 1][nAncho + 1]
									+ "";
							contador++;
						}

					}
					if (nAlto + 1 < alto) {
						if (mapa[nAlto + 1][nAncho] == 0) {
							mapaRes[nAlto + 1][nAncho] = "0";
							contador++;
						} else {
							mapaRes[nAlto + 1][nAncho] = mapa[nAlto + 1][nAncho]
									+ "";
							contador++;
						}
					}
					if (nAncho - 1 >= 0 && nAlto + 1 < alto) {
						if (mapa[nAlto + 1][nAncho - 1] == 0) {
							mapaRes[nAlto + 1][nAncho - 1] = "0";
							contador++;
						} else {
							mapaRes[nAlto + 1][nAncho - 1] = mapa[nAlto + 1][nAncho - 1]
									+ "";
							contador++;
						}
					}
					if (nAncho + 1 < ancho) {
						if (mapa[nAlto][nAncho + 1] == 0) {
							mapaRes[nAlto][nAncho + 1] = "0";
						} else {
							mapaRes[nAlto][nAncho + 1] = mapa[nAlto][nAncho + 1]
									+ "";
							contador++;
						}
					}
					if (nAncho - 1 >= 0) {
						if (mapa[nAlto][nAncho - 1] == 0) {
							mapaRes[nAlto][nAncho - 1] = "0";
							contador++;
						} else {
							mapaRes[nAlto][nAncho - 1] = mapa[nAlto][nAncho - 1]
									+ "";
							contador++;
						}
					}
					if (nAlto - 1 >= 0 && nAncho + 1 < ancho) {
						if (mapa[nAlto - 1][nAncho + 1] == 0) {
							mapaRes[nAlto - 1][nAncho + 1] = "0";
							contador++;
						} else {
							mapaRes[nAlto - 1][nAncho + 1] = mapa[nAlto - 1][nAncho + 1]
									+ "";
							contador++;
						}
					}
					if (nAlto - 1 >= 0) {
						if (mapa[nAlto - 1][nAncho] == 0) {
							mapaRes[nAlto - 1][nAncho] = "0";
							contador++;
						} else {
							mapaRes[nAlto - 1][nAncho] = mapa[nAlto - 1][nAncho]
									+ "";
							contador++;
						}
					}
					if (nAlto - 1 >= 0 && nAncho - 1 >= 0) {
						if (mapa[nAlto - 1][nAncho - 1] == 0) {
							mapaRes[nAlto - 1][nAncho - 1] = "0";
							contador++;
						} else {
							mapaRes[nAlto - 1][nAncho - 1] = mapa[nAlto - 1][nAncho - 1]
									+ "";
							contador++;
						}
					}
					nAlto = numAlto;
					nAncho = numAncho;
				}
			}
			System.out.println("Mapa:");
			for (int a = 0; a < alto; a++) {
				System.out.print("|");
				for (int b = 0; b < ancho; b++) {
					System.out.print(mapaRes[a][b] + "|");
				}
				System.out.println();
			}
		} while (contador < mosaicosNoMinas);
		if (contador == mosaicosNoMinas) {
			System.out.println("Enhorabuena, has completado el mapa");
		}
	}
		
	public static void main(String[] args) {
		buscaminas mapa = new buscaminas();
		mapa.distribuirBombas();
		mapa.Jugar();
	}
}