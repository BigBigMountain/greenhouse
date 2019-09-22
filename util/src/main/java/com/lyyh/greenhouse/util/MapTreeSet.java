package com.lyyh.greenhouse.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class MapTreeSet<K,V> {

    private Map<K,TreeSet<V>> map = new HashMap<K,TreeSet<V>>();

    private Comparator<V> comparator;

    public MapTreeSet(Comparator<V> comparator) {
        this.comparator = comparator;
    }

    public boolean put(K k,V v){
        if(this.map.containsKey(k)){
            return map.get(k).add(v);
        } else {
            TreeSet<V> treeSet = new TreeSet<>(comparator);
            boolean add = treeSet.add(v);
            this.map.put(k, treeSet);
            return add;
        }
    }

    public TreeSet<V> get(K k){
        return this.map.get(k);
    }





}
