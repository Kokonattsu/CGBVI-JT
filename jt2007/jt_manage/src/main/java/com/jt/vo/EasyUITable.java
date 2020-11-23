package com.jt.vo;

import com.jt.pojo.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)

public class EasyUITable implements Serializable {
    private static final long serialVersionUID = -1457566221011950429L;
    private Long total;
    private List<?> rows;
}
