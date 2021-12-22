package bankapp.view;

import java.util.Locale;
import java.util.Scanner;

import bankapp.model.Conta;
import bankapp.controller.ContaService;

public class Menu {
	public void menuGeral() {
		Scanner input = new Scanner(System.in);
		int escolha = 0;
		Conta conta = new Conta();
		Conta contaDestino = new Conta();
		ContaService conta1 = new ContaService();
		
		while (escolha != 5) {
			System.out.println(
					"O que deseja fazer?\n 1 - Deposito \n 2 - Saque \n 3 - Transferência \n 4 - Extrato \n 5 - Sair");

			escolha = input.nextInt();
			if (escolha == 1) {
				this.deposito(conta, conta1);
			}
			if (escolha == 2) {
				this.saque(conta, conta1);
			}
			if (escolha == 3) {
				this.transferencia(contaDestino, conta1, conta);
			}
			if (escolha == 4) {

			} else {
				System.out.println("");
			}
		}
		System.out.println("Operação cancelada pelo usuário!");
	}

	public void deposito(Conta conta, ContaService conta1) {
		System.out.println("Qual valor deseja depositar? \n");
		Scanner input = new Scanner(System.in);
		input.useLocale(Locale.ENGLISH);
		double deposito;
		deposito = input.nextDouble();
		if (deposito > 0) {
			conta1.depositar(conta, deposito);
			System.out.println("O valor atual do saldo é: " + conta.getSaldo());
		} else {
			System.out.println("Saldo insuficiente para esta transação!");
		}
	}
	public void saque(Conta conta, ContaService conta1) {
		System.out.println("Qual valor deseja sacar? \n");
		Scanner input = new Scanner(System.in);
		input.useLocale(Locale.ENGLISH);
		
		int saque;
		saque = (int) input.nextDouble();
		if (saque <= conta.getSaldo()) {
			conta1.sacar(conta, saque);
			System.out.println("O valor atual do saldo é: " + conta.getSaldo());
		} else {
			System.out.println("Saldo insuficiente para esta transação!");
		}
	}
	public void transferencia(Conta conta, ContaService conta1, Conta contaDestino) {
		System.out.println("Qual valor deseja transferir? \n");
		Scanner input = new Scanner(System.in);
		input.useLocale(Locale.ENGLISH);
		double transferencia;
		transferencia = input.nextDouble();
		if (transferencia <= conta.getSaldo()) {
			conta1.transferir(conta, transferencia, contaDestino);
			System.out.println("Transferência de R$" + transferencia + " realizada com sucesso!");
			System.out.println("O valor atual do saldo é: " + conta.getSaldo());
		} else {
			System.out.println("Saldo insuficiente para esta transação!");
		}
	}
	
}
