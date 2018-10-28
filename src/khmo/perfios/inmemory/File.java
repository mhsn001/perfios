package khmo.perfios.inmemory;

public class File extends AbstractDirectory {
    private String content; /// for simplicity

    public File(String name, String content) {
        super(name);
        this.content = content;
    }

    public File(String name) {
        super(name);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public long getLength() {
        if (content != null) {
            return content.getBytes().length;
        }

        return 0;
    }
}
