package delphitree;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;

import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    void testApp()
    {
        File dir = new File("src/test/resources/");
        TreeParser parser = new TreeParser(dir);

        File noneDir = new File("umlstest/app/none");
        File ifaceDir = new File("umlstest/app/iface");
        File implDir = new File("umlstest/app/impl");
        File bothDir = new File("umlstest/app/both");

        noneDir.mkdirs();
        ifaceDir.mkdirs();
        implDir.mkdirs();
        bothDir.mkdirs();

        final String prefix = "uCEF";

        Forest nodes = parser.makeTree(IncludeType.None, prefix, "");
        GraphBuilder builder = new GraphBuilder(nodes);
        builder.build(false).flush(noneDir);
        assertNotNull(builder.getNodes());

        nodes = parser.makeTree(IncludeType.Interface, prefix, "");
        builder = new GraphBuilder(nodes);
        builder.build(false).flush(ifaceDir);
        assertNotNull(builder.getNodes());

        nodes = parser.makeTree(IncludeType.Implementation, prefix, "");
        builder = new GraphBuilder(nodes);
        builder.build(false).flush(implDir);
        assertNotNull(builder.getNodes());

        nodes = parser.makeTree(IncludeType.Both, prefix, "");
        builder = new GraphBuilder(nodes);
        builder.build(false).flush(bothDir);
        assertNotNull(builder.getNodes());
    }
}
