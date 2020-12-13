package proofit.objects;

public enum Wait {
    LONG(15000),
    SHORT(3000);

    private int length;

    private Wait(int length) {
        this.length = length;
    }

    public int length() {
        return this.length;
    }
}
