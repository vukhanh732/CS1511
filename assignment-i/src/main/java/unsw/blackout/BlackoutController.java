package unsw.blackout;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import unsw.device.*;
import unsw.satellite.*;
import unsw.response.models.EntityInfoResponse;
import unsw.response.models.FileInfoResponse;
import unsw.utils.Angle;
import static unsw.utils.MathsHelper.*;

public class BlackoutController {
    List<Device> deviceList = new ArrayList<>();
    List<Satellite> satelliteList = new ArrayList<>();

    /**
     * Creates a new device with the specified id, type, and position.
     * 
     * @param deviceId The id of the device to create.
     * @param type     The type of device to create. Should be one of
     *                 "HandheldDevice", "LaptopDevice", or "DesktopDevice".
     * @param position The initial position of the device, represented as an Angle.
     */
    public void createDevice(String deviceId, String type, Angle position) {
        // TODO: Task 1a)
        if (deviceList == null) {
            deviceList = new ArrayList<>();
        }
        Device newDevice = null;
        switch (type) {
        case "HandheldDevice":
            newDevice = new HandheldDevice(deviceId, position, "HandheldDevice");
            break;
        case "LaptopDevice":
            newDevice = new LaptopDevice(deviceId, position, "LaptopDevice");
            break;
        case "DesktopDevice":
            newDevice = new DesktopDevice(deviceId, position, "DesktopDevice");
            break;
        default:
            throw new IllegalArgumentException("Invalid device type.");
        }
        deviceList.add(newDevice);

    }

    /**
     * Removes the device with the specified ID.
     * 
     * @param deviceId the unique ID of the device to be removed
     * @pre deviceList is not null
     * @post the device with the specified ID is removed from deviceList
     */
    public void removeDevice(String deviceId) {
        // TODO: Task 1b)
        for (Device device : deviceList) {
            if (device.getDeviceId().equals(deviceId)) {
                deviceList.remove(device);
                break;
            }
        }
    }

    /**
     * Creates a new satellite with the specified ID, type, height, and position.
     * 
     * @param satelliteId the unique ID of the satellite
     * @param type        the type of the satellite (StandardSatellite,
     *                    TeleportingSatellite, or RelaySatellite)
     * @param height      the height of the satellite in kilometers
     * @param position    the angular position of the satellite in radians
     * @pre satelliteID is valid
     * @post a new satellite is added to satelliteList
     */
    public void createSatellite(String satelliteId, String type, double height, Angle position) {
        // TODO: Task 1c)
        if (satelliteList == null) {
            satelliteList = new ArrayList<>();
        }
        Satellite satellite = null;
        switch (type) {
        case "StandardSatellite":
            satellite = new StandardSatellite(satelliteId, height, position, "StandardSatellite");
            break;
        case "TeleportingSatellite":
            satellite = new TeleportingSatellite(satelliteId, height, position, "TeleportingSatellite");
            break;
        case "RelaySatellite":
            satellite = new RelaySatellite(satelliteId, height, position, "RelaySatellite");
            break;
        }
        satelliteList.add(satellite);

    }

    /**
     * Removes the satellite with the specified ID.
     * 
     * @param satelliteId the unique ID of the satellite to be removed
     * @pre satelliteList is not null
     * @post the satellite with the specified ID is removed from satelliteList
     */
    public void removeSatellite(String satelliteId) {
        // TODO: Task 1d)
        for (Satellite satellite : satelliteList) {
            if (satellite.getSatelliteId() == satelliteId) {
                satelliteList.remove(satellite);
                return;
            }
        }
    }

    /**
     * Lists all device IDs currently in the system.
     * 
     * @return a list of device IDs
     */
    public List<String> listDeviceIds() {
        List<String> deviceIds = new ArrayList<>();
        for (Device device : deviceList) {
            deviceIds.add(device.getDeviceId());
        }
        return deviceIds;
    }

    /**
     * Lists all satellite IDs currently in the system.
     * 
     * @return a list of satellite IDs
     */
    public List<String> listSatelliteIds() {
        // TODO: Task 1f)
        List<String> satelliteIds = new ArrayList<>();
        for (Satellite satellite : satelliteList) {
            satelliteIds.add(satellite.getSatelliteId());
        }
        return satelliteIds;
    }

    /**
     * Adds a file to the device with the specified ID.
     * 
     * @param deviceId the unique ID of the device to add the file to
     * @param filename the name of the file to add
     * @param content  the content of the file to add
     * @pre the device with the specified ID exists in deviceList
     * @post a new file is added to the device's file list
     */
    public void addFileToDevice(String deviceId, String filename, String content) {
        // TODO: Task 1g)
        // Find the device with the specified id
        File file = new File(filename, content);
        file.setSize(content.length());
        for (Device device : deviceList) {
            if (device.getDeviceId() == deviceId) {
                device.addFile(file);
            }
        }

    }

    /**
     * Gets detailed information about a single device or satellite.
     * 
     * @param id the unique ID of the device or satellite to get information about
     * @return an EntityInfoResponse object containing information about the device
     *         or satellite
     */
    public EntityInfoResponse getInfo(String id) {
        for (Satellite satellite : satelliteList) {
            if (satellite.getSatelliteId().equals(id)) {
                Map<String, FileInfoResponse> filesMap = createFilesMap(satellite.getFiles());
                return new EntityInfoResponse(id, satellite.getPosition(), satellite.getHeight(), satellite.getType(),
                        filesMap);
            }
        }

        for (Device device : deviceList) {
            if (device.getDeviceId().equals(id)) {
                Map<String, FileInfoResponse> filesMap = createFilesMap(device.getFiles());
                return new EntityInfoResponse(id, device.getPosition(), RADIUS_OF_JUPITER, device.getType(), filesMap);
            }
        }

        return null;
    }

    private Map<String, FileInfoResponse> createFilesMap(List<File> files) {
        Map<String, FileInfoResponse> filesMap = new HashMap<>();
        for (File file : files) {
            FileInfoResponse fileInfo = new FileInfoResponse(file.getFileName(), file.getContent(), file.getSize(),
                    file.isFileComplete());
            filesMap.put(file.getFileName(), fileInfo);
        }
        return filesMap;
    }

    public void simulate() {
        // TODO: Task 2a)
        for (Satellite satellite : satelliteList) {
            satellite.updatePosition();
        }

    }

    /**
     * Simulate for the specified number of minutes. You shouldn't need to modify
     * this function.
     */
    public void simulate(int numberOfMinutes) {
        for (int i = 0; i < numberOfMinutes; i++) {
            simulate();
        }
    }

    public List<String> communicableEntitiesInRange(String id) {
        // TODO: Task 2 b)
        return new ArrayList<>();
    }

    public void sendFile(String fileName, String fromId, String toId) throws FileTransferException {
        // TODO: Task 2 c)
    }

    public void createDevice(String deviceId, String type, Angle position, boolean isMoving) {
        createDevice(deviceId, type, position);
        // TODO: Task 3
    }

    public void createSlope(int startAngle, int endAngle, int gradient) {
        // TODO: Task 3
        // If you are not completing Task 3 you can leave this method blank :)
    }

}
