package Homework;

import java.util.*;

public class HomeworkM10FolderDriver {

	public static void main(String[] args) {
		Folder folder0 = new Folder("Folder0");
		
	   	// The second parameter is the depth of the folder hierarchy.
    	// I recommend starting with something small (such as 2).
    	// Eventually, test with larger numbers.
		// You should run tests multiple times for each depth- the code
		// uses random numbers to generate the hierarchy, so you want
		// to run those tests more than once to make sure you catch
		// any errors.
    	buildFolders(folder0, 5);
    	List<Folder> folder0SubFolders = folder0.getFolderList();
		
    	System.out.println("*****************************************************RECURSIVE PRINT:");
    	// parameter 1: the top folder of the hierarchy
    	// parameter 2: true if you want to see the print with indentation, false otherwise
    	printFolderContentsRecursive(folder0, true);
    	
    	System.out.println("\n*****************************************************STACK PRINT:");
    	printFolderContentsWithStack(folder0);
    	
    	System.out.println("\n*****************************************************CONFIRM FOLDER ORDER:");    	
    	System.out.println("Test that folder list order is not altered:");
    	System.out.println("Before printing, Folder0 subfolders: " + folder0SubFolders);
    	System.out.println(" After printing, Folder0 subfolders: " + folder0.getFolderList());
 	}
	
	public static void printFolderContentsRecursive(Folder topFolder, boolean indent) {
		printFolderContentsHelper(topFolder, 0, indent);
	}
	private static void printFolderContentsHelper(Folder topFolder, int indentationIndex, boolean indent) {
		for (int i = 0; indent && i < indentationIndex; i++) {
			System.out.print("   ");
		}
		System.out.println(topFolder);
		if(!topFolder.getFileList().isEmpty()) {
			for (int i = 0; indent && i < indentationIndex+1; i++) {
				System.out.print("   ");
			}
			System.out.println("Files: " + topFolder.getFileList()); // small part solving now- print the files in the current folder
		}
		
		List<Folder> subfolderList = topFolder.getFolderList();
		for (Folder subfolder : subfolderList) {
			printFolderContentsHelper(subfolder, indentationIndex+1, indent); // recursive call
		}
		
		// implicit base case- happens when a folder has no more subfolders- the recursion will end

	}
	
	
	public static void printFolderContentsWithStack(Folder topFolder) {
		Stack<Folder> folderStack = new Stack<Folder>();
		folderStack.push(topFolder);
		Stack<Integer> indentStack = new Stack<>();		// track indent level in separate stack
		indentStack.push(0);

		while (!folderStack.isEmpty()) {
			Folder current = folderStack.pop();
			int indentationIndex = indentStack.pop();

			for (int i = 0; i < indentationIndex; i++) {
				System.out.print("   ");
			}

			System.out.println(current);

			if (!current.getFileList().isEmpty()) {
				for (int i = 0; i < indentationIndex + 1; i++) {
					System.out.print("   ");
				}

				System.out.println("Files: " + current.getFileList()); // small part solving now- print the files in the current folder
			}

			List<Folder> subfolderList = current.getFolderList();

			for (int i = subfolderList.size() - 1; i >= 0; i--) {	// push folders into stack in reverse
				folderStack.push(subfolderList.get(i));
				indentStack.push(indentationIndex + 1);
			}
		}
	}


	/* these methods are here to randomly build the folder/file hierarchy */
	private static final Random generator = new Random();
	private static int folderNumber = 1;
	private static int fileCharNumber = 65;
	
	private static void addFiles(Folder folder, int numberOfFiles) {
		String filePrefix = folder.getName().replace("Homework.Folder","") + "/Homework.File-";
		for(int i=1; i<=numberOfFiles; i++) {
			char fileNameChar = (char) (fileCharNumber + (i-1));
			fileCharNumber++;
			File file = new File(filePrefix + fileNameChar);
			folder.addFile(file);
		}
	}
	
	private static void addSubFolders(Folder folder, int numberOfSubfolders) {
		String folderPrefix =  folder.getName() + "/Homework.Folder";
		for(int i=1; i<=numberOfSubfolders; i++) {
			Folder subFolder = new Folder(folderPrefix + folderNumber);
			folderNumber++;
			folder.addFolder(subFolder);
		}
	}
	
	private static void buildFolders(Folder folder, int depth) {
		if(depth>1) {
			addSubFolders(folder, generator.nextInt(3));
			for(Folder f : folder.getFolderList()) {
				buildFolders(f, depth-1);
			}
			addFiles(folder, generator.nextInt(4));
		} else {
			addFiles(folder, generator.nextInt(4));
		}
	}

}
