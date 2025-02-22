package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> carList;

    public Cars(List<String> carNames) {
        carList = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public void move() {
        for (Car car: carList){
            car.move();
        }
    }

    public Winners findWinners(){
        Position maxPosition = carList.stream()
                .map(Car::getPosition)
                .max(Position::compareTo)
                .orElse(new Position(0));

        List<Car> winnerCars = carList.stream()
                .filter(car -> car.isAtPosition(maxPosition))
                .collect(Collectors.toList());

        return new Winners(winnerCars);
    }

    @Override
    public String toString() {
        return carList.stream()
                .map(Car::toString)
                .collect(Collectors.joining("\n"));
    }
}
