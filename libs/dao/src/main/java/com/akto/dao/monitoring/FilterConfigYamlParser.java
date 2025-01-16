package com.akto.dao.monitoring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.akto.dao.api_protection_parse_layer.AggregationLayerParser;
import com.akto.dao.test_editor.filter.ConfigParser;
import com.akto.dto.api_protection_parse_layer.AggregationRules;
import com.akto.dto.monitoring.FilterConfig;
import com.akto.dto.test_editor.ConfigParserResult;
import com.akto.dto.test_editor.ExecutorConfigParserResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class FilterConfigYamlParser {

    public static FilterConfig parseTemplate(String content, boolean shouldParseExecutor) throws Exception {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        Map<String, Object> config = mapper.readValue(content, new TypeReference<Map<String, Object>>() {
        });
        return parseConfig(config, shouldParseExecutor);
    }

    public static FilterConfig parseConfig(Map<String, Object> config,boolean shouldParseExecutor) throws Exception {

        FilterConfig filterConfig = null;
        boolean isFilterError = false;

        String id = (String) config.get(FilterConfig.ID);
        if (id == null) {
            return filterConfig;
        }

        Object filterMap = config.get(FilterConfig.FILTER);
        if (filterMap == null) {
            isFilterError = true;
            filterConfig = new FilterConfig(id, null, null, null);
        }

        ConfigParser configParser = new ConfigParser();
        ConfigParserResult filters = configParser.parse(filterMap);
        if (filters == null) {
            // todo: throw error
            isFilterError = true;
            filterConfig = new FilterConfig(id, null, null, null);
        }

        Map<String, List<String>> wordListMap = new HashMap<>();
        try {
            if (config.containsKey(FilterConfig.WORD_LISTS)) {
                wordListMap = (Map) config.get(FilterConfig.WORD_LISTS);
            }
        } catch (Exception e) {
            isFilterError = true;
            filterConfig = new FilterConfig(id, filters, null, null);
        }
        if(!isFilterError){
            filterConfig =  new FilterConfig(id, filters, wordListMap, null);
        }

        if(shouldParseExecutor){
            com.akto.dao.test_editor.executor.ConfigParser executorConfigParser = new com.akto.dao.test_editor.executor.ConfigParser();
            Object executionMap = config.get("execute");
            if(executionMap == null){
                return filterConfig;
            }
            ExecutorConfigParserResult executorConfigParserResult = executorConfigParser.parseConfigMap(executionMap);
            filterConfig.setExecutor(executorConfigParserResult);
        }

        AggregationLayerParser parser = new AggregationLayerParser();
        AggregationRules aggRules = null;
        try {
            aggRules = parser.parse(config);
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (filterConfig != null) {
            filterConfig.setAggregationRules(aggRules);
        }

        return filterConfig;
    }

}
