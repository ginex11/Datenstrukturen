package ADTMap;

import ADTListe.List;

import static ADTListe.List.list;

public class Map_main {
    public static void main(String[] args) {
        List<Tuple<Integer, String>> list = list(new Tuple<>(1, "Kost"));
        Map<Integer, String> map = ListMap.empty();
        Map<Integer, String> map2 = ListMap.empty();
        map = map.insert(2, "ka");
        map=map.insert(2,"k");
        System.out.println(map.insert(3, "samuel"));
        System.out.println(map.delete(3));
        System.out.println(map);

    }
}
