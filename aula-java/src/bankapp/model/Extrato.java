package bankapp.model;

import java.util.Calendar;

public class Extrato {
	
	private String contaString;
	private String tipoTransacao;
	private Calendar data; 
	private Calendar hora;
	private double valorTransacao; 
	private double saldoAtual;
	

	public Extrato(String contaString, String tipoTransacao, Calendar data, Calendar hora, double valorTransacao, double saldoAtual) {
		// constructor 
		this.contaString = contaString;
		this.tipoTransacao = tipoTransacao;
		this.data = data;
		this.hora = hora;
		this.valorTransacao = valorTransacao;
		this.saldoAtual = saldoAtual;
	}
	
}
