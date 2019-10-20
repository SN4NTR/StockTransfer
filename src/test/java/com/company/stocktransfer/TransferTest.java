package com.company.stocktransfer;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransferTest {

    @Test
    public void countRealStocks_unrestrictedStock() {
        List<MaterialStock> materialStocks = new ArrayList<>();

        MaterialStock materialStock = new MaterialStock();
        materialStock.setId(1);
        materialStock.setBlockedStock(BigDecimal.valueOf(0));
        materialStock.setQualityInspectionStock(BigDecimal.valueOf(0));
        materialStock.setUnrestrictedStock(BigDecimal.valueOf(123.45678));
        materialStocks.add(materialStock);

        List<MovedStock> movedStocks = new ArrayList<>();

        MovedStock movedStock = new MovedStock();
        movedStock.setId(1);
        movedStock.setMoveType("201");
        movedStock.setMovTypeSumResult(BigDecimal.valueOf(87.654321));
        movedStocks.add(movedStock);

        List<MaterialStock> expected = new ArrayList<>();

        MaterialStock materialExpected = new MaterialStock();
        materialExpected.setId(1);
        materialExpected.setBlockedStock(BigDecimal.valueOf(0));
        materialExpected.setQualityInspectionStock(BigDecimal.valueOf(0));
        materialExpected.setUnrestrictedStock(BigDecimal.valueOf(211.111101));
        expected.add(materialExpected);

        List<MaterialStock> actual = Transfer.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countRealStocks_qualityInspectionStock() {
        List<MaterialStock> materialStocks = new ArrayList<>();

        MaterialStock materialStock = new MaterialStock();
        materialStock.setId(1);
        materialStock.setBlockedStock(BigDecimal.valueOf(0));
        materialStock.setQualityInspectionStock(BigDecimal.valueOf(123.45678));
        materialStock.setUnrestrictedStock(BigDecimal.valueOf(0));
        materialStocks.add(materialStock);

        List<MovedStock> movedStocks = new ArrayList<>();

        MovedStock movedStock = new MovedStock();
        movedStock.setId(1);
        movedStock.setMoveType("323");
        movedStock.setMovTypeSumResult(BigDecimal.valueOf(87.654321));
        movedStocks.add(movedStock);

        List<MaterialStock> expected = new ArrayList<>();

        MaterialStock materialExpected = new MaterialStock();
        materialExpected.setId(1);
        materialExpected.setBlockedStock(BigDecimal.valueOf(0));
        materialExpected.setQualityInspectionStock(BigDecimal.valueOf(211.111101));
        materialExpected.setUnrestrictedStock(BigDecimal.valueOf(0));
        expected.add(materialExpected);

        List<MaterialStock> actual = Transfer.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countRealStocks_blockedStock() {
        List<MaterialStock> materialStocks = new ArrayList<>();

        MaterialStock materialStock = new MaterialStock();
        materialStock.setId(1);
        materialStock.setBlockedStock(BigDecimal.valueOf(123.45678));
        materialStock.setQualityInspectionStock(BigDecimal.valueOf(0));
        materialStock.setUnrestrictedStock(BigDecimal.valueOf(0));
        materialStocks.add(materialStock);

        List<MovedStock> movedStocks = new ArrayList<>();

        MovedStock movedStock = new MovedStock();
        movedStock.setId(1);
        movedStock.setMoveType("325");
        movedStock.setMovTypeSumResult(BigDecimal.valueOf(87.654321));
        movedStocks.add(movedStock);

        List<MaterialStock> expected = new ArrayList<>();

        MaterialStock materialExpected = new MaterialStock();
        materialExpected.setId(1);
        materialExpected.setBlockedStock(BigDecimal.valueOf(211.111101));
        materialExpected.setQualityInspectionStock(BigDecimal.valueOf(0));
        materialExpected.setUnrestrictedStock(BigDecimal.valueOf(0));
        expected.add(materialExpected);

        List<MaterialStock> actual = Transfer.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countRealStocks_unsortedLists() {
        List<MaterialStock> materialStocks = new ArrayList<>();

        MaterialStock materialStockOne = new MaterialStock();
        materialStockOne.setId(1);
        materialStockOne.setBlockedStock(BigDecimal.valueOf(123.45678));
        materialStockOne.setQualityInspectionStock(BigDecimal.valueOf(0));
        materialStockOne.setUnrestrictedStock(BigDecimal.valueOf(0));

        MaterialStock materialStockTwo = new MaterialStock();
        materialStockTwo.setId(2);
        materialStockTwo.setBlockedStock(BigDecimal.valueOf(0));
        materialStockTwo.setQualityInspectionStock(BigDecimal.valueOf(123.45678));
        materialStockTwo.setUnrestrictedStock(BigDecimal.valueOf(0));

        MaterialStock materialStockThree = new MaterialStock();
        materialStockThree.setId(3);
        materialStockThree.setBlockedStock(BigDecimal.valueOf(0));
        materialStockThree.setQualityInspectionStock(BigDecimal.valueOf(0));
        materialStockThree.setUnrestrictedStock(BigDecimal.valueOf(123.45678));

        materialStocks.add(materialStockThree);
        materialStocks.add(materialStockOne);
        materialStocks.add(materialStockTwo);

        List<MovedStock> movedStocks = new ArrayList<>();

        MovedStock movedStockOne = new MovedStock();
        movedStockOne.setId(1);
        movedStockOne.setMoveType("326");
        movedStockOne.setMovTypeSumResult(BigDecimal.valueOf(87.654321));

        MovedStock movedStockTwo = new MovedStock();
        movedStockTwo.setId(2);
        movedStockTwo.setMoveType("324");
        movedStockTwo.setMovTypeSumResult(BigDecimal.valueOf(87.654321));

        MovedStock movedStockThree = new MovedStock();
        movedStockThree.setId(3);
        movedStockThree.setMoveType("262");
        movedStockThree.setMovTypeSumResult(BigDecimal.valueOf(87.654321));

        movedStocks.add(movedStockTwo);
        movedStocks.add(movedStockOne);
        movedStocks.add(movedStockThree);

        List<MaterialStock> expected = new ArrayList<>();

        MaterialStock materialExpectedOne = new MaterialStock();
        materialExpectedOne.setId(1);
        materialExpectedOne.setBlockedStock(BigDecimal.valueOf(211.111101));
        materialExpectedOne.setQualityInspectionStock(BigDecimal.valueOf(0));
        materialExpectedOne.setUnrestrictedStock(BigDecimal.valueOf(0));

        MaterialStock materialExpectedTwo = new MaterialStock();
        materialExpectedTwo.setId(2);
        materialExpectedTwo.setBlockedStock(BigDecimal.valueOf(0));
        materialExpectedTwo.setQualityInspectionStock(BigDecimal.valueOf(211.111101));
        materialExpectedTwo.setUnrestrictedStock(BigDecimal.valueOf(0));

        MaterialStock materialExpectedThree = new MaterialStock();
        materialExpectedThree.setId(3);
        materialExpectedThree.setBlockedStock(BigDecimal.valueOf(0));
        materialExpectedThree.setQualityInspectionStock(BigDecimal.valueOf(0));
        materialExpectedThree.setUnrestrictedStock(BigDecimal.valueOf(211.111101));

        expected.add(materialExpectedOne);
        expected.add(materialExpectedTwo);
        expected.add(materialExpectedThree);

        List<MaterialStock> actual = Transfer.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }
}