package delfitree;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;

import org.junit.jupiter.api.Test;

public class GraphBuilderTest {
    @Test
    void testBuildGraph()
    {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");
        Node X = new Node("X");
        Node Y = new Node("Y");

        A.addIface(B);
        A.addIface(C);
        B.addIface(D);
        B.addIface(C);
        D.addIface(C);
        D.addImpl(E);
        E.addImpl(F);
        F.addImpl(D);
        X.addIface(Y);

        B.setRoot(false);

        Forest nodes = new Forest();
        nodes.add(A);
        nodes.add(B);
        nodes.add(X);
        nodes.clear();

        GraphBuilder builder = new GraphBuilder(nodes);
        File dir = new File("umlstest/graphbuilder");
        dir.mkdirs();
        builder.build(false).flush(dir);
        assertNotNull(builder.getNodes());
    }
}
