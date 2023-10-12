package com.akto.dao;

import com.akto.dto.ApiInfo;
import com.akto.dto.FilterSampleData;
import com.akto.dto.type.SingleTypeInfo;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;


public class FilterSampleDataDao extends AccountsContextDao<FilterSampleData>{

    public static FilterSampleDataDao instance = new FilterSampleDataDao();

    public List<ApiInfo.ApiInfoKey> getApiInfoKeys() {
        Bson projection = Projections.fields(Projections.include());
        MongoCursor<FilterSampleData> cursor = instance.getMCollection().find().projection(projection).cursor();

        ArrayList<ApiInfo.ApiInfoKey> ret = new ArrayList<>();

        while(cursor.hasNext()) {
            FilterSampleData elem = cursor.next();
            ret.add(elem.getId().getApiInfoKey());
        }

        return ret;
    }

    public static Bson getFilter(ApiInfo.ApiInfoKey apiInfoKey, int filterId) {
        return Filters.and(
                Filters.eq("_id.apiInfoKey.url", apiInfoKey.getUrl()),
                Filters.eq("_id.apiInfoKey.method", apiInfoKey.getMethod()+""),
                Filters.in(SingleTypeInfo._COLLECTION_IDS, apiInfoKey.getApiCollectionId()),
                Filters.eq("_id.filterId", filterId)
        );
    }

    public static Bson getFilterForApiInfoKey(ApiInfo.ApiInfoKey apiInfoKey) {
        return Filters.and(
                Filters.eq("_id.apiInfoKey.url", apiInfoKey.getUrl()),
                Filters.eq("_id.apiInfoKey.method", apiInfoKey.getMethod()+""),
                Filters.in(SingleTypeInfo._COLLECTION_IDS, apiInfoKey.getApiCollectionId())
        );
    }

    @Override
    public String getCollName() {
        return "filter_sample_data";
    }

    @Override
    public Class<FilterSampleData> getClassT() {
        return FilterSampleData.class;
    }
}
