package ru.itpark.service;

import ru.itpark.model.Recipe;

import javax.servlet.http.Part;
import java.nio.file.Files;


import java.util.*;
import java.util.stream.Collectors;

public class CookService {
    private final Collection<Recipe> items = new LinkedList<>();

    public Collection<Recipe> getAll() {
        return items;
    }

    public Collection<Recipe> searchByName(String name) {
        return items.stream()
                .filter(o -> o.getName().contains(name))
                .collect(Collectors.toList());
    }

    public Collection<Recipe> searchByIngredient(String ingredient) {
        List<String> ingredients = Arrays.asList(ingredient.split(" "));
        return items.stream()
                .filter(o -> ingredients.containsAll(o.getIngredients()))
                .collect(Collectors.toList());
    }


    public void save(String id, String name, String ingredients, String description) {
        // "  Помидор  "
        final List<String> parsedIngredients = Arrays.stream(ingredients.split(" "))
                .filter(o -> !o.isEmpty())
                .collect(Collectors.toList());

        if (id.equals("")) {
            id = generatedId();

            insert(new Recipe(id, name, parsedIngredients, description));
            return;
        }
    }

    public void insert(Recipe recipe) {
        items.add(recipe);
    }


    private String generatedId() {
        return UUID.randomUUID().toString();
    }

    public void save(Recipe recipe) {
    }

//    public void save(Recipe recipe) {
//    }
}
