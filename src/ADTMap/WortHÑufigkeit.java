package ADTMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class WortHäufigkeit {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\kon_m\\Downloads\\Essay.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Map<String, Integer> map = ListMap.empty();
        String text = "";
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            text += currentLine;
        }
        String[] arr = text.split(" ");
        for (String s : arr) {
            s = s.toLowerCase().replaceAll("[_[^\\w\\däüöÄÜÖ\\+\\- ]]", "");
            System.out.println(map);
            if (!map.member(s)) {
                map = map.insert(s, 1);
                continue;
            }
            if (map.member(s)) {
                map = map.insert(s, map.lookup(s) + 1);
            }
        }
        System.out.println(map);
    }
}
