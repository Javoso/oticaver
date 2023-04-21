package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatadorData {

	public static String formatar(Date data, String formato) {
		return new SimpleDateFormat(formato).format(data);
	}
	
}
