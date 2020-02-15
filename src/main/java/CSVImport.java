import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVImport {
    ArrayList<String[]> records;
    List<String> names;
    FuzzySearch finder;


    public CSVImport(String path) throws FileNotFoundException {
        records = new ArrayList<>();
        names = new ArrayList<>();
        List<String> names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null){
                String[] values  = line.split(",");
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < records.size(); i++ ){
            if(!records.get(i)[0].equals("")) {
                names.add(records.get(i)[0]);
            }
        }
    }

    public int getLocation(String itemName){
        if(fuzzySearch(itemName, records) == null){return -1;}
        int index = fuzzySearch(itemName, records).getIndex();
        return index;
    }

    public String getPrice(String itemName){
        if(fuzzySearch(itemName, records) == null){return null;}
        int index = fuzzySearch(itemName, records).getIndex();
        String price = records.get(index)[4];
        return price;
    }

    public String getRarity(String itemName){
        if(fuzzySearch(itemName, records) == null){return null;}
        int index = fuzzySearch(itemName, records).getIndex();
        String rarity = records.get(index)[1];
        return rarity;
    }

    public String getAttunement(String itemName){
        if(fuzzySearch(itemName, records) == null){return null;}
        int index = fuzzySearch(itemName, records).getIndex();
        String attunement = records.get(index)[2];
        if(attunement.contains("Yes") || attunement.contains("No")) {
            return attunement;
        }else{
            return "No";
        }
    }


    public Item fuzzySearch(String in, List<String[]> list){

        int index = -1;
        int best = 0;
        for (int i = 0; i < list.size(); i++) {
            int compare = FuzzySearch.ratio(in, list.get(i)[0]);
            System.out.print(compare);
            if (compare > best) {
                index = i;
                best = compare;
            }
        }
        if (best<50 || index == -1){
            return null;
        }
        String name = records.get(index)[0];
        int price = Integer.valueOf(records.get(index)[4]);
        String attunement = records.get(index)[2];
        String rarity = records.get(index)[1];
        String description = " Still in Progress";
        return new Item(name, index, price, attunement, rarity, description);
    }
}
