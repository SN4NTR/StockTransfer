package com.company.stocktransfer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Transfer {

    public static List<MaterialStock> countRealStocks(List<MaterialStock> basicMaterialStocks, List<MovedStock> movedMaterialStocks) {
        Map<StockId, MaterialStock> materialStockMap = basicMaterialStocks.stream().collect(Collectors.toMap(MaterialStock::getId, materialStock -> materialStock));

        movedMaterialStocks.forEach(movedStock -> updateMaterialStock(movedStock, materialStockMap.get(movedStock.getId())));

        return basicMaterialStocks;
    }

    private static void updateMaterialStock(MovedStock movedStock, MaterialStock materialStock) {
        switch (movedStock.getMoveType()) {
            case "201":
            case "202":
            case "261":
            case "262":
            case "311":
            case "312":
                BigDecimal movTypeSumResultToBigDecimal = BigDecimal.valueOf(Double.parseDouble(movedStock.getMovTypeSumResult()));
                BigDecimal resultSum = materialStock.getUnrestrictedStock().add(movTypeSumResultToBigDecimal);
                materialStock.setUnrestrictedStock(resultSum);
                break;
            case "323":
            case "324":
                movTypeSumResultToBigDecimal = BigDecimal.valueOf(Double.parseDouble(movedStock.getMovTypeSumResult()));
                resultSum = materialStock.getQualityInspectionStock().add(movTypeSumResultToBigDecimal);
                materialStock.setQualityInspectionStock(resultSum);
                break;
            case "325":
            case "326":
                movTypeSumResultToBigDecimal = BigDecimal.valueOf(Double.parseDouble(movedStock.getMovTypeSumResult()));
                resultSum = materialStock.getBlockedStock().add(movTypeSumResultToBigDecimal);
                materialStock.setBlockedStock(resultSum);
                break;
            default:
                break;
        }
    }
}
