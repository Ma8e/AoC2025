package se.magnusrehn;

public class Dial {

    public record Position(Direction direction, int number) {
        public Position {
            assert number >= 0 && number < 100;
        }
    }

    public Position position = new Position(Direction.R, 50);

    public Position turn(Rotation rotation) {
        int number = this.position.number + rotation.direction().multiplier * rotation.steps();
        number %= 100;
        while (number < 0) {
            number += 100;
        }
        this.position = new Position(rotation.direction(), number);
        return this.position;
    }
}
