package wt.muppety.dao;

import wt.muppety.model.Category;
import wt.muppety.model.Supplier;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.List;
import java.util.Optional;

public class CategoryDao extends BaseDao<Category> {
    public Optional<Category> create(String name) {
        Category category = new Category(name);
        try {
            this.save(category);
            return findByName(category.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Category> create(Category category) {
        try {
            this.save(category);
            return findByName(category.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public ObservableList<Category> listAll() {

        try{ 
            ObservableList<Category> categories = FXCollections.observableArrayList(currentSession().createQuery("FROM Category").list());
            System.out.println("list all categories");
            for(Category category : categories){
                System.out.println(category.getName());
            }
            
            return categories;
            //return Optional.of(categories);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
        //return Optional.empty();
    }

    public Optional<Category> findById(final int indexNumber) {
        try {
            Category category = currentSession().createQuery("SELECT c FROM Category c WHERE c.id = :id", Category.class)
                    .setParameter("id", indexNumber).getSingleResult();
            return Optional.of(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Category> findByName(final String name) {
        try {
            Category category = currentSession().createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)
                    .setParameter("name", name).getSingleResult();
            return Optional.of(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}