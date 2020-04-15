package application;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import entities.Conta;
import entities.ContaCorrente;
import entities.ContaPoupanca;
import exceptions.ContaException;

public class Program {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Map<Integer, Conta> contas = new TreeMap<Integer, Conta>();
		int op = 1;
		int numConta;
		double quantia;
		Conta temp;

		while (op != 0) {
			try {
				System.out.println("Digite uma opção: ");
				System.out.println("1 - Abrir conta");
				System.out.println("2 - Depositar");
				System.out.println("3 - Sacar");
				System.out.println("4 - Transferir");
				System.out.println("5 - Virar mês");
				System.out.println("6 - Consultar Saldo");
				System.out.println("0 - Sair");
				op = in.nextInt();
				switch (op) {
					//abrir conta
					case 1:
						System.out.println("Digite o nome do titular: ");
						in.nextLine();
						String titular = in.nextLine();
						System.out.println("Digite o numero da conta: ");
						numConta = in.nextInt();
						if(contas.containsKey(numConta))
							throw new ContaException("Este número de conta já existe!!");
						System.out.println("Digite o cpf do titular: ");
						in.nextLine();
						String cpf = in.nextLine();
						System.out.println("Escolha o tipo de conta: ");
						System.out.println("1 - Conta corrente");
						System.out.println("2 - Conta poupança");
						int tipo = in.nextInt();
						if(tipo == 1) {
							System.out.println("Digite o limite do cheque especial: ");
							double lim = in.nextDouble();
							temp = new ContaCorrente(numConta, titular, cpf, lim);
						}else if(tipo == 2) {
							temp = new ContaPoupanca(numConta, titular, cpf);
						}else {
							System.out.println("Opção Inválida");
							break;
						}
						contas.put(numConta, temp);
						break;
					//depositar
					case 2:
						System.out.println("Digite o numero da conta: ");
						numConta = in.nextInt();
						if(!contas.containsKey(numConta))
							throw new ContaException("Esta conta não existe!!");
						temp = contas.get(numConta);
						System.out.println(temp);
						System.out.println("Digite a quantia a ser depositada: ");
						quantia = in.nextDouble();
						temp.depositar(quantia);
						System.out.println("Saldo: " + temp.getSaldo());
						temp = null;
						break;
					//sacar
					case 3:
						System.out.println("Digite o numero da conta: ");
						numConta = in.nextInt();
						if(!contas.containsKey(numConta))
							throw new ContaException("Esta conta não existe!!");
						temp = contas.get(numConta);
						System.out.println(temp);
						System.out.println("Digite a quantia a ser sacada: ");
						quantia = in.nextDouble();
						temp.sacar(quantia);
						System.out.println("Saldo: " + temp.getSaldo());
						temp = null;
						break;
					//transferir
					case 4:
						System.out.println("Digite o numero da conta do depositante: ");
						numConta = in.nextInt();
						if(!contas.containsKey(numConta))
							throw new ContaException("Esta conta não existe!!");
						temp = contas.get(numConta);
						System.out.println("Digite o numero da conta de destino: ");
						numConta = in.nextInt();
						if(!contas.containsKey(numConta))
							throw new ContaException("Esta conta não existe!!");
						Conta destino = contas.get(numConta);
						System.out.println("Digite a quantia a ser sacada: ");
						quantia = in.nextDouble();
						temp.transferir(quantia, destino);
						temp = null;
						break;
					//Aplicar rendimentos
					case 5:
						for(int n : contas.keySet()) {
							if(contas.get(n) instanceof ContaPoupanca) {
								ContaPoupanca cp = (ContaPoupanca) contas.get(n);
								cp.aplicarRendimento();
							}
						}
						break;
					//verifica o saldo
					case 6:
						System.out.println("Digite o numero da conta: ");
						numConta = in.nextInt();
						System.out.println(contas.get(numConta));
						break;
					case 0:
						break;
					default:
						System.out.println("Opção Inválida");
						break;
				}
			} catch (ContaException e) {
				System.out.println(e.getMessage());
			}
		}
		in.close();

	}

}
