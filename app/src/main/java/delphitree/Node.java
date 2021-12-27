package delphitree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import delphitree.exceptions.UnhandledIncludeType;
import lombok.Data;

@Data
public class Node
{
    private final String moduleName;
    private List<Node> ifaceUses;
    private List<Node> implUses;
    private boolean isRoot;
    private boolean isCustom;

    public Node(String moduleName) {
        this.ifaceUses = new LinkedList<>();
        this.implUses = new LinkedList<>();
        this.moduleName = moduleName;
        this.isRoot = true;
        this.isCustom = true;
    }

    public void addIface(Node child)
    {
        ifaceUses.add(child);
    }

    public void addImpl(Node child)
    {
        implUses.add(child);
    }

    private List<Node> getUses(IncludeType includeType)
    {
        switch (includeType) {
            case Interface:
                return ifaceUses;
            case Implementation:
                return implUses;
            default:
                throw new UnhandledIncludeType(includeType);
        }
    }

    private Node findNode(String moduleName, List<Node> list, IncludeType includeType)
    {
        if (list.contains(this)) return null;
        else list.add(this);

        if (this.moduleName.equals(moduleName))
        {
            return this;
        } else {
            for (Node node : getUses(includeType))
            {
                Node found = node.findNode(moduleName, list, includeType);
                if (found != null) {
                    return found;
                }
            }

            return null;
        }
    }

    public Node findNodeIface(String moduleName)
    {
        return findNode(moduleName, new LinkedList<>(), IncludeType.Interface);
    }

    public Node findNodeImpl(String moduleName)
    {
        return findNode(moduleName, new LinkedList<>(), IncludeType.Implementation);
    }

    private void merge(IncludeType includeType, List<Node> handled) {
        if (handled.contains(this)) return;
        else handled.add(this);

        Set<Node> toremove = new HashSet<>();

        for (Node child : getUses(includeType)) {
            for (Node check : getUses(includeType)) {
                if (child.toString().endsWith(String.format(".%s", check.toString())))
                    toremove.add(check);
            }
        }

        for (Node node : toremove) {
            getUses(includeType).remove(node);
        }

        for (Node child : getUses(includeType)) {
            child.merge(includeType, handled);
        }
    }

    private Set<Node> clear(IncludeType includeType, List<Node> handled)
    {
        Set<Node> toremove = new HashSet<>();
        Set<Node> nodes = new HashSet<>();

        //System.out.println("==== " + this.toString() + " ====");

        for (Node child : getUses(includeType)) {
            if (handled.contains(child)) continue;
            else handled.add(child);

            for (Node transitive : child.clear(includeType, handled)) {
                toremove.add(transitive);
                nodes.add(transitive);
            }

            nodes.add(child);
        }

        for (Node node : toremove) {
            getUses(includeType).remove(node);
        }

        return nodes;
    }

    public void clear()
    {
        clear(IncludeType.Interface, new LinkedList<>());
        clear(IncludeType.Implementation, new LinkedList<>());
        merge(IncludeType.Interface, new LinkedList<>());
        merge(IncludeType.Implementation, new LinkedList<>());
    }

    @Override
    public String toString() {
        return moduleName;
    }

    @Override
    public boolean equals(Object obj) {
        final Node other = (Node) obj;

        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        } else if (this.moduleName == null && other.moduleName != null){
            return false;
        } else if (this.moduleName != null && other.moduleName == null){
            return false;
        } else if (!this.moduleName.equals(other.moduleName)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int hashCode() {
        return this.moduleName != null ? this.moduleName.hashCode() : 0;
    }
}
