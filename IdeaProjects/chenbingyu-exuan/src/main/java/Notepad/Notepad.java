package Notepad;

import java.awt.*;
import javax.swing.*;
import java.text.DateFormat;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.datatransfer.*;
import java.util.*;
import javax.swing.Timer;
import java.io.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;


public class Notepad extends JFrame {

    private static final long serialVersionUID = 1L;
    JFrame frame;
    JMenuBar menuBar;
    JMenu file;
    JMenuItem open, newFile, save, print, exit;
    JMenuItem Searchitem;
    JMenu Search;
    JMenu View;
    JMenuItem cut, copy, paste;
    JMenuItem pcut, pcopy, ppaste;
    JPanel time;
    JLabel times;
    JMenu Manage;
    JMenu help;
    JMenuItem Convert;
    JMenuItem About;
    JFileChooser fileChooser;
    JTextArea textArea;
    JPopupMenu popup;

    Notepad() {
        frame = new JFrame("Notepad Application");
        file = new JMenu("File");
        View = new JMenu("View");
        Search = new JMenu("Search");
        Manage = new JMenu("Manage");
        help = new JMenu("Help");

        newFile = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        print = new JMenuItem("Print");
        exit = new JMenuItem("Exit");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        pcut = new JMenuItem("Cut");
        pcopy = new JMenuItem("Copy");
        ppaste = new JMenuItem("Paste");
        Searchitem = new JMenuItem("Search");
        About = new JMenuItem("About");
        Convert = new JMenuItem("Convert to PDF");
        textArea = new JTextArea();
        fileChooser = new JFileChooser();
        menuBar = new JMenuBar();
        popup = new JPopupMenu("Popup");
        time = new JPanel();
        times = new JLabel();

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollpane = new JScrollPane();
        scrollpane.setViewportView(textArea);


        file.add(open);
        file.add(newFile);
        file.add(save);
        file.add(print);
        file.add(exit);
        Manage.add(cut);
        Manage.add(copy);
        Manage.add(paste);
        Manage.add(Convert);
        Search.add(Searchitem);
        help.add(About);
        menuBar.add(file);
        menuBar.add(Search);
        menuBar.add(View);
        menuBar.add(Manage);
        menuBar.add(help);
        popup.add(pcut);
        popup.add(pcopy);
        popup.add(ppaste);

        frame.setJMenuBar(menuBar);
        frame.add(new ClockPane(), BorderLayout.NORTH);
        frame.add(textArea, BorderLayout.CENTER);

        NewListener NewL = new NewListener();
        OpenListener openL = new OpenListener();
        SaveListener saveL = new SaveListener();
        PrintListener PrintL = new PrintListener();
        CutListener CutL = new CutListener();
        CopyListener CopyL = new CopyListener();
        PasteListener PasteL = new PasteListener();
        ConvertListener ConvertL = new ConvertListener();
        ExitListener exitL = new ExitListener();
        SearchListener SearchL = new SearchListener();
        AboutListener AboutL = new AboutListener();
        open.addActionListener(openL);
        newFile.addActionListener(NewL);
        save.addActionListener(saveL);
        exit.addActionListener(exitL);
        print.addActionListener(PrintL);

        Searchitem.addActionListener(SearchL);
        cut.addActionListener(CutL);
        copy.addActionListener(CopyL);
        paste.addActionListener(PasteL);
        pcut.addActionListener(CutL);
        pcopy.addActionListener(CopyL);
        ppaste.addActionListener(PasteL);
        About.addActionListener(AboutL);
        //selectAll.addActionListener(SelectL);

        frame.setSize(800, 600);
        frame.setVisible(true);
        textArea.addMouseListener(new PopupTriggerListener());
    }

    class OpenListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(frame)) {
                File file = fileChooser.getSelectedFile();
                textArea.setText("");
                Scanner in = null;
                String fileName = file.getName();
                if (fileName.substring(fileName.lastIndexOf(".") + 1).equals("txt") || fileName.substring(fileName.lastIndexOf(".") + 1).equals("ini")) {
                    loadtxt(textArea, file);
                    in.close();
                }
                if (fileName.substring(fileName.lastIndexOf(".") + 1).equals("odt")) {
                    try {
                        in = new Scanner(file);
                        while (in.hasNext()) {
                            String line = in.nextLine();
                            textArea.append(line + "\n");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        in.close();
                    }
                }
            }
        }
    }

    public static void loadtxt(JTextArea textArea, File file) {
        Scanner in = null;
        try {
            in = new Scanner(file);
            while (in.hasNext()) {
                String line = in.nextLine();
                textArea.append(line + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            in.close();
        }
    }


    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(frame)) {
                File file = fileChooser.getSelectedFile();
                savetxt(textArea, file);


            }
        }
    }

    public static void savetxt(JTextArea textArea, File file) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(file);
            String output = textArea.getText();
            out.print(output);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                out.flush();
            } catch (Exception ex1) {

            }
            try {
                out.close();
            } catch (Exception ex1) {

            }
        }
    }


class ConvertListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(frame)) {
            File file = fileChooser.getSelectedFile();
            PrintWriter out = null;
            try {
                out = new PrintWriter(file);
                String output = textArea.getText();
                System.out.println(output);
                out.println(output);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    out.flush();
                } catch (Exception ex1) {

                }
                try {
                    out.close();
                } catch (Exception ex1) {

                }
            }
        }
    }
}

class NewListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        textArea.setText("");
    }
}

class SearchListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Chen Ming Yu 17189" + "\n" + "E Xuan 175181");
        JOptionPane.showInputDialog(frame, "What's your name?");


    }

}

class PrintListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        PrintService ps[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        PrintService service = ServiceUI.printDialog(null, 200, 200, ps, defaultService, flavor, pras);

        if (service != null) {
            try {
                DocPrintJob job = service.createPrintJob();
                DocAttributeSet das = new HashDocAttributeSet();
                ByteArrayInputStream fis = new ByteArrayInputStream(textArea.getText().getBytes());
                Doc doc = new SimpleDoc(fis, flavor, das);

                try {
                    job.print(doc, pras);
                    System.err.println("Job sent to printer.");
                } catch (PrintException ee) {
                    System.err.println("Print error!\n\n" + ee.getMessage());
                }
            } finally {

            }
        }
    }
}


class AboutListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Chen Ming Yu 17189" + "\n" + "E Xuan 175181");


    }

}

class ExitListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}


class PasteListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        try {
            String sel = (String) t.getTransferData(DataFlavor.stringFlavor);
            textArea.replaceRange(sel, textArea.getSelectionStart(), textArea.getSelectionEnd());
        } catch (Exception exc) {
            System.out.println("not string flavour");
        }

    }
}

class CopyListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String copystring = (String) textArea.getSelectedText();
        StringSelection ss = new StringSelection(copystring);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }
}

class CutListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String copystring = (String) textArea.getSelectedText();
        StringSelection ss = new StringSelection(copystring);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
    }
}

class PopupTriggerListener extends MouseAdapter {
    public void mousePressed(MouseEvent ev) {
        if (ev.isPopupTrigger()) {
            popup.show(ev.getComponent(), ev.getX(), ev.getY());
        }
    }

    public void mouseReleased(MouseEvent ev) {
        if (ev.isPopupTrigger()) {
            popup.show(ev.getComponent(), ev.getX(), ev.getY());
        }
    }

    public void mouseClicked(MouseEvent ev) {
    }
}

class ClockPane extends JPanel {

    private JLabel clock = new JLabel();

    public ClockPane() {
        setLayout(new BorderLayout());
        tickTock();
        add(clock);
        Timer timer = new Timer(500, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                tickTock();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }

    public void tickTock() {
        clock.setText(DateFormat.getDateTimeInstance().format(new Date()));
    }

}


    public static void main(String args[]) {
        Notepad n = new Notepad();
    }
}
