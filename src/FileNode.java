import java.util.HashMap;
import java.util.Map;

class FileNode {
    public FileNode parent;
    public Map<String, FileNode> children = new HashMap<String, FileNode>();
    public boolean isFile;
    public String path;

    public FileNode(){}

    public FileNode(String path, boolean isFile) {
        this.path = path;
        this.isFile = isFile;
    }

}