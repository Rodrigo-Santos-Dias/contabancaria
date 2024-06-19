package conta.controlle;
import conta.model.Conta;
import conta.repository.ContaRepository;
import java.util.ArrayList;


public class ContaController implements ContaRepository {

	private ArrayList <Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;
	@Override
	public void procurarPorNumero(int numero) {
		 var conta  = buscarNaColection(numero);
		 if(conta!=null)  
			conta.visualizar();
		 else
			 System.out.println("\nA conta "+conta+ "não encontrada!");
		 
	}

	@Override
	public void listarTodas() {
		 for(var conta :listaContas) {
			 conta.visualizar();
		 }
		
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\nA conta número: "+conta.getNumero()+" foi criada com sucesso!");
	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaColection(conta.getNumero());
		if (buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\nA conta número: "+conta.getNumero()+" foi atualizada com sucesso!");
		}else
			 System.out.println("\nA conta numero "+conta.getNumero()+ "não foi encontrada!");
	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaColection(numero); 
		if (conta != null) {
			if(listaContas.remove(conta) == true) {
				System.out.println("\nA conta número: "+ numero +" foi atualizada com sucesso!");
			}else 
				 System.out.println("\nA conta numero "+ numero + "não foi encontrada!");
		}
	}

	@Override
	public void sacar(int numero, double valor) {
		var conta = buscarNaColection(numero);
		if (conta != null) {
			if(conta.sacar(valor)==true)
				System.out.println("\\nO saque na  conta número: "+ numero +" foi efetuado com sucesso!");
		}else
			 System.out.println("\nA conta numero "+ numero + "não foi encontrada!");
	}

	@Override
	public void depositar(int numero, double valor) {
		var conta = buscarNaColection(numero); 
		if (conta != null) {
			conta.depositar(valor);
			System.out.println("\\nO Deposito na  conta número: "+ numero +" foi efetuado com sucesso!");
		}else
			System.out.println("\nA conta numero "+ numero + "não foi encontrada ou conta destino não é conta corrente!");
	}
	 

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, double valor) {
		var contaOrigem = buscarNaColection(numero); 
		var contaDestino = buscarNaColection(numero); 
		
		if (contaOrigem != null && contaDestino!=null) {
			if(contaOrigem.sacar(valor)==true) {
				contaDestino.sacar(valor);
				System.out.println("\nA Transferencia foi efetuada com sucesso!");
			}
		}else
			System.out.println("\nA Conta de origem e/ou Destino não foram encontradas!");
	}
	
	public int gerarNumero() {
		return ++numero;
	}
	
	public Conta buscarNaColection(int numero) {
		for(var conta : listaContas) {
			if(conta.getNumero( )== numero) {
				return conta;
			}
		}
		return null;
	}

}
