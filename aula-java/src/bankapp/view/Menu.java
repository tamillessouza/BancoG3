package bankapp.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import bankapp.model.Conta;
import bankapp.controller.ContaService;

public class Menu {
	public void menuGeral() {

		int escolha = 0;
		Conta conta = new Conta();
		Conta contaDestino = new Conta();
		ContaService conta1 = new ContaService();

		while (escolha != 5) {
			Scanner input = new Scanner(System.in);
			System.out.println(
					"O que deseja fazer?\n 1 - Deposito \n 2 - Saque \n 3 - Transferência \n 4 - Agendamento Transferencia \n 5 - Sair");

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
				this.agendamentoTransferencia(contaDestino, conta1, conta);
			} else {
				System.out.println("Opcao invalida!");
			}
		}
		LocalDateTime horaTransacao = LocalDateTime.now();
		ZoneId fusoHorario = ZoneId.of("America/Sao_Paulo");
		ZonedDateTime hora = ZonedDateTime.of(horaTransacao, fusoHorario);

		System.out.println("Operação cancelada pelo usuário! \n " + hora);
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

	public void agendamentoTransferencia(Conta conta, ContaService conta1, Conta contaDestino) {
		System.out.println("Qual valor deseja transferir? \n");
		Scanner input = new Scanner(System.in);
		input.useLocale(Locale.ENGLISH);
		double valorAgendamento;
		valorAgendamento = input.nextDouble();
		if (valorAgendamento <= conta.getSaldo()) {
			System.out.println("Digite a data desejada");
			LocalDate dataAgendamento = LocalDate.parse("2022-01-01");
			LocalDate dataAtual = LocalDate.now();
			if (dataAgendamento.isAfter(dataAtual)) {
				if (dataAgendamento.isBefore(dataAtual.plusYears(1))) {
					System.out.println("Agendamento máximo de um mês após a data atual!");
					System.exit(0);
				}
				if (dataAgendamento.getDayOfWeek().ordinal() == 5 || dataAgendamento.getDayOfWeek().ordinal() == 6) {
					System.out.println("A data escolhida é um final de semana!");
				} else {
					conta1.agendar(conta, valorAgendamento, contaDestino);
					Calendar hora = Calendar.getInstance();
					Integer horaAtual = hora.get(Calendar.HOUR_OF_DAY);
					Integer minAtual = hora.get(Calendar.MINUTE);
					Integer segAtual = hora.get(Calendar.SECOND);
					Integer milAtual = hora.get(Calendar.MILLISECOND);
					System.out.println("Agendamento de R$ " + valorAgendamento + " realizado em: " + dataAtual
							+ " Horario: " + horaAtual + ":" + minAtual + ":" + segAtual + ":" + milAtual);
					System.out.println("O valor atual do saldo é: " + conta.getSaldo());
				}
			} else {
				System.out.println("Data inválida");
			}

		}else {
			System.out.println("Saldo insuficiente para esta transação!");
		}
	}

}
