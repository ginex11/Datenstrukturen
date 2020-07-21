package TreeMap;

import ADTMap.Map;

public class TMapMain {
    public static void main(String[] args) throws Exception {
        Map<Integer,String>map=TreeMap.empty();
        map=map.insert(2,"s");
        map=map.insert(3,"1");
        System.out.println(map.delete(3));
    }
}
