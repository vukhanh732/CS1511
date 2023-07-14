package unsw.blackout;

import unsw.response.models.FileInfoResponse;

public class File {
    private String fileName;
    private String content;
    private int fileSize;
    private boolean isFileComplete;

    public File(String filename, String content) {
        this.fileName = filename;
        this.content = content;
        this.isFileComplete = true;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSize() {
        return fileSize;
    }

    public void setSize(int size) {
        this.fileSize = size;
    }

    public boolean isFileComplete() {
        return isFileComplete;
    }

}
