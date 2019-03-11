package model;

public class NewElementJsonFile {
    private final long id;
    private final boolean valid;

    public NewElementJsonFile(long id, boolean valid){
        this.id=id;
        this.valid = valid;
    }

    public long getId(){
        return id;
    }

    public boolean getValid(){
        return valid;
    }
}
