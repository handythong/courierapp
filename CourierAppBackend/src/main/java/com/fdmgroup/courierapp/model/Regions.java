package com.fdmgroup.courierapp.model;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Regions {

    List<Integer> d1 = Arrays.asList(1, 2, 3, 4, 5, 6);
    List<Integer> d2 = Arrays.asList(7, 8);
    List<Integer> d3 = Arrays.asList(14,15, 16);
    List<Integer> d4 = Arrays.asList(9, 10);
    List<Integer> d5 = Arrays.asList(11, 12, 13);
    List<Integer> d6 = List.of(17);
    List<Integer> d7 = Arrays.asList(18, 19);
    List<Integer> d8 = Arrays.asList(20, 21);
    List<Integer> d9 = Arrays.asList(22, 23);
    List<Integer> d10 = Arrays.asList(24, 25, 26, 27);
    List<Integer> d11= Arrays.asList(28, 29, 30);
    List<Integer> d12= Arrays.asList(31, 32, 33);
    List<Integer> d13 = Arrays.asList(34, 35, 36, 37);
    List<Integer> d14 = Arrays.asList(38, 39, 40, 41);
    List<Integer> d15 = Arrays.asList(42, 43, 44, 45);
    List<Integer> d16 = Arrays.asList(46, 47, 48);
    List<Integer> d17 = Arrays.asList(49, 50, 81);
    List<Integer> d18 = Arrays.asList(51, 52);
    List<Integer> d19 = Arrays.asList(53, 54, 55, 82);
    List<Integer> d20 = Arrays.asList(56, 57);
    List<Integer> d21= Arrays.asList(58, 59);
    List<Integer> d22= Arrays.asList(60, 61, 62, 63, 64);
    List<Integer> d23= Arrays.asList(65, 66, 67, 68);
    List<Integer> d24= Arrays.asList(69, 70, 71);
    List<Integer> d25= Arrays.asList(72, 73);
    List<Integer> d26= Arrays.asList(77, 78);
    List<Integer> d27= Arrays.asList(75, 76);
    List<Integer> d28= Arrays.asList(79, 80);

    List<Integer> central = Stream.of(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d21)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    List<Integer> north = Stream.of(d25, d26, d27)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    List<Integer> northeast = Stream.of(d19, d20, d28)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    List<Integer> east = Stream.of(d16, d17, d18)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    List<Integer> west = Stream.of(d22, d23, d24)
            .flatMap(List::stream)
            .collect(Collectors.toList());

    public Dictionary<RegionEnum, List<Integer>> regions = new Hashtable<>() {{
        put(RegionEnum.NORTH, north);
        put(RegionEnum.NORTHEAST, northeast);
        put(RegionEnum.EAST, east);
        put(RegionEnum.WEST, west);
        put(RegionEnum.CENTRAL, central);
    }};
}
