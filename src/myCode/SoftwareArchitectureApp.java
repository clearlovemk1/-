package myCode;

import structure1.function1;
import structure2.*;
import structure3.*;
import structure4.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SoftwareArchitectureApp {
    private JFrame frame;
    private JComboBox<String> architectureComboBox;
    private JTextArea outputArea;
    private JButton selectFileButton;
    private JButton processButton;
    private File selectedFile;
    private static final String OUTPUT_FILE_PATH = "D:\\output.txt"; // 固定输出文件路径

    public SoftwareArchitectureApp() {
        frame = new JFrame("经典软件体系结构教学软件");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        architectureComboBox = new JComboBox<>(new String[] {
                "主程序-子程序", "面向对象", "事件系统", "管道-过滤器"
        });

        outputArea = new JTextArea();
        selectFileButton = new JButton("选择输入文件");
        processButton = new JButton("处理文件");

        // 选择输入文件
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("文本文件 (*.txt)", "txt"));
                int returnValue = fileChooser.showOpenDialog(frame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    outputArea.append("已选择输入文件: " + selectedFile.getAbsolutePath() + "\n");
                }
            }
        });

        // 处理文件
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    String selectedArchitecture = (String) architectureComboBox.getSelectedItem();
                    String inputFilePath = formatFilePath(selectedFile.getAbsolutePath()); // 格式化路径
                    if (inputFilePath != null) {
                        switch (selectedArchitecture) {
                            case "主程序-子程序":
                                function1(inputFilePath);
                                break;
                            case "面向对象":
                                function2(inputFilePath);
                                break;
                            case "事件系统":
                                function3(inputFilePath);
                                break;
                            case "管道-过滤器":
                                try {
                                    function4(inputFilePath);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                break;
                        }
                        // 从 output.txt 文件中读取结果并显示在页面上
                        String outputContent = readFile(OUTPUT_FILE_PATH);
                        if (outputContent != null) {
                            outputArea.append("处理结果:\n" + outputContent + "\n");
                        }
                    }
                } else {
                    outputArea.append("请先选择一个文件。\n");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(selectFileButton);
        panel.add(architectureComboBox);
        panel.add(processButton);
        frame.add(panel, "North");
        frame.add(new JScrollPane(outputArea), "Center");

        frame.setVisible(true);
    }

    // 假设所有 function 已经将结果写入 D:\\output.txt
    private void function1(String fileContent) {
        function1.mainProgram_Subroutines(fileContent);// 处理逻辑
    }

    private void function2(String fileContent) {
        function2.OOP(fileContent);// 处理逻辑
    }

    private void function3(String fileContent) {
        function3.eventSystem(fileContent);// 处理逻辑
    }

    private void function4(String fileContent) throws IOException {
        function4.pope_Filter(fileContent);// 处理逻辑
    }

    // 将路径中的单反斜杠替换为双反斜杠
    private String formatFilePath(String filePath) {
        return filePath.replace("\\", "\\\\");
    }

    // 读取文件内容
    private String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            outputArea.append("读取文件时出错: " + e.getMessage() + "\n");
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SoftwareArchitectureApp());
    }
}




