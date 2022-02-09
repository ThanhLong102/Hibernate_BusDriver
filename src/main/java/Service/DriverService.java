package Service;

import Entity.Driver;
import Menu.Menu;
import Repository.DriverDao;
import Repository.DriverDaoImpl;
import Util.DataUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DriverService {
    public DriverDao driverDao = new DriverDaoImpl();

    public void showDriver() {
        for (Driver driver : Menu.driverList) System.out.println(driver);
    }

    public void addNewDriver() {
        System.out.print("Nhập số lái xe muốn thêm mới: ");
        int driverNumber = -1;
        do {
            try {
                driverNumber = new Scanner(System.in).nextInt();
                if (driverNumber > 0) {
                    break;
                }
                System.out.print("Số lái xe muốn thêm mới phải là số nguyên, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Số lái xe muốn thêm mới phải là một số nguyên, vui lòng nhập lại: ");
            }
        } while (true);
        for (int i = 0; i < driverNumber; i++) {
            Driver driver = new Driver();
            driver.inputInfo();
            Menu.driverList.add(driver);
            driverDao.addNewDriver(driver);
        }
    }

    public Driver findDriverById(int driverId) {
        for (Driver driver : Menu.driverList) {
            if (driver.getId() == driverId)
                return driver;
        }
        return null;
    }

    public void initializeDriverData() {
        List<Driver> driverList = driverDao.getAll();
        if (!DataUtil.isEmpty(driverList)) {
            Menu.driverList = driverList;
        } else {
            Menu.driverList = new ArrayList<>();
        }

    }
}
