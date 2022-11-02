package ru.akirakozov.sd.refactoring.html;

import ru.akirakozov.sd.refactoring.product.Product;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class HtmlManager {
    private <T> void printBody(PrintWriter writer, Action action) {
        writer.println("<html><body>");
        action.print();
        writer.println("</body></html>");
    }

    private void printWithHeader(PrintWriter writer, String header, Action action) {
        printBody(writer, () -> {
            writer.println(header);
            action.print();
        });
    }

    private void printProductWithHeader(PrintWriter writer, Product product, String header) {
        printWithHeader(writer, header, () -> {
            if (product != null) {
                writer.println(product.getName() + "\t" + product.getPrice() + "</br>");
            }
        });
    }

    public void printMaxPriceProduct(PrintWriter writer, Product product) {
        printProductWithHeader(writer, product, "<h1>Product with max price: </h1>");
    }

    public void printMinPriceProduct(PrintWriter writer, Product product) {
        printProductWithHeader(writer, product, "<h1>Product with min price: </h1>");
    }

    public void printSummaryPrice(PrintWriter writer, Integer summaryPrice) {
        printWithHeader(writer, "Summary price: ", () -> {
            if (summaryPrice != null) {
                writer.println(summaryPrice.intValue());
            }
        });
    }

    public void printProductsAmount(PrintWriter writer, Integer productAmount) {
        printWithHeader(writer, "Number of products: ", () -> {
            if (productAmount != null) {
                writer.println(productAmount.intValue());
            }
        });
    }

    public void printUnknownCommand(PrintWriter writer, String command) {
        writer.println("Unknown command: " + command);
    }

    public void printOk(PrintWriter writer) {
        writer.println("OK");
    }

    public void printProductsList(PrintWriter writer, List<Product> products) {
        printBody(writer, () -> {
            for (Product product : products) {
                writer.println(product.getName() + "\t" + product.getPrice() + "</br>");
            }
        });
    }

    @FunctionalInterface
    private interface Action {
        void print();
    }
}
