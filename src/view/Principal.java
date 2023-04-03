/*
RESUMO      : Classe de execução, simulando um sistema de multiprocessamento.
PROGRAMADORA: Luiza Felix
DATA        : 02/04/2023
 */

package view;

import javax.swing.JOptionPane;

import controller.Supercalculadora;

public class Principal {

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Para iniciar a execução do projeto, aperte OK.");
		
		for(int i = 1; i<=21; i++) {
			Thread thread = new Supercalculadora(i);
			thread.start();
		}

	}

}
