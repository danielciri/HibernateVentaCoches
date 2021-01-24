package Lib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class Utils {
	public static Date parseFecha(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(fecha);
			
		}catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Formato de fecha no valido debe ser yyyy-mm-dd");
			// TODO: handle exception
		}
		return fechaDate;
	}

}
