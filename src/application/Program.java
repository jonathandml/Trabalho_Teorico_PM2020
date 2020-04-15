package application;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import entities.Conta;
import entities.ContaCorrente;
import entities.ContaPoupanca;
import exceptions.SaqueException;

public class Program {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Map<Integer, Conta> contas = new TreeMap<Integer, Conta>();
		int op = 1;
		int numConta;
		double quantia;
		Conta temp;

		while (op != 0) {
			System.out.println("Digite uma opção: ");
			System.out.println("1 - Abrir conta");
			System.out.println("2 - Depositar");
			System.out.println("3 - Sacar");
			System.out.println("4 - Transferir");
			System.out.println("5 - Virar mês");
			System.out.println("0 - Sair");
			
			try {
				op = in.nextInt();
				switch (op) {
					case 1:
						System.out.println("Digite o nome do titular: ");
						String titular = in.nextLine();
						System.out.println("Digite o numero da conta: ");
						numConta = in.nextInt();
						System.out.println("Digite o cpf do titular: ");
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
					case 2:
						System.out.println("Digite o numero da conta: ");
						numConta = in.nextInt();
						temp = contas.get(numConta);
						System.out.println(temp);
						System.out.println("Digite a quantia a ser depositada: ");
						quantia = in.nextDouble();
						temp.depositar(quantia);
						System.out.println("Saldo: " + temp.getSaldo());
						temp = null;					
					case 3:
						System.out.println("Digite o numero da conta: ");
						numConta = in.nextInt();
						temp = contas.get(numConta);
						System.out.println(temp);
						System.out.println("Digite a quantia a ser sacada: ");
						quantia = in.nextDouble();
						temp.sacar(quantia);
						System.out.println("Saldo: " + temp.getSaldo());
						temp = null;
						break;
					case 4:
						break;
					case 5:
						break;
					case 0:
						break;
					default:
						System.out.println("Opção Inválida");
						break;
				}
			} catch (SaqueException e) {
				System.out.println(e.getMessage());
			}finally{
				in.close();
			}
		}

	}

}
