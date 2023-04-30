package calculadora;

import java.util.Stack;

public class Prefixa {
	/*
	 * Lavínia Rocha Leite
	 * 3° Período
	 */
//  int i = expressao.length() - 1; i >= 0; i--
//	loop que percorre a string expressao de trás para frente, começando 
//	pelo último caractere e terminando no primeiro. Isso é necessário para processar uma 	    	
//	notação pré-fixa, onde os operadores são escritos antes dos operandos. 

	public static int calcularPrefixa(String expressao) {
		Stack<Integer> pilha = new Stack<>();
		for (int i = expressao.length() - 1; i >= 0; i--) {
			char atual = expressao.charAt(i);

			if (Character.isDigit(atual)) { // Se for um digito, adiciona ao operando
				int operando = Character.getNumericValue(atual); // Operando transforma em um valor numerico
				pilha.push(operando);

			} else if (isOperador(atual)) {// Se o caractere atual for um operador(Considerados na metodo)
				// desempilha os dois operandos anteriores da pilha, realiza a operação e
				// empilha o resultado
				int operand2 = pilha.pop();
				int operand1 = pilha.pop();
				int resultado;

				switch (atual) {
				case '+':
					resultado = operand1 + operand2;
					break;
				case '-':
					resultado = operand1 - operand2;
					break;
				case '*':
					resultado = operand1 * operand2;
					break;
				case '/':
					resultado = operand2 / operand1;
					break;
				default:
					throw new IllegalArgumentException("Operador inválido: " + atual);
				}
				pilha.push(resultado);
			}
		}
		return pilha.pop(); // O resultado final será o único valor restante na pilha
	}

	public static boolean isOperador(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/') {
			return true;
		} else {
			return false;
		}
	}

	public static String converterParaPosfixa(String expressao) {
		Stack<String> pilha = new Stack<>();
		for (int i = expressao.length() - 1; i >= 0; i--) {
			char atual = expressao.charAt(i);

			if (Character.isDigit(atual)) {
				String digito = String.valueOf(atual);
				pilha.push(digito);

			} else {
				String operando1 = pilha.pop();
				String operando2 = pilha.pop();
				String operacao = operando1 + operando2 + atual;
				pilha.push(operacao);
			}
		}

		return pilha.pop();
	}

	public static String converterParaInfixa(String expressao) {
		Stack<String> pilha = new Stack<>();
		for (int i = expressao.length() - 1; i >= 0; i--) {
			char atual = expressao.charAt(i);

			if (Character.isDigit(atual)) {// Se for um dígito, adiciona diretamente à notação posfixa
				String digito = String.valueOf(atual);
				pilha.push(digito);

			} else if (isOperador(atual)) {
				String operand2 = pilha.pop();
				String operand1 = pilha.pop();
				String resultado = ('(' + operand2 + atual + operand1 + ')');
				pilha.push(resultado);
			}

		}

		return pilha.pop();
	}

}
