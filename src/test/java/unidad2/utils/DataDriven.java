package unidad2.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataDriven {
    public static void main(String[] args) throws IOException {
        //Crear objeto tipo file con la ruta del excel
        FileInputStream file = new FileInputStream("C:\\Users\\domingo.saavedra\\Documents\\Selenium2Unidad\\src\\test\\resources\\data\\Data.xlsx");

        //Crear objeto excel usando como parametro en constructor el file
        XSSFWorkbook excel = new XSSFWorkbook(file);

        int sheets = excel.getNumberOfSheets();

        System.out.println("La cantidad de hojas es: "+sheets);

            for(int i= 0; i< sheets;i++){
                if(excel.getSheetName(i).equalsIgnoreCase("SetDatos")){
                    //Encuentro la hoja
                    XSSFSheet hojaExcel = excel.getSheetAt(i);
                }
            }

    }
}
