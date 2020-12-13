package proofit.objects;

public enum FormHeader {
    APPLY_LEAVE("Apply Leave");

    private String header;

    private FormHeader(String header) {
        this.header = header;
    }

    public String header() {
        return this.header;
    }
}
