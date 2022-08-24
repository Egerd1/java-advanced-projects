package vehicleIO.model;

import java.io.*;
import java.util.*;

public class Main {

    public static final String FILE_PATH = "C:\\Users\\midav\\java-advanced-projects\\";
    public static final String FILE_NAME = "vehicles.txt";

    private static List<Vehicle> vehicleList = new ArrayList<>();
    private static List<Car> carList = new ArrayList<>();
    private static List<Motorcycle> motorcycleList = new ArrayList<>();
    private static List<Tractor> tractorList = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        readFileAndConvertToObjects(FILE_PATH + FILE_NAME);

        countVehiclesForType();

        countVehiclesPerBrand();

        // Do as your homework
        sortCarsByPrice();

        sortChoppersByTopSpeed();

        displayVehiclesInDifferentFiles();

    }

    /*
     * This method will read the provided filename and convert each line to a Java object
     * It will then store the objects into a list called 'vehicleList' so we can process them more easily
     * */
    private static void readFileAndConvertToObjects(String filename) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            // reading line by line until we reach EOF (end of file)
            while (line != null) {

                // converting each line to an object of type Vehicle
                Vehicle vehicle = convertLineToVehicle(line);

                // storing vehicle in list
                if (vehicle != null) {
                    vehicleList.add(vehicle);
                }

                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * This method will split the large vehicle list into smaller lists for cars, motorcycles and tractors
     * */
    private static void countVehiclesForType() {
        // We iterate over the whole vehicleList
        // and check what type of object each element is, then store them in their respective lists
        for (Vehicle v : vehicleList) {
            if (v instanceof Car) carList.add((Car) v);
            if (v instanceof Motorcycle) motorcycleList.add((Motorcycle) v);
            if (v instanceof Tractor) tractorList.add((Tractor) v);
        }

        System.out.println("Cars: " + carList.size() + " Motorcycles: " + motorcycleList.size() + " Tractor: " + tractorList.size());

    }

    /*
     * This method will categorize vehicles by brand
     * */
    private static void countVehiclesPerBrand() {
        // Key in the map is brand
        // value is the number of vehicles with that brand
        Map<String, Integer> vehiclesCountedPerBrandMap = new HashMap<>();

        for (Vehicle v : vehicleList) {
            // If the brand of the current vehicle (v) already exists in the map,
            // we add the vehicle to the list of vehicles of the same brand
            if (vehiclesCountedPerBrandMap.containsKey(v.getBrand())) {
                Integer brandCount = vehiclesCountedPerBrandMap.get(v.getBrand());
                brandCount++;
                vehiclesCountedPerBrandMap.put(v.getBrand(), brandCount);
                // if the brand doesn't exist, we add it to the map
            } else {
                vehiclesCountedPerBrandMap.put(v.getBrand(), 1);
            }
        } // end of forEach loop

        System.out.println(vehiclesCountedPerBrandMap);
    }

    /*
     * This method will sort cars by price
     * */
    private static void sortCarsByPrice() {

        System.out.println("Sorted Cars by price:");
        carList.stream()
                .sorted(Comparator.comparing(Car::getPrice).reversed())
                .forEach(System.out::println);

// Nii loob uue array
//        List<Car> sortedCars = carList
//                .stream().sorted(Comparator.comparing(Car::getPrice)).toList();
//        sortedCars.forEach(System.out::println);
//--------------üks varjant veel--------------
//        System.out.println("Sorting by price:");
//        carList.sort(Comparator.comparingDouble(Vehicle::getPrice));
//        carList.forEach(System.out::println);
    }

    private static void sortChoppersByTopSpeed() {
        System.out.println("Sorted Choppers by TopSpeed");

        motorcycleList.stream()
                .filter(motorcycleshape -> motorcycleshape.getShape() == MotorcycleShape.CHOPPER)
                .sorted(Comparator.comparing(Motorcycle::getTopSpeed).reversed())
                .forEach(System.out::println);
    }

    private static void displayVehiclesInDifferentFiles() throws IOException {

//Meetodid mis kirjutava´d sõidukid eraldi failidesse
        writeCarsToFile();
        writeMotorcyclesToFile();
        writeTractorsToFile();

//Kirjutab olemasoleva falili lihtsalt uude faili
//        FileReader fr = new FileReader(FILE_NAME);
//        FileWriter fw = new FileWriter("vehicles2.txt");
//        try {
//            String str = "";
//            int i;
//            while ((i = fr.read()) != -1) {
//                str += (char) i;
//            }
//            fw.write(str);
//
//        } catch (IOException e) {
//            System.out.println("There are some IOException");
//        } finally {
//            fr.close();
//            fw.close();
//        }

    }


    private static Vehicle convertLineToVehicle(String line) {
        // read line and store it in a variable of type Vehicle
        String[] lineProperties = line.split(", ");
        Vehicle vehicle = null;

        // Depending on the value of lineProperties[0] we know which kind of vehicle
        // we want: Car, Motorcycle or Tractor
        switch (lineProperties[0]) {
            case "Car":
                vehicle = covertLineToCar(lineProperties);
                ;
                break;
            case "Motorcycle":
                vehicle = convertLineToMotorcycle(lineProperties);
                break;
            case "Tractor":
                vehicle = convertLineToTractor(lineProperties);
                break;

        }
        return vehicle;
    }

    private static Car covertLineToCar(String[] lineProperties) {
        Car car = new Car();
        car.setBrand(lineProperties[1]);
        car.setModel(lineProperties[2]);
        car.setPrice(Integer.valueOf(lineProperties[3]));
        car.setTopSpeed(Integer.valueOf(lineProperties[4]));
        car.setTransmission(Transmission.valueOf(lineProperties[5]));
        car.setShape(CarShape.valueOf(lineProperties[6]));
        return car;
    }

    private static Motorcycle convertLineToMotorcycle(String[] lineProperties) {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setBrand(lineProperties[1]);
        motorcycle.setModel(lineProperties[2]);
        motorcycle.setPrice(Integer.valueOf(lineProperties[3]));
        motorcycle.setTopSpeed(Integer.valueOf(lineProperties[4]));
        motorcycle.setShape(MotorcycleShape.valueOf(lineProperties[5]));
        return motorcycle;
    }


    private static Vehicle convertLineToTractor(String[] lineProperties) {
        Vehicle vehicle;
        Tractor tractor = new Tractor();
        tractor.setBrand(lineProperties[1]);
        tractor.setModel(lineProperties[2]);
        try {
            tractor.setPrice(Integer.valueOf(lineProperties[3]));
        } catch (Exception e) {
            System.out.println("Invalid Tractor Price");
        }
        tractor.setMaxPulledWeight(Integer.valueOf(lineProperties[4]));
        vehicle = tractor;
        return vehicle;
    }


    private static void writeCarsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + "Cars.txt"))) {
            for (Car c : carList) {
                String carLine = "Car, " +
                        c.getBrand() + ", " +
                        c.getModel() + ", " +
                        c.getPrice() + ", " +
                        c.getTopSpeed() + ", " +
                        c.getTransmission() + ", " +
                        c.getShape();
                writer.write(carLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeMotorcyclesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + "Motorcycles.txt"))) {
            for (Motorcycle m : motorcycleList) {
                String motorcycleLine = "Motorcycle, " +
                        m.getBrand() + ", " +
                        m.getModel() + ", " +
                        m.getPrice() + ", " +
                        m.getTopSpeed() + ", " +
                        m.getShape();
                writer.write(motorcycleLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeTractorsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + "Tractors.txt"))) {
            for (Tractor t : tractorList) {
                String tractorLine = "Tractor, " +
                        t.getBrand() + ", " +
                        t.getModel() + ", " +
                        t.getPrice();
                writer.write(tractorLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
