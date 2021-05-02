package com.zte.auth.utils.execl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class ExcelTableExportParams {

    private String sheetName;

    private String fileName;

    private Collection<?> dataSet;

    private Class<?> cls;

    public ExcelTableExportParams(Collection<?> dataSet,Class<?> cls){
        this.dataSet = dataSet;
        this.cls = cls;
    }
}
