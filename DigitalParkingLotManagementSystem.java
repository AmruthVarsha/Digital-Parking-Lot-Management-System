import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ParkingLot{
    HashMap<Integer,String> carMap=new HashMap<>();
    HashMap<Integer,String> bikeMap=new HashMap<>();
    ImageIcon bikeimage=new ImageIcon("bike image.png");
    ImageIcon carimage=new ImageIcon("car image.png");

    ParkingLot(int carSlots,int bikeSlots){
        for(int i=1;i<=carSlots;i++){
            carMap.put(i,null);
        }
        for(int i=1;i<=bikeSlots;i++){
            bikeMap.put(i,null);
        }
    }

    public JButton parkVehicle(String vehicleNumber,String type,int id, JButton clickedButton){
        Image img;
        if(type.equals("car")){
            img=carimage.getImage();
        }
        else{
            img=bikeimage.getImage();
        }

        Image img2=img.getScaledInstance(clickedButton.getWidth()-10, clickedButton.getHeight()-15,img.SCALE_SMOOTH);
        ImageIcon resimg=new ImageIcon(img2);

        if(type.equalsIgnoreCase("car") && !vehicleNumber.equals("")){
            if(carMap.get(id)==null){
                carMap.put(id,vehicleNumber);
                buttonImaging(resimg, clickedButton,vehicleNumber);
                clickedButton.setBackground(new Color(240,128,128));
                return null;

            }
        }
        else if(type.equalsIgnoreCase("bike") && !vehicleNumber.equals("")){
            if(bikeMap.get(id)==null){
                bikeMap.put(id,vehicleNumber);
                buttonImaging(resimg, clickedButton,vehicleNumber);
                clickedButton.setBackground(new Color(255,182,110));
                return null;
            }
        }
        return clickedButton;
        
    }

    public void removeVehicle(String vehicleNumber,String type,int id, JButton clickedButton){
        if(clickedButton!=null){
            if(type.equalsIgnoreCase("car")){
                carMap.put(id,null);
                clickedButton.setBackground(new Color(135,206,250));

            }
            else if(type.equalsIgnoreCase("bike")){
                bikeMap.put(id,null);
                clickedButton.setBackground(new Color(144,238,144));
            }
            clickedButton.setIcon(null);
            clickedButton.setText(id+"");
        }
    }

    public void buttonImaging(ImageIcon resimg,JButton clickedButton,String vehicleNumber){
        clickedButton.setText(vehicleNumber);
        clickedButton.setIcon(resimg);
        clickedButton.setHorizontalTextPosition(SwingConstants.CENTER);
        clickedButton.setVerticalTextPosition(SwingConstants.NORTH);
    }
}


public class DigitalParkingLotManagementSystem{

    private static int carSlots=24,bikeSlots=30,id=-1;
    public static String vehicleNumber="",type="";
    public static JTextField t1;
    public static JButton carClickedbutton,bikeClickedbutton;
    public static void main(String[] args) {

        ParkingLot obj=new ParkingLot(carSlots,bikeSlots);

        JFrame mainFrame=new JFrame("Parking Lot");
        mainFrame.setSize(800,800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.getContentPane().setBackground(new Color(30,30,30));

        ImageIcon logo=new ImageIcon("logo.jpg");

        mainFrame.setIconImage(logo.getImage());

        JLabel textLabel1=new JLabel("Enter Vehicle Number: ");
        textLabel1.setOpaque(true);
        textLabel1.setBackground(new Color(220,220,220));

        t1=new JTextField("");
        t1.setBackground(new Color(220,220,220));

        JPanel textPanel=new JPanel();
        textPanel.setLayout(new GridLayout(1,2,10,10));
        textPanel.add(textLabel1);
        textPanel.add(t1);
        textPanel.setBackground(new Color(220,220,220));

        JButton park=new JButton("Park");
        JButton remove=new JButton("Remove");
        park.setBackground(new Color(240,230,140));
        remove.setBackground(new Color(240,230,140));

        JPanel buttonPanel=new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2,10,10));
        buttonPanel.add(park);
        buttonPanel.add(remove);
        buttonPanel.setBackground(new Color(220,220,220));

        JPanel topPanel=new JPanel();
        topPanel.setLayout(new BorderLayout(0,20));
        topPanel.add(textPanel,BorderLayout.NORTH);
        topPanel.add(buttonPanel,BorderLayout.CENTER);
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.setBackground(new Color(220,220,220));

        JLabel carParking=new JLabel("Car Parking");
        carParking.setOpaque(true);
        carParking.setBackground(new Color(220,220,220));
        JLabel bikeParking=new JLabel("Bike Parking");
        bikeParking.setOpaque(true);
        bikeParking.setBackground(new Color(220,220,220));

        JPanel headingPanel=new JPanel();
        headingPanel.setLayout(new GridLayout(1,2,10,10));
        headingPanel.add(carParking);
        headingPanel.add(bikeParking);
        headingPanel.setBackground(new Color(220,220,220));


        JPanel carGridPanel=new JPanel();
        carGridPanel.setLayout(new GridLayout(6,6,5,5));
        HashMap<JButton,Integer> carButtons=new HashMap<>();
        for(int i=1;i<=carSlots;i++){
            JButton button=new JButton(""+i);
            carButtons.put(button,i);
            button.setBackground(new Color(135,206,250));

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    checkButtons();
                    button.setBackground(new Color(240,128,128));
                    id=carButtons.get(button);
                    carClickedbutton=(JButton) e.getSource();
                    type="car";
                    
                }
            });

            carGridPanel.add(button);
            
        }
        carGridPanel.setBackground(new Color(220,220,220));

        JPanel bikeGridPanel=new JPanel();
        bikeGridPanel.setLayout(new GridLayout(10,5,5,5));
        HashMap<JButton,Integer> bikeButtons=new HashMap<>();
        for(int i=1;i<=bikeSlots;i++){
            JButton button=new JButton(""+i);
            bikeButtons.put(button,i);
            button.setBackground(new Color(144,238,144));

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    checkButtons();
                    button.setBackground(new Color(255,182,110));
                    id=bikeButtons.get(button);
                    bikeClickedbutton=(JButton) e.getSource();
                    type="bike";

                }
            });

            bikeGridPanel.add(button);
            
        }
        bikeGridPanel.setBackground(new Color(220,220,220));

        JPanel totalGridPanel=new JPanel();
        totalGridPanel.setLayout(new GridLayout(1,2,10,10));
        totalGridPanel.add(carGridPanel);
        totalGridPanel.add(bikeGridPanel);
        totalGridPanel.setBackground(new Color(220,220,220));

        JPanel outputPanel=new JPanel();
        outputPanel.setLayout(new BorderLayout(0,20));
        outputPanel.add(headingPanel,BorderLayout.NORTH);
        outputPanel.add(totalGridPanel,BorderLayout.CENTER);
        outputPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        outputPanel.setBackground(new Color(220,220,220));

        JPanel mainOutputPanel=new JPanel();
        mainOutputPanel.setLayout(new BorderLayout(0,20));
        mainOutputPanel.add(topPanel,BorderLayout.NORTH);
        mainOutputPanel.add(outputPanel,BorderLayout.CENTER);
        mainOutputPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainOutputPanel.setBackground(new Color(220,220,220));

        park.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                updateText();
                if(type.equalsIgnoreCase("car")){
                    carClickedbutton=obj.parkVehicle(vehicleNumber,type,id,carClickedbutton);
                }
                else if(type.equalsIgnoreCase("bike")){
                    bikeClickedbutton=obj.parkVehicle(vehicleNumber, type, id,bikeClickedbutton);
                }
            }
        });

        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(type.equalsIgnoreCase("car")){
                    obj.removeVehicle(vehicleNumber,type,id,carClickedbutton);
                }
                else if(type.equalsIgnoreCase("bike")){
                    obj.removeVehicle(vehicleNumber, type, id,bikeClickedbutton);
                }
            }
        });




        mainFrame.add(mainOutputPanel);
 

        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static void updateText(){
        vehicleNumber=t1.getText();
        t1.setText("");
    }

    public static void checkButtons(){
        if(carClickedbutton!=null){
            carClickedbutton.setBackground(new Color(135,206,250));
        }
        if(bikeClickedbutton!=null){
            bikeClickedbutton.setBackground(new Color(144,238,144));
        }
    }
}