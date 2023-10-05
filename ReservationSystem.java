import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class HomePage extends JFrame{
    JPanel bg;
    JPanel panel_bar;
    JPanel Booking_panel;
    JPanel Ticket_panel;

    JDialog profile;
    JDialog ticket_details;

    JLabel title_label;
    JLabel ticket_info;
    JLabel booking_info;
    JLabel pnr_label;

    JLabel user_Label;
    JLabel Mobile;
    JLabel email;
    JLabel date;
    JLabel source;
    JLabel dest;
    JLabel classType;
    JLabel train_number;
    JLabel train_name;
    JLabel pnrLabel;


    JTextField pnr_field;
    JTextField User_field;
    JTextField number_field;
    JTextField email_field;

    JTextArea train_names;

    JComboBox<String>days;
    JComboBox<String>months;
    JComboBox<String>years;
    JComboBox<String>sources;
    JComboBox<String>destination;
    JComboBox<String>clstype;
    JComboBox<String>train_num;

    JButton exit_btn;
    JButton logout_btn;
    JButton profile_btn;
    JButton insert_btn;
    JButton submit_btn;

    static String DD = String.format("%02d",Calendar.getInstance().get(Calendar.DATE));
    static String MM = String.format("%02d",Calendar.getInstance().get(Calendar.MONTH)+1);
    static int YY = Calendar.getInstance().get(Calendar.YEAR);
    static String []days_set= new String[31];
    static String []months_set = {"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
    static String []years_set = {String.valueOf(YY),String.valueOf(YY+1),String.valueOf(YY+2),String.valueOf(YY+3),String.valueOf(YY+4),String.valueOf(YY+5),String.valueOf(YY+6),String.valueOf(YY+7)};
    static String pnr_no;

    Connection conn;
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "SHISHIR", "Skm@7330");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    void Home(){
        int Width = ((Toolkit.getDefaultToolkit()).getScreenSize()).width;
        int Height = ((Toolkit.getDefaultToolkit()).getScreenSize()).height;

        ImageIcon obj = new ImageIcon("bg.jpg");
        Image img=obj.getImage();
        Image temp=img.getScaledInstance(Width,Height,Image.SCALE_SMOOTH);
        obj = new ImageIcon(temp);
        JLabel bg=new JLabel("",obj,JLabel.CENTER);
        bg.setBounds(0,0,Width,Height);
        add(bg);

        bg.setLayout(new GridBagLayout());
        GridBagConstraints c= new GridBagConstraints();
        c.anchor= GridBagConstraints.NORTH;
        c.fill=GridBagConstraints.HORIZONTAL;

        panel_bar = new JPanel(new GridBagLayout());
        panel_bar.setPreferredSize(new Dimension(Width,60));
        panel_bar.setBackground(new Color(28, 80, 200));
        GridBagConstraints c1= new GridBagConstraints();
        c1.anchor= GridBagConstraints.WEST;

        exit_btn=new JButton("EXIT");
        exit_btn.setBackground(new Color(28, 80, 200));
        exit_btn.setForeground(Color.WHITE);
        exit_btn.setFont(new Font("Arial", Font.BOLD, 20));
        exit_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exit_btn.setContentAreaFilled(false);
        exit_btn.setBorderPainted(false);
        exit_btn.setFocusPainted(false);
        exit_btn.addActionListener(e -> System.exit(0));
        c1.gridx=0;
        c1.gridy=0;
        panel_bar.add(exit_btn,c1);

        title_label = new JLabel("ONLINE RESERVATION SYSTEM",JLabel.CENTER);
        title_label.setPreferredSize(new Dimension(1500,25));
        title_label.setBackground(new Color(28, 80, 200));
        title_label.setForeground(Color.WHITE);
        title_label.setFont(new Font("Arial", Font.BOLD, 25));
        c1.gridx=1;
        c1.gridy=0;
        panel_bar.add(title_label,c1);

        logout_btn=new JButton("LOG OUT");
        logout_btn.setBackground(new Color(28, 80, 200));
        logout_btn.setForeground(Color.WHITE);
        logout_btn.setFont(new Font("Arial", Font.BOLD, 20));
        logout_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout_btn.setContentAreaFilled(false);
        logout_btn.setBorderPainted(false);
        logout_btn.setFocusPainted(false);
        logout_btn.addActionListener(e -> {this.dispose(); new ReservationSystem();});
        c1.gridx=2;
        c1.gridy=0;
        panel_bar.add(logout_btn,c1);

        profile_btn=new JButton("PROFILE");
        profile_btn.setBackground(new Color(28, 80, 200));
        profile_btn.setForeground(Color.WHITE);
        profile_btn.setFont(new Font("Arial", Font.BOLD, 20));
        profile_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profile_btn.setContentAreaFilled(false);
        profile_btn.setBorderPainted(false);
        profile_btn.setFocusPainted(false);
        profile_btn.addActionListener(e -> {Profile(ReservationSystem.userid,ReservationSystem.check);});
        c1.gridx=3;
        c1.gridy=0;
        panel_bar.add(profile_btn,c1);

        Booking_panel =new JPanel(new GridBagLayout());
        Booking_panel.setPreferredSize(new Dimension(Width-500,Height-60));
        Booking_panel.setOpaque(false);
        Booking_panel.setBackground(new Color(0,0,0,130));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;

        booking_info = new JLabel("TICKET BOOKING",JLabel.CENTER);
        booking_info.setPreferredSize(new Dimension(Width-550,28));
        booking_info.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        booking_info.setOpaque(true);
        booking_info.setBackground(new Color(170, 101, 37));
        booking_info.setForeground(Color.BLACK);
        booking_info.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=4;
        gbc.weighty=1.0;
        gbc.insets = new Insets(6,0,20,0);
        Booking_panel.add(booking_info,gbc);

        user_Label = new JLabel("PASSENGER NAME :   ",JLabel.CENTER);
        user_Label.setForeground(Color.WHITE);
        user_Label.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0,350,0,0);
        Booking_panel.add(user_Label,gbc);

        User_field = new JTextField(30);
        User_field.setForeground(Color.BLACK);
        User_field.setBackground(Color.WHITE);
        User_field.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=3;
        gbc.insets = new Insets(0,0,0,0);
        Booking_panel.add(User_field,gbc);

        Mobile = new JLabel("MOBILE NUMBER :   ",JLabel.CENTER);
        Mobile.setForeground(Color.WHITE);
        Mobile.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0,350,0,0);
        Booking_panel.add(Mobile,gbc);

        number_field = new JTextField(30);
        number_field.setForeground(Color.BLACK);
        number_field.setBackground(Color.WHITE);
        number_field.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=3;
        gbc.insets = new Insets(0,0,0,0);
        Booking_panel.add(number_field,gbc);

        email = new JLabel("GMAIL :   ",JLabel.CENTER);
        email.setForeground(Color.WHITE);
        email.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0,350,0,0);
        Booking_panel.add(email,gbc);

        email_field = new JTextField(30);
        email_field.setForeground(Color.BLACK);
        email_field.setBackground(Color.WHITE);
        email_field.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx=1;
        gbc.gridy=3;
        gbc.gridwidth=3;
        gbc.insets = new Insets(0,0,0,0);
        Booking_panel.add(email_field,gbc);

        date = new JLabel("BOOKING DATE :   ",JLabel.CENTER);
        date.setForeground(Color.WHITE);
        date.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0,350,0,0);
        Booking_panel.add(date,gbc);

        days = new JComboBox<String>();
        days.setForeground(Color.BLACK);
        days.setBackground(Color.WHITE);
        days.setSelectedItem(DD);
        days.setFont(new Font("Arial", Font.PLAIN, 20));
        {
            if(MM.equals("02")){
                for (int i=Integer.parseInt(DD);i<=28;i++){
                    days.addItem(String.format("%02d",i));
                }
            }
            else if (MM.equals("04") || MM.equals("06") || MM.equals("09") || MM.equals("11")){
                for (int i=Integer.parseInt(DD);i<=30;i++){
                    days.addItem(String.format("%02d",i));
                } 
            }
            else{
                for (int i=Integer.parseInt(DD);i<=31;i++){
                    days.addItem(String.format("%02d",i));
                }
            }
        }   
        gbc.gridx=3;
        gbc.gridy=4;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0,30,0,0);
        days.setFocusable(false);
        days.setOpaque(false);
        Booking_panel.add(days,gbc);

        months = new JComboBox<String>();
        months.setForeground(Color.BLACK);
        months.setBackground(Color.WHITE);
        for (int ms=(Integer.parseInt(MM)-1);ms<12;ms++){
            months.addItem(months_set[ms]);
        }
        months.setFont(new Font("Arial", Font.PLAIN, 20));
        months.setFocusable(false);
        months.setOpaque(false);
        months.addItemListener(e ->{
            days.removeAllItems();
            String month = String.valueOf(months.getSelectedItem());
            int curr_date=1;
            if (months.getSelectedIndex() == 0 && String.valueOf(YY).equals(years.getSelectedItem())){
                curr_date=Integer.parseInt(DD);
            }
            if(month.equals("FEBRUARY")){
                for (int i=curr_date;i<=28;i++){
                    days.addItem(String.format("%02d",i));
                }
            }
            else if (month.equals("APRIL") || month.equals("JUNE") || month.equals("SEPTEMBER") || month.equals("NOVEMBER")){
                for (int i=curr_date;i<=30;i++){
                    days.addItem(String.format("%02d",i));
                } 
            }
            else{
                for (int i=curr_date;i<=31;i++){
                    days.addItem(String.format("%02d",i));
                }
            }
        });
        gbc.gridx=2;
        gbc.gridy=4;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0,30,0,0);
        Booking_panel.add(months,gbc);

        years = new JComboBox<String>(years_set);
        years.setForeground(Color.BLACK);
        years.setBackground(Color.WHITE);
        years.setSelectedItem(String.valueOf(YY));
        years.setFocusable(false);
        years.setOpaque(false);
        years.setFont(new Font("Arial", Font.PLAIN, 20));
        years.addItemListener(e ->{
            days.removeAllItems();
            String month = String.valueOf(months.getSelectedItem());
            int curr_date=1;
            if (months.getSelectedIndex() == 0 && String.valueOf(YY).equals(years.getSelectedItem())){
                curr_date=Integer.parseInt(DD);
                months.removeAllItems();
                for (int ms=(Integer.parseInt(MM)-1);ms<12;ms++){
                    months.addItem(months_set[ms]);
                }
            }
            else{
                months.removeAllItems();
                for (int ms=0;ms<12;ms++){
                    months.addItem(months_set[ms]);
                }
            }
            if(month.equals("FEBRUARY")){
                for (int i=curr_date;i<=28;i++){
                    days.addItem(String.format("%02d",i));
                }
            }
            else if (month.equals("APRIL") || month.equals("JUNE") || month.equals("SEPTEMBER") || month.equals("NOVEMBER")){
                for (int i=curr_date;i<=30;i++){
                    days.addItem(String.format("%02d",i));
                } 
            }
            else{
                for (int i=curr_date;i<=31;i++){
                    days.addItem(String.format("%02d",i));
                }
            }
        });
        gbc.gridx=1;
        gbc.gridy=4;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0,30,0,0);
        Booking_panel.add(years,gbc);

        source = new JLabel("SOURCE :   ",JLabel.CENTER);
        source.setForeground(Color.WHITE);
        source.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0,350,0,0);
        Booking_panel.add(source,gbc);

        dest = new JLabel("DESTINATION :   ",JLabel.CENTER);
        dest.setForeground(Color.WHITE);
        dest.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx=0;
        gbc.gridy=6;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0,350,0,0);
        Booking_panel.add(dest,gbc);

        String []boarding_point ={"--select--","BHUBANESWAR","DELHI","KOLKATA","MUMBAI","HYDERABAD","LUCKNOW","BENGALURU"}; 
        sources = new JComboBox<String>(boarding_point);
        sources.setPreferredSize(new Dimension(480,30));
        sources.setForeground(Color.BLACK);
        sources.setBackground(Color.WHITE);
        sources.setOpaque(false);
        sources.setFocusable(false);
        sources.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx=1;
        gbc.gridy=5;
        gbc.gridwidth=3;
        gbc.insets = new Insets(0,0,0,0);
        Booking_panel.add(sources,gbc);

        String []unboarding = {"--select--"};
        destination = new JComboBox<String>(unboarding);
        destination.setPreferredSize(new Dimension(480,30));
        destination.setForeground(Color.BLACK);
        destination.setBackground(Color.WHITE);
        destination.setOpaque(false);
        destination.setFocusable(false);
        destination.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx=1;
        gbc.gridy=6;
        gbc.gridwidth=3;
        gbc.insets = new Insets(0,0,0,0);
        Booking_panel.add(destination,gbc);

        ItemListener itemListener1 = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt){
                String s = (String)evt.getItem();
                switch(s){
                    case "BHUBANESWAR":{
                        destination.removeAllItems();
                        destination.addItem("--select--");
                        destination.addItem("PURI");
                        destination.addItem("KOLKATA");
                        destination.addItem("HYDERABAD");
                        destination.addItem("BHOPAL");
                        break;
                    }
                    case "DELHI":{
                        destination.removeAllItems();
                        destination.addItem("--select--");
                        destination.addItem("LUCKNOW");
                        destination.addItem("SRINAGAR");
                        destination.addItem("CHANDIGARH");
                        destination.addItem("JAIPUR");
                        break;
                    }
                    case "KOLKATA":{
                        destination.removeAllItems();
                        destination.addItem("--select--");
                        destination.addItem("LUCKNOW");
                        destination.addItem("BHUBANESWAR");
                        destination.addItem("SHILLONG");
                        destination.addItem("RANCHI");
                        break;
                    }
                    case "MUMBAI":{
                        destination.removeAllItems();
                        destination.addItem("--select--");
                        destination.addItem("JAIPUR");
                        destination.addItem("PUNE");
                        destination.addItem("PANAJI");
                        destination.addItem("BENGALURU");
                        break;
                    }
                    case "HYDERABAD":{
                        destination.removeAllItems();
                        destination.addItem("--select--");
                        destination.addItem("BHUBANESWAR");
                        destination.addItem("AMARAVATI");
                        destination.addItem("CHENNAI");
                        destination.addItem("BENGALURU");
                        break;
                    }
                    case "LUCKNOW":{
                        destination.removeAllItems();
                        destination.addItem("--select--");
                        destination.addItem("DELHI");
                        destination.addItem("KOLKATA");
                        destination.addItem("SHIMLA");
                        destination.addItem("PATNA");
                        break;
                    }
                    case "BENGALURU":{
                        destination.removeAllItems();
                        destination.addItem("--select--");
                        destination.addItem("MUMBAI");
                        destination.addItem("CHENNAI");
                        destination.addItem("HYDERABAD");
                        destination.addItem("THIRUVANANTHAPURAM");
                        break;
                    }
                    default:{
                        break;
                    }

                }
            }
        };
        sources.addItemListener(itemListener1);

        classType = new JLabel("CLASS TYPE :   ",JLabel.CENTER);
        classType.setForeground(Color.WHITE);
        classType.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx=0;
        gbc.gridy=7;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0,350,0,0);
        Booking_panel.add(classType,gbc);

        String []clstypes ={"--select--","FIRST AC(1A)","EXECUTIVE CLASS(EC)","SECOND AC(2A)","FIRST CLASS(FC)","THIRD AC(3A)","SLEEPER CLASS(SL)","SECOND CLASS(2S)"}; 
        clstype = new JComboBox<String>(clstypes);
        clstype.setPreferredSize(new Dimension(480,30));
        clstype.setForeground(Color.BLACK);
        clstype.setBackground(Color.WHITE);
        clstype.setOpaque(false);
        clstype.setFocusable(false);
        clstype.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx=1;
        gbc.gridy=7;
        gbc.gridwidth=3;
        gbc.insets = new Insets(0,0,0,0);
        Booking_panel.add(clstype,gbc);

        train_number = new JLabel("TRAIN NUMBER :   ",JLabel.CENTER);
        train_number.setForeground(Color.WHITE);
        train_number.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx=0;
        gbc.gridy=8;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0,350,0,0);
        Booking_panel.add(train_number,gbc);

        String []train = {" "};
        train_num = new JComboBox<String>(train);
        train_num.setPreferredSize(new Dimension(480,30));
        train_num.setForeground(Color.BLACK);
        train_num.setBackground(Color.WHITE);
        train_num.setSelectedIndex(0);
        train_num.setOpaque(false);
        train_num.setFocusable(false);
        train_num.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx=1;
        gbc.gridy=8;
        gbc.gridwidth=3;
        gbc.insets = new Insets(0,0,0,0);
        Booking_panel.add(train_num,gbc);

        train_name = new JLabel("TRAIN NAME/S :   ",JLabel.CENTER);
        train_name.setPreferredSize(new Dimension(700,30));
        train_name.setForeground(new Color(0,0,0));
        train_name.setOpaque(true);
        train_name.setBackground(new Color(255,255,255,150));
        train_name.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx=0;
        gbc.gridy=9;
        gbc.gridwidth=4;
        gbc.insets = new Insets(0,350,0,0);
        Booking_panel.add(train_name,gbc);

        train_names = new JTextArea(5,30);
        train_names.setPreferredSize(new Dimension(700,80));
        train_names.setForeground(Color.BLACK);
        train_names.setOpaque(true);
        train_names.setEditable(false);
        train_names.setBackground(new Color(0,0,0,0));
        train_names.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx=0;
        gbc.gridy=10;
        gbc.gridwidth=4;
        gbc.insets = new Insets(0,350,0,0);
        Booking_panel.add(train_names,gbc);

        insert_btn = new JButton("INSERT");
        insert_btn.setForeground(Color.WHITE);
        insert_btn.setBackground(new Color(133, 24, 170));
        insert_btn.setFont(new Font("Arial", Font.BOLD, 20));
        insert_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        insert_btn.setFocusPainted(false);
        insert_btn.addActionListener(e -> {
            try{
                String YY1 = String.valueOf(years.getSelectedItem());
                String MM1="00";
                switch(String.valueOf(months.getSelectedItem())){
                    case "JANUARY": {MM1="01"; break;}
                    case "FEBRUARY" : {MM1="02"; break;}
                    case "MARCH" :{ MM1="03"; break;}
                    case "APRIL" :{ MM1="04"; break;}
                    case "MAY" :{ MM1="05"; break;}
                    case "JUNE" :{ MM1="06"; break;}
                    case "JULY" :{ MM1="07"; break;}
                    case "AUGUST" :{ MM1="08"; break;}
                    case "SEPTEMBER" :{ MM1="09"; break;}
                    case "OCTOBER" :{ MM1="10"; break;}
                    case "NOVEMBER" : {MM1="11"; break;}
                    case "DECEMBER" :{ MM1="12"; break;}
                }
                String DD1 = String.valueOf(days.getSelectedItem());
                if (String.valueOf(clstype.getSelectedItem()).equals("--select--")){
                    throw new java.lang.NumberFormatException();
                }
                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                st.execute("use ReservationSystem");
                st.execute("CREATE TABLE IF NOT EXISTS PassengerData(USER_ID VARCHAR(30) NOT NULL, MOBILE_NUMBER int(15) NOT NULL, EMAIL TEXT, DATE_OF_BOOKING DATE NOT NULL, SOURCE TEXT NOT NULL, DESTINATION TEXT NOT NULL, CLASS_TYPE VARCHAR(30) NOT NULL, TRAIN_NUMBER INTEGER NOT NULL, TRAIN_NAME TEXT NOT NULL, PNR_NUMBER TEXT NOT NULL)");
                PreparedStatement ps = conn.prepareStatement("INSERT INTO PassengerData VALUES(?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1,User_field.getText());
                ps.setInt(2,Integer.parseInt(String.valueOf(number_field.getText())));
                ps.setString(3,email_field.getText());
                ps.setDate(4,java.sql.Date.valueOf(YY1+"-"+MM1+"-"+DD1));
                ps.setString(5,String.valueOf(sources.getSelectedItem()));
                ps.setString(6,String.valueOf(destination.getSelectedItem()));
                ps.setString(7,String.valueOf(clstype.getSelectedItem()));
                ps.setInt(8,Integer.parseInt(String.format("%.5s",String.valueOf(train_num.getSelectedItem()))));
                ps.setString(9,(String.valueOf(train_num.getSelectedItem())).substring(10,(String.valueOf(train_num.getSelectedItem())).length()));
                DD = String.format("%02d",Calendar.getInstance().get(Calendar.DATE));
                MM = String.format("%02d",Calendar.getInstance().get(Calendar.MONTH)+1);
                YY = Calendar.getInstance().get(Calendar.YEAR);
                String hh = String.format("%02d",Calendar.getInstance().get(Calendar.HOUR));
                String mm = String.format("%02d",Calendar.getInstance().get(Calendar.MINUTE));
                String ss = String.format("%02d",Calendar.getInstance().get(Calendar.SECOND));
                pnr_no = String.valueOf(String.valueOf(YY)+""+MM+""+DD+""+hh+""+mm+""+ss);
                ps.setString(10,pnr_no);
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Ticket Booked Successfully!!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                User_field.setText("");
                number_field.setText("");
                email_field.setText("");
                sources.setSelectedIndex(0);
                destination.setSelectedIndex(0);
                clstype.setSelectedIndex(0);
                train_num.removeAllItems();
                Ticket_Details("insert");
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, "All Fields are Required for Ticket Booking !!","FAILURE",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gbc.gridx=0;
        gbc.gridy=11;
        gbc.anchor= GridBagConstraints.NORTH;
        gbc.gridwidth=4;
        gbc.insets = new Insets(0,0,0,0);
        Booking_panel.add(insert_btn,gbc);

        ItemListener itemListener2 = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ev){
                train_num.removeAllItems();
                train_names.setText("");
                try{
                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    stmt.execute("use ReservationSystem");
                    PreparedStatement ps = conn.prepareStatement("SELECT TRAIN_NUMBER,TRAIN_NAME FROM train_routes WHERE SOURCES = ? AND DESTINATION = ?");
                    ps.setString(1,String.valueOf(sources.getSelectedItem()));
                    ps.setString(2,String.valueOf(destination.getSelectedItem()));
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        train_names.setBackground(Color.WHITE);
                        train_num.addItem(rs.getString(1)+" --> "+rs.getString(2));
                        train_names.append(rs.getString(2)+"\n");
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        };
        destination.addItemListener(itemListener2);


        Ticket_panel = new JPanel(new GridBagLayout());
        Ticket_panel.setPreferredSize(new Dimension(500,Height-60));
        Ticket_panel.setBackground(new Color(0,0,0,140));
        Ticket_panel.setBorder(BorderFactory.createEmptyBorder(0,10,0, 10));
        GridBagConstraints gc= new GridBagConstraints();
        gc.anchor= GridBagConstraints.NORTH;
        gc.insets= new Insets(5,5,5,5);

        ticket_info = new JLabel("CANCEL TICKET",JLabel.CENTER);
        ticket_info.setPreferredSize(new Dimension(440,28));
        ticket_info.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ticket_info.setOpaque(true);
        ticket_info.setBackground(new Color(170, 101, 37));
        ticket_info.setForeground(Color.BLACK);
        ticket_info.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        gc.gridx=0;
        gc.gridy=0;
        gc.gridwidth=2;
        Ticket_panel.add(ticket_info,gc);

        pnr_label = new JLabel("PNR NUMBER ",JLabel.CENTER);
        pnr_label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pnr_label.setForeground(Color.WHITE);
        gc.gridx=0;
        gc.gridy=1;
        gc.gridwidth=1;
        gc.insets = new Insets(20,0,20,0);
        Ticket_panel.add(pnr_label,gc);

        pnr_field = new JTextField(22);
        pnr_field.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pnr_field.setBackground(Color.WHITE);
        pnr_field.setForeground(Color.BLACK);
        pnr_field.setOpaque(true);
        pnr_field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gc.gridx=1;
        gc.gridy=1;
        gc.gridwidth=1;
        gc.insets = new Insets(20,0,20,0);
        Ticket_panel.add(pnr_field,gc);

        submit_btn = new JButton("SUBMIT");
        submit_btn.setBackground(new Color(38, 149, 33));
        submit_btn.setForeground(Color.WHITE);
        submit_btn.setFont(new Font("Arial", Font.BOLD, 20));
        submit_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit_btn.setFocusPainted(false);
        submit_btn.addActionListener(e -> {
            try{Ticket_Details("delete");}catch(Exception Ex){}
        });
        gc.gridx=0;
        gc.gridy=2;
        gc.gridwidth=2;
        gc.weighty=1.0;
        gc.insets = new Insets(30,0,0,0);
        Ticket_panel.add(submit_btn,gc);


        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        bg.add(panel_bar,c);
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=1;
        bg.add(Booking_panel,c);
        c.gridx=1;
        c.gridy=1;
        c.gridwidth=1;
        bg.add(Ticket_panel,c);

        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void Profile(String uservalue,String passvalue){
        JLabel username;
        JLabel password;
        JLabel newpassword;
        JLabel header;
        JTextField userTextField;
        JPasswordField passwordField;
        JPasswordField newpasswordField;
        JButton update;

        profile = new JDialog(this,"USER PROFILE");
        profile.setLayout(new GridBagLayout());
        GridBagConstraints c= new GridBagConstraints();
        c.anchor= GridBagConstraints.WEST;
        c.fill=GridBagConstraints.HORIZONTAL;

        header = new JLabel("PROFILE",JLabel.CENTER);
        header.setPreferredSize(new Dimension(440,28));
        header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        header.setOpaque(true);
        header.setBackground(new Color(28, 80, 200));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        profile.add(header,c);

        username=new JLabel("USENAME:",JLabel.LEFT);
        username.setFont(new Font("Arial", Font.BOLD, 20));
        username.setForeground(Color.BLACK);
        c.gridx=0;
        c.gridy=1;
        c.ipady=10;
        c.gridwidth=1;
        profile.add(username,c);

        userTextField = new JTextField(20);
        userTextField.setText(uservalue);
        userTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        userTextField.setForeground(Color.BLACK);
        c.gridx=1;
        c.gridy=1;
        c.ipady=0;
        c.weighty=0.5;
        profile.add(userTextField,c);

        password=new JLabel("NEW PASSWORD:",JLabel.LEFT);
        c.gridx=0;
        c.gridy=2;
        c.ipady=10;
        password.setFont(new Font("Arial", Font.BOLD, 20));
        password.setForeground(Color.BLACK);
        profile.add(password,c);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordField.setForeground(Color.BLACK);
        passwordField.setText(passvalue);
        passwordField.setEchoChar('*');
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e){
                passwordField.setEchoChar('\0');
                passwordField.setText(String.valueOf(passwordField.getPassword()));
            }
            @Override
            public void focusLost(FocusEvent e){
                passwordField.setEchoChar('*');
                passwordField.setText(String.valueOf(passwordField.getPassword()));
            }

        });
        c.gridx=1;
        c.gridy=2;
        c.ipady=0;
        c.weighty=0.5;
        profile.add(passwordField,c);

        newpassword=new JLabel("RE-TYPE PASSWORD:",JLabel.LEFT);
        newpassword.setFont(new Font("Arial", Font.BOLD, 20));
        newpassword.setForeground(Color.BLACK);
        c.gridx=0;
        c.gridy=3;
        c.ipady=0;
        c.weighty=0.5;
        profile.add(newpassword,c);

        newpasswordField = new JPasswordField(20);
        newpasswordField.setFont(new Font("Arial", Font.PLAIN, 20));
        newpasswordField.setForeground(Color.BLACK);
        newpasswordField.setEchoChar('\0');
        newpasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e){
                newpasswordField.setEchoChar('\0');
                newpasswordField.setText(String.valueOf(newpasswordField.getPassword()));
            }
            @Override
            public void focusLost(FocusEvent e){
                newpasswordField.setEchoChar('*');
                newpasswordField.setText(String.valueOf(newpasswordField.getPassword()));
            }

        });
        c.gridx=1;
        c.gridy=3;
        c.ipady=0;
        c.weighty=0.5;
        profile.add(newpasswordField,c);

        update=new JButton("SAVE");
        update.setBackground(new Color(28, 80, 200));
        update.setForeground(Color.WHITE);
        update.setPreferredSize(new Dimension(450,35));
        update.setBorder(BorderFactory.createLineBorder(new Color(170, 101, 37)));
        update.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        update.setCursor(new Cursor(Cursor.HAND_CURSOR));
        update.setFocusPainted(false);
        update.addActionListener(e -> {
            if ((String.valueOf(passwordField.getPassword())).equals((String.valueOf(newpasswordField.getPassword())))){
                if ((String.valueOf(passwordField.getPassword())).length() == 0){
                    JOptionPane.showMessageDialog(profile, "Can not change password!!","QUERY",JOptionPane.INFORMATION_MESSAGE);
                }
                else if (ReservationSystem.check.equals(String.valueOf(passwordField.getPassword()))){
                    JOptionPane.showMessageDialog(profile, "Can not change to old password!!","QUERY",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    ReservationSystem.userid = userTextField.getText();
                    ReservationSystem.check = String.valueOf(passwordField.getPassword());
                    try{
                        Statement smt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                        smt.execute("use ReservationSystem");

                        PreparedStatement pst = conn.prepareStatement("SELECT * FROM Credentials WHERE USER_ID = ?");
                        pst.setString(1,ReservationSystem.userid);
                        ResultSet rs = pst.executeQuery();
                        if(rs.next()){
                            if((rs.getString(2)).equals(ReservationSystem.check)){
                                JOptionPane.showMessageDialog(profile, "Can not change to old password!!","QUERY",JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                pst = conn.prepareStatement("UPDATE Credentials SET PASSWORD = ? WHERE USER_ID = ?");
                                pst.setString(1,ReservationSystem.check);
                                pst.setString(2,ReservationSystem.userid);
                                pst.executeUpdate();
                                JOptionPane.showMessageDialog(profile, "Password changed Successfully!!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        else{
                            throw new java.sql.SQLException();
                        }
                    }
                    catch(Exception exp){
                        JOptionPane.showMessageDialog(profile, "       USER NOT FOUND!! \nPlease Register First as The User.","QUERY",JOptionPane.INFORMATION_MESSAGE);
                    }
                    profile.dispose();
                }
            }
            else{
                JOptionPane.showMessageDialog(profile, "Please match both Password!!","QUERY",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        c.gridx=0;
        c.gridy=4;
        c.gridwidth=2;
        c.weighty=1.5;
        profile.add(update,c);
        profile.pack();
        profile.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        profile.setLocationRelativeTo(null);
        profile.setVisible(true);
    }
    public void Ticket_Details(String op) throws Exception{
        JLabel top_bar;
        JLabel user_Label;
        JLabel Mobile;
        JLabel email;
        JLabel date;
        JLabel source;
        JLabel dest;
        JLabel classType;
        JLabel train_number;
        JLabel train_name;
        JLabel pnrLabel;

        JLabel print_user_Label;
        JLabel print_Mobile;
        JLabel print_email;
        JLabel print_date;
        JLabel print_source;
        JLabel print_dest;
        JLabel print_classType;
        JLabel print_train_number;
        JLabel print_train_name;
        JLabel print_pnrLabel;

        JButton confirm;
        JButton cancel;

        ResultSet rs = null;
        boolean valid = true;

        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM PassengerData WHERE PNR_NUMBER = ?");
            if (op.equals("delete")){
                pnr_no = pnr_field.getText();
            }
            ps.setString(1,pnr_no);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            stmt.execute("use ReservationSystem");
            rs = ps.executeQuery();
            valid = rs.next();
            if(valid == false){
                JOptionPane.showMessageDialog(null, "Invalid PNR Number!!","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
            }             
        }
        catch(Exception E){
            E.printStackTrace();
        }
        
        if (valid){
        ticket_details = new JDialog(this,"TICKET DETAILS");
        ticket_details.setLayout(new GridBagLayout());
        GridBagConstraints c= new GridBagConstraints();
        c.anchor= GridBagConstraints.NORTH;
        c.fill=GridBagConstraints.HORIZONTAL;

        top_bar = new JLabel("TICKET PRINTOUT",JLabel.CENTER);
        top_bar.setPreferredSize(new Dimension(440,28));
        top_bar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        top_bar.setOpaque(true);
        top_bar.setBackground(new Color(28, 80, 150));
        top_bar.setForeground(Color.WHITE);
        top_bar.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        ticket_details.add(top_bar,c);

        pnrLabel=new JLabel("PNR NUMBER:",JLabel.LEFT);
        pnrLabel.setFont(new Font("Arial", Font.BOLD, 15));
        pnrLabel.setForeground(Color.BLACK);
        c.gridx=0;
        c.gridy=1;
        c.ipady=10;
        c.gridwidth=2;
        c.insets = new Insets(0,10,0,0);
        ticket_details.add(pnrLabel,c);

        print_pnrLabel=new JLabel(rs.getString(10),JLabel.LEFT);
        print_pnrLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        print_pnrLabel.setForeground(Color.RED);
        c.gridx=1;
        c.gridy=1;
        c.ipady=10;
        c.gridwidth=2;
        ticket_details.add(print_pnrLabel,c);

        user_Label=new JLabel("PASSENGER NAME:",JLabel.LEFT);
        user_Label.setFont(new Font("Arial", Font.BOLD, 15));
        user_Label.setForeground(Color.BLACK);
        c.gridx=0;
        c.gridy=2;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(user_Label,c);

        print_user_Label=new JLabel(rs.getString(1),JLabel.LEFT);
        print_user_Label.setFont(new Font("Arial", Font.PLAIN, 15));
        print_user_Label.setForeground(Color.BLACK);
        c.gridx=1;
        c.gridy=2;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(print_user_Label,c);

        Mobile=new JLabel("MOBILE NUMBER:",JLabel.LEFT);
        Mobile.setFont(new Font("Arial", Font.BOLD, 15));
        Mobile.setForeground(Color.BLACK);
        c.gridx=0;
        c.gridy=3;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(Mobile,c);

        print_Mobile=new JLabel(String.valueOf(rs.getInt(2)),JLabel.LEFT);
        print_Mobile.setFont(new Font("Arial", Font.PLAIN, 15));
        print_Mobile.setForeground(Color.BLACK);
        c.gridx=1;
        c.gridy=3;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(print_Mobile,c);

        email=new JLabel("EMAIL:",JLabel.LEFT);
        email.setFont(new Font("Arial", Font.BOLD, 15));
        email.setForeground(Color.BLACK);
        c.gridx=0;
        c.gridy=4;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(email,c);

        print_email=new JLabel(rs.getString(3),JLabel.LEFT);
        print_email.setFont(new Font("Arial", Font.PLAIN, 15));
        print_email.setForeground(Color.BLACK);
        c.gridx=1;
        c.gridy=4;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(print_email,c);

        date=new JLabel("DATE:",JLabel.LEFT);
        date.setFont(new Font("Arial", Font.BOLD, 15));
        date.setForeground(Color.BLACK);
        c.gridx=0;
        c.gridy=5;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(date,c);

        print_date=new JLabel((rs.getDate(4)).toString(),JLabel.LEFT);
        print_date.setFont(new Font("Arial", Font.PLAIN, 15));
        print_date.setForeground(Color.BLACK);
        c.gridx=1;
        c.gridy=5;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(print_date,c);

        source=new JLabel("SOURCE:",JLabel.LEFT);
        source.setFont(new Font("Arial", Font.BOLD, 15));
        source.setForeground(Color.BLACK);
        c.gridx=0;
        c.gridy=6;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(source,c);

        print_source=new JLabel(rs.getString(5),JLabel.LEFT);
        print_source.setFont(new Font("Arial", Font.PLAIN, 15));
        print_source.setForeground(Color.BLACK);
        c.gridx=1;
        c.gridy=6;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(print_source,c);

        dest=new JLabel("DESTINATION:",JLabel.LEFT);
        dest.setFont(new Font("Arial", Font.BOLD, 15));
        dest.setForeground(Color.BLACK);
        c.gridx=0;
        c.gridy=7;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(dest,c);

        print_dest=new JLabel(rs.getString(6),JLabel.LEFT);
        print_dest.setFont(new Font("Arial", Font.PLAIN, 15));
        print_dest.setForeground(Color.BLACK);
        c.gridx=1;
        c.gridy=7;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(print_dest,c);

        classType=new JLabel("CLASS TYPE:",JLabel.LEFT);
        classType.setFont(new Font("Arial", Font.BOLD, 15));
        classType.setForeground(Color.BLACK);
        c.gridx=0;
        c.gridy=8;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(classType,c);

        print_classType=new JLabel(rs.getString(7),JLabel.LEFT);
        print_classType.setFont(new Font("Arial", Font.PLAIN, 15));
        print_classType.setForeground(Color.BLACK);
        c.gridx=1;
        c.gridy=8;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(print_classType,c);

        train_number=new JLabel("TRAIN NUMBER:",JLabel.LEFT);
        train_number.setFont(new Font("Arial", Font.BOLD, 15));
        train_number.setForeground(Color.BLACK);
        c.gridx=0;
        c.gridy=9;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(train_number,c);

        print_train_number=new JLabel(String.valueOf(rs.getInt(8)),JLabel.LEFT);
        print_train_number.setFont(new Font("Arial", Font.PLAIN, 15));
        print_train_number.setForeground(Color.BLACK);
        c.gridx=1;
        c.gridy=9;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(print_train_number,c);

        train_name=new JLabel("TRAIN NAME:",JLabel.LEFT);
        train_name.setFont(new Font("Arial", Font.BOLD, 15));
        train_name.setForeground(Color.BLACK);
        c.gridx=0;
        c.gridy=10;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(train_name,c);

        print_train_name=new JLabel(rs.getString(9),JLabel.LEFT);
        print_train_name.setFont(new Font("Arial", Font.PLAIN, 15));
        print_train_name.setForeground(Color.BLACK);
        c.gridx=1;
        c.gridy=10;
        c.ipady=10;
        c.gridwidth=1;
        ticket_details.add(print_train_name,c);

        if (op.equals("delete")){
            confirm=new JButton("OK");
            confirm.setBackground(new Color(179, 29, 29));
            confirm.setForeground(Color.WHITE);
            confirm.setPreferredSize(new Dimension(200,30));
            confirm.setBorder(BorderFactory.createLineBorder(new Color(179, 29, 29)));
            confirm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            confirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
            confirm.setFocusPainted(false);
            confirm.addActionListener(e -> {
            try{
                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                st.execute("use ReservationSystem");
                PreparedStatement p = conn.prepareStatement("DELETE FROM PassengerData WHERE PNR_NUMBER = ?");
                p.setString(1,pnr_no);
                if (p.executeUpdate() != 0){
                    JOptionPane.showMessageDialog(null, "Ticket Cancelled Successfully!!","CONFIRMATION",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid PNR Number!!","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception exp){}
                ticket_details.dispose();
            });
            c.gridx=0;
            c.gridy=11;
            c.gridwidth=1;
            c.weighty=1.5;
            c.insets = new Insets(10,10,20,10);
            ticket_details.add(confirm,c);

            cancel=new JButton("CANCEL");
            cancel.setBackground(new Color(141, 141, 4));
            cancel.setForeground(Color.WHITE);
            cancel.setPreferredSize(new Dimension(200,30));
            cancel.setBorder(BorderFactory.createLineBorder(new Color(141, 141, 4)));
            cancel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            cancel.setFocusPainted(false);
            cancel.addActionListener(e -> {
                ticket_details.dispose();
            });
            c.gridx=1;
            c.gridy=11;
            c.gridwidth=1;
            c.weighty=1.5;
            c.insets = new Insets(10,10,20,10);
            ticket_details.add(cancel,c);
            }

        ticket_details.pack();
        ticket_details.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ticket_details.setLocationRelativeTo(null);
        ticket_details.setVisible(true);
        }

    }
}

public class ReservationSystem extends JFrame{
    static JButton login_btn;
    static JButton register_btn;
    static JButton forgot_btn;
    
    static JPanel login_panel;
    
    static JLabel Iconlabel;
    static JLabel header;
    static JLabel username;
    static JLabel password;

    static JTextField userTextField;
    static JPasswordField passwordField;

    static String userid = "";
    static String check = "";
    static Connection con;
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "SHISHIR", "Skm@7330");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void Register(String id,String pass){
        try{
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stmt.execute("CREATE DATABASE IF NOT EXISTS ReservationSystem");
            stmt.execute("use ReservationSystem");
            stmt.execute("CREATE TABLE IF NOT EXISTS Credentials(USER_ID VARCHAR(30) PRIMARY KEY,PASSWORD VARCHAR(16) NOT NULL)");
            try{
                PreparedStatement pdst = con.prepareStatement("INSERT INTO Credentials values(?,?)");
                pdst.setString(1,id);
                pdst.setString(2, pass);
                int msg = pdst.executeUpdate();
                if (msg == 1){
                    JOptionPane.showMessageDialog(null, "You are Registered Successfully!!","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password Entry!!","QUERY",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "You are already a user.\n       Please Log in.","QUERY",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    private boolean Login(String id,String pass){
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Credentials WHERE USER_ID = ?");
            ps.setString(1,id);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            stmt.execute("use ReservationSystem");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if((rs.getString(1)).equals(id) && (rs.getString(2)).equals(pass)){
                    userid = rs.getString(1);
                    check = rs.getString(2);
                    try{con.close();}catch(Exception E){}
                    HomePage h = new HomePage();
                    h.Home();
                    JOptionPane.showMessageDialog(null, "Logged in Successfully!!","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Wrong Password!!","QUERY",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "       USER NOT FOUND!! \nPlease Register First as The User.","QUERY",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    ReservationSystem(){

        int Height = ((Toolkit.getDefaultToolkit()).getScreenSize()).height;
        int Width = ((Toolkit.getDefaultToolkit()).getScreenSize()).width;

        ImageIcon Img = new ImageIcon("background.jpg");
        Image obj=Img.getImage();
        Image temp=obj.getScaledInstance(Width,Height,Image.SCALE_SMOOTH);
        Img = new ImageIcon(temp);
        JLabel background=new JLabel("",Img,JLabel.CENTER);
        background.setBounds(0,0,Width,Height);
        add(background);

        background.setLayout(new GridBagLayout());
        GridBagConstraints c= new GridBagConstraints();
        c.anchor= GridBagConstraints.WEST;
        c.fill=GridBagConstraints.HORIZONTAL;

        login_panel = new JPanel(new GridBagLayout());
        login_panel.setBackground(new Color(0,0,0,100));
        login_panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        GridBagConstraints constraints1= new GridBagConstraints();
        constraints1.anchor= GridBagConstraints.NORTH;
        constraints1.insets= new Insets(10,10,10,10);

        Iconlabel = new JLabel(new ImageIcon("icon.png"));
        Iconlabel.setPreferredSize(new Dimension(90,120));
        constraints1.gridx=0;
        constraints1.gridy=0;
        constraints1.ipady=50;
        constraints1.gridwidth=2;
        login_panel.add(Iconlabel,constraints1);

        header=new JLabel("SIGN IN",JLabel.CENTER);
        constraints1.gridx=0;
        constraints1.gridy=1;
        constraints1.ipady=5;
        constraints1.gridwidth=2;
        header.setFont(new Font("Arial", Font.BOLD, 25));
        header.setForeground(Color.WHITE);
        login_panel.add(header,constraints1);

        username=new JLabel("USENAME:",JLabel.LEFT);
        constraints1.gridx=0;
        constraints1.gridy=2;
        constraints1.ipady=0;
        constraints1.gridwidth=1;
        username.setFont(new Font("Arial", Font.BOLD, 20));
        username.setForeground(Color.WHITE);
        login_panel.add(username,constraints1);

        userTextField = new JTextField(20);
        constraints1.gridx=1;
        constraints1.gridy=2;
        constraints1.ipady=0;
        userTextField.setText("Username or Email");
        userTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        userTextField.setForeground(Color.DARK_GRAY);
        userTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e){
                if (userTextField.getText().equals("Username or Email")){
                    userTextField.setText("");
                    userTextField.setForeground(Color.BLACK);
                }
                else{
                    userTextField.setText(userTextField.getText());
                    userTextField.setForeground(Color.BLACK);
                }
                
            }
            @Override
            public void focusLost(FocusEvent e){
                if (userTextField.getText().equals("Username or Email")|| userTextField.getText().length()==0){
                    userTextField.setText("Username or Email");
                    userTextField.setForeground(Color.DARK_GRAY);
                }
                else{
                    userTextField.setText(userTextField.getText());
                    userTextField.setForeground(Color.BLACK);
                }
            }

        });
        userTextField.setFocusable(false);
        login_panel.add(userTextField,constraints1);

        password=new JLabel("PASSWORD:",JLabel.LEFT);
        constraints1.gridx=0;
        constraints1.gridy=3;
        constraints1.ipady=0;
        password.setFont(new Font("Arial", Font.BOLD, 20));
        password.setForeground(Color.WHITE);
        login_panel.add(password,constraints1);

        passwordField = new JPasswordField(20);
        constraints1.gridx=1;
        constraints1.gridy=3;
        constraints1.ipady=0;
        passwordField.setText("Password");
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordField.setForeground(Color.DARK_GRAY);
        passwordField.setEchoChar('\0');
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e){
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('*');
                
            }
            @Override
            public void focusLost(FocusEvent e){
                if (String.valueOf(passwordField.getPassword()).equals("")){
                    passwordField.setEchoChar('\0');
                    passwordField.setText("Password");
                    passwordField.setForeground(Color.DARK_GRAY);
                }
            }

        });
        passwordField.setFocusable(false);
        login_panel.add(passwordField,constraints1);

        login_btn=new JButton("LOG IN");
        login_btn.setBackground(new Color(161, 15, 201));
        login_btn.setForeground(Color.WHITE);
        login_btn.setPreferredSize(new Dimension(450,35));
        login_btn.setBorder(BorderFactory.createLineBorder(new Color(161, 15, 201)));
        login_btn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        login_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login_btn.setFocusPainted(false);
        login_btn.addActionListener(e -> {
            if (Login(userTextField.getText(),String.valueOf(passwordField.getPassword()))){
                this.dispose();
            }
        });
        constraints1.gridx=0;
        constraints1.gridy=4;
        constraints1.gridwidth=2;
        login_panel.add(login_btn,constraints1);

        register_btn=new JButton("REGISTER");
        register_btn.setBackground(new Color(80, 123, 14));
        register_btn.setForeground(Color.WHITE);
        register_btn.setPreferredSize(new Dimension(450,35));
        register_btn.setBorder(BorderFactory.createLineBorder(new Color(80, 123, 14)));
        register_btn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        register_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        register_btn.setFocusPainted(false);
        register_btn.addActionListener(e -> {
            Register(userTextField.getText(),String.valueOf(passwordField.getPassword()));
        });
        constraints1.gridx=0;
        constraints1.gridy=5;
        constraints1.gridwidth=2;
        login_panel.add(register_btn,constraints1);

        forgot_btn=new JButton("Forgot password ?");
        forgot_btn.setBackground(new Color(255,255,255));
        forgot_btn.setPreferredSize(new Dimension(450,32));
        forgot_btn.setForeground(Color.BLACK);
        forgot_btn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        forgot_btn.addActionListener(e -> {
            HomePage hp = new HomePage();
            hp.Profile("","");
        });
        forgot_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgot_btn.setFocusPainted(false);
        constraints1.gridx=0;
        constraints1.gridy=6;
        constraints1.gridwidth=2;
        login_panel.add(forgot_btn,constraints1);

        c.gridx=0;
        c.gridy=1;
        background.add(login_panel,c);

        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                userTextField.setFocusable(true);
                passwordField.setFocusable(true);
            }
            public void mouseMoved(MouseEvent e){
                userTextField.setFocusable(true);
                passwordField.setFocusable(true);
            }
        });
        setTitle("ONLINE EXAMINATION");
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    public static void main(String[] args){
        new ReservationSystem();
    }
}
