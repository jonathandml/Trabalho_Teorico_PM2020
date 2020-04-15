package entities;

import exceptions.ContaException;

public class ContaCorrente extends Conta{
	
	double limiteChequeEspecial;
	
	public ContaCorrente(int numero, double saldo, String titular, String cpf, double limiteChequeEspecial) {
		super(numero, saldo, titular, cpf);
		this.limiteChequeEspecial = limiteChequeEspecial;
	}
	
	public ContaCorrente(int numero, String titular, String cpf, double limiteChequeEspecial) {
		super(numero, titular, cpf);
		this.limiteChequeEspecial = limiteChequeEspecial;
	}

	public double getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}

	public void setLimiteChequeEspecial(double limiteChequeEspecial) {
		this.limiteChequeEspecial = limiteChequeEspecial;
	}
	
	@Override
	public void sacar(double quantia) {
		if(limiteChequeEspecial < Math.abs(this.saldo - quantia)) {
			throw new ContaException("Saldo Insuficiente!");
		}
		if(quantia <= 0)
			throw new ContaException("A quantia não pode ser 0 ou negativa");
		this.saldo -= quantia;
	}
	

}
