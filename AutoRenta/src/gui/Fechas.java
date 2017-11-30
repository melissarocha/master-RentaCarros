/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author eopg9
 */
public class Fechas {

    public Fechas() {
    }
    
    public static String getFechaActual() {
        Date ahora = new Date();
        //Formato de Fecha de MySQL
        SimpleDateFormat formateador = new SimpleDateFormat("yy-MM-dd");
        return formateador.format(ahora);
    }
    
    
    public static synchronized int diferenciasEntreFechas(Date fechaInicial, Date fechaFinal)
    {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        try {
            fechaInicial = df.parse(fechaInicioString);
        } catch (ParseException ex) {
        }

        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaFinal = df.parse(fechaFinalString);
        } catch (ParseException ex) {
        }

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }
    
    public static String sumarFechasDias(Date fecha, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fecha.getTime());
        cal.add(Calendar.DATE, dias);
        SimpleDateFormat formateador = new SimpleDateFormat("yy-MM-dd");
        String FechaFinal = formateador.format(cal.getTime());
        return FechaFinal;
    }
}
