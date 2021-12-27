package delphitree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ForestTest {
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

        Forest forest = new Forest();
        forest.add(A);
        forest.add(B);
        forest.add(C);
        forest.add(D);

        forest.clear();

        assertEquals(forest.size(), 4);

        assertEquals(forest.get(0).getIfaceUses().size(), 1);
        assertEquals(forest.get(1).getIfaceUses().size(), 1);
        assertEquals(forest.get(2).getIfaceUses().size(), 1);
        assertEquals(forest.get(3).getIfaceUses().size(), 0);
    }
}
