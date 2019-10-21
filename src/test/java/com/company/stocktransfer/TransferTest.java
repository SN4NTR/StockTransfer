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

        StockId materialStockId = createStockId("0", "0", "1");
        MaterialStock materialStock = createMaterialStock(materialStockId, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(123.45678));
        materialStocks.add(materialStock);

        List<MovedStock> movedStocks = new ArrayList<>();

        StockId movedStockId = createStockId("0", "0", "1");
        MovedStock movedStock = createMovedStock(movedStockId, "201", "87.654321");
        movedStocks.add(movedStock);

        List<MaterialStock> expected = new ArrayList<>();

        StockId expectedStockId = createStockId("0", "0", "1");
        MaterialStock materialExpected = createMaterialStock(expectedStockId, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(211.111101));
        expected.add(materialExpected);

        List<MaterialStock> actual = Transfer.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countRealStocks_qualityInspectionStock() {
        List<MaterialStock> materialStocks = new ArrayList<>();

        StockId materialStockId = createStockId("0", "0", "1");
        MaterialStock materialStock = createMaterialStock(materialStockId, BigDecimal.valueOf(0), BigDecimal.valueOf(123.45678), BigDecimal.valueOf(0));
        materialStocks.add(materialStock);

        List<MovedStock> movedStocks = new ArrayList<>();

        StockId movedStockId = createStockId("0", "0", "1");
        MovedStock movedStock = createMovedStock(movedStockId, "323", "87.654321");
        movedStocks.add(movedStock);

        List<MaterialStock> expected = new ArrayList<>();

        StockId expectedStockId = createStockId("0", "0", "1");
        MaterialStock materialExpected = createMaterialStock(expectedStockId, BigDecimal.valueOf(0), BigDecimal.valueOf(211.111101), BigDecimal.valueOf(0));
        expected.add(materialExpected);

        List<MaterialStock> actual = Transfer.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countRealStocks_blockedStock() {
        List<MaterialStock> materialStocks = new ArrayList<>();

        StockId materialStockId = createStockId("0", "0", "1");
        MaterialStock materialStock = createMaterialStock(materialStockId, BigDecimal.valueOf(123.45678), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        materialStocks.add(materialStock);

        List<MovedStock> movedStocks = new ArrayList<>();

        StockId movedStockId = createStockId("0", "0", "1");
        MovedStock movedStock = createMovedStock(movedStockId, "325", "87.654321");
        movedStocks.add(movedStock);

        List<MaterialStock> expected = new ArrayList<>();

        StockId expectedStockId = createStockId("0", "0", "1");
        MaterialStock materialExpected = createMaterialStock(expectedStockId, BigDecimal.valueOf(211.111101), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        expected.add(materialExpected);

        List<MaterialStock> actual = Transfer.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countRealStocks_unsortedLists() {
        List<MaterialStock> materialStocks = new ArrayList<>();

        StockId materialStockIdOne = createStockId("0", "0", "1");
        StockId materialStockIdTwo = createStockId("0", "0", "2");
        StockId materialStockIdThree = createStockId("0", "0", "3");
        MaterialStock materialStockOne = createMaterialStock(materialStockIdOne, BigDecimal.valueOf(123.45678), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        MaterialStock materialStockTwo = createMaterialStock(materialStockIdTwo, BigDecimal.valueOf(0), BigDecimal.valueOf(123.45678), BigDecimal.valueOf(0));
        MaterialStock materialStockThree = createMaterialStock(materialStockIdThree, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(123.45678));

        materialStocks.add(materialStockThree);
        materialStocks.add(materialStockOne);
        materialStocks.add(materialStockTwo);

        List<MovedStock> movedStocks = new ArrayList<>();

        StockId movedStockIdOne = createStockId("0", "0", "1");
        StockId movedStockIdTwo = createStockId("0", "0", "2");
        StockId movedStockIdThree = createStockId("0", "0", "3");
        MovedStock movedStockOne = createMovedStock(movedStockIdOne, "326", "87.654321");
        MovedStock movedStockTwo = createMovedStock(movedStockIdTwo, "324", "87.654321");
        MovedStock movedStockThree = createMovedStock(movedStockIdThree, "262", "87.654321");

        movedStocks.add(movedStockTwo);
        movedStocks.add(movedStockOne);
        movedStocks.add(movedStockThree);

        List<MaterialStock> expected = new ArrayList<>();

        StockId expectedStockIdOne = createStockId("0", "0", "1");
        StockId expectedStockIdTwo = createStockId("0", "0", "2");
        StockId expectedStockIdThree = createStockId("0", "0", "3");
        MaterialStock materialExpectedOne = createMaterialStock(expectedStockIdOne, BigDecimal.valueOf(211.111101), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        MaterialStock materialExpectedTwo = createMaterialStock(expectedStockIdTwo, BigDecimal.valueOf(0), BigDecimal.valueOf(211.111101), BigDecimal.valueOf(0));
        MaterialStock materialExpectedThree = createMaterialStock(expectedStockIdThree, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(211.111101));

        expected.add(materialExpectedOne);
        expected.add(materialExpectedTwo);
        expected.add(materialExpectedThree);

        List<MaterialStock> actual = Transfer.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countRealStocks_listsWithDifferentLength() {
        List<MaterialStock> materialStocks = new ArrayList<>();

        StockId materialStockIdOne = createStockId("0", "0", "1");
        StockId materialStockIdTwo = createStockId("0", "0", "2");
        StockId materialStockIdThree = createStockId("0", "0", "3");
        MaterialStock materialStockOne = createMaterialStock(materialStockIdOne, BigDecimal.valueOf(123.45678), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        MaterialStock materialStockTwo = createMaterialStock(materialStockIdTwo, BigDecimal.valueOf(0), BigDecimal.valueOf(123.45678), BigDecimal.valueOf(0));
        MaterialStock materialStockThree = createMaterialStock(materialStockIdThree, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(123.45678));

        materialStocks.add(materialStockThree);
        materialStocks.add(materialStockOne);
        materialStocks.add(materialStockTwo);

        List<MovedStock> movedStocks = new ArrayList<>();

        StockId movedStockIdOne = createStockId("0", "0", "1");
        StockId movedStockIdTwo = createStockId("0", "0", "3");
        MovedStock movedStockOne = createMovedStock(movedStockIdOne, "326", "87.654321");
        MovedStock movedStockThree = createMovedStock(movedStockIdTwo, "262", "87.654321");

        movedStocks.add(movedStockThree);
        movedStocks.add(movedStockOne);

        List<MaterialStock> expected = new ArrayList<>();

        StockId expectedStockIdOne = createStockId("0", "0", "1");
        StockId expectedStockIdTwo = createStockId("0", "0", "2");
        StockId expectedStockIdThree = createStockId("0", "0", "3");
        MaterialStock materialExpectedOne = createMaterialStock(expectedStockIdOne, BigDecimal.valueOf(211.111101), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        MaterialStock materialExpectedTwo = createMaterialStock(expectedStockIdTwo, BigDecimal.valueOf(0), BigDecimal.valueOf(123.45678), BigDecimal.valueOf(0));
        MaterialStock materialExpectedThree = createMaterialStock(expectedStockIdThree, BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(211.111101));

        expected.add(materialExpectedOne);
        expected.add(materialExpectedTwo);
        expected.add(materialExpectedThree);

        List<MaterialStock> actual = Transfer.countRealStocks(materialStocks, movedStocks);

        Assert.assertEquals(expected, actual);
    }

    private MaterialStock createMaterialStock(StockId id, BigDecimal blockedStock, BigDecimal qualityStock, BigDecimal unrestrictedStock) {
        MaterialStock materialStock = new MaterialStock();
        materialStock.setId(id);
        materialStock.setBlockedStock(blockedStock);
        materialStock.setQualityInspectionStock(qualityStock);
        materialStock.setUnrestrictedStock(unrestrictedStock);

        return materialStock;
    }

    private MovedStock createMovedStock(StockId id, String moveType, String movTypeSumResult) {
        MovedStock movedStock = new MovedStock();
        movedStock.setId(id);
        movedStock.setMoveType(moveType);
        movedStock.setMovTypeSumResult(movTypeSumResult);

        return movedStock;
    }

    private StockId createStockId(String plant, String storageLocation, String material) {
        StockId stockId = new StockId();
        stockId.setPlant(plant);
        stockId.setStorageLocation(storageLocation);
        stockId.setMaterial(material);

        return stockId;
    }
}