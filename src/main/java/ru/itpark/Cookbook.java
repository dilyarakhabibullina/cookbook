package ru.itpark;

import ru.itpark.constant.Constants;
import ru.itpark.model.Recipe;
import ru.itpark.service.CookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class Cookbook extends HttpServlet {
    private CookService service;

    @Override
    public void init() throws ServletException {
        try {
            service = new CookService();
            service.save(new Recipe("", "Fish under FurCoat", Arrays.asList("fish", "furcoat"), "very tasty"));
            service.save(new Recipe("", "Borsch", Arrays.asList("cabidqe", "svekla"), "not very tasty"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI().substring(req.getContextPath().length());
        if (url.equals("/")) {
            req.setAttribute("page-title", "Что приготовить?");
            req.setAttribute(Constants.ITEMS, service.getAll());
            req.getRequestDispatcher("/WEB-INF/front.jsp").forward(req, resp);

            return;

        }
        if (url.equals("/search")) {
            req.setAttribute("page-title", "Вот что!");
            final Collection<Recipe> foundByName = service.searchByName(req.getParameter("q"));
            Set<Recipe> items = new HashSet<>();
            items.addAll(foundByName);
            req.setAttribute(Constants.ITEMS, items);
            req.setAttribute(Constants.SEARCH_QUERY1, req.getParameter("q"));
            req.getRequestDispatcher("/WEB-INF/front.jsp").forward(req, resp);
        }
        if (url.equals ("/searchByIngredients")) {
            req.setAttribute("page-title", "поиск по ингредиентам");
            final Collection<Recipe> foundByIngredients = service.searchByIngredient(req.getParameter("queryIngredients"));
            List<Recipe> items = new ArrayList<>();
            items.addAll(foundByIngredients);
            req.setAttribute(Constants.ITEMS, items);
            req.setAttribute(Constants.SEARCH_QUERY2, req.getParameter("queryIngredients"));
            req.getRequestDispatcher("/WEB-INF/front.jsp").forward(req, resp);
        }
}


//            if (url.equals("/houses/search")) {
//                final String q = req.getParameter("q");
//                final Collection<House> foundByUnderground = service.searchByUnderground(q);
//                final Collection<House> foundByDistrict = service.searchByDistrict(q);
////делаем HashSet, так как это множество без дубликатов-нам надо убрать строки, где метро называется также, как район
//                Set<House> items = new HashSet<>();
//                items.addAll(foundByUnderground);
//                items.addAll(foundByDistrict);
//
//                req.setAttribute(Constants.ITEMS, items);
//                //???строка ниже нужна, чтобы запрос отображался в строке?
//                req.setAttribute(Constants.SEARCH_QUERY, q);
//
//                req.getRequestDispatcher("/WEB-INF/houses.jsp").forward(req, resp);
//                return;
//            }
//
//
//            req.setAttribute(Constants.ITEMS, service.getAll());
//            req.getRequestDispatcher("/WEB-INF/front.jsp").forward(req, resp);
//            return;
//
////        это правильно, работает (нужно для проверки)
////        if (url.equals("/search")){
////            req.setAttribute("page-title", "Вот что!");
////resp.getWriter().write("rtyuw");
////
////            return;
//
//        }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        final String action = req.getParameter("action");
        if (action.equals("save")) {
            // ничего не изменится
            final String id = req.getParameter("id");
            final String name = req.getParameter("name");
            final String ingredients = req.getParameter("ingredients");
            final String description = req.getParameter("description");

            //CookService service= new CookService();
            service.save(id, name, ingredients, description);
            // чтобы повторно не отрисовывать, отправляем его на страницу, где итак отрисовывается весь список

            resp.sendRedirect(req.getRequestURI());
            return;
        }
    }

}


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("cp1251");
//        String url = req.getRequestURI();
//        if (url.equals("/")) {
//            req.setAttribute("page-title", "Что приготовить?");
//            req.setAttribute(Constants.ITEM, items);
//            req.getRequestDispatcher("/WEB-INF/front.jsp").forward(req, resp);
//            return;
//        }
//        if (url.equals("/search")) {
//            req.getRequestDispatcher("/WEB-INF/search.jsp").forward(req, resp);
//            //тонкое искусство пофигизма
//            return;
//        }
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//                req.setAttribute(Constants.ITEMS, service.getAll());
//        req.getRequestDispatcher("/WEB-INF/front.jsp").forward(req, resp);
//        final String action = req.getParameter("action");
//        if (action.equals("save")) {
//            final String id = req.getParameter("id");
//            final String name = req.getParameter("name");
//            final String ingredients = req.getParameter("ingredients");
//            final String description = req.getParameter("description");
//            service.save(id, name, ingredients, description);
//            resp.sendRedirect(req.getRequestURI());
//            return;
//        }
//    }
//}
//
