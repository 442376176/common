package com.zcc.utils.sqlBuilder;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zcc.utils.GetAnnotationValue;

import javax.persistence.Table;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zcc
 * @date 2021/07/05
 */

public class SqlBuilder<T> implements Builder<String> {
    // sql字符串拼接
    private final StringBuilder sql = new StringBuilder();

    @Override
    public String build() {
        return this.sql.toString();
    }

    public SqlBuilder builder() {
        return new SqlBuilder();
    }

    public SqlBuilder select(boolean isDistinct, Class t) throws Exception {
        sql.append("select ");
        if (isDistinct) {
            sql.append("distinct ");
        }
        Map<String, Object> stringObjectMap = BeanUtils.AllField(t);
        Iterator<Map.Entry<String, Object>> iterator = stringObjectMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            sql.append(entry.getKey())
                    .append(" ").append("as").append(" ")
                    .append(entry.getValue())
                    .append(",");
        }
        sql.deleteCharAt(sql.lastIndexOf(",")).append(" ");
        return this;
    }

    public SqlBuilder select(boolean isDistinct) {
        sql.append("select ");
        if (isDistinct) {
            sql.append("distinct ");
        }
        sql.append("* ");
        return this;
    }

    public SqlBuilder from(Class clazz) throws Exception {
        sql.append("from ");
        Map<String, Object> classAnnotationValue = GetAnnotationValue.getClassAnnotationValue(clazz, Table.class);
        if (classAnnotationValue.size() > 0) {
            Object name = classAnnotationValue.get("name");
            sql.append(name.toString() + " ");
            return this;
        }
        Map<String, Object> classAnnotationValue1 = GetAnnotationValue.getClassAnnotationValue(clazz, TableName.class);
        if (classAnnotationValue1.size() > 0) {
            Object name = classAnnotationValue.get("value");
            sql.append(name.toString() + " ");
        }
        return this;
    }

    public SqlBuilder from(String tableName) {
        sql.append("from ").append(tableName + " ");
        return this;
    }

    public SqlBuilder where() {
        sql.append(" where ");
        return this;
    }

    public SqlBuilder where(T t) throws Exception {
        sql.append(" where ");
        Map<String, Object> fields = BeanUtils.isNotNullField(t);
        if (fields.size() > 0) {
            sql.append(fields);
            return this;
        }
        return this;
    }

    public SqlBuilder and() {
        sql.append(" and ");
        return this;
    }

    public SqlBuilder append(String str) {
        sql.append(str);
        return this;
    }

    public SqlBuilder orderBy(String field, boolean isDesc) throws Exception {
        if (isDesc) {
            sql.append(" order by ").append(StringUtils.humpToLine2(field)).append(" ").append("desc");
        } else {
            sql.append(" order by ").append(StringUtils.humpToLine2(field));
        }
        return this;
    }

    public SqlBuilder byCondition(Condition condition) {
        sql.append(condition);
        return this;
    }

    public SqlBuilder in(String field, List<?> list) {
        sql.append(StringUtils.humpToLine2(field)).append(" ").append("in").append(" ").append("(");
        if (list.stream().findFirst().orElse(null) instanceof String) {
            for (int i = 0; i < list.size(); i++) {
                sql.append("'").append(list.get(i)).append("',");
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                sql.append(list.get(i)).append(",");
            }
        }
        sql.deleteCharAt(sql.lastIndexOf(",")).append(")");
        return this;
    }

    public SqlBuilder like(String field, Object value) {
        sql.append(StringUtils.humpToLine2(field)).append(" ").append("like").append(" ").append("'")
                .append(value).append("'");
        return this;
    }

    /**
     * SQL中多表关联用的关键字
     *
     * @author Looly
     */
    public enum Join {
        /**
         * 如果表中有至少一个匹配，则返回行
         */
        INNER,
        /**
         * 即使右表中没有匹配，也从左表返回所有的行
         */
        LEFT,
        /**
         * 即使左表中没有匹配，也从右表返回所有的行
         */
        RIGHT,
        /**
         * 只要其中一个表中存在匹配，就返回行
         */
        FULL
    }

    public SqlBuilder join(String tableName, String type) {

        return this;
    }

    public SqlBuilder join(Class clazz, String type) throws Exception {
        StringBuilder tableName = new StringBuilder();
        Map<String, Object> classAnnotationValue = GetAnnotationValue.getClassAnnotationValue(clazz, Table.class);
        if (classAnnotationValue.size() > 0) {
            Object name = classAnnotationValue.get("name");
            tableName.append(name.toString());
        }
//        Join.valueOf()
        return this;
    }

}
