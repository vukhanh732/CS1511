package unsw.satellite;

import unsw.utils.Angle;
import static unsw.utils.MathsHelper.RADIUS_OF_JUPITER;

public class RelaySatellite extends Satellite {
    private static final int LINEAR_SPEED = 1500;
    private static final int MAX_RANGE = 300000;

    private static final Angle ANGLE_140 = Angle.fromDegrees(140);
    private static final Angle ANGLE_190 = Angle.fromDegrees(190);
    private static final Angle ANGLE_345 = Angle.fromDegrees(345);
    private boolean movingClockwise = false;

    public RelaySatellite(String id, double height, Angle position, String type) {
        super(id, height, position, type);
        if (position.compareTo(ANGLE_190) > 0 || position.compareTo(ANGLE_345) < 0) {
            movingClockwise = true;
        }
    }

    // ...

    public void updatePosition() {
        Angle curr = super.getPosition();
        Angle diff = Angle.fromRadians(LINEAR_SPEED / (RADIUS_OF_JUPITER + getHeight()));

        if (movingClockwise) {
            Angle newPos = curr.add(diff);
            if (newPos.compareTo(ANGLE_190) > 0) {
                newPos = ANGLE_190;
                movingClockwise = false;
            }
            super.setPosition(newPos);
        } else {
            Angle newPos = curr.subtract(diff);
            if (newPos.compareTo(ANGLE_140) < 0) {
                newPos = ANGLE_140;
                movingClockwise = true;
            }
            super.setPosition(newPos);
        }
    }

    public int getLinearSpeed() {
        return LINEAR_SPEED;
    }

    public int getMaxRange() {
        return MAX_RANGE;
    }

}
