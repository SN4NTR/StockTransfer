package com.company.stocktransfer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Transfer {

    public static List<MaterialStock> countRealStocks(List<MaterialStock> basicMaterialStocks, List<MovedStock> movedMaterialStocks) {
        basicMaterialStocks.sort(getMaterialStockComparator());

        movedMaterialStocks.forEach(movedStock -> findElementById(movedStock, basicMaterialStocks));

        return basicMaterialStocks;
    }

    private static void findElementById(MovedStock movedStock, List<MaterialStock> basicMaterialStocks) {
        int elementIndex = Collections.binarySearch(basicMaterialStocks, movedStock, getComparatorForTwoLists());

        MaterialStock element = basicMaterialStocks.get(elementIndex);

        BigDecimal resultSum;
        BigDecimal movTypeSumResultToBigDecimal;

        switch (movedStock.getMoveType()) {
            case "201":
            case "202":
            case "261":
            case "262":
            case "311":
            case "312":
                movTypeSumResultToBigDecimal = BigDecimal.valueOf(Double.parseDouble(movedStock.getMovTypeSumResult()));
                resultSum = element.getUnrestrictedStock().add(movTypeSumResultToBigDecimal);
                element.setUnrestrictedStock(resultSum);
                break;
            case "323":
            case "324":
                movTypeSumResultToBigDecimal = BigDecimal.valueOf(Double.parseDouble(movedStock.getMovTypeSumResult()));
                resultSum = element.getQualityInspectionStock().add(movTypeSumResultToBigDecimal);
                element.setQualityInspectionStock(resultSum);
                break;
            case "325":
            case "326":
                movTypeSumResultToBigDecimal = BigDecimal.valueOf(Double.parseDouble(movedStock.getMovTypeSumResult()));
                resultSum = element.getBlockedStock().add(movTypeSumResultToBigDecimal);
                element.setBlockedStock(resultSum);
                break;
            default:
                break;
        }
    }

    private static Comparator<MaterialStock> getMaterialStockComparator() {
        return (one, two) -> {
            String oneId = one.getId().toString();
            String twoId = two.getId().toString();

            return oneId.compareTo(twoId);
        };
    }

    private static Comparator<MovedStock> getMovedStockComparator() {
        return (one, two) -> {
            String oneId = one.getId().toString();
            String twoId = two.getId().toString();

            return oneId.compareTo(twoId);
        };
    }

    private static Comparator<Object> getComparatorForTwoLists() {
        return (one, two) -> {
            String oneId = ((MaterialStock) one).getId().toString();
            String twoId = ((MovedStock) two).getId().toString();

            return oneId.compareTo(twoId);
        };
    }
}
