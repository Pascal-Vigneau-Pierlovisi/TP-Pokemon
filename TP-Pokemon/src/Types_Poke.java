import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/* Tables des types pokemons, permettant de calculer l'efficacite et resistance en fonction des types */
public class Types_Poke implements Serializable {

    private Map<String, List<String>> efficacite = new HashMap<>();
    private Map<String, List<String>> peuEfficace = new HashMap<>();
    private Map<String, List<String>> neutralite = new HashMap<>();  
    private List<String> listType = new ArrayList<>();  
    

    //Constructeur
    public Types_Poke(){
        {
            try {
                FileInputStream file = new FileInputStream(new File("TP-Pokemon/csv/Pokemon_Type_Chart.xlsx"));
                try (Workbook workbook = new XSSFWorkbook(file)) {
                    Sheet sheet = workbook.getSheetAt(0);
                    int i = 0;
                    for (Row row : sheet) {
                        if (i >= 1){
                            String type = row.getCell(0).getRichStringCellValue().getString();
                            efficacite.put(type, new ArrayList<String>());
                            peuEfficace.put(type, new ArrayList<String>());
                            neutralite.put(type, new ArrayList<String>());
                            listType.add(type);
                            for(Cell cell : row){
                                if(cell.getCellType().equals(CellType.NUMERIC)){
                                    int place = cell.getColumnIndex();
                                    String typeCol = sheet.getRow(0).getCell(place).getRichStringCellValue().getString();
                                    if(compare(cell.getNumericCellValue(), 0.5)){
                                        peuEfficace.get(type).add(typeCol);
                                    }
                                    else if(cell.getNumericCellValue() ==  2){
                                        efficacite.get(type).add(typeCol);
                                    }
                                    else if(cell.getNumericCellValue() ==  0){
                                        neutralite.get(type).add(typeCol);
                                    }
                                    else{
                                        
                                    }
                                }  
                            }
                        }
                        
                        i++;
                    }
                }
                
            } catch (Exception e) {
                
            }
        }
    }

    

    //Getter

    public List<String> getListType() {
        return listType;
    }

    public List<String> getEfficacite(String type) {
        return efficacite.get(type);
    }

    public List<String> getNeutralite(String type) {
        return neutralite.get(type);
    }

    public List<String> getPeuEfficace(String type) {
        return peuEfficace.get(type);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    private boolean compare(double numericCellValue, double d) {
        return false;
    }

}
