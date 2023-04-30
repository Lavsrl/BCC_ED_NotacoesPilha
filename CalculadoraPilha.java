package calculadora;

import java.util.Scanner;
import java.util.Stack;

public class CalculadoraPilha {
	/*
	 * Lavínia Rocha Leite 
	 * 3° Período
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String calculo;
		
		while (true) {
			System.out.println("Por favor, informe em qual tipo de notação deseja fazer seu cálculo:" 
					+ "\n1 - Infixa"
					+ "\n2 - Pós-fixa" 
					+ "\n3 - Pré-fixa");
			
			int opcoes = sc.nextInt();
			
			switch (opcoes) {
			case 1:
				System.out.println("Digite a expressão: ");
				calculo = sc.next();
				
				String notacaoPosfixa = Infixa.converterParaPosfixa(calculo);
				System.out.println("Notação Pós-fixa: " + notacaoPosfixa);

				String notacaoPrefixa = Infixa.converterParaPrefixa(calculo);
				System.out.println("Notação Pré-fixa: " + notacaoPrefixa);

				int resultado = Infixa.calcularInfixa(notacaoPosfixa);
				System.out.println("Resultado: " + resultado);
				break;
				
			case 2:
				System.out.println("Digite a expressão: ");
				calculo = sc.next();
				
				String notacaoInfixa = Posfixa.converterParaInfixa(calculo);
				System.out.println("Notação Infixa: " + notacaoInfixa);

				notacaoPrefixa = Posfixa.converterParaPrefixa(calculo);
				System.out.println("Notação Pré-fixa: " + notacaoPrefixa);

				resultado = Posfixa.calcularPosfixa(calculo);
				System.out.println("Resultado: " + resultado);
				break;
				
			case 3:
				System.out.println("Digite a expressão: ");
				calculo = sc.next();
				
				notacaoInfixa = Prefixa.converterParaInfixa(calculo);
				System.out.println("Notação Infixa: " + notacaoInfixa);

				notacaoPosfixa = Prefixa.converterParaPosfixa(calculo);
				System.out.println("Notação Pós-Fixa: " + notacaoPosfixa);

				resultado = Prefixa.calcularPrefixa(calculo);
				System.out.println("Resultado: " + resultado);
				break;
				
			default:
				System.out.println("Opção inválida");
			}

		}
	}

}