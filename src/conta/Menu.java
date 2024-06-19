package conta;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import conta.controlle.ContaController;
import conta.util.Cores;
 
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
public class Menu {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        ContaController contas = new ContaController();
        
		int opcao,tipo,aniversario,agencia,numero,numeroDestino;
		String titular;
		double saldo,limite,valor;
				
		while(true) {
			exibirMenu();
				
			try {
				opcao = sc.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				sc.nextLine();
				opcao=0;
			}
			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				sobre();
				sc.close();
				System.exit(0);
			}
				
			switch (opcao) {
				case 1:
					System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");
					System.out.println("\nDigite o número da Agência: ");
					agencia = sc.nextInt();
					
					System.out.println("Digite o nome do titular: ");
					sc.skip("\\R?");
					titular = sc.nextLine();
					
					do {
						System.out.println("\nDigite o tipo da conta (1-CC ou 2-CP): ");
						tipo = sc.nextInt();
					}while(tipo <1 && tipo<2);
					System.out.println("Digite o saldo da conta (R$): ");
					saldo = sc.nextDouble();
					
					switch (tipo) {
						case 1 ->{
						System.out.println("Digite o limite do credito: (R$) ");
						limite = sc.nextDouble();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(),agencia,tipo,titular,saldo,limite));
						}
						case 2 ->{
						System.out.println("Digite o aniversário da conta (R$): ");
						aniversario = sc.nextInt();
						contas.cadastrar(new ContaPoupanca(contas.gerarNumero(),agencia,tipo,titular,saldo,aniversario));
						}
					}
					keyPress();
                    break;
				case 2:
					System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
					contas.listarTodas();
					keyPress();
                    break;
				case 3:
					System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");
					numero = sc.nextInt();
					contas.procurarPorNumero(numero);
					keyPress();	
                    break;
				case 4:
					System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");
					System.out.println("\nDigite o número da conta: ");
					numero = sc.nextInt();
					
					var buscaConta = contas.buscarNaColection(numero);
					if (buscaConta != null) {
						
						
						tipo = buscaConta.getTipo();
						System.out.println("\nDigite o número da Agência: ");
						agencia = sc.nextInt();
						
						System.out.println("Digite o nome do titular: ");
						sc.skip("\\R?");
						titular = sc.nextLine();
						
						System.out.println("Digite o saldo da conta (R$): ");
						saldo = sc.nextDouble();
						switch (tipo) {
						case 1 ->{
						System.out.println("Digite o limite do credito: (R$) ");
						limite = sc.nextDouble();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(),agencia,tipo,titular,saldo,limite));
						}
						case 2 ->{
						System.out.println("Digite o aniversário da conta (R$): ");
						aniversario = sc.nextInt();
						contas.cadastrar(new ContaPoupanca(contas.gerarNumero(),agencia,tipo,titular,saldo,aniversario));
						}
						default -> {
							System.out.println("Opção inválida!");
							}
						}
						
					}else {
						 System.out.println("\nA conta não foi encontrada!");
					}
					keyPress();	
                    break;
                    
				case 5:
					System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");
					System.out.println("\nDigite o número da conta: ");
					numero = sc.nextInt();
					contas.deletar(numero);
					keyPress();	
                   	break;
                   	
				case 6:
					System.out.println(Cores.TEXT_WHITE + "Saque\n\n");
					System.out.println("\nDigite o número da conta: ");
					numero = sc.nextInt();
					
					do {
						System.out.println("\nDigite o valor do saque (R$): ");
						valor = sc.nextDouble();
					}while(valor <= 0);
					contas.sacar(numero, valor);
					keyPress();	
                   	break;
                   	
				case 7:
					System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");
					System.out.println("\nDigite o número da conta: ");
					numero = sc.nextInt();
					
					do {
						System.out.println("\nDigite o valor do deposito (R$): ");
						valor = sc.nextDouble();
					}while(valor <= 0);
					contas.sacar(numero, valor);
					
					keyPress();	
                    break;
				case 8:
					System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");
					System.out.println("\nDigite o número da conta de Origem: ");
					numero = sc.nextInt();
					System.out.println("\nDigite o número da conta de Destino: ");
					numeroDestino = sc.nextInt();
					
					do {
						System.out.println("\nDigite o valor da transferência (R$): ");
						valor = sc.nextDouble();
					}while(valor <= 0);
					contas.transferir(numero,numeroDestino ,valor);
					
					keyPress();	
                    break;
				default:
					System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
					keyPress();	
                    break;
			}
		}	
    }


    
    public static void keyPress(){
		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();
		} catch (IOException e) {
			System.out.println("Você pressionou uma tecla diferente de enter!");
		}
	}
    
   public static void exibirMenu() {

		System.out.println(Cores.TEXT_BLUE_UNDERLINED + Cores.ANSI_BLACK_BACKGROUND
				+ "*****************************************************");
		System.out.println("                                                     ");
		System.out.println("                BANCO DO BRAZIL COM Z                ");
		System.out.println("                                                     ");
		System.out.println("*****************************************************");
		System.out.println("                                                     ");
		System.out.println("            1 - Criar Conta                          ");
		System.out.println("            2 - Listar todas as Contas               ");
		System.out.println("            3 - Buscar Conta por Numero              ");
		System.out.println("            4 - Atualizar Dados da Conta             ");
		System.out.println("            5 - Apagar Conta                         ");
		System.out.println("            6 - Sacar                                ");
		System.out.println("            7 - Depositar                            ");
		System.out.println("            8 - Transferir valores entre Contas      ");
		System.out.println("            9 - Sair                                 ");
		System.out.println("                                                     ");
		System.out.println("*****************************************************");
		System.out.println("Entre com a opção desejada:                          ");
		System.out.println("                                                     " + Cores.TEXT_RESET);
   }
   
   public static void sobre() {
	System.out.println("\n*********************************************************");
	System.out.println("Projeto Desenvolvido por: ");
	System.out.println("Generation Brasil - generation@generation.org");
	System.out.println("github.com/conteudoGeneration");
	System.out.println("*********************************************************");
  }

}