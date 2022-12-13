import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Pokedex implements Serializable{

    private Map<Integer, List<Object>> pokedex = new HashMap<>();

    public Pokedex(File nPokedex){
        {

            try {
                FileInputStream file = new FileInputStream(nPokedex);
                try (Workbook workbook = new XSSFWorkbook(file)) {
                    Sheet sheet = workbook.getSheetAt(0);
                    int i = 1;
                    for (Row row : sheet) {
                        pokedex.put(i, new ArrayList<Object>());
                        for (Cell cell : row) {
                            switch (cell.getCellType()) {
                                case STRING:
                                    pokedex.get(Integer.valueOf(i)).add(cell.getRichStringCellValue().getString());
                                    break;
                                case NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        pokedex.get(i).add(cell.getDateCellValue());
                                    } else {
                                        pokedex.get(i).add(cell.getNumericCellValue());
                                    };
                                    break;
                                case FORMULA:
                                    pokedex.get(i).add(cell.getBooleanCellValue()); break;
                                default: pokedex.get(Integer.valueOf(i)).add(" ");
                            }
                        }
                        i++;
                    }
                }
                
            } catch (Exception e) {
                System.out.println("Erreur" + e);
            }
        }
    }
    public Map<Integer, List<Object>> getPokedex() {
        return pokedex;
    }
}


