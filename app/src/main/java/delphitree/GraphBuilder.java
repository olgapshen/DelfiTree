package delphitree;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import delphitree.exceptions.GraphBuilderException;
import lombok.Data;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Data
public class GraphBuilder
{
    private final Map<String, String> graphs;
    private final Forest nodes;

    public GraphBuilder(Forest nodes)
    {
        this.graphs = new HashMap<>();
        this.nodes = nodes;
    }

    private void iterate(
        Node node,
        List<Node> declarations,
        List<Entry<Node, Node>> iface,
        List<Entry<Node, Node>> impl,
        List<Node> list
    ) {
        if (list.contains(node)) return;
        else list.add(node);

        if (!declarations.contains(node)) declarations.add(node);

        for (Node child : node.getIfaceUses())
        {
            Entry<Node, Node> entry = new UmlEntry(node, child);
            if (!iface.contains(entry)) iface.add(entry);
            iterate(child, declarations, iface, impl, list);
        }

        for (Node child : node.getImplUses())
        {
            Entry<Node, Node> entry = new UmlEntry(node, child);
            if (!impl.contains(entry)) impl.add(entry);
            iterate(child, declarations, iface, impl, list);
        }
    }

    private void addNode(Node root)
    {
        StringBuilder uml = new StringBuilder();
        List<Node> declarations = new ArrayList<>();
        List<Entry<Node, Node>> iface = new ArrayList<>();
        List<Entry<Node, Node>> impl = new ArrayList<>();

        List<Node> list = new LinkedList<>();
        iterate(root, declarations, iface, impl, list);

        uml.append(String.format("@startuml %s", root));
        uml.append(System.lineSeparator());
        uml.append(String.format("title: %s (%s)", root, root.isRoot() ? "корень" : "узел"));
        uml.append(System.lineSeparator());

        for (Node node : declarations) {
            uml.append(String.format("class %s", node.toString()));
            uml.append(System.lineSeparator());
        }

        for (Entry<Node, Node> node : iface)
        {
            uml.append(String.format("%s -down-> %s", node.getKey(), node.getValue()));
            uml.append(System.lineSeparator());
        }

        for (Entry<Node, Node> node : impl)
        {
            uml.append(String.format("%s .down.> %s", node.getKey(), node.getValue()));
            uml.append(System.lineSeparator());
        }

        uml.append("@enduml");
        graphs.put(root.toString(), uml.toString());
    }

    public GraphBuilder build(Boolean justRoots)
    {
        for (Node node : nodes)
            if (!justRoots || node.isRoot())
                addNode(node);

        return this;
    }

    public void flush(File destDir)
    {
        for (String node : graphs.keySet()) {
            String content = graphs.get(node);
            File destFile = new File(destDir.toString(), String.format("%s.puml", node));
            try (OutputStream out = new BufferedOutputStream(new FileOutputStream(destFile))) {
                out.write(content.getBytes());
            } catch (IOException e) {
                throw new GraphBuilderException(e.getMessage());
            }
        }
    }
}
