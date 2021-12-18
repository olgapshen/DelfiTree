package delfitree;

import java.util.LinkedList;
import java.util.List;

public class Forest extends LinkedList<Node>
{
    public Node getNodeByName(String name)
    {
        for (Node node : this) {
            Node found = node.findNodeIface(name);
            if (found != null) return found;

            found = node.findNodeImpl(name);
            if (found != null) return found;
        }

        return null;
    }

    public void clear()
    {
        this.stream().forEach(f -> f.clear());
        List<Node> toremove = new LinkedList<>();
        for (Node node : this) {
            if (!node.isCustom())
                toremove.add(node);
        }

        toremove.stream().forEach(f -> this.remove(f));
    }
}
