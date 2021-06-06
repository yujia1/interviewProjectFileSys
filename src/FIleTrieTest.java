import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FIleTrieTest {
    private FileTrie fileTrie;

    @Before
    public void setUp(){
        fileTrie = new FileTrie();
        fileTrie.insert("/ab/cd/ef/gh");
        fileTrie.insert("/abc/def/ghi");
    }

    @Test
    public void testIsPath(){
        FileNode node = fileTrie.searchFile("/ab/cd/ef/gh");
        assertEquals(true, node.isFile);
    }

    @Test
    public void testInsert() {
        assertTrue(fileTrie.search("/ab"));
        assertTrue(fileTrie.search("/abc/def"));
        assertTrue(fileTrie.search("/ab/cd/ef/gh"));
    }

    @Test
    public void testSearch(){
        assertTrue(fileTrie.search("/abc"));
        assertTrue(fileTrie.search("/ab/cd"));
        assertTrue(fileTrie.search("/ab/cd/ef"));
        assertTrue(fileTrie.search("/ab/cd/ef/gh"));
    }

    @Test
    public void testSearchFile() {
        String currentPath = "/ab/cd/ef";
        String parentPath = "/ab/cd";
        FileNode node = fileTrie.searchFile("/ab/cd/ef");
        assertEquals(currentPath, node.path);
        assertEquals(parentPath, node.parent.path);
        assertEquals(true, node.isFile);
    }
}
