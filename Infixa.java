package calculadora;

import java.util.Stack;

public class Infixa {

// Função para calcular expressão
	public static int calcularInfixa(String expressao) {
		/*
		 * Lavínia Rocha Leite 
		 * 3° Período
		 */
		Stack<Integer> pilha = new Stack<>();
		for (int i = 0; i < expressao.length(); i++) {
			char atual = expressao.charAt(i);

			if (Character.isDigit(atual)) { // Se for um digito, adiciona ao operando
				int operando = Character.getNumericValue(atual); // Operando transforma em um valor numerico
				pilha.push(operando);

			} else if (isOperador(atual)) {// Se o caractere atual for um operador (Considerados na metodo)
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
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	public static String converterParaPosfixa(String expressao) {
		String notacaoPosfixa = "";
		Stack<Character> pilha = new Stack<>();

		for (int i = 0; i < expressao.length(); i++) {
			char atual = expressao.charAt(i);

			if (Character.isDigit(atual)) {// Se for um dígito, adiciona à notação posfixa
				notacaoPosfixa += atual;

			} else if (atual == '(') {// Se for um parêntese de abertura, empilha
				pilha.push(atual);

			} else if (atual == ')') {// Se for um parêntese de fechamento, desempilha todos os operadores
				// até encontrar o parêntese de abertura
				while (!pilha.isEmpty() && pilha.peek() != '(') {
					notacaoPosfixa += pilha.pop();
				}
				pilha.pop(); // Remove o parêntese de abertura

			} else {// Se for um operador, desempilha os operadores de maior ou igual precedência
				// antes de empilhar o operador atual
				while (!pilha.isEmpty() && prioridade(pilha.peek()) >= prioridade(atual)) {
					notacaoPosfixa += pilha.pop();
				}
				pilha.push(atual); // Empilha o operador atual
			}
		}
		while (!pilha.isEmpty()) {
			notacaoPosfixa += pilha.pop();
		}

		return notacaoPosfixa;
	}

	public static String converterParaPrefixa(String expressao) {
		String notacaoPrefixa = "";
		Stack<Character> pilha = new Stack<>();

		// Inverter a expressão antes de processá-la
		expressao = new StringBuilder(expressao).reverse().toString();
		for (int i = 0; i < expressao.length(); i++) {
			char atual = expressao.charAt(i);

			if (Character.isDigit(atual)) {// Se for um dígito, adiciona diretamente à notação pré-fixa
				notacaoPrefixa += atual;

			} else if (atual == ')') {// Se for um parêntese de fechamento, empilha
				pilha.push(atual);

			} else if (atual == '(') {// Se for um parêntese de abertura, desempilha todos os operadores
				// até encontrar o parêntese de fechamento
				while (!pilha.isEmpty() && pilha.peek() != ')') {
					notacaoPrefixa += pilha.pop();
				}
				pilha.pop(); // Remove o parêntese de fechamento

			} else {
				// Se for um operador, desempilha os operadores de maior ou igual precedência
				// antes de empilhar o operador atual
				while (!pilha.isEmpty() && prioridade(pilha.peek()) > prioridade(atual)) {
					notacaoPrefixa += pilha.pop();
				}
				pilha.push(atual);
			}
		}
		while (!pilha.isEmpty()) {
			notacaoPrefixa += pilha.pop();
		}

		// Inverter a notação pré-fixa antes de retornar
		return new StringBuilder(notacaoPrefixa).reverse().toString();
	}

	public static int prioridade(char c) {
		switch (c) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		default:
			return -1;
		}
	}

}
