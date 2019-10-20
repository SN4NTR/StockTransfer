package com.company.stocktransfer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Transfer {

    public static List<MaterialStock> countRealStocks(List<MaterialStock> basicMaterialStocks, List<MovedStock> movedMaterialStocks) {
        basicMaterialStocks.sort(Comparator.comparing(MaterialStock::getId));
        movedMaterialStocks.forEach(movedStock -> search(movedStock, basicMaterialStocks));

        return basicMaterialStocks;
    }

    private static void search(MovedStock movedStock, List<MaterialStock> basicMaterialStocks) {
        int elementIndex = Collections.binarySearch(basicMaterialStocks, movedStock, (one, two) ->
                ((MaterialStock) one).getId().compareTo(((MovedStock) two).getId()));

        MaterialStock element = basicMaterialStocks.get(elementIndex);

        BigDecimal resultSum;
        switch (movedStock.getMoveType()) {
            case "201":
            case "202":
            case "261":
            case "262":
            case "311":
            case "312":
                resultSum = element.getUnrestrictedStock().add(movedStock.getMovTypeSumResult());
                element.setUnrestrictedStock(resultSum);
                break;
            case "323":
            case "324":
                resultSum = element.getQualityInspectionStock().add(movedStock.getMovTypeSumResult());
                element.setQualityInspectionStock(resultSum);
                break;
            case "325":
            case "326":
                resultSum = element.getBlockedStock().add(movedStock.getMovTypeSumResult());
                element.setBlockedStock(resultSum);
                break;
            default:
                break;
        }
    }
}
