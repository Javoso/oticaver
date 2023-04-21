package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DataUtil {

	
	public static Date adicionarDias(int dias) {
		Date dataTeste = new Date();

		Calendar cal = Calendar.getInstance(); 
		cal.setTime(dataTeste ); 
		cal.add(Calendar.DATE, dias);
		dataTeste = cal.getTime();
		return dataTeste;
	}
	
	public static Date formatarData(String dateString) {
		Date data = new Date();
		try {
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

			data = formatador.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static String formatarDataParaNomeDoArquivo(Date date) {
		String data = null;
		SimpleDateFormat formatador = new SimpleDateFormat("dd_MM_yyyy");
		data = formatador.format(date);
		return data;
	}
	
	public static String formatarDataDeExportacao(Date date) {
		if(date == null)
			date = new Date();
		
		String data = null;
		SimpleDateFormat formatador = new SimpleDateFormat("ddMMyyyy");
		data = formatador.format(date);
		return data;
	}

	public static String formatarData(Date date) {
		String data = null;
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		data = formatador.format(date);
		return data;
	}
	
	public static String formatarDataHora(Date date) {
		String data = null;
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		data = formatador.format(date);
		return data;
	}

	public static Date getDataAtual() {
		Calendar data = Calendar.getInstance();
		data = DateUtils.truncate(data, Calendar.DAY_OF_MONTH);

		return data.getTime();
	}

	public static Date getPrimeiroDiaSemanaAtual() {
		Calendar data = Calendar.getInstance();
		data = DateUtils.truncate(data, Calendar.DAY_OF_MONTH);

		while (data.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			data.add(Calendar.DAY_OF_WEEK, -1);
		}

		return data.getTime();
	}

	public static Date getPrimeiroDiaMesAtual() {
		Calendar data = Calendar.getInstance();
		data = DateUtils.truncate(data, Calendar.DAY_OF_MONTH);
		data.set(Calendar.DAY_OF_MONTH, 1);
		return data.getTime();
	}
	
	public static Date getPrimeiroDiaSemestreAtual() {
		Calendar data = Calendar.getInstance();
		
		if(data.get(Calendar.MONTH) < 6) {
			data.set(Calendar.DAY_OF_MONTH, 1);
			data.set(Calendar.MONTH, 0);
		} else {
			data.set(Calendar.DAY_OF_MONTH, 1);
			data.set(Calendar.MONTH, 6);
		}
		
		return data.getTime();
	}
	
	public static Date getPrimeiroDiaAnoAtual() {
		Calendar data = Calendar.getInstance();
		data.set(Calendar.DAY_OF_MONTH, 1);
		data.set(Calendar.MONTH, 0);
		
		return data.getTime();
	}
	
	Integer inscricaoMunicipalParam = 615024;
	public static Integer retornaDigitoVerificador(Integer inscricaoMunicipalLomg) {
		String inscricaoMunicipal = String.valueOf(inscricaoMunicipalLomg);
		
		Integer tamanhoInscricao = inscricaoMunicipal.length();
		String inscricao = inscricaoMunicipal.toString();
		
		inscricao = Integer.valueOf(inscricao).toString();
		Integer somatorio = 0;
		Integer peso = tamanhoInscricao + 1;
		Integer digito = 0;
		
		for (int i = 0; i < tamanhoInscricao; i++) {
			somatorio += Integer.parseInt(String.valueOf(inscricao.charAt(i))) * peso;
			peso --;
		}
		
		digito = 11 - (somatorio % 11);
		if(digito == 10 || digito == 11) {
			digito = 0;
		}
		return digito;
	}
	
	
	public static void main(String[] args) {
//		System.out.println(getPrimeiroDiaAnoAtual());
		
//		 BigDecimal valor = new BigDecimal(25.30);
//		   System.out.println( new Extenso(valor).toString() );
		
//		System.out.println(retornaDigitoVerificador(615025));

//		System.out.println(formatarData(formatarData("2021-04-01")));
		System.out.println(adicionarDias(3));
		
	}
}
