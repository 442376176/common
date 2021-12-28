package com.zcc.entity;

import com.zcc.utils.AnnotationExport;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@Table(name = "t_user")
@NoArgsConstructor
public class User {
    @AnnotationExport(columnName = "id")
    private Integer id;
    @AnnotationExport(columnName = "姓名")
    private String name;
    private String sex;
    private String phone;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
