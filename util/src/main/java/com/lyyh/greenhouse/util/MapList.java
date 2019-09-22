package com.lyyh.greenhouse.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;

public class MapList<K , V> {
    private Map<K, List<V>> map = new HashMap<>();

    private Comparator<V> comparator = null;

    public MapList(Comparator<V> comparator) {
        this.comparator = comparator;
    }

    public MapList() {
		super();
	}

	public boolean put(K k,V v){
        if(map.containsKey(k)){
            return map.get(k).add(v);
        } else {
            ArrayList<V> list = new ArrayList<>();
            boolean add = list.add(v);
            map.put(k, list);
            return add;
        }
    }

    public List<V> get(K k){
        List<V> list = map.get(k);
        if(list == null){
            return null;
        } else {
        	if(null == comparator){
        		return list;
        	}else{
        		Collections.sort(list,comparator);
        		return list;
        	}
        }
    }

    public Set<K> keySet(){
        return this.map.keySet();
    }
    
    public void removeAll(){
    	map.clear();
    }
    public void removeAll(K k){
    	map.remove(k);
    }

    
    public void remove(K k,V v){
    	List<V> list = map.get(k);
    	if(!CollectionUtils.isEmpty(list)){
    		list.remove(v);
    	}
    }
    
    public void remove(V v){
    	Collection<List<V>> values = map.values();
    	if(!CollectionUtils.isEmpty(values)){
    		for (List<V> list : values) {
				list.remove(v);
			}
    	}
    }
    
    public List<V> values(){
    	ArrayList<V> arrayList = new ArrayList<>();
    	Collection<List<V>> values = map.values();
    	for (List<V> list : values) {
			arrayList.addAll(list);
		}
    	return arrayList;
    }
}
