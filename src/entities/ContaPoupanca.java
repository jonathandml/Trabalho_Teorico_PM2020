package entities;

public class ContaPoupanca extends Conta{
	
	public static final double TAXA_REND_MENSAL = 0.03;
	
	public ContaPoupanca(int numero, String titular, String cpf) {
		super(numero, titular, cpf);
	}
	
	public void aplicarRendimento() {
		this.saldo += this.saldo * TAXA_REND_MENSAL;
	}


}
