package com.hzh.chapter14.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/17 6:49
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        // 创建广播电台, 放入到Map
        Map<String, HashSet<String>> broadcasts = new HashMap<>();
        // 放入电台
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        Set<String> set3 = new HashSet<>();
        Set<String> set4 = new HashSet<>();
        Set<String> set5 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");
        set4.add("上海");
        set4.add("天津");
        set5.add("杭州");
        set5.add("大连");
        broadcasts.put("k1", new HashSet<>(set1));
        broadcasts.put("k2", new HashSet<>(set2));
        broadcasts.put("k3", new HashSet<>(set3));
        broadcasts.put("k4", new HashSet<>(set4));
        broadcasts.put("k5", new HashSet<>(set5));

        // 所有地区
        Set<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 创建list, 存放选择的电台集合
        List<String> selects = new ArrayList<>();
        // 定义一个临时的集合, 在遍历的过程中, 存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        Set<String> tempSet = new HashSet<>();
        // 定义一个比较集合, tempSet和当前电台比较时, 当前电台也要和未选地区取交集
        Set<String> compareSet = new HashSet<>();
        // 最大值交集的电台key
        String maxKey = null;

        // 直到所有地区都选择了再退出循环
        while (allAreas.size() > 0) {
            maxKey = null;
            // 遍历电台
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                compareSet.clear();
                tempSet.addAll(broadcasts.get(key)); // ***不能用引用, 每次循环需要clear
                tempSet.retainAll(allAreas); // 临时集合和所有剩下的未选的地区去交集, 记住这边不能直接引用retainAll, 会影响broadcasts的数据
                if (tempSet.size() > 0) {
                    if (broadcasts.get(maxKey) == null) { // 没有则maxKey取当前
                        maxKey = key;
                    } else { // 注意比较上一个 broadcasts.get(maxKey).size() 的时候也是需要取交集
                        compareSet.addAll(broadcasts.get(maxKey));
                        compareSet.retainAll(allAreas);
                        if (tempSet.size() > compareSet.size()) {
                            maxKey = key;
                        }
                    }
                }
            }
            // maxKey != null, 则将maxKey加入到selects中
            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }

}
