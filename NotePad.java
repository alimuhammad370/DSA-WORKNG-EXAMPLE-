
package com.mycompany.mavenproject1;

import java.io.*;
import java.util.Scanner;

public class NotePad {
    public static class Node {
        char character;
        Node before;
        Node after;
        Node above;
        Node below;

public Node(char character) {
            this.character = character;
            this.before = this.after = this.above = this.below = null;
        }
    }

    public static class TextBuffer {
        Node head;
        Node cursor;

        public TextBuffer() {
            head = new Node('\n');
            cursor = head;
        }

 public void addCharacter(char c) {
            Node newNode = new Node(c);
            if (cursor.before != null) {
                cursor.before.after = newNode;
            }
            newNode.before = cursor.before;
            newNode.after = cursor;
            cursor.before = newNode;
            cursor = newNode;
        }

        public void deleteCharacter() {
            if (cursor.before != null) {
                Node prevNode = cursor.before;
                if (prevNode.before != null) {
                    prevNode.before.after = cursor;
                }
                cursor.before = prevNode.before;
            }
        }

        public void     moveCursorUp() {
            if (cursor.above != null) {
                cursor = cursor.above;
            }
        }

        public void moveCursorDown() {
            if (cursor.below != null) {
                cursor = cursor.below;
            }
        }

        public void moveCursorLeft() {
            if (cursor.before != null) {
                cursor = cursor.before;
            }
        }

        public void moveCursorRight() {
            if (cursor.after != null) {
                cursor = cursor.after;
            }
        }




        public void addNewLine() {
            Node newLine = new Node('\n');
            Node currentLine = cursor;
            while (currentLine.before != null) {
                currentLine = currentLine.before;
            }

            currentLine.below = newLine;
            newLine.above = currentLine;
            cursor = newLine;
        }

        public void printText() {
            Node currentLine = head;
            while (currentLine != null) {
                Node currentChar = currentLine;
                while (currentChar != null && currentChar.character != '\n') {
                    System.out.print(currentChar.character);
                    currentChar = currentChar.after;
                }
                System.out.println();
                currentLine = currentLine.below;
            }
        }
    }

public static class FileManager {
        private static final String FILENAME = "save.txt";

        public void save(TextBuffer buffer) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
                Node current = buffer.head;
                while (current != null) {
                    Node charNode = current;
                    while (charNode != null && charNode.character != '\n') {
                        writer.write(charNode.character);
                        charNode = charNode.after;
                    }
                    writer.newLine();
                    current = current.below;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public TextBuffer load() {
            TextBuffer buffer = new TextBuffer();
            try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    for (char c : line.toCharArray()) {
                        buffer.addCharacter(c);
                    }
                    buffer.addNewLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buffer;
        }
    }

    public static class Notepad {
            private TextBuffer textBuffer;
        private FileManager fileManager;

        public Notepad() {
            fileManager = new FileManager();
            textBuffer = fileManager.load();
        }

        public void start() {
            Scanner scanner = new Scanner(System.in);
            char input;
            while (true) {
                System.out.println("Current Text:");
                textBuffer.printText();
                System.out.println("Use 's' to save, 'l' to load, 'Esc' to exit.");

                input = scanner.next().charAt(0);
                handleInput(input);
            }
        }

        private void handleInput(char input) {
            if (input == 27) {  
                System.out.println("Exiting...");
                System.exit(0);
            } else if (input == 's') {  
                fileManager.save(textBuffer);
                System.out.println("Text saved.");
            } else if (input == 'l') {  
                textBuffer = fileManager.load();
                System.out.println("Text loaded.");
            } else if (input == 8) {  
                textBuffer.deleteCharacter();
            } else {
                textBuffer.addCharacter(input);  
            }
        }

        public static void main(String[] args) {
            Notepad notepad = new Notepad();
            notepad.start();
        }
    }
   
    
    public static void main(String[] args) {
        Notepad notepad = new Notepad();
        notepad.start();
    
}

}
