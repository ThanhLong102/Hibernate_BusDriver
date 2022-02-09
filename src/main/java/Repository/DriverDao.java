package Repository;

import Entity.Driver;

import java.util.List;

public interface DriverDao {

    void addNewDriver(Driver driver);

    List<Driver> getAll();
}
