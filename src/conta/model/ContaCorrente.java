package conta.model;
 	
public class ContaCorrente extends Conta{

	private double limite;

	public ContaCorrente(int numero, int agencia, int tipo, String titular, double saldo, double limite) {
			super(numero, agencia, tipo, titular, saldo);
			this.limite = limite;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
	@Override
	public boolean sacar(double valor) { 
		
		if(this.getSaldo() + this.getLimite() < valor) {
			System.out.println("\n Saldo Insuficiente!");
			return false;
		}
		
		this.setSaldo(this.getSaldo() - valor);
		return true;
		
	}
	
    @Override
	public void visualizar() {
		super.visualizar();
		System.out.println("Limite de Crédito: " + this.limite);
	}	
}
