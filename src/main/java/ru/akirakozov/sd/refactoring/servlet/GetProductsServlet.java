package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.DatabaseManager;
import ru.akirakozov.sd.refactoring.html.HtmlManager;
import ru.akirakozov.sd.refactoring.product.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends BaseProductServlet {

    public GetProductsServlet(DatabaseManager databaseManager, HtmlManager htmlManager) {
        super(databaseManager, htmlManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        htmlManager.printProductsList(response.getWriter(), databaseManager.getProducts());

        setOkResponse(response);
    }
}
