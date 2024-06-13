package com.fdmgroup.courierapp.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Regions {

    List<String> d1 = Arrays.asList("01", "02", "03", "04", "05", "06");
    List<String> d2 = Arrays.asList("07", "08");
    List<String> d3 = Arrays.asList("14", "15", "16");
    List<String> d4 = Arrays.asList("09", "10");
    List<String> d5 = Arrays.asList("11", "12", "13");
    List<String> d6 = List.of("17");
    List<String> d7 = Arrays.asList("18", "19");
    List<String> d8 = Arrays.asList("20", "21");
    List<String> d9 = Arrays.asList("22", "23");
    List<String> d10 = Arrays.asList("24", "25", "26", "27");
    List<String> d11= Arrays.asList("28", "29", "30");
    List<String> d12= Arrays.asList("31", "32", "33");
    List<String> d13 = Arrays.asList("34", "35", "36", "37");
    List<String> d14 = Arrays.asList("38", "39", "40", "41");
    List<String> d15 = Arrays.asList("42", "43", "44", "45");
    List<String> d16 = Arrays.asList("46", "47", "48");
    List<String> d17 = Arrays.asList("49", "50", "81");
    List<String> d18 = Arrays.asList("51", "52");
    List<String> d19 = Arrays.asList("53", "54", "55", "82");
    List<String> d20 = Arrays.asList("56", "57");
    List<String> d21= Arrays.asList("58", "59");
    List<String> d22= Arrays.asList("60", "61", "62", "63", "64");
    List<String> d23= Arrays.asList("65", "66", "67", "68");
    List<String> d24= Arrays.asList("69", "70", "71");
    List<String> d25= Arrays.asList("72", "73");
    List<String> d26= Arrays.asList("77", "78");
    List<String> d27= Arrays.asList("75", "76");
    List<String> d28= Arrays.asList("79", "80");

    List<String> central = Stream.of(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d21)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    List<String> north = Stream.of(d25, d26, d27)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    List<String> northeast = Stream.of(d19, d20, d28)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    List<String> east = Stream.of(d16, d17, d18)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    List<String> west = Stream.of(d22, d23, d24)
            .flatMap(List::stream)
            .collect(Collectors.toList());

    public HashMap<RegionEnum, List<String>> regionsHash = new HashMap<>() {{
        put(RegionEnum.NORTH, north);
        put(RegionEnum.NORTHEAST, northeast);
        put(RegionEnum.EAST, east);
        put(RegionEnum.WEST, west);
        put(RegionEnum.CENTRAL, central);
    }};
}
