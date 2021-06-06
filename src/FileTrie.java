
import java.util.HashMap;
import java.util.Map;
//goal: change current directory
// given two path string from command line and prints either a new path or an error message

// first path is current dir
// second path is new dir
// directory can only have alph characters
// a single dot '." indicates a current directoory
// .. indicate previous path
// / indicates a root directory

//program check second path validation without replying on OS sys or shell command

// Q:
// 1. the order of . .. / as input
// 2. how to identify .. and .
// 2. if current dir is on same root with second, does application allows to do it?

/**
 * test
 */
//# mycd / abc
//        /abc
//
//
//        # mycd /abc/def ghi
//        /abc/def/ghi
//
//
//        # mycd /abc/def ..
//        /abc
//
//
//        # mycd /abc/def /abc
//        /abc
//
//
//        # mycd /abc/def /abc/klm
//        /abc/klm
//
//
//        # mycd /abc/def ../..
//        /
//
//
//        # mycd /abc/def ../../..
//        /
//
//
//        # mycd /abc/def .
//        /abc/def
//
//
//        # mycd /abc/def ..klm
//        ..klm: No such file or directory
//
//
//        # mycd /abc/def //////
//        /
//
//
//        # mycd /abc/def ......
//        ......: No such file or directory
//
//
//        # mycd /abc/def ../gh///../klm/.
//        /abc/klm

public class FileTrie {


    private FileNode root;

    public FileTrie() {
      root = new FileNode("", true);
    }

    public boolean insert(String filePath) {
        String[] files = splitPath(filePath);

        FileNode cur = root;
        if (root == null || filePath == null) return false;

        for (int i = 0; i < files.length; i++) {
            FileNode next = cur.children.get(files[i]);
            if (next == null) {
                next = new FileNode();
                cur.children.put(files[i], next);   // add new file node
                cur.children.get(files[i]).parent = cur;
                cur.children.get(files[i]).path = cur.path + "/" + files[i];
                cur.children.get(files[i]).isFile = true;
            }

            cur = next; // move to next following file path
        }
        cur.isFile = true; // set true path
        return true;
    }
    public boolean search(String filePath) {
        FileNode cur = root;
        String[] files = splitPath(filePath);
        for (int i = 0; i < files.length; i++) {
            FileNode next = cur.children.get(files[i]);
            if (next == null) return false;
            cur = next;
        }
        return cur.isFile;
    }

    public FileNode searchFile(String filePath) {
       Map<String, FileNode> child = root.children;
       FileNode t= null;
       String[] files = splitPath(filePath);
       for (int i = 0; i < files.length; i++) {
           String file = files[i];
           if (child.containsKey(file)) {
               t = child.get(file);
               child = t.children;
           } else {
               return null;
           }
       }
       return t;
    }

    public String[] splitPath(String filePath){
        String str = filePath.substring(1);
        return str.split("/");
    }

    public String changeDirectory(String command, String currentPath, String newPath) throws Exception{
        if (command != "mycd") {
            System.out.println("-bash: " + command +": command not found");
        }
        if (!search(currentPath) || !search(newPath)) {
            System.out.println("No such file or directory");
        }
        String path = "";
        return path;
    }

    public static void main(String[] args) {
//        FileTrie fileTrie = new FileTrie();
//        fileTrie.insert("/ab/cd/ef/gh");
//        FileNode node = fileTrie.searchFile("/ab/cd/ef");
//        System.out.println(node.path);
//        System.out.println(node.parent.path);
    }
}
