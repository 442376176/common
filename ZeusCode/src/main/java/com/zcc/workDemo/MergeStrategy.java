package com.zcc.workDemo;

import com.alibaba.excel.metadata.property.LoopMergeProperty;
import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.handler.context.RowWriteHandlerContext;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * @ProjectName: building-lease-contract-income
 * @Package: com.eazytec.excel
 * @ClassName: MergeStrategy
 * @Author: yangjianfei
 * @Description: 自定义合并单元格策略
 * @Date: 2022/2/15 14:31
 * @Version: 1.0
 */
public class MergeStrategy implements RowWriteHandler {
    private int eachRow;
    private int columnExtend;
    private List<Integer> columnIndex;

    public MergeStrategy(int eachRow,  List<Integer>  columnIndex) {
        this(eachRow, 1, columnIndex);
    }

    public MergeStrategy(int eachRow, int columnExtend, List<Integer> columnIndex) {
        if (eachRow < 1) {
            throw new IllegalArgumentException("EachRows must be greater than 1");
        } else if (columnExtend < 1) {
            throw new IllegalArgumentException("ColumnExtend must be greater than 1");
        } else if (columnExtend == 1 && eachRow == 1) {
            throw new IllegalArgumentException("ColumnExtend or eachRows must be greater than 1");
        } else {
            this.eachRow = eachRow;
            this.columnExtend = columnExtend;
            this.columnIndex = columnIndex;
        }
    }

    public MergeStrategy(LoopMergeProperty loopMergeProperty, List<Integer> columnIndex) {
        this(loopMergeProperty.getEachRow(), loopMergeProperty.getColumnExtend(), columnIndex);
    }

    @Override
    public void afterRowDispose(RowWriteHandlerContext context) {
        for (int i = 0; i < columnIndex.size(); i++) {
            if (!context.getHead() && context.getRelativeRowIndex() != null) {
                if (context.getRelativeRowIndex() % this.eachRow == 0) {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(context.getRowIndex(), context.getRowIndex() + this.eachRow - 1, this.columnIndex.get(i), this.columnIndex.get(i) + this.columnExtend - 1);
                    context.getWriteSheetHolder().getSheet().addMergedRegionUnsafe(cellRangeAddress);
                }
            }
        }
    }
}
