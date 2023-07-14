package unsw.device;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import unsw.utils.Angle;
import unsw.response.models.EntityInfoResponse;
import unsw.response.models.FileInfoResponse;
import unsw.blackout.File;

public abstract class Device {
    private String deviceId;
    private String type;
    private Angle position;
    private ArrayList<File> files;

    public Device(String deviceId, Angle position, String type) {
        this.deviceId = deviceId;
        this.position = position;
        this.type = type;
        this.files = new ArrayList<>();
    }

    // Getter
    public String getDeviceId() {
        return deviceId;
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

    public File getFile(String fileName) {
        for (File file : files) {
            if (file.getFileName() == fileName) {
                return file;
            }
        }
        return null;
    }

    // Setter
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPosition(Angle position) {
        this.position = position;
    }

    public void addFile(File file) {
        this.files.add(file);
    }

}
