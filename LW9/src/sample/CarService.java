package sample;

import sample.data.Car;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarService {
    final private EntityManager em;

    public CarService(EntityManager em) {
        this.em = em;
    }

    public List<Car> findAll() {
        return em.createNamedQuery("Car.findAllCars", Car.class).getResultList();
    }

    public TypedQuery<Car> getQueryA() {
        return em.createNamedQuery("Car.ExerciseA", Car.class);
    }

    public TypedQuery<Car> getQueryB() {
        return em.createNamedQuery("Car.ExerciseB", Car.class);
    }

    public TypedQuery<Car> getQueryC() {
        return em.createNamedQuery("Car.ExerciseC", Car.class);
    }

    public TypedQuery<Car> getQueryD() {
        return em.createNamedQuery("Car.ExerciseD", Car.class);
    }

    public TypedQuery<Car> getQueryF() {
        return em.createNamedQuery("Car.ExerciseF", Car.class);
    }
}
