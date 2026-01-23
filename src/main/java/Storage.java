import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.FileWriter;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    // Use of ChatGPT for File/Path methods used
    // Use of ChatGPT for LocalDateTime methods
   
    public TaskList load() throws IOException {
        checkFileExists();

        ArrayList<Task> tasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(this.filePath)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return new TaskList(tasks);
    }

    public void save(ArrayList<Task> taskList) throws IOException {
        checkFileExists();

        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            for (Task task : taskList) {
                writer.write(task.toString() + "\n");
            }
        }
    }

    private void checkFileExists() throws IOException {
        Path parent = filePath.getParent();
        // second check !Files.exists(parent) suggested by chatgpt
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

    private Task parseTask(String line) {
        if (line.length() < 6) {
            return null;
        }

        char type = line.charAt(1); // check T/D/E
        boolean isDone = line.charAt(4) == 'X';
        String description;
        String extra = null; // extra info within parentheses

        int parenIndex = line.indexOf('(');
        if (parenIndex != -1) {
            description = line.substring(6, parenIndex).trim();
            extra = line.substring(parenIndex + 1, line.length() - 1).trim();
        } else {
            description = line.substring(6).trim();
        }

        if (type == 'T') {
            ToDo todo = new ToDo(description);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;

        } else if (type == 'D') {
            if (extra != null && extra.startsWith("by: ")) {
                try {
                    LocalDateTime deadlineTime = DateTimeParser.parse(extra.substring(4).trim());
                    Deadline deadline = new Deadline(description, deadlineTime);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    return deadline;
                } catch (AnoopException e){
                    System.err.println("Failed to parse deadline");
                }
            }
            return null;

        } else if (type == 'E') {
            if (extra != null && extra.startsWith("from: ")) {
                String[] parts = extra.substring(6).split(" to: ");
                if (parts.length == 2) {
                    try {
                        LocalDateTime fromTime = DateTimeParser.parse(parts[0].trim());
                        LocalDateTime toTime = DateTimeParser.parse(parts[1].trim());
                        Event event = new Event(description, fromTime, toTime);
                        if (isDone) {
                            event.markAsDone();
                        }
                        return event;
                    } catch (AnoopException e) {
                        System.err.println("Failed to parse from / to time");
                    }
                }
                return null;
            }
        }
        return null;
    }

}
