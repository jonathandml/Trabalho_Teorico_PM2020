package entities;

import exceptions.ContaException;


public abstract class Conta {
	private int numero;
	protected double saldo;
	private String titular;
	private String cpf;
	
	
	
	public Conta(int numero, double saldo, String titular, String cpf) {
		this.numero = numero;
		this.saldo = saldo;
		this.titular = titular;
		this.cpf = cpf;
	}
	
	public Conta(int numero, String titular, String cpf) {
		this.numero = numero;
		this.titular = titular;
		this.cpf = cpf;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public String getTitular() {
		return titular;
	}
	
	public void setTitular(String titular) {
		this.titular = titular;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void depositar(double quantia) {
		if(quantia <= 0)
			throw new ContaException("A quantia não pode ser 0 ou negativa");
		saldo += quantia;
		
	}
	
	public void sacar(double quantia) {
		if(quantia <= 0)
			throw new ContaException("A quantia não pode ser 0 ou negativa");
		if(saldo < quantia)
			throw new ContaException("Saldo Insuficiente!");
		saldo -= quantia;
		
	}
	
	public void transferir(double quantia, Conta destino) {
		if(quantia <= 0)
			throw new ContaException("A quantia não pode ser 0 ou negativa");
		if(quantia > saldo)
			throw new ContaException("Saldo Insuficiente!");
		
		destino.saldo += quantia;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Numero: " + numero + "\n");
		s.append("Titular: " + titular + ", cpf: " + cpf + "\n");
		s.append("Saldo: " + saldo);
		return s.toString();
	}
	
	
	
	
	
}
