package ru.akirakozov.sd.refactoring.html;

import ru.akirakozov.sd.refactoring.product.Product;

import java.io.PrintWriter;
import java.util.List;

public class HtmlManager {
    private void printBody(PrintWriter writer, Runnable action) {
        writer.println("<html><body>");
        action.run();
        writer.println("</body></html>");
    }

    private void printWithHeader(PrintWriter writer, String header, Runnable action) {
        printBody(writer, () -> {
            writer.println(header);
            action.run();
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
}
