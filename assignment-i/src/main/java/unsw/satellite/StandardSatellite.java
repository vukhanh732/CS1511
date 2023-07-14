package unsw.satellite;

import unsw.utils.*;
import static unsw.utils.MathsHelper.RADIUS_OF_JUPITER;;

public class StandardSatellite extends Satellite {
    private static final int LINEAR_SPEED = 2500;
    private static final int MAX_RANGE = 150000;
    private static final int MAX_STORAGE_FILES = 3;
    private static final int MAX_STORAGE_BYTES = 80;
    private static final int DOWNLOAD_SPEED = 1;
    private static final int UPLOAD_SPEED = 1;

    public StandardSatellite(String id, double height, Angle position, String type) {
        super(id, height, position, type);
    }

    public int getLinearSpeed() {
        return LINEAR_SPEED;
    }

    public int getMaxRange() {
        return MAX_RANGE;
    }

    public int getMaxStorageFiles() {
        return MAX_STORAGE_FILES;
    }

    public int getMaxStorageBytes() {
        return MAX_STORAGE_BYTES;
    }

    public int getDownloadSpeed() {
        return DOWNLOAD_SPEED;
    }

    public int getUploadSpeed() {
        return UPLOAD_SPEED;
    }

    @Override
    public void updatePosition() {
        Angle curr = super.getPosition();
        Angle diff = Angle.fromRadians(LINEAR_SPEED / (RADIUS_OF_JUPITER + getHeight()));
        super.setPosition(curr.subtract(diff));
    }
}
