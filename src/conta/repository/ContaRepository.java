package conta.repository;
import conta.model.Conta;
public interface ContaRepository {
	
	public void procurarPorNumero(int numero);
	public void listarTodas();
	public void cadastrar(Conta conta);
	public void atualizar(Conta conta);
	public void deletar(int numero);
	
	// Métodos Bancários
	public void sacar(int numero, double valor);
	public void depositar(int numero, double valor);
	public void transferir(int numeroOrigem, int numeroDestino, double valor);

}
