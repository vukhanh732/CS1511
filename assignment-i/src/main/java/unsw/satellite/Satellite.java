package unsw.satellite;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import unsw.response.models.EntityInfoResponse;
import unsw.response.models.FileInfoResponse;
import unsw.utils.Angle;
import unsw.blackout.File;

public abstract class Satellite {
    private String satelliteId;
    private String type;
    private Angle position;
    private double height;
    private ArrayList<File> files;

    public Satellite(String satelliteId, double height, Angle position, String type) {
        this.satelliteId = satelliteId;
        this.type = type;
        this.height = height;
        this.position = position;
        this.files = new ArrayList<File>();
    }

    // Getter
    public String getSatelliteId() {
        return satelliteId;
    }

    public double getHeight() {
        return height;
    }

    public Angle getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    // Setter
    public void setSatelliteId(String satelliteId) {
        this.satelliteId = satelliteId;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setPosition(Angle position) {
        this.position = position;
    }

    public void setType(String type) {
        this.type = type;
    }

    public File getFile(String fileName) {
        for (File file : files) {
            if (file.getFileName() == fileName) {
                return file;
            }
        }
        return null;
    }

    public void addFile(File file) {
        this.files.add(file);
    }

    public void updatePosition() {

    };
}
