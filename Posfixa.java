package calculadora;

import java.util.Stack;

public class Posfixa {
	/*
	 * Lavínia Rocha Leite
	 * 3° Período
	 */
	public static int calcularPosfixa(String expressao) {
		Stack<Integer> pilha = new Stack<>();
		for (int i = 0; i < expressao.length(); i++) {
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
					resultado = operand1 / operand2;
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

	public static String converterParaInfixa(String expressao) {
		Stack<String> pilha = new Stack<>();
		for (int i = 0; i < expressao.length(); i++) {
			char atual = expressao.charAt(i);

			if (Character.isDigit(atual)) {// Se for um dígito, adiciona diretamente à notação posfixa
				String operando = String.valueOf(atual);
				pilha.push(operando);

			} else if (isOperador(atual)) {
				String operand2 = pilha.pop();
				String operand1 = pilha.pop();
				String resultado = ('(' + operand1 + atual + operand2 + ')');
				pilha.push(resultado);
			}

		}

		return pilha.pop();
	}

	public static String converterParaPrefixa(String expressao) {
		Stack<String> pilha = new Stack<>();
		for (int i = 0; i < expressao.length(); i++) {
			char atual = expressao.charAt(i);

			if (Character.isDigit(atual)) {// Se for um dígito, adiciona diretamente à notação posfixa
				String operando = String.valueOf(atual);
				pilha.push(operando);

			} else if (isOperador(atual)) {
				String operand2 = pilha.pop();
				String operand1 = pilha.pop();
				String resultado = (atual + operand1 + operand2);
				pilha.push(resultado);
			}

		}
		return pilha.pop();
	}

}
