package delfitree;

import java.util.Map.Entry;

public class UmlEntry implements Entry<Node,Node>
{
    private final Node key;
    private final Node value;

    public UmlEntry(Node key, Node value) {
        this.key = key;
        this.value = value;

    }

    @Override
    public Node getKey() {
        return key;
    }

    @Override
    public Node getValue() {
        return value;
    }

    @Override
    public Node setValue(Node value) {
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (!(obj instanceof Entry))
        {
            return false;
        }

        Entry<Node, Node> other = (Entry<Node, Node>)obj;
        return getKey().equals(other.getKey()) &&
        getValue().equals(other.getValue());
    }
}
