package unsw.satellite;

import unsw.utils.Angle;

public class TeleportingSatellite extends Satellite {
    private static final int LINEAR_SPEED = 1000;
    private static final int MAX_RANGE = 200000;
    private static final int MAX_STORAGE_BYTES = 200;
    private static final int DOWNLOAD_SPEED = 15;
    private static final int UPLOAD_SPEED = 10;

    public TeleportingSatellite(String id, double height, Angle position, String type) {
        super(id, height, position, type);
    }

    public int getLinearSpeed() {
        return LINEAR_SPEED;
    }

    public int getMaxRange() {
        return MAX_RANGE;
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
}
