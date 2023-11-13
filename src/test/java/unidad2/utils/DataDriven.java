package unidad2.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {
    public static ArrayList<String> getData(String nombreCP) {
        //Crear nuestro arreglo
        ArrayList<String> data = new ArrayList<String>();


        //Crear objeto tipo file con la ruta del excel
        FileInputStream file = null;

        try{
            file = new FileInputStream("C:\\Users\\domingo.saavedra\\Documents\\Selenium2Unidad\\src\\test\\resources\\data\\Data.xlsx");
        }catch (FileNotFoundException ex){
            System.out.println("Ha ocurrido un error cargando el archivo :/");
            System.out.println("Error: "+ ex.getMessage());
        }


        //Crear objeto excel usando como parametro en constructor el file
        XSSFWorkbook excel = null;

        try{
            excel = new XSSFWorkbook(file);
        }catch (IOException ex){
            System.out.println("Ha ocurrido un error interpretando el formato del archivo :/");
            System.out.println("Error: "+ ex.getMessage());
        }

        int sheets = excel.getNumberOfSheets();

        System.out.println("La cantidad de hojas es: "+sheets);

            for(int i= 0; i< sheets;i++){
                if(excel.getSheetName(i).equalsIgnoreCase("SetDatos")){
                    //Encuentro la hoja
                    XSSFSheet hojaExcel = excel.getSheetAt(i);

                    Iterator<Row> filas = hojaExcel.iterator();

                    Row fila = filas.next();

                    Iterator<Cell> celda = fila.cellIterator();

                    int k=0;
                    int columna =0;
                    while(celda.hasNext()){
                        Cell celdaSeleccionada = celda.next();


                        if(celdaSeleccionada.getStringCellValue().equalsIgnoreCase("CasosDePrueba")){
                            //encontramos la columna
                            columna = k++;
                        }
                        k++;
                    }


                    while(filas.hasNext()){
                        Row row = filas.next();

                        if(row.getCell(columna).getStringCellValue().equalsIgnoreCase(nombreCP)){
                            Iterator<Cell> cellIter = row.cellIterator();

                            while (cellIter.hasNext()){
                                Cell cell = cellIter.next();

                                if(cell.getCellType() == CellType.STRING){
                                    //System.out.println(cell.getStringCellValue());
                                    data.add(cell.getStringCellValue());
                                }else if(cell.getCellType() == CellType.NUMERIC){
                                    String valorCelda = NumberToTextConverter.toText(cell.getNumericCellValue());
                                    //System.out.println(valorCelda);
                                    data.add(valorCelda);
                                }
                            }
                        }
                    }
                }
            }
        return data;
    }
}
