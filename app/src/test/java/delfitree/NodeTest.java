package delfitree;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NodeTest
{
    @Test
    void testEquals()
    {
        Node nodeApp = new Node("uCEFApp");
        Node nodeAppClone = new Node("uCEFApp");
        Node nodeTest = new Node("uCEFTest");
        Node nodeNull = new Node(null);

        assertTrue(nodeApp.equals(nodeAppClone));
        assertFalse(nodeTest.equals(nodeApp));
        assertFalse(nodeTest.equals(nodeNull));
        assertFalse(nodeTest.equals(null));
    }

    @Test
    void testClear()
    {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");

        A.addIface(B);
        A.addIface(D);
        B.addIface(C);
        B.addIface(D);
        C.addIface(D);

        A.clear();

        assertEquals(1, A.getIfaceUses().size());
        assertEquals(1, B.getIfaceUses().size());
        assertEquals(1, C.getIfaceUses().size());
        assertEquals(B, A.getIfaceUses().get(0));
        assertEquals(C, B.getIfaceUses().get(0));
        assertEquals(D, C.getIfaceUses().get(0));
    }
}
