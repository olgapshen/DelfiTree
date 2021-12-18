package delfitree;

import delfitree.exceptions.UnhandledIncludeType;

public enum IncludeType {
    None((byte)0x00),
    Interface((byte)0x01),
    Implementation((byte)0x02),
    Both((byte)0x03);

    private byte flags;

    private IncludeType(byte flags) {
        this.flags = flags;
    }

    public byte value()
    {
        return flags;
    }

    public boolean enabledFor(IncludeType includeType)
    {
        return (flags & includeType.value()) != 0;
    }

    @Override
    public String toString() {
        switch (this) {
            case Interface:
                return "Interface";
            case Implementation:
                return "Implementation";
            case Both:
                return "Both";
            default:
                throw new UnhandledIncludeType(this);
        }
    }
}
