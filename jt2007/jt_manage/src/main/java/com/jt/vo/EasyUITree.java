package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITree implements Serializable {
    private static final long serialVersionUID = -6026585273875121549L;

    private Long id;
    private String text;
    private String state;
   // private List<EasyUITree> cid;
}
