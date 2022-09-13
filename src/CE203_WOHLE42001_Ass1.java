import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CE203_WOHLE42001_Ass1 {

    public static void main(String[] args) {
        /*
         * Creates the frame and sets the title.
         * */
        ID frame = new ID();
        frame.setTitle("User Control Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class ID extends JFrame{
    static String data;

    public ID() {
        /*
         * Contains all the buttons and action listeners,
         * to wish each button has its own action listener and set of instructions to complete.
         * Organises each button and text to where they need to be according to the assignment brief.
         * */

        setSize(460,150);

        ArrayList<String> identification = new ArrayList<>();
        Color finalForm = new Color(0,0,0);

        JButton submitButton = new JButton("Submit");
        JButton removeButton = new JButton("Remove");
        JButton sortButton = new JButton("Sort");
        JButton clearList = new JButton("Clear List");
        JButton colourChange = new JButton("Colour submit");


        JLabel enterIDLabel = new JLabel("Enter ID:");
        enterIDLabel.setBounds(10,10,100,100);
        JLabel label1 = new JLabel();
        label1.setBounds(10,110,150,100);
        JTextField textField = new JTextField(6);
        textField.setBounds(70,45,200,30);

        JLabel rgbColourLabel = new JLabel("Enter RGB:");
        rgbColourLabel.setBounds(10,10,100,100);
        JLabel label2 = new JLabel();
        label2.setBounds(10,100,150,100);
        JTextField value1 = new JTextField(3);
        JTextField value2 = new JTextField(3);
        JTextField value3 = new JTextField(3);
        value1.setBounds(100,45,200,30);
        value2.setBounds(110,45,200,30);
        value3.setBounds(120,45,200,30);


        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(submitButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(clearList);
        panel.add(enterIDLabel);
        panel.add(textField);
        panel.add(label1);
        panel.add(rgbColourLabel);
        panel.add(value1);
        panel.add(value2);
        panel.add(value3);
        panel.add(colourChange);
        panel.add(label2);

        add(panel, BorderLayout.PAGE_START);
        add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(submit -> {
            /*
             * Submit button. Takes identification which is the array that holds all the IDs and displays it on the JFrame.
             * */
            data = textField.getText();
            int lenData = data.length();
            if (lenData == 6) {
                identification.add(data);
                newList(finalForm,identification);
            } else {
                JOptionPane.showMessageDialog(null, "Input must be 6 digits. Try again.");
            }
        });

        removeButton.addActionListener(remove -> {
            /*
             * Removes the data that matches the value of the text field.
             * */
            data = textField.getText();
            identification.remove(data);
            newList(finalForm,identification);
        });

        sortButton.addActionListener(sort -> {
            /*
             * Takes the array which will then use Collection.sort to sort the entire array.
             * */
            String [] stringArray = identification.toArray(new String[0]);
            int sizeOfArray = stringArray.length;
            int [] newSize = new int[sizeOfArray];              //Converts the array to an integer
            for(int i = 0; i < sizeOfArray; i++) {
                newSize[i] = Integer.parseInt(stringArray[i]);
            }
            Collections.sort(identification);
            newList(finalForm,identification);
        });

        clearList.addActionListener(clear -> {
            /*
             * Clears the Array.
             * */
            identification.clear();
            newList(finalForm,identification);
        });

        colourChange.addActionListener(colour -> {
            /*
             * Calls the Colours class which has the three inputs from the user.
             * In order to change the colour to the specified colour.
             * Catches the error when user doesnt input a value.
             * */
            try {
                Colour colourClass = new Colour();
                String int1 = value1.getText();
                String int2 = value2.getText();
                String int3 = value3.getText();
                colourClass.mainColour(int1,int2,int3, identification);

            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Input must have a value. Try again.");
            }

        });
    }

    public void newList(Color finalForm, ArrayList<String> identification) {
        /*
         *Converts the entire array to a string in order to be displayed on the JFrame.
         * Once creating the labels it sets them to the colour the user has inputted.
         * Then revalidates the page just so it would refresh itself with the new data the user has inputted.
         *  */
        String newString = String.valueOf(toString(identification));

        String idText = "ID: " + newString;
        JLabel idLabel = new JLabel(idText);
        idLabel.setForeground(finalForm);

        JPanel lists = new JPanel();
//        System.out.println(finalForm);        // Outputs the value of finalForm which is the colour value.
        lists.add(idLabel);
        add(lists, BorderLayout.CENTER);
        revalidate();

//        System.out.println(identification);   //Outputs the ID array.
    }
    // outputs a string representation of the object
    public List<String> toString(ArrayList<String> identification) {
        return identification;
    }
}

class Colour {

    public void mainColour(String int1, String int2, String int3, ArrayList<String> identification){
        /*
         * Converts the users input into an integer which it will then check if they are in bounds with
         * the colour limits, 0 to 255.
         * Which will then call newList within FilledFrame class and assign the new font colour to the input.
         * */
        int finalValue1 = Integer.parseInt(int1);
        int finalValue2 = Integer.parseInt(int2);
        int finalValue3 = Integer.parseInt(int3);

        if (finalValue1 >= 0 && finalValue1 <= 255
                && finalValue2 >=0 && finalValue2 <= 255
                && finalValue3 >=0 && finalValue3 <= 255) {
            Color finalForm = new Color(finalValue1, finalValue2, finalValue3);
            ID colours = new ID();
            colours.newList(finalForm, identification);

        } else{
            JOptionPane.showMessageDialog(null, "Values must be between 0 to 255. Try Again.");
            Color finalForm = new Color(0, 0, 0);
            ID colours = new ID();
            colours.newList(finalForm,identification);
        }
    }
}

