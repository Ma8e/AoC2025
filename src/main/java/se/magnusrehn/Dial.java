package se.magnusrehn;

import static se.magnusrehn.Direction.R;

public class Dial {

    public record Position(Direction direction, int number) {
        public Position {
            assert number >= 0 && number < 100;
        }
    }

    public Position position;
    public long zeroPasses = 0;
    public long zeroStops = 0;

    public Dial() {
        position = new Position(R, 50);
    }

    public Dial(Position p) {
        this.position = p;
    }

    public Dial(int number) {
        this(new Position(R, number));
    }

    public void turn(Rotation rotation) {
        if (rotation.steps() == 0) {
            return;
        }
        int number = this.position.number + rotation.direction().multiplier * rotation.steps();

        if (number > 0) {
            zeroPasses += number / 100;
        }
        else {
            zeroPasses += (-number / 100) + (this.position.number == 0 ? 0 : 1);
        }

        number %= 100;
        if (number < 0) {
            number += 100;
        }
        if (number == 0) zeroStops++;
        this.position = new Position(rotation.direction(), number);
    }
}
