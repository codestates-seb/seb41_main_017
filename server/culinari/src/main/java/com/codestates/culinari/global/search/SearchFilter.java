package com.codestates.culinari.global.search;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

@Transactional
@Component
public class SearchFilter {

    public HashMap<String, String> hashFilterMap(String filter) throws UnsupportedEncodingException {
        String filterDecoding = URLDecoder.decode(filter, "UTF-8");
        HashMap<String, String> filterMap = new HashMap<>();
        String[] filterList = filterDecoding.split("\\|");
        if(filter.equals("")) return null;

        for (String s : filterList) {
            int idx = s.indexOf(":");
            String key = s.substring(0, idx);
            String value = s.substring(idx + 1);
            filterMap.put(key, value);
        }
        return filterMap;
    }

    public List<String> listFilter(String filterList) {
        if(filterList == null) return null;
        if(filterList.contains(",")) return List.of(filterList.split(","));
        else return List.of(filterList);
    }
}
