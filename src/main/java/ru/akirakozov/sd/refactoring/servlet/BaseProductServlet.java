package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.database.DatabaseManager;

import javax.servlet.http.HttpServlet;

public abstract class BaseProductServlet extends HttpServlet {
    protected final DatabaseManager databaseManager;

    public BaseProductServlet(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}