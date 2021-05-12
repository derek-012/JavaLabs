package sample;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import sample.data.Car;

public class DBReader {
    CarService cs;
    EntityManager em;

    DBReader() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lw9");
        em = factory.createEntityManager();
        cs = new CarService(em);
    }

    public List<Car> getList() {
        return em.createNamedQuery("Car.findAllCars", Car.class).getResultList();
    }

    public List<Car> getExerciseA(String model) {
        return em.createNamedQuery("Car.ExerciseA", Car.class)
                .setParameter("model", model)
                .getResultList();

    }

    public List<Car> getExerciseB(String model, int exp) {
        return em.createNamedQuery("Car.ExerciseB", Car.class)
                .setParameter("model", model)
                .setParameter("exp", exp)
                .getResultList();
    }

    public List<Car> getExerciseC(Integer year, Integer price) {
        return em.createNamedQuery("Car.ExerciseC", Car.class)
                .setParameter("year", year)
                .setParameter("price", price)
                .getResultList();
    }

    public List<Car> getExerciseD() {
        return em.createNamedQuery("Car.ExerciseD", Car.class)
                .getResultList();
    }

    public Set<String> getExerciseE() {
        return em.createNamedQuery("Car.findAllCars", Car.class)
                .getResultList()
                .stream()
                .map(Car::getModel)
                .collect(Collectors.toSet());
    }

    public List<Car> getExerciseF(String model) {
        return em.createNamedQuery("Car.ExerciseF", Car.class)
                .setParameter("model", model)
                .getResultList();
    }
}
