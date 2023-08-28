import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        String logo = " _ \n"
                + "| |\n"
                + "| |\n"
                + "| |___\n"
                + "|_____|\n";
        System.out.println("Greetings from\n" + logo);
        System.out.println("Hi! This is your intelligent friend L.\n\"Dream big.\"\n" +
                "What can I do for you today?");
        while (scanner.hasNextLine()) {
            try {
                String repeat = scanner.nextLine();
                Pattern markPattern = Pattern.compile("(mark|unmark|delete) (\\d+)");
                Matcher markMatcher = markPattern.matcher(repeat);
                Pattern taskPattern = Pattern.compile("(todo|deadline|event) (.+)");
                Matcher taskMatcher = taskPattern.matcher(repeat);
                if (repeat.contains("bye") || repeat.contains("88")) {
                    System.out.println("Bye!\n\"Beware the barrenness of a busy life.\"");
                    break;
                } else if (markMatcher.matches()) {
                    String action = markMatcher.group(1);
                    int taskIndex = Integer.parseInt(markMatcher.group(2));
                    if (action.equals("delete")) {
                        System.out.println(storage.deleteTask(taskIndex));
                    } else {
                        boolean isDone = markMatcher.group(1).equals("mark");
                        System.out.println(storage.markTask(taskIndex, isDone));
                    }
                } else if (taskMatcher.matches()) {
                    System.out.println(storage.addTask(TaskType.valueOf(taskMatcher.group(1).toUpperCase()), taskMatcher.group(2)));
                } else if (repeat.contains("list") || repeat.contains("List")) {
                    System.out.println(storage.getTasks());
                } else {
                    throw new DukeException("undefined");
                }
            } catch (DukeException e) {
                String message = e.getMessage();
                if (message.equals("undefined")) {
                    System.out.println("⚠ Sorry! I am not able to understand you. Try another language:D");
                } else if (message.equals("task not found")) {
                    System.out.println("⚠ Oops! Cannot find the task:(");
                } else if (message.equals("todo error")) {
                    System.out.println("⚠ Oops! Need description for the todo:(");
                } else if (message.equals("deadline error")) {
                    System.out.println("⚠ Oops! Need description and by date for the deadline:(");
                } else if (message.equals("event error")) {
                    System.out.println("⚠ Oops! Need description, from and to date for the event:(");
                }
            }
        }
        scanner.close();
    }
}

