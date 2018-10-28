package khmo.perfios.inmemory;

public class InMemoryFileSystemMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Directory root = new Directory("root");
        File file = new File("wallpaper.jpg");
        root.add(file);

        Directory personal = new Directory("personal");
        root.add(personal);

        Directory study = new Directory("study");

        study.add(new File("javaTutrial.mp4"));
        study.add(new File("angularjsTutorial.mp4"));
        File headFirst = new File("headFirst.pdf");
        study.add(headFirst);
        personal.add(study);

        Directory movies = new Directory("Movies");
        movies.add(new File("Avengers"));
        movies.add(new File("Dangal"));

        personal.add(movies);

        root.printTree();

        System.out.println("name: " + personal.getName());
        System.out.println("Created: " + personal.getCreated());

	}

}
