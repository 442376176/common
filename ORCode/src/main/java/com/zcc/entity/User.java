package com.zcc.entity;

import com.zcc.utils.AnnotationExport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @AnnotationExport(columnName = "id")
    private int id;
    @AnnotationExport(columnName = "姓名")
    private String name;
    private String sex;

}
