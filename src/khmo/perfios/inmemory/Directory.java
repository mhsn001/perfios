package khmo.perfios.inmemory;

import java.util.Set;
import java.util.TreeSet;

public class Directory extends AbstractDirectory{

	
	
	private Set<AbstractDirectory> nodes;

    public Directory(String path) {
        super(path);
        nodes = new TreeSet<AbstractDirectory>();
    }

    public void add(AbstractDirectory node) {
        node.setRoot(this);
        nodes.add(node);
    }

    public Set<AbstractDirectory> getNodes() {
        return nodes;
    }

    @Override
    public long getLength() {
        long length = 0;
        for (AbstractDirectory node : nodes) {
            length += node.getLength();
        }
        return length;
    }

    public void printTree() {
        int indent = 0;
        StringBuilder sb = new StringBuilder();
        printDirectoryTree(this, indent, sb);
        System.out.println(sb.toString());
    }

    private void printDirectoryTree(AbstractDirectory AbstractDirectory, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(AbstractDirectory.getName());
        sb.append("/");
        sb.append("\n");

        if (AbstractDirectory.isDirectory()) {
            Directory directory = (Directory) AbstractDirectory;
            for (AbstractDirectory file : directory.getNodes()) {
                printDirectoryTree(file, indent + 1, sb);
            }
        }
    }

    private static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("|  ");
        }
        return sb.toString();
    }

}
