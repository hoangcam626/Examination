package com.university.examination.repository.common;

import java.util.List;
import java.util.Map;

public interface QueryRepo {

    <T> T query(String sql, Map<String, Object> params, Class<T> classTarget);

    <T> List<T> queryList(String sql, Map<String, Object> params, Class<T> classTarget);


}
