package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository {
    static int id = 0;
    private List<Car> carData = new ArrayList<>();
    public Car create(Car car) {
        if (car.getItemId() == null) {
            UUID uuid = UUID.randomUUID();
            car.setItemId(uuid.toString());
        }
        carData.add(car);
        return car;
    }

    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    public Car findById(String id) {
        for (Car car : carData) {
            if (car.getItemId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    public Car edit(String id, Car updatedCar) {
        for (Car car : carData) {
            if (car.getItemId().equals(id)) {
                car.setItemId(updatedCar.getItemName());
                car.setCarColor(updatedCar.getCarColor());
                car.setItemQuantity(updatedCar.getItemQuantity());
                return car;
            }
        }
        return null;
    }

    public void delete(String id) {
        carData.removeIf(car -> car.getItemId().equals(id));
    }
}
