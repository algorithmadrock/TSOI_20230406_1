/*
RESUMO      : Classe das threads de multiprocessamento (as chamei de supercalculadoras pq elas só fazem cálculos e registram as coisas no banco de dados)
PROGRAMADORA: Luiza Felix
DATA        : 02/04/2023
 */

package controller;

import java.util.concurrent.Semaphore;

public class Supercalculadora extends Thread {

	private int ID, cmin, cmax,tmax;
	private static Semaphore acesso = new Semaphore(1);

	public Supercalculadora(int ID) {
		this.ID = ID;
	}

	@Override
	public void run() {
		
		switch (ID%3) {
		case 0:
			cmin = 1000;
			cmax = 1001;
			tmax = 1501;
			id(3);
			break;
			
		case 1:
			cmin = 500;
			cmax = 1001;
			tmax = 1501;
			id(3);
			break;
		
		case 2:
			cmin = 200;
			cmax = 1001;
			tmax = 1001;
			id(2);
			break;
		}
	}

	private void id(int voltas) {
		if(voltas != 0) {
			calculos(cmin,cmax);
			transacao(tmax);
			
			id(voltas-1);
		}
	}

	private void calculos(int min, int max) {
		int tempo = (int)(Math.random()*max + min);
		try {
			System.out.println("ID#" + ID + " comecou seus calculos.");
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void transacao(int max) {
		try {
			acesso.acquire();
			System.out.println("ID#" + ID + " comecou uma transacaoo no banco de dados.");
			int tempo = (int) (Math.random()*max);
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			acesso.release();
		}	
	}
}
